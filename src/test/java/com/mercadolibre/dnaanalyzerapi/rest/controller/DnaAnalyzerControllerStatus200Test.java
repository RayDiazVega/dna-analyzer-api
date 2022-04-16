package com.mercadolibre.dnaanalyzerapi.rest.controller;

import com.mercadolibre.dnaanalyzerapi.constant.Constant;
import com.mercadolibre.dnaanalyzerapi.human.infrastructure.ports.DnaAnalyzerDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class DnaAnalyzerControllerStatus200Test {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  DnaAnalyzerDao dnaAnalyzerDao;

  @Test
  void isMutant() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/mutant/")
            .contentType(MediaType.APPLICATION_JSON).content(Constant.mutant))
        .andExpectAll(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());

    dnaAnalyzerDao.delete(dnaAnalyzerDao.findByDna(Constant.dna).get());
  }

  @Test
  void getStats() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/stats"))
        .andExpectAll(MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.count_mutant_dna").isNumber(),
            MockMvcResultMatchers.jsonPath("$.count_human_dna").isNumber(),
            MockMvcResultMatchers.jsonPath("$.ratio").isNumber())
        .andDo(MockMvcResultHandlers.print());
  }
}