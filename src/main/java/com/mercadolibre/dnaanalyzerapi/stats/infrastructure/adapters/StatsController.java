package com.mercadolibre.dnaanalyzerapi.stats.infrastructure.adapters;

import com.mercadolibre.dnaanalyzerapi.stats.application.StatsService;
import com.mercadolibre.dnaanalyzerapi.stats.dto.Stats;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StatsController {

  @Autowired
  private StatsService statsService;

  @Operation(summary = "Get verification statistics")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Stats> getStats() {
    return ResponseEntity.ok(statsService.getStats());
  }
}
