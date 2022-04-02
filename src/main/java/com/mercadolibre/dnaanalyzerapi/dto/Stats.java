package com.mercadolibre.dnaanalyzerapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Stats {

  @Schema(example = "40")
  @JsonProperty("count_mutant_dna")
  private Integer mutantDNACount;

  @Schema(example = "100")
  @JsonProperty("count_human_dna")
  private Integer humanDNACount;

  @Schema(example = "0.4")
  private Float ratio;
}
