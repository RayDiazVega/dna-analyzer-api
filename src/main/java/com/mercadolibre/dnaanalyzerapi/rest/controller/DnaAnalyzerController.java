package com.mercadolibre.dnaanalyzerapi.rest.controller;

import com.mercadolibre.dnaanalyzerapi.dto.Human;
import com.mercadolibre.dnaanalyzerapi.dto.Stats;
import com.mercadolibre.dnaanalyzerapi.service.DnaAnalyzerService;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
  public ResponseEntity<Void> validateDNA(@Valid @RequestBody Human human) {
    if (dnaAnalyzerService.validateDNA(human)) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
  }

  @Operation(summary = "Get verification statistics")
  @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Stats> getStats() {
    return ResponseEntity.ok(dnaAnalyzerService.getStats());
  }
}
