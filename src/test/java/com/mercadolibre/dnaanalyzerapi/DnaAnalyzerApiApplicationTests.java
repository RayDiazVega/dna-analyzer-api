package com.mercadolibre.dnaanalyzerapi;

import com.mercadolibre.dnaanalyzerapi.human.infrastructure.ports.HumanRepository;
import com.mercadolibre.dnaanalyzerapi.human.infrastructure.adapters.HumanController;
import com.mercadolibre.dnaanalyzerapi.human.application.HumanService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AllArgsConstructor(onConstructor_ = {@Autowired})
class DnaAnalyzerApiApplicationTests {

  HumanController dnaAnalyzerController;
  HumanService dnaAnalyzerService;
  HumanRepository dnaAnalyzerDao;

  @Test
  void contextLoads() {
    Assertions.assertNotNull(dnaAnalyzerController);
    Assertions.assertNotNull(dnaAnalyzerService);
    Assertions.assertNotNull(dnaAnalyzerDao);
  }
}
