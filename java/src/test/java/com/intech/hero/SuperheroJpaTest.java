package com.intech.hero;

import static org.junit.Assert.assertEquals;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.intech.hero.model.Superhero;
import com.intech.hero.repositories.SuperheroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperheroJpaTest {

	@Autowired
	private SuperheroRepository superheroRepository;

	@Test(expected = ConstraintViolationException.class)
	public void heroname_max() throws Exception {
		Superhero sh = new Superhero();
		sh.setHeroName(
				"name50________________________________________________________________________________________________");
		sh.setFullName("fullname");

		superheroRepository.save(sh);
	}

	@Test
	public void creation_success() {
		Superhero sh = new Superhero();
		sh.setHeroName("moi");
		sh.setFullName("noname");

		sh = superheroRepository.save(sh);

		assertEquals("noname", superheroRepository.findById(sh.getIdHero()).get().getFullName());

	}

}
