package com.mercadolibre.dnaanalyzerapi;

import com.mercadolibre.dnaanalyzerapi.human.infrastructure.ports.DnaAnalyzerDao;
import com.mercadolibre.dnaanalyzerapi.human.infrastructure.adapters.DnaAnalyzerController;
import com.mercadolibre.dnaanalyzerapi.human.application.DnaAnalyzerService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AllArgsConstructor(onConstructor_ = {@Autowired})
class DnaAnalyzerApiApplicationTests {

  DnaAnalyzerController dnaAnalyzerController;
  DnaAnalyzerService dnaAnalyzerService;
  DnaAnalyzerDao dnaAnalyzerDao;

  @Test
  void contextLoads() {
    Assertions.assertNotNull(dnaAnalyzerController);
    Assertions.assertNotNull(dnaAnalyzerService);
    Assertions.assertNotNull(dnaAnalyzerDao);
  }
}
