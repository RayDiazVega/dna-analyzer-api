package com.mercadolibre.dnaanalyzerapi.rest.controller;

import com.mercadolibre.dnaanalyzerapi.dto.Human;
import com.mercadolibre.dnaanalyzerapi.service.DnaAnalyzerService;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DnaAnalyzerController {

  @Autowired
  private DnaAnalyzerService dnaAnalyzerService;

  @Operation(summary = "Valid DNA sequence")
  @PostMapping(value = "/mutant/", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> isMutant(@Valid @RequestBody Human human) {
    dnaAnalyzerService.isMutant(human);
    return ResponseEntity.ok().build();
  }
}
