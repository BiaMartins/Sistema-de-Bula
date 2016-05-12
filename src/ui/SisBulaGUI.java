package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import br.ufpb.sisbula.DoencaInexitenteException;
import br.ufpb.sisbula.DoencaJaExisteException;
import br.ufpb.sisbula.Fabricante;
import br.ufpb.sisbula.IndicacaoMedicamento;
import br.ufpb.sisbula.Doenca;
import br.ufpb.sisbula.Sintoma;
import br.ufpb.sisbula.Medicamento;
import br.ufpb.sisbula.MedicamentoInexistenteException;
import br.ufpb.sisbula.MedicamentoJaExisteException;
import br.ufpb.sisbula.SintomaJaExisteException;
import br.ufpb.sisbula.SisBula;
import br.ufpb.sisbula.SisBulaMemory;

/**
 * Classe representando uma interface grÃ¡fica com Menu para o sistema SisBula
 * ObservaÃ§Ã£o: CÃ³digo baseado no Exemplo 8.2 do livro Java 7 Ensino DidÃ¡tico
 */

public class SisBulaGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SisBula sisBula;
	private Container painelDeConteudo;
	private JMenuBar barraDeMenu;
	private JMenu medicamentoMenu, doencaMenu, sintomaMenu, creditosMenu;
	private JMenuItem cadastraMed, pesquisaMed, pesquisaMedPara, pesquisaMedDoFabricante, pesquisaDoencaPeloNome, cadastraDoenca, cadastraSintoma, creditos;
	
	
	public SisBulaGUI(SisBula sis){
		this.sisBula = sis;
		inicializarComponentes();
		definirEventos();
		
	}
	
	private void inicializarComponentes(){
		setTitle("SisBula");
		setBounds(0,0,800,600);
		painelDeConteudo = getContentPane();
		
		barraDeMenu = new JMenuBar();
		medicamentoMenu = new JMenu("Medicamento");
		doencaMenu = new JMenu("Doença");
		sintomaMenu = new JMenu("Sintoma");
		creditosMenu = new JMenu("Creditos");
		
		barraDeMenu.add(medicamentoMenu);
		barraDeMenu.add(doencaMenu);
		barraDeMenu.add(sintomaMenu);
		barraDeMenu.add(creditosMenu);
		
		cadastraMed = new JMenuItem("Cadastrar medicamento");
		cadastraDoenca = new JMenuItem("Cadastra Doença");
		cadastraSintoma = new JMenuItem("Cadastra Sintomas");
		pesquisaDoencaPeloNome = new JMenu("Pesquisa a doença atraves do nome");
		pesquisaMed = new JMenuItem("Pesquisar medicamento");
		pesquisaMedPara = new JMenuItem("Pesquisa medicamento para certa indicação");
		pesquisaMedDoFabricante = new JMenuItem("Pesquisa medicamento do fabricante ...");
		
		medicamentoMenu.add(cadastraMed);
		doencaMenu.add(cadastraDoenca);
		doencaMenu.add(pesquisaDoencaPeloNome);
		sintomaMenu.add(cadastraSintoma);
		medicamentoMenu.add(pesquisaMed);
		medicamentoMenu.add(pesquisaMedPara);
		medicamentoMenu.add(pesquisaMedDoFabricante);
		
		creditos = new JMenuItem("Creditos");
		creditosMenu.add(creditos);
		setJMenuBar(barraDeMenu);
	}
	
	
	
	private void definirEventos(){
		cadastraMed.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Medicamento m = leDadosMedicamento();
				try {
					sisBula.cadastraMedicamento(m);
					JOptionPane.showMessageDialog(painelDeConteudo, "Cadastro efetuado com sucesso");
				} catch (MedicamentoJaExisteException e1) {
					JOptionPane.showMessageDialog(painelDeConteudo, e1.getMessage());
				}
			}
		});
		cadastraDoenca.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Doenca d = leDadosDoencas();
				try{
					sisBula.cadastraDoenca1(d);
					JOptionPane.showMessageDialog(painelDeConteudo, "Cadastro efetuado com sucesso");
				}catch(DoencaJaExisteException e1){
					JOptionPane.showMessageDialog(painelDeConteudo,e1.getMessage());
				}
				
			}
		});
		cadastraSintoma.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Sintoma sint = leDadosSinomas();
				try{
					sisBula.cadastraSintoma(sint.getNome(), sint.getDescricao());
					JOptionPane.showMessageDialog(painelDeConteudo, "cadastro efetuado com sucesso");
				}catch(SintomaJaExisteException e1){
					JOptionPane.showMessageDialog(painelDeConteudo, e1.getMessage());
				}
			}
			
		});
		
		pesquisaMed.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String nome = JOptionPane.showInputDialog("Qual o nome do medicamento a pesquisar?");
				Fabricante fab = leFabricante();
				try {
					Medicamento m  = sisBula.pesquisaMedicamento(nome, fab);
					JOptionPane.showMessageDialog(painelDeConteudo, "Medicamento encontrado:"+m.toString());
				} catch (MedicamentoInexistenteException e1) {
					JOptionPane.showMessageDialog(painelDeConteudo, e1.getMessage());
				}
			}
		});
		
		pesquisaMedPara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String ind = JOptionPane.showInputDialog("Qual é o nome do medicamento que deseja encontrar para esse sintoma/Doença ?");
				IndicacaoMedicamento indicacaoMed = new Doenca(ind); 
				List<Medicamento> m = sisBula.pesquisaMedicamentosPara(indicacaoMed);
				JOptionPane.showMessageDialog(painelDeConteudo, "Medicamento encontardo foi " + m.toString());
			}
		});
		
		pesquisaMedDoFabricante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String m = JOptionPane.showInputDialog("Qual fabricante deseja pesquisa todo os medicamentos?");
				Fabricante x= leFabricante();
				List<Medicamento> m1 = sisBula.pesquisaMedicamentosDoFabricante(x);
				JOptionPane.showMessageDialog(painelDeConteudo, "Os medicamentos encontrado desse fabricante foi " + m1.toString());
				
			}
		});
		
		pesquisaDoencaPeloNome.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String  nomeDoenca = JOptionPane.showInputDialog("Qual nome da doença que deseja pesquisar?");
				Doenca d = leDadosDoencas();
				try {
					d = sisBula.pesquisaDoencaPeloNome(nomeDoenca);
				} catch (DoencaInexitenteException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showInputDialog(painelDeConteudo, "A doença encontrada com o nome pesquisado foi:" + d.toString());
			}
		});
			
			
		
		creditos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(painelDeConteudo, "Sistema feito para aula de POO de Bia com base no exemplo 8.2 do livro Java 7 Ensino Didatico");
			}
		});
		
		
	}

	
	private Medicamento leDadosMedicamento() {
		String nome = JOptionPane.showInputDialog("Qual o nome do medicamento?");
		Fabricante fabricante = leFabricante();
		return new Medicamento(nome,fabricante);
	}
	
	private Doenca leDadosDoencas(){
		String nome = JOptionPane.showInputDialog("Qual é o nome da doença?");
		return new Doenca(nome);
	}
	
	private Sintoma leDadosSinomas(){
		String nome = JOptionPane.showInputDialog("Qual nome do sintoma?");
		String descricao = JOptionPane.showInputDialog("Qual são as descrição desse sintoma?");
		return new Sintoma(nome,descricao);
	}
	
	
	private Fabricante leFabricante(){
		String fabricante = JOptionPane.showInputDialog("Qual o fabricante?");
		if (fabricante.equalsIgnoreCase("MEDLEY")){
			return Fabricante.MEDLEY;
		} else if (fabricante.equalsIgnoreCase("EMS")){
			return Fabricante.EMS;
		} else if (fabricante.equalsIgnoreCase("EUROFARMA")){
			return Fabricante.EUROFARMA;
		} else {
			return Fabricante.FABRICANTE_DESCONHECIDO;
		}
	}
	
	public static void main(String [] args) throws Exception{
		SisBula sis = new SisBulaMemory();
		SisBulaGUI janelaPrincipal = new SisBulaGUI(sis);
		janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janelaPrincipal.setVisible(true);
	}
}
