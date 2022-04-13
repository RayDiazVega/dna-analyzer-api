package com.mercadolibre.dnaanalyzerapi.service;

import com.mercadolibre.dnaanalyzerapi.dao.DnaAnalyzerDao;
import com.mercadolibre.dnaanalyzerapi.dto.Human;
import com.mercadolibre.dnaanalyzerapi.dto.Stats;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DnaAnalyzerService {

  @Autowired
  private DnaAnalyzerDao dnaAnalyzerDao;

  public boolean validateDNA(Human human) {

    List<String> dna = human.getDna();

    if (dna.stream().anyMatch(sequence -> sequence.length() != dna.size())) {
      throw new IllegalArgumentException(
          "The length of each sequence must be equal to the number of sequences");
    }

    Optional<Human> optionalHuman = dnaAnalyzerDao.findByDna(dna);

    if (optionalHuman.isPresent()) {
      return optionalHuman.get().getIsMutant();
    }

    human.setIsMutant(isMutant(dna));
    return dnaAnalyzerDao.save(human).getIsMutant();
  }

  private boolean isMutant(List<String> dna) {

    int size = dna.size();
    int horizontalMatches = 0;
    int verticalMatches = 0;
    int obliqueLeftMatches = 0;
    int obliqueRightMatches = 0;

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (j < size - 3) {
          if (dna.get(i).charAt(j) == dna.get(i).charAt(j + 1) &&
              dna.get(i).charAt(j + 1) == dna.get(i).charAt(j + 2) &&
              dna.get(i).charAt(j + 2) == dna.get(i).charAt(j + 3)) {
            if (horizontalMatches < 1) {
              horizontalMatches++;
            } else {
              log.error("There is more than one horizontal match");
              return false;
            }
          }
        }

        if (i < size - 3) {
          if (dna.get(i).charAt(j) == dna.get(i + 1).charAt(j) &&
              dna.get(i + 1).charAt(j) == dna.get(i + 2).charAt(j) &&
              dna.get(i + 2).charAt(j) == dna.get(i + 3).charAt(j)) {
            if (verticalMatches < 1) {
              verticalMatches++;
            } else {
              log.error("There is more than one vertical match");
              return false;
            }
          }
        }

        if (j < size - 3 && i < size - 3) {
          if (dna.get(i).charAt(j) == dna.get(i + 1).charAt(j + 1) &&
              dna.get(i + 1).charAt(j + 1) == dna.get(i + 2).charAt(j + 2) &&
              dna.get(i + 2).charAt(j + 2) == dna.get(i + 3).charAt(j + 3)) {
            if (obliqueLeftMatches < 1) {
              obliqueLeftMatches++;
            } else {
              log.error("There is more than one oblique left match");
              return false;
            }
          }

          if (dna.get(i).charAt(size - 1 - j) == dna.get(i + 1).charAt(size - 2 - j) &&
              dna.get(i + 1).charAt(size - 2 - j) == dna.get(i + 2).charAt(size - 3 - j) &&
              dna.get(i + 2).charAt(size - 3 - j) == dna.get(i + 3).charAt(size - 4 - j)) {
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

    if (horizontalMatches + verticalMatches + obliqueLeftMatches + obliqueRightMatches < 2) {
      log.error("There are no matches");
      return false;
    }

    log.info("true, is mutant");
    return true;
  }

  public Stats getStats() {
    List<Human> humans = dnaAnalyzerDao.findAll();
    long mutantDNACount = humans.stream().filter(Human::getIsMutant).count();
    return Stats.builder().mutantDNACount((int) mutantDNACount).humanDNACount(humans.size())
        .ratio(Float.valueOf(String.format("%.2f", mutantDNACount / ((float) humans.size()))
            .replace(",", "."))).build();
  }
}
