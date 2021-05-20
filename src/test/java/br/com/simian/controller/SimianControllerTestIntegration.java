package br.com.simian.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class SimianControllerTestIntegration {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Order(1)
	@Test
	void shouldExpectedStatusOk() throws Exception {
		String json = "{\n" + "\"dna\":  [\"ATGCGA\", \n" + "         \"CAGTAC\", \n" + "         \"TTAACT\", \n"
				+ "         \"AGATGG\", \n" + "         \"CGCCTA\", \n" + "         \"TCACTG\"]\n" + "}";
		this.mockMvc.perform(post(SimianController.PATH).contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andExpect(status().isOk());
	}

	@Order(2)
	@Test
	void shouldExpectedStatusForbiddenAndExecuteHandlerExceptionDNANotSimianException() throws Exception {
		String json = "{\n" + "\"dna\":  [\"ATGCGA\", \n" + "         \"CTGTAC\", \n" + "         \"TTAGCT\", \n"
				+ "         \"AGATGG\", \n" + "         \"CGCCTA\", \n" + "         \"TCACTG\"]\n" + "}";
		this.mockMvc.perform(post(SimianController.PATH).contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andExpect(status().isForbidden()).andExpect(jsonPath("$.message", is("Not recognized as simian.")))
				.andExpect(jsonPath("$.status", is(403)));
	}

	@Order(3)
	@Test
	void shouldValidateStatisticAndExpectedStatusOk() throws Exception {
		this.mockMvc.perform(get(SimianController.PATH.concat("/stats")).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.count_mutant_dna", is(1)))
				.andExpect(jsonPath("$.count_human_dna", is(1))).andExpect(jsonPath("$.ratio", is(1.0)));
	}

	@Order(4)
	@Test
	void shouldExpectedExecuteHandlerExceptionWithDNAInvalidException() throws Exception {
		String json = "{\n" + "\"dna\":  [\"FFFFFF\", \n" + "         \"CTGTAC\", \n" + "         \"TTAGCT\", \n"
				+ "         \"AGATGG\", \n" + "         \"CGCCTA\", \n" + "         \"TCACTG\"]\n" + "}";
		this.mockMvc.perform(post(SimianController.PATH).contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andExpect(status().isForbidden())
				.andExpect(jsonPath("$.message", is(
						"DNA must contain the following letters: A, T, C, G - Check that all letters are being entered.")))
				.andExpect(jsonPath("$.status", is(403)));
	}

}
