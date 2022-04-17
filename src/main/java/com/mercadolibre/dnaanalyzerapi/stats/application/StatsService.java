package com.mercadolibre.dnaanalyzerapi.stats.application;

import com.mercadolibre.dnaanalyzerapi.human.dto.Human;
import com.mercadolibre.dnaanalyzerapi.human.infrastructure.ports.HumanRepository;
import com.mercadolibre.dnaanalyzerapi.stats.dto.Stats;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StatsService {

  @Autowired
  private HumanRepository humanRepository;

  public Stats getStats() {
    List<Human> humans = humanRepository.findAll();
    long mutantDNACount = humans.stream().filter(Human::getIsMutant).count();
    return Stats.builder().mutantDNACount((int) mutantDNACount).humanDNACount(humans.size())
        .ratio(Float.valueOf(String.format("%.2f", mutantDNACount / ((float) humans.size()))
            .replace(",", "."))).build();
  }
}
