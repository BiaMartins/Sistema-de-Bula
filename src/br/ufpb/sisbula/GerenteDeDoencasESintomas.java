package br.ufpb.sisbula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Gerencia operações referentes a doenças e sintomas.
 * @author Bia
 */
public class GerenteDeDoencasESintomas {
	private Map<String, Sintoma> sintomas;
	private Map<String, Doenca> doencas;
	private Map<String, CausaDeDoenca> causas;
	/**
	 * Construtor da classe.
	 */
	public GerenteDeDoencasESintomas(){
		this.sintomas = new HashMap<String,Sintoma>();
		this.doencas  = new HashMap<String, Doenca>();
		this.causas = new HashMap<String, CausaDeDoenca>();
	}
	/**
	 * Cadastra uma doença com este nome.
	 * @param nomeDoenca O nome da doença a ser cadastrada.
	 * @return Retorna True se conseguir cadastrar e false caso ja exista uma doença come esse nome.
	 */
	public boolean cadastraDoenca(String nomeDoenca) {
		if(this.doencas.containsKey(nomeDoenca)){
			return false;
		}else{
			Doenca d = new Doenca(nomeDoenca);
			this.doencas.put(nomeDoenca, d);
			return true;
		}
	}
	/**
	 * Cadastra um sintoma
	 * @param nomeSintoma O nome do sintoma
	 * @return True se o sintoma for cadastrado e false 
	 * se não for cadastrado por já existir.
	 */
	public boolean cadastraSintoma(String nomeSintoma){
		Sintoma s = this.sintomas.get(nomeSintoma);
		if(s== null){
			s = new Sintoma(nomeSintoma);
			this.sintomas.put(nomeSintoma, s);
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Cadastra um sintoma com o nome do sintoma e a descrição dele.
	 * @param nome da sintoma
	 * @param descricao do sintoma
	 * @throws SintomaJaExisteException, Lança a exeção caso o sintoma já esteja cadastrado.
	 */
	public void cadastraSintoma(String nome, String descricao)throws SintomaJaExisteException{
		Sintoma s = this.sintomas.get(nome);
		if(s== null){
			s= new Sintoma(nome);
			this.sintomas.put(nome, s).getDescricao();
			
		}else{
			throw new SintomaJaExisteException("Sintoma já cadastrado.");
		}
	}
	/**
	 * Metodo que pesquisa uma doenca pelo o seu nome.
	 * @param nomeDoenca Nome da doença 
	 * @return A doença encontrada com o nome pesquisado.
	 */
	public Doenca pesquisaDoenca(String nomeDoenca){
		for(Doenca d : this.doencas.values()){
			if(d.getNome().equals(nomeDoenca)){
				return d;
			}
		}
		return null;
	}
	/**
	 * Pesquisa se existe uma doenca ou sintoma com este nome.
	 * @param nomeDS O nome da doença ou sintoma.
	 * @return A doença ou sintoma com esse nome, 
	 * ou null se não encontrar nem doença nem sintoma com esse nome.
	 */
	public IndicacaoMedicamento pesquisaDoencaOuSintoma(String nomeDS){
		IndicacaoMedicamento ind = this.doencas.get(nomeDS);
		if(ind== null){
			ind = this.sintomas.get(nomeDS);
			return ind;
		}else{
			return ind;
		}
	}
	/**
	 * Método que pesquisa uma doenca pelo o nome
	 * @param nome Nome da doença a ser pesquisado
	 * @return A doenca encontrada.
	 */
	public Doenca pesquisaDoencaPeloNome(String nome) {
		if(doencas.containsKey(nome)){
			return doencas.get(nome);
		}else{
			return null;
		}
	}
	/**
	 * Metodo que cadastra uma doença passando uma doenca		
	 * @param doenca O nome da doença do tipo Doenca que deseja cadastrar
	 * @return True caso a doenca seja cadastrada ou false caso a doenca ja exista.
	 */
	public boolean cadastraDoenca1(Doenca doenca){
		if(doencas.containsValue(doenca)){
			return false;
		}else{
			doencas.put(doenca.getNome(), doenca);
			return true;
		}
	}
	/**
	 * Método que cadastra o sintoma de uma doença, recebendo o nome da doença e sintoma dela.
	 * @param nomeDoenca Nome da doença que deseja cadastrar.
	 * @param sintoma Nome do sintoma da doenca que deseja cadastrar.
	 */
	public void cadastraSintomaDeDoenca(String nomeDoenca, String sintoma) {
		Sintoma sint = this.sintomas.get(sintoma);
		this.doencas.get(nomeDoenca).adicionaSintoma(sint);
		
	}
	/**
	 * Pesquisa uma doença causada por um sintoma
	 * @param sintoma O nome do sintoma da doença
	 * @return UMa lista de doença causada por esse sintoma.
	 */
	public List<Doenca> pesquisaDoencaCausadaPor(String sintoma) {
		List<Doenca> doencasCausadasPor = new ArrayList<Doenca>();
		for(Doenca d : doencas.values()){
			List<CausaDeDoenca> causasDoenca = d.getCausas();
			for(CausaDeDoenca c: causasDoenca){
				if(c.toString().equalsIgnoreCase(sintoma)){
					doencasCausadasPor.add(d);
				}
			}
		}
		return doencasCausadasPor;
	
	}
	/**
	 * Metodo que pesquisa as possiveis causas de uma doenca,
	 * passando como parametro o nome da doença.
	 * @param doenca O nome da doenca.
	 * @return uma lista de causas que essa doenca tras.
	 */
	
	public List<CausaDeDoenca> pesquisaPossiveisCausaDe(String doenca){
		List<CausaDeDoenca> causaD = pesquisaDoenca(doenca).getCausas();
		return causaD;
	}
	/**
	 * Metodo que cadastra possiveis causas de uma doenca, 
	 * passando como parametro o nome da doença e suas causas.
	 * @param nomeDoenca Nome da doenca.
	 * @param causaDoenca causas da doenca.
	 */
	public void cadastraPossivelCausaDeDoenca(String nomeDoenca, String causaDoenca) {
		CausaDeDoenca causas =  this.causas.get(causaDoenca);
		if(causas == null){
			causas = new CausaDeDoenca(causaDoenca);
			this.causas.put(causaDoenca, causas);
		}
		this.doencas.get(nomeDoenca).adicionaCausas(causas);
	}
}







