package com.mercadolibre.dnaanalyzerapi.service.impl;

import com.mercadolibre.dnaanalyzerapi.dto.Human;
import com.mercadolibre.dnaanalyzerapi.service.DnaAnalyzerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DnaAnalyzerServiceImpl implements DnaAnalyzerService {

  @Override
  public void isMutant(Human human) {
    if (human.getDna().stream().anyMatch(sequence -> sequence.length() != human.getDna().size())) {
      throw new IllegalArgumentException(
          "The length of each sequence must be equal to the number of sequences");
    }
    log.info(human.toString());
  }
}
