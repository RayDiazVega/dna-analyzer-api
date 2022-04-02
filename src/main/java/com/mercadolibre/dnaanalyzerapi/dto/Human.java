package com.mercadolibre.dnaanalyzerapi.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "HumanTable")
public class Human {

  @JsonIgnore
  @DynamoDBHashKey(attributeName = "PK")
  @DynamoDBAutoGeneratedKey
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
  @DynamoDBAttribute(attributeName = "DNA")
  private List<
      @Size(min = 4, message = "sequences must have a minimum length of 4 characters")
      @Pattern(regexp = "[ATCG]+", message = "only values A,T,C and G are allowed") String> dna;

  @JsonIgnore
  @DynamoDBTyped(DynamoDBAttributeType.BOOL)
  @DynamoDBAttribute(attributeName = "IsMutant")
  private Boolean isMutant;
}
