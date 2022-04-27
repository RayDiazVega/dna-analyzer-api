package com.mercadolibre.dnaanalyzerapi.human.infrastructure.ports;

import com.mercadolibre.dnaanalyzerapi.human.dto.Human;
import java.util.List;
import java.util.Optional;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScanCount
@EnableScan
@Repository
public interface HumanRepository extends CrudRepository<Human, String> {

  Optional<Human> findByDna(List<String> dna);

  long countByIsMutantTrue();
}
