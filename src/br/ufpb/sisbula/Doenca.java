package br.ufpb.sisbula;

import java.util.ArrayList;
import java.util.List;

public class Doenca extends IndicacaoMedicamento{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Sintoma> sintomas;
	List<CausaDeDoenca> causas;
	
	public Doenca(String nome){
		super(nome);
		this.sintomas = new ArrayList<Sintoma>();
		this.causas = new ArrayList<CausaDeDoenca>();
	}
	public void setSintomas(List<Sintoma> sintomas){
		this.sintomas = sintomas;
	}
	public List <Sintoma> getSintomas(){
		return this.sintomas;
	}
	
	public List<CausaDeDoenca> getCausas(){
		return this.causas;
	}
	
	public void setCausaDeDoenca(List<CausaDeDoenca> causas){
		this.causas= causas;
	}
	
	public void adicionaSintoma(Sintoma sint){
		this.sintomas.add(sint);
	}
	public void adicionaCausas(CausaDeDoenca causa){
		this.causas.add(causa);
	}
	public String toString(){
		return this.getNome();
	}
}
