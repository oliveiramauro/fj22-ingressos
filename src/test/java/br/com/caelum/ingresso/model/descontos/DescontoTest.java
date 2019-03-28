package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

public class DescontoTest {
	
	private Sessao sessao;
	private Ingresso ingresso;
	private Lugar lugar;
	BigDecimal precoEsperado;
	
	@Before
	public void inicializa() {
		Sala sala = new Sala ("Eldorado", new BigDecimal("20.5"));
		Filme filme = new Filme ("Capitã Marvel", Duration.ofMinutes(120), "Aventura", new BigDecimal("12"));
		sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		lugar = new Lugar("A",1);
	}
	
	@After
	public void finaliza() {
	Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal () {
//		Essas instâncias foram transferidas para @Before
//		Sala sala = new Sala ("Eldorado", new BigDecimal("20.5"));
//		Filme filme = new Filme ("Capitã Marvel", Duration.ofMinutes(120), "Aventura", new BigDecimal("12"));
//		
//		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		ingresso = new Ingresso (sessao, TipoDeIngresso.INTEIRO, lugar);
		precoEsperado = new BigDecimal("32.50");
		
//		Essa comparação foi transferida para @After
//		Assert.assertEquals(precoEsperado, ingresso.getPreco());
		
	}
	
	@Test
	public void deveConcederDescontoDe30PorcentoParaIngressosDeClientesDeBancos () {
		ingresso = new Ingresso (sessao, TipoDeIngresso.BANCO, lugar);
		precoEsperado = new BigDecimal("22.75");		
	}
	
	@Test
	public void deveConcederDescontoDe50PorcentoParaIngressoDeEstudante () {
		ingresso = new Ingresso (sessao, TipoDeIngresso.ESTUDANTE, lugar);
		precoEsperado = new BigDecimal("16.25");		
	}

}
