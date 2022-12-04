package com.intech.hero;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.cors.CorsConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CorsTest {

	@Autowired
	private MockMvc mvc;

	/**
	 * Teste que CORS est bien actif
	 * 
	 * @throws Exception
	 */
	@Test
	public void cors() throws Exception {
		mvc.perform(options("/superheroes")
				.header(HttpHeaders.ORIGIN, "http://site.com")
				.header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.GET.name()))
	        .andExpect(status().isOk())
	        .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, CorsConfiguration.ALL))
	        .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, HttpMethod.GET.name()))
			.andDo(print()).andReturn();
	}

}
