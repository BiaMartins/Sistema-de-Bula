package br.ufpb.sisbula;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SisBulaMemoryTest {

	SisBulaMemory sis;
	@Before
	public void setUp() throws Exception {
		sis = new SisBulaMemory();
	}

	@Test
	public void testaCadastroDeMedicamentoOK() {
		Medicamento m = new Medicamento("Novalgina");
		try {
			sis.cadastraMedicamento(m);
		} catch (Exception e){
			fail("Lançou exceção sem necessidade");
		}	
	}
	
	@Test
	public void testaCadastroDeMedicamentoOK2() {
		
		try {
			sis.cadastraMedicamento("Novalgina");
			Medicamento m = sis.pesquisaMedicamento("Novalgina", Fabricante.FABRICANTE_DESCONHECIDO);
			assertEquals("Novalgina", m.getNome());
			assertEquals(Fabricante.FABRICANTE_DESCONHECIDO, m.getFabricante());
		} catch (Exception e){
			fail("LanÃ§ou exceÃ§Ã£o sem necessidade");
		}	
	}
	
	@Test
	public void testaPesquisaMedicamento(){
		try {
			sis.cadastraMedicamento("Novalgina");
			Medicamento m = sis.pesquisaMedicamento("Novalgina");
			assertEquals("Novalgina", m.getNome());
			assertEquals(Fabricante.FABRICANTE_DESCONHECIDO, m.getFabricante());
		} catch (Exception e){
			fail("LanÃ§ou exceÃ§Ã£o sem necessidade");
		}	
	}
	
	
	@Test
	public void testaCadastroDeMedicamentoDuasVezes() {
		Medicamento m = new Medicamento("Novalgina");
		try {
			sis.cadastraMedicamento(m);
		} catch (Exception e){
			fail("LanÃ§ou exceÃ§Ã£o sem necessidade");
		}	
		
		try {
			sis.cadastraMedicamento(new Medicamento("Novalgina"));
			fail("Deveria ter lançado a exceção");
		} catch (MedicamentoJaExisteException e) {
			System.out.println("Muito bem, lanÃ§ou a excecao direito");
		}
	}
	
	@Test
	public void testaCadastraDoenca1(){
		Doenca doencas = new Doenca("Zika");
		try{
			sis.cadastraDoenca1(doencas);
		}catch(Exception e){
			fail("Laçou exceção sem necessidade");
		}
	}
	@Test
	public void testaCadastraSintoma(){
		Sintoma sint = new Sintoma("Febre", "Alta teperatura corporal");
		try{
			sis.cadastraSintoma(sint.getNome(), sint.getDescricao());
		}catch(Exception e){
			fail("Lançou exceção sem necessidade");
		}
	}
	
	@Test
	public void testeDaProva() throws Exception{
		SisBulaMemory sisBula = new SisBulaMemory();
		List<Medicamento> lista = sisBula.pesquisaMedicamentosDoFabricante(Fabricante.MEDLEY);
		assertEquals(0, lista.size());
		Medicamento dip = new Medicamento("Dipirona", Fabricante.MEDLEY);
		try {
			sisBula.cadastraMedicamento(dip);
		} catch (MedicamentoJaExisteException e){
			fail("NÃ£o deveria lanÃ§ar exceÃ§Ã£o. Cadastro autorizado");
		}
		List<Medicamento> lista2 = sisBula.pesquisaMedicamentosDoFabricante(Fabricante.MEDLEY);
		assertEquals(1, lista2.size());
		assertTrue(lista2.get(0).getNome().equals("Dipirona"));
		Medicamento dip2 = new Medicamento("Dipirona",Fabricante.MEDLEY);
		try {
			sisBula.cadastraMedicamento(dip2);
			fail("Deveria ter lanÃ§ado a exceÃ§Ã£o");
		} catch (MedicamentoJaExisteException e2){
			System.out.println("ExceÃ§Ã£o esperada");
		}
		
	}
	@Test
	public void testaCadastraIndicacoesMedicamento() throws Exception{
		sis.cadastraDoenca("Zika");
		sis.cadastraMedicamento("Dipirona");
		sis.cadastraSintoma("Febre");
		sis.cadastraMedicamentoParaDoenca("Dipirona", "Zika");
		sis.cadastraMedicamentoParaSintoma("Dipirona", "Febre");
		List<Medicamento> remediosPraZika  = sis.pesquisaMedicamentosPara("Zika");
		List<Medicamento> remediosPraFebre = sis.pesquisaMedicamentosPara("Febre");
		assertEquals(1, remediosPraZika.size());
		assertEquals(1, remediosPraFebre.size());
		Medicamento m1 = remediosPraZika.get(0);
		Medicamento m2 = remediosPraFebre.get(0);
		assertEquals("Dipirona" , m1.getNome());
		assertEquals("Dipirona", m2.getNome());
	}
	
	@Test
	public void testaCadastraDoenca2(){
		sis.cadastraDoenca("Alzheimer");
		sis.cadastraSintomaDeDoenca("Alzheimer", "Perda de Memoria");
		sis.cadastraSintomaDeDoenca("Alzheimer", "Distúrbios de comportamento");
		sis.cadastraPossivelCausaDeDoenca("Alzheimer", "Sedentarismo");
		sis.cadastraPossivelCausaDeDoenca("Alzheimer", "Falta de exercício físico");
		List<Doenca> doencas = sis.pesquisaDoencasCausadasPor("Sedentarismo");
		
		assertEquals(1,doencas.size());
		assertEquals("Alzheimer",doencas.get(0).getNome());
		List<CausaDeDoenca> possiveisCausas = sis.pesquisaPossiveisCausaDe("Alzheimer");
		System.out.println(possiveisCausas);
		assertEquals(2, possiveisCausas.size());
		assertTrue(!possiveisCausas.get(0).equals(possiveisCausas.get(1)));
		assertTrue(possiveisCausas.get(0).toString().equals("Sedentarismo")|| possiveisCausas.get(0).toString().equals("Falta de exercício mental"));
		assertTrue(possiveisCausas.get(1).toString().equals("Sedentarismo")|| possiveisCausas.get(1).toString().equals("Falta de exercício mental"));
		
	}
	

}
