package com.mercadolibre.dnaanalyzerapi.dao;

import com.mercadolibre.dnaanalyzerapi.dto.Human;
import java.util.List;
import java.util.Optional;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface DnaAnalyzerDao extends CrudRepository<Human, String> {

  List<Human> findAll();

  Optional<Human> findByDna(List<String> dna);
}
