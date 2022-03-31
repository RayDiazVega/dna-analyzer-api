package com.mercadolibre.dnaanalyzerapi.service;

import com.mercadolibre.dnaanalyzerapi.dto.Human;
import com.mercadolibre.dnaanalyzerapi.dto.Stats;

public interface DnaAnalyzerService {

  boolean validateDNA(Human human);

  Stats getStats();
}