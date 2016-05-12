package br.ufpb.sisbula;

public class MedicamentoInexistenteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MedicamentoInexistenteException(String msg){
		super(msg);
	}

}
