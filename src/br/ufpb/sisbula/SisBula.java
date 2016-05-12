package br.ufpb.sisbula;

import java.util.List;

/**
 * Descreve as funcionalidades de um sistema de informaÃ§Ãµes
 * sobre medicamentos
 * 
 * @author ayla
 *
 */
public interface SisBula {
	
	/**
	 * Cadastra um novo medicamento no sistema 
	 * @param m O novo medicamento a ser cadastrado.
	 * @throws MedicamentoJaExisteException Quando jÃ¡ existe
	 * um medicamento com o mesmo nome do medicamento a ser
	 * cadastrado.
	 */
	public void cadastraMedicamento(Medicamento m) throws MedicamentoJaExisteException;
	
	/**
	 * Cadastra uma doença.
	 * @param d Nome da doença a ser cadastrada.
	 * @throws DoencaJaExisteException Lança a exeção caso a doenca já exista.
	 */
	
	public void cadastraMedicamento(String nomeMedicamento) throws MedicamentoJaExisteException;
	/**
	 * Metodo que cadastra uma doenca recendo o objeto Doença.
	 * @param d é o nome do objeto passado como parametro.
	 * @throws DoencaJaExisteException Lança uma exeção, caso a doenca que qerira cadastrar ja exista.
	 */
	public void cadastraDoenca1(Doenca d) throws DoencaJaExisteException;
	
	/**
	 * Metodo que cadastra um sintoma.
	 * @param nome do sintoma.
	 * @param descricao do sintoma.
	 * @throws SintomaJaExisteException Lança a exeção caso o sintoma já exista.
	 */
	
	public boolean cadastraDoenca(String nome);
	/**
	 * Metodo que cadastra sintoma passando o nome do sintoma e a descrição.
	 * @param nome da sintoma
	 * @param descricao do sintoma
	 * @throws SintomaJaExisteException Lança exeção caso o sintoma já esteja cadastrado.
	 */
	public void cadastraSintoma(String nome, String descricao) throws SintomaJaExisteException;
	
	/**
	 * Retorna uma lista dos medicamentos indicados para certas
	 * doenÃ§as ou sintomas. 
	 * @param ind Um sintoma ou doenÃ§a
	 * @return a lista dos medicamentos para o sintoma
	 * ou doenÃ§a pesquisado
	 */
	
	public void cadastraSintoma(String nomeSintomas) throws SintomaJaExisteException;
	/**
	 * pesquisa medicamento para uma determinada indicação de medicamento recebendo um indicação.
	 * @param ind indicação de medcamento pesquisado.
	 * @return uma lista de medicamentos com todos os medicamentos com essa indicação.
	 */
	public List<Medicamento> pesquisaMedicamentosPara(IndicacaoMedicamento ind);
	/**
	 * pesquisa um medicamento pra uma doençca ou sintoma recebendo o nomeDS.
	 * @param nomeDS nome da doença ou sintoma.
	 * @return uma lista de medicamento com todos os medicamentos para essa doenca/sintoma.
	 */
	public List<Medicamento> pesquisaMedicamentosPara(String nomeDS);
	
	/**
	 * Metodo que pesquisa uma doença pelo o nome.
	 * @param nome da doença  que estar procurando.
	 * @return retorna a doença encontrada.
	 * @throws DoencaInexitenteException Lança uma exeção caso essa doença não exista.
	 */
	public Doenca pesquisaDoencaPeloNome(String nome) throws DoencaInexitenteException;
	
	/**
	 * Metodo que pesquisa um medicamento pelo o nome do medicamento e pelo o fabricante.
	 * @param nome do medicamento que estar procurado.
	 * @param fabricante do medicamento procurado.
	 * @return uma lista de medicamento encontrado com o mesmo nome e o mesmo fabricante.
	 * @throws MedicamentoInexistenteException lança exeção, caso o medicamento pesquisado não exista.
	 */
	public Medicamento pesquisaMedicamento(String nome, Fabricante fabricante) throws MedicamentoInexistenteException;
	
	/**
	 * Metodo que pesquisa medicamento por o nome do fabricante.
	 * @param fab O nome do fabricante.
	 * @return Uma lista de medicamento existente nesse fabricante.
	 */
	
	public Medicamento pesquisaMedicamento(String nome) throws MedicamentoInexistenteException;
	/**
	 * metodo que pesquisa medicamento de um certo fabricante, recebendo o nome do fabricante desejado.
	 * @param fab nome do fabricante que deseja pegar os medicamentos.
	 * @return uma lista de medicamentos de todos os medicamentos desse fabricante.
	 */
	public List<Medicamento> pesquisaMedicamentosDoFabricante(Fabricante fab);
	
	/**
	 * Metodo que cadastra um medicamento para uma certa doença 
	 * @param nomeMedicamento que quer cadastrar.
	 * @param nomeDoenca Nome da doença desejada que quer cadastrar o medicamento.
	 * @throws MedicamentoInexistenteException Lança a exeção caso não exista o medicamento para essa doença.
	 */
	public void cadastraMedicamentoParaDoenca(String nomeMedicamento, String nomeDoenca) throws MedicamentoInexistenteException;
	/**
	 * metodo que cadastra medicamento parar um sintoma, recebendo o nome do medicamento e o nome do sintoma.
	 * @param nomeMedicamento o nome do medicamento que quer cadastrar.
	 * @param nomeSintoma o nome do sintoma que quer cadastrar.
	 * @throws MedicamentoInexistenteException lança exeção caso o medicamento não exista.
	 */
	public void cadastraMedicamentoParaSintoma(String nomeMedicamento, String nomeSintoma) throws MedicamentoInexistenteException;
	
	/**
	 * Método que cadastra o sintoma de uma doença, recebendo o nome da doença e sintoma dela
	 * @param nomeDoenca Nome da doença que deseja cadastrar.
	 * @param sintoma Nome do sintoma da doenca que deseja cadastrar.
	 */
	public void cadastraSintomaDeDoenca(String nomeDoenca, String sintoma);
	/**
	 * Método que pesquisa possiveis causas de uma certa doença.
	 * @param doencas O nome da doença a que está procurando as causas.
	 * @return Retorna uma lista de causas de doenças dessa doença passada.
	 */
	public List<CausaDeDoenca> pesquisaPossiveisCausaDe(String doencas);
	/**
	 * Método que pesquisa uma doença causada por um sintoma.
	 * @param sintoma O sintoma um doenca.
	 * @return Uma lista de doenca com esse determinado sintoma.
	 */
	public List<Doenca> pesquisaDoencasCausadasPor(String sintoma);
	/**
	 * Método que cadastra possiveis causas de uma certa doenca,
	 * passando como parametro o nome da doenca e a causa da doenca.
	 * @param nomeDoenca Nome da doença.
	 * @param causaDaDoenca Nome das causas da doenca que quer cadastrar.
	 */
	public void cadastraPossivelCausaDeDoenca(String nomeDoenca, String causaDaDoenca);
	
}
