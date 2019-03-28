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
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class DescontoTest {
	
	private Sessao sessao;
	private Ingresso ingresso;
	BigDecimal precoEsperado;
	
	@Before
	public void inicializa() {
		Sala sala = new Sala ("Eldorado", new BigDecimal("20.5"));
		Filme filme = new Filme ("Capitã Marvel", Duration.ofMinutes(120), "Aventura", new BigDecimal("12"));
		
		sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
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
		ingresso = new Ingresso (sessao, new SemDesconto());
		precoEsperado = new BigDecimal("32.50");
		
//		Essa comparação foi transferida para @After
//		Assert.assertEquals(precoEsperado, ingresso.getPreco());
		
	}
	
	@Test
	public void deveConcederDescontoDe30PorcentoParaIngressosDeClientesDeBancos () {
		ingresso = new Ingresso (sessao, new DescontoParaBancos());
		precoEsperado = new BigDecimal("22.75");		
	}
	
	@Test
	public void deveConcederDescontoDe50PorcentoParaIngressoDeEstudante () {
		ingresso = new Ingresso (sessao, new DescontoParaEstudantes());
		precoEsperado = new BigDecimal("16.25");		
	}

}
