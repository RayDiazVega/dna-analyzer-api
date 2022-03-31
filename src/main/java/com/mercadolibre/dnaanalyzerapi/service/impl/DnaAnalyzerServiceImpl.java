package com.mercadolibre.dnaanalyzerapi.service.impl;

import com.mercadolibre.dnaanalyzerapi.dao.DnaAnalyzerDao;
import com.mercadolibre.dnaanalyzerapi.dto.Human;
import com.mercadolibre.dnaanalyzerapi.dto.Stats;
import com.mercadolibre.dnaanalyzerapi.service.DnaAnalyzerService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DnaAnalyzerServiceImpl implements DnaAnalyzerService {

  @Autowired
  private DnaAnalyzerDao dnaAnalyzerDao;

  @Override
  public boolean validateDNA(Human human) {
    if (human.getDna().stream().anyMatch(sequence -> sequence.length() != human.getDna().size())) {
      throw new IllegalArgumentException(
          "The length of each sequence must be equal to the number of sequences");
    }

    dnaAnalyzerDao.save(human);
    return isMutant(human.getDna());
  }

  private boolean isMutant(List<String> humanDna) {

    char[][] dna = humanDna.stream().map(String::toCharArray).toArray(char[][]::new);
    int size = dna.length;
    int horizontalMatches = 0;
    int verticalMatches = 0;
    int obliqueLeftMatches = 0;
    int obliqueRightMatches = 0;

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (j < size - 3) {
          if (dna[i][j] == dna[i][j + 1] &&
              dna[i][j + 1] == dna[i][j + 2] &&
              dna[i][j + 2] == dna[i][j + 3]) {
            if (horizontalMatches < 1) {
              horizontalMatches++;
            } else {
              log.error("There is more than one horizontal match");
              return false;
            }
          }
        }

        if (i < size - 3) {
          if (dna[i][j] == dna[i + 1][j] &&
              dna[i + 1][j] == dna[i + 2][j] &&
              dna[i + 2][j] == dna[i + 3][j]) {
            if (verticalMatches < 1) {
              verticalMatches++;
            } else {
              log.error("There is more than one vertical match");
              return false;
            }
          }
        }

        if (j < size - 3 && i < size - 3) {
          if (dna[i][j] == dna[i + 1][j + 1] &&
              dna[i + 1][j + 1] == dna[i + 2][j + 2] &&
              dna[i + 2][j + 2] == dna[i + 3][j + 3]) {
            if (obliqueLeftMatches < 1) {
              obliqueLeftMatches++;
            } else {
              log.error("There is more than one oblique left match");
              return false;
            }
          }

          if (dna[i][size - 1 - j] == dna[i + 1][size - 2 - j] &&
              dna[i + 1][size - 2 - j] == dna[i + 2][size - 3 - j] &&
              dna[i + 2][size - 3 - j] == dna[i + 3][size - 4 - j]) {
            if (obliqueRightMatches < 1) {
              obliqueRightMatches++;
            } else {
              log.error("There is more than one oblique right match");
              return false;
            }
          }
        }
      }
    }

    if (horizontalMatches + verticalMatches + obliqueLeftMatches + obliqueRightMatches == 0) {
      log.error("There are no matches");
      return false;
    }

    log.info("true, is mutant");
    return true;
  }

  @Override
  public Stats getStats() {
    return null;
  }
}
