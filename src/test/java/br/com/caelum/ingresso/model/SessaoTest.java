package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

import junit.framework.Assert;

public class SessaoTest {
	
	@Test
	
	public void oPrecoDaSessaoDeveSerigualASomaDoPrecoDaSalaMaisOPrecoDoFilme() {
		
		Sala sala = new Sala("Eldorado", new BigDecimal("22.5"));
		Filme filme = new Filme("Capitã Marvel", Duration.ofMinutes(120), "Aventura", new BigDecimal("12.0"));
		
		BigDecimal somaDosPrecosDaSalaEFilme = sala.getPreco().add(filme.getPreco());
		
		Sessao sessao = new Sessao (LocalTime.parse("10:00:00"), sala, filme);
		
		Assert.assertEquals(somaDosPrecosDaSalaEFilme, sessao.getPreco());
		
	}

}
