package com.mercadolibre.dnaanalyzerapi.service;

import com.mercadolibre.dnaanalyzerapi.dto.Human;
import com.mercadolibre.dnaanalyzerapi.dto.Stats;

public interface DnaAnalyzerService {

  void isMutant(Human human);

  Stats getStats();
}