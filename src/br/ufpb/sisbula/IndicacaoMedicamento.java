package br.ufpb.sisbula;

import java.io.Serializable;

public abstract class IndicacaoMedicamento  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	
	public IndicacaoMedicamento(String nome){
		this.nome = nome;
	}
	public String getNome(){
		return this.nome;
	}

}
