package com.mercadolibre.dnaanalyzerapi.human.infrastructure.adapters;

import com.mercadolibre.dnaanalyzerapi.human.application.HumanService;
import com.mercadolibre.dnaanalyzerapi.human.dto.Human;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HumanController {

  @Autowired
  private HumanService dnaAnalyzerService;

  @Operation(summary = "Valid DNA sequence")
  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> validateDNA(@Valid @RequestBody Human human) {
    return dnaAnalyzerService.validateDNA(human) ? ResponseEntity.ok().build() :
        ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }
}
