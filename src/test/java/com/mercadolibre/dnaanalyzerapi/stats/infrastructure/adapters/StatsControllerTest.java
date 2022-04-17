package com.mercadolibre.dnaanalyzerapi.stats.infrastructure.adapters;

import com.mercadolibre.dnaanalyzerapi.stats.application.StatsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class StatsControllerTest {

  @Autowired
  MockMvc mockMvc;

  @SpyBean
  StatsService statsService;

  @Test
  void getStatsStatus200() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/stats"))
        .andExpectAll(MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.count_mutant_dna").isNumber(),
            MockMvcResultMatchers.jsonPath("$.count_human_dna").isNumber(),
            MockMvcResultMatchers.jsonPath("$.ratio").isNumber())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void getStatsStatus500() throws Exception {
    Mockito.when(statsService.getStats()).thenThrow(NullPointerException.class);

    mockMvc.perform(MockMvcRequestBuilders.get("/stats"))
        .andExpectAll(MockMvcResultMatchers.status().isInternalServerError(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.message").value("Server error"))
        .andDo(MockMvcResultHandlers.print());
  }
}