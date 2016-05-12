package br.ufpb.sisbula;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufpb.sisbula.persistencia.GerenteDeArquivoDeMedicamentos;
import br.ufpb.sisbula.persistencia.SisBulaPercistenciaException;

public class GerenteMedicamento {
	private Map<String, Medicamento> medicamentos;
	private GerenteDeArquivoDeMedicamentos gerenteArqMed;

	public GerenteMedicamento(){
		this.gerenteArqMed = new GerenteDeArquivoDeMedicamentos();
		this.medicamentos = new HashMap<String,Medicamento>();
		try{
			Collection<Medicamento> medRecuperados = gerenteArqMed.leMedicamentos();
			for(Medicamento m: medRecuperados){
				this.medicamentos.put(m.getId(), m);
			}
		}catch(IOException e){
			System.err.println("Não foi possivel recuperar arquivo dos medicamentos");
		}catch(Exception e1){
			System.err.println("Não foi possivel recuperar arquivo dos medicamentos e não deu erro de entrada e saida.");
		}
	}
	
	public void cadastraMedicamento(String nomeMedicamento) throws MedicamentoJaExisteException{
		Medicamento m = new Medicamento (nomeMedicamento);
		this.cadastraMedicamento(m);
	}

	public void cadastraMedicamento(Medicamento m) throws MedicamentoJaExisteException {
		Medicamento med = this.medicamentos.get(m.getId());
		if (med!=null){
			throw new MedicamentoJaExisteException("JÃ¡ existe medicamento com este nome:" + m.getNome());
		} else {
			this.medicamentos.put(m.getId(), m);
		}

	}
	
	public Medicamento pesquisaMedicamento(String nome) throws MedicamentoInexistenteException{
		for(Medicamento m : this.medicamentos.values()){// values retorna a coleção dos medicamentos
			if(m.getNome().equals(nome)){
				return m;
			}
		}
		throw new MedicamentoInexistenteException("não há medicamento com o nome"+nome);
	}

	public Medicamento pesquisaMedicamento(String nome, Fabricante fabricante) throws MedicamentoInexistenteException {
		String id = nome+fabricante.toString();
		Medicamento med = this.medicamentos.get(id);
		
		if (med!=null) {
				return med;
		} else {
			throw new MedicamentoInexistenteException("NÃ£o existe medicamento com este nome:" + nome);
	
		}
	}
	
	public List<Medicamento> pesquisaMedicamentosDoFabricante(Fabricante fab){
		List<Medicamento> lista = new ArrayList<Medicamento>();
		for (Medicamento m: this.medicamentos.values()){
			if (m.getFabricante().equals(fab)){
				lista.add(m);
			}
		}
		return lista;
	}

	
	public List<Medicamento> pesquisaMedicamentosPara(IndicacaoMedicamento ind) {
		List <Medicamento> lista = new ArrayList<Medicamento>();
		for (Medicamento m: this.medicamentos.values()){
			for (IndicacaoMedicamento i: m.getIndicacoes()){
				if (i.getNome().equals(ind.getNome())){
					lista.add(m);
					break;
				}
			}
		}
		return lista;
	}
	
	public void cadastraMedicamentoPara(String nomeMed, IndicacaoMedicamento indicacao) throws MedicamentoInexistenteException{
		Medicamento m= this.pesquisaMedicamento(nomeMed);
		m.adicionaIndicacao(indicacao);
	}
	
	public void salvarDados() throws SisBulaPercistenciaException{
		try{
			this.gerenteArqMed.gravaMedicamento(medicamentos.values());
		}catch(IOException e){
			throw new SisBulaPercistenciaException("Não foi possive salvar os dados do medicamento");
		}
	}
}
