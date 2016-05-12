package br.ufpb.sisbula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Classe fachada, onde será delegada as função e executado todos os metodos.
 * @author Bia
 *
 */
public class SisBulaMemory implements SisBula {
	
	private GerenteMedicamento gerenteMed;
	private GerenteDeDoencasESintomas gerenteDS;
	/**
	 * Construtor do SisBualMemory
	 * @throws Exception caso precise.
	 */
	public SisBulaMemory() throws Exception {
		this.gerenteMed = new GerenteMedicamento();
		this.gerenteDS = new GerenteDeDoencasESintomas();
	}
	
	@Override
	public boolean cadastraDoenca(String nome) {
		return this.gerenteDS.cadastraDoenca(nome);
	}
	@Override
	public void cadastraSintoma(String nomeSintomas) throws SintomaJaExisteException{
		this.gerenteDS.cadastraSintoma(nomeSintomas);
	}
	
	public Doenca pesquisaDoenca(String nomeDoenca){
		return this.gerenteDS.pesquisaDoenca(nomeDoenca);
	}
	@Override
	public void cadastraMedicamento(String nomeMedicamento) throws MedicamentoJaExisteException{
		gerenteMed.cadastraMedicamento(nomeMedicamento);
	}

	@Override
	public void cadastraMedicamento(Medicamento m) throws MedicamentoJaExisteException {
		gerenteMed.cadastraMedicamento(m);

	}
	@Override
	public Medicamento pesquisaMedicamento(String nome) throws MedicamentoInexistenteException{
		return gerenteMed.pesquisaMedicamento(nome);
	}
	
	@Override
	public Medicamento pesquisaMedicamento(String nome, Fabricante fabricante) throws MedicamentoInexistenteException{ 
		return gerenteMed.pesquisaMedicamento(nome, fabricante);
	}
	@Override
	public Doenca pesquisaDoencaPeloNome(String nome) throws DoencaInexitenteException{
		return gerenteDS.pesquisaDoencaPeloNome(nome);
	}
	@Override
	public List<Medicamento> pesquisaMedicamentosDoFabricante(Fabricante fab){
		return gerenteMed.pesquisaMedicamentosDoFabricante(fab);
	}
	@Override
	public List<Medicamento> pesquisaMedicamentosPara(String nomeDS){
		IndicacaoMedicamento ind = this.gerenteDS.pesquisaDoencaOuSintoma(nomeDS);
		if(ind!= null){
			return this.pesquisaMedicamentosPara(ind);
		} else{
			return new ArrayList<Medicamento>();
		}
	}
	
	@Override
	public List<Medicamento> pesquisaMedicamentosPara(IndicacaoMedicamento ind) {
		return gerenteMed.pesquisaMedicamentosPara(ind);
	}
	@Override
	public void cadastraMedicamentoParaDoenca(String nomeMedicamento, String nomeDoenca) throws MedicamentoInexistenteException{ 
		IndicacaoMedicamento indicacao = this.gerenteDS.pesquisaDoencaOuSintoma(nomeDoenca);
		if(indicacao == null){
			this.gerenteDS.cadastraDoenca(nomeDoenca);
			indicacao = this.gerenteDS.pesquisaDoencaOuSintoma(nomeDoenca);
		}
		this.gerenteMed.cadastraMedicamentoPara(nomeMedicamento, indicacao);
	}
	@Override
	public void cadastraMedicamentoParaSintoma(String nomeMedicamento, String nomeSintoma) throws MedicamentoInexistenteException{ 
		IndicacaoMedicamento indicacao = this.gerenteDS.pesquisaDoencaOuSintoma(nomeSintoma);
		if(indicacao == null){
			this.gerenteDS.cadastraSintoma(nomeSintoma);
			indicacao = this.gerenteDS.pesquisaDoencaOuSintoma(nomeSintoma);
		}
		this.gerenteMed.cadastraMedicamentoPara(nomeMedicamento, indicacao);
	}

	@Override
	public void cadastraDoenca1(Doenca d) throws DoencaJaExisteException {
		this.gerenteDS.cadastraDoenca1(d);
		
	}

	@Override
	public void cadastraSintoma(String nome, String descricao) throws SintomaJaExisteException {
		this.gerenteDS.cadastraSintoma(nome);
		this.gerenteDS.cadastraDoenca(descricao);
		
	}
	@Override
	public void cadastraSintomaDeDoenca(String nomeDoenca, String sintoma) {
		this.gerenteDS.cadastraSintoma(sintoma);
		this.gerenteDS.cadastraSintomaDeDoenca(nomeDoenca, sintoma);
	}
	@Override
	public List<Doenca> pesquisaDoencasCausadasPor(String sintoma){
		return gerenteDS.pesquisaDoencaCausadaPor(sintoma);
		
	}
	@Override
	public List<CausaDeDoenca> pesquisaPossiveisCausaDe(String doencas) {
		return gerenteDS.pesquisaPossiveisCausaDe(doencas);
	}
	@Override
	public void cadastraPossivelCausaDeDoenca(String nomeDoenca, String causaDaDoenca) {
		this.gerenteDS.cadastraPossivelCausaDeDoenca(nomeDoenca, causaDaDoenca);
		
	}

	
}
