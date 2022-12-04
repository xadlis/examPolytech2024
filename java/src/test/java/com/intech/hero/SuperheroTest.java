package com.intech.hero;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intech.hero.api.SuperheroDto;
import com.intech.hero.model.Superhero;
import com.intech.hero.repositories.SuperheroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SuperheroTest {

	@Autowired
	private MockMvc mvc;

	private ObjectMapper json = new ObjectMapper();

	@MockBean
	private SuperheroRepository superheroRepository;

	/**
	 * Teste creation avec le minimum d'infos
	 * 
	 * @throws Exception
	 */
	@Test
	@WithMockUser
	public void minimum_valid_creation() throws Exception {
		SuperheroDto dto = new SuperheroDto();
		dto.setHeroName("name");
		dto.setFullName("fullname");

		Superhero sh = new Superhero();

		Mockito.when(superheroRepository.save(ArgumentMatchers.any(Superhero.class))).thenReturn(sh);
		
		mvc.perform(post("/superheroes").contentType(MediaType.APPLICATION_JSON).content(json.writeValueAsString(dto)))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	public void creation_without_heroname_invalid() throws Exception {
		SuperheroDto dto = new SuperheroDto();
		dto.setFullName("fullname");

		Superhero sh = new Superhero();

		Mockito.when(superheroRepository.save(ArgumentMatchers.any(Superhero.class))).thenReturn(sh);

		mvc.perform(post("/superheroes").contentType(MediaType.APPLICATION_JSON).content(json.writeValueAsString(dto)))
				.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}

	@Test
	@WithMockUser
	public void creation_without_fullname_invalid() throws Exception {
		SuperheroDto dto = new SuperheroDto();
		dto.setHeroName("name");

		Superhero sh = new Superhero();

		Mockito.when(superheroRepository.save(ArgumentMatchers.any(Superhero.class))).thenReturn(sh);

		mvc.perform(post("/superheroes").contentType(MediaType.APPLICATION_JSON).content(json.writeValueAsString(dto)))
				.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}

	@Test
	@WithMockUser
	public void heroname_max() throws Exception {
		SuperheroDto dto = new SuperheroDto();
		dto.setHeroName(
				"name50________________________________________________________________________________________________");
		dto.setFullName("fullname");

		Superhero sh = new Superhero();

		Mockito.when(superheroRepository.save(ArgumentMatchers.any(Superhero.class))).thenReturn(sh);

		mvc.perform(post("/superheroes").contentType(MediaType.APPLICATION_JSON).content(json.writeValueAsString(dto)))
				.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}

}
