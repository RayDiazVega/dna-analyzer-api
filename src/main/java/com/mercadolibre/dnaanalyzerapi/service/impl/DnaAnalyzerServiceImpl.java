package com.mercadolibre.dnaanalyzerapi.service.impl;

import com.mercadolibre.dnaanalyzerapi.dto.Human;
import com.mercadolibre.dnaanalyzerapi.exception.NotMutantException;
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

    char[][] dna = human.getDna().stream().map(String::toCharArray).toArray(char[][]::new);
    int size = dna.length;
    int horizontalMatches = 0;
    int verticalMatches = 0;
    int obliqueMatches = 0;

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (j < size - 3) {
          if (dna[i][j] == dna[i][j + 1] &&
              dna[i][j + 1] == dna[i][j + 2] &&
              dna[i][j + 2] == dna[i][j + 3]) {
            if (horizontalMatches < 1) {
              horizontalMatches++;
            } else {
              throw new NotMutantException("There is more than one horizontal match");
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
              throw new NotMutantException("There is more than one vertical match");
            }
          }
        }

        if (j < size - 3 && i < size - 3) {
          if (dna[i][j] == dna[i + 1][j + 1] &&
              dna[i + 1][j + 1] == dna[i + 2][j + 2] &&
              dna[i + 2][j + 2] == dna[i + 3][j + 3]) {
            if (obliqueMatches < 1) {
              obliqueMatches++;
            } else {
              throw new NotMutantException("There is more than one oblique match");
            }
          }

          if (dna[i][size - 1 - j] == dna[i + 1][size - 2 - j] &&
              dna[i + 1][size - 2 - j] == dna[i + 2][size - 3 - j] &&
              dna[i + 2][size - 3 - j] == dna[i + 3][size - 4 - j]) {
            if (obliqueMatches < 1) {
              obliqueMatches++;
            } else {
              throw new NotMutantException("There is more than one oblique match");
            }
          }
        }
      }
    }

    if (horizontalMatches + verticalMatches + obliqueMatches == 0) {
      throw new NotMutantException("There are no matches");
    }

    log.info("true, is mutant");
  }
}
