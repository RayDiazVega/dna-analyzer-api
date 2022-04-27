package com.mercadolibre.dnaanalyzerapi.stats.application;

import com.mercadolibre.dnaanalyzerapi.human.infrastructure.ports.HumanRepository;
import com.mercadolibre.dnaanalyzerapi.stats.dto.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

  @Autowired
  private HumanRepository humanRepository;

  public Stats getStats() {
    long humanDNACount = humanRepository.count();
    long mutantDNACount = humanRepository.countByIsMutantTrue();
    return Stats.builder().mutantDNACount(mutantDNACount).humanDNACount(humanDNACount)
        .ratio(Float.valueOf(String.format("%.2f", mutantDNACount / ((float) humanDNACount))
            .replace(",", "."))).build();
  }
}
