package com.mercadolibre.dnaanalyzerapi.constant;

import java.util.List;

public class Constant {

  public static final String mutant = "{\n"
      + "    \"dna\": [\n"
      + "        \"ATGTGA\",\n"
      + "        \"CATTGC\",\n"
      + "        \"TTATGT\",\n"
      + "        \"TGAAGG\",\n"
      + "        \"CCCCTA\",\n"
      + "        \"TCACTG\"\n"
      + "    ]\n"
      + "}";

  public static final String notMutant = "{\n"
      + "    \"dna\": [\n"
      + "        \"CAGCCA\",\n"
      + "        \"CAGTGC\",\n"
      + "        \"TTATGT\",\n"
      + "        \"AGAAGG\",\n"
      + "        \"ACCCTA\",\n"
      + "        \"TCACTG\"\n"
      + "    ]\n"
      + "}";

  public static final List<String> dna = List.of("ATGTGA", "CATTGC", "TTATGT", "TGAAGG", "CCCCTA",
      "TCACTG");
}
