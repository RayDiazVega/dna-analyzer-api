package com.mercadolibre.dnaanalyzerapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class Human {

  @JsonIgnore
  private String id;

  @Schema(description = "DNA sequence", example = "[\n"
      + "        \"ATGCGA\",\n"
      + "        \"CAGTGC\",\n"
      + "        \"TTATGT\",\n"
      + "        \"AGAAGG\",\n"
      + "        \"CCCCTA\",\n"
      + "        \"TCACTG\"\n"
      + "    ]")
  @NotNull(message = "dna is required")
  @NotEmpty(message = "dna can't be empty")
  private List<
      @Size(min = 4, message = "sequences must have a minimum length of 4 characters")
      @Pattern(regexp = "[ATCG]+", message = "only values A,T,C and G are allowed") String> dna;

  @JsonIgnore
  private boolean isMutant;
}
