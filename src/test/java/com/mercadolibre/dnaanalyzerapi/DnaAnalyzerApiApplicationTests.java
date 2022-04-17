package com.mercadolibre.dnaanalyzerapi;

import com.mercadolibre.dnaanalyzerapi.human.infrastructure.ports.HumanRepository;
import com.mercadolibre.dnaanalyzerapi.human.infrastructure.adapters.HumanController;
import com.mercadolibre.dnaanalyzerapi.human.application.HumanService;
import com.mercadolibre.dnaanalyzerapi.stats.application.StatsService;
import com.mercadolibre.dnaanalyzerapi.stats.infrastructure.adapters.StatsController;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AllArgsConstructor(onConstructor_ = {@Autowired})
class DnaAnalyzerApiApplicationTests {

  HumanController humanController;
  HumanService humanService;
  HumanRepository humanRepository;
  StatsController statsController;
  StatsService statsService;

  @Test
  void contextLoads() {
    Assertions.assertNotNull(humanController);
    Assertions.assertNotNull(humanService);
    Assertions.assertNotNull(humanRepository);
    Assertions.assertNotNull(statsController);
    Assertions.assertNotNull(statsService);
  }
}
