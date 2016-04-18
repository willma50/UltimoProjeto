package veicular.ui;


import java.awt.Container;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import veicular.logica.app.VeiculoLogicaIF;


public class Principal extends JFrame implements VeiculoUIIF, ActionListener{
	
	public Panel painel = new Panel();
	JMenuBar barra; 
	JMenu manutencao, veiculo, sair;
	JMenuItem cadastrar, consultar, remover, consultaNome; //obs: não terá menu para remover 
												//vi ser na tela de listagem
	private VeiculoLogicaIF appLogica;
	
	public Principal(){		
		super("Tela inicial");
	}
	
	public void montarTela() {
		
		Container tela = getContentPane();
		//painel.setBackground(new Color(0,0,0)); //configurando cor de fundo para o painel
        painel.setBounds(new Rectangle(0,28,800,520));//configurando forma no caso Retângulo com posição x,y e medidas w, h
        tela.add(painel);  
         
		tela.setLayout(null);
		
		barra = new JMenuBar();
		
		setJMenuBar(barra);
		manutencao = new JMenu("Manutenção");	
		veiculo = new JMenu("Veiculo");
		sair = new JMenu("Sair");		
		cadastrar = new JMenuItem("Cadastrar");
		consultar = new JMenuItem("Consultar");		
		remover = new JMenuItem("Remover");
		consultaNome = new JMenuItem("Consulta Por Nome");
		
		barra.add(manutencao);
		barra.add(sair);
		manutencao.add(veiculo);		
		veiculo.add(cadastrar);
		veiculo.add(consultar);		
		veiculo.add(remover);
		veiculo.add(consultaNome);
		
		consultar.addActionListener(this);
		cadastrar.addActionListener(this);
		consultaNome.addActionListener(this);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == consultar){
			System.out.println("entrou menu ");
			
			try {
				this.appLogica.findAll();
			} catch (Exception e1) {
				System.out.println("Erro consulta!");
				e1.printStackTrace();
			}		
		}else
			
		if(e.getSource() == cadastrar){
				
			try {
				this.appLogica.addVeiculo("teste placa2", 2009, 1, 10000, "Maria");
			} catch (Exception e1) {
				System.out.println("Erro cadastro!");
				e1.printStackTrace();
			}	
		}else
		 if(e.getSource() == remover){
			 try {
				this.appLogica.removerVeiculo("teste placa");
			} catch (Exception e1) {
				System.out.println("Erro remoção!");
				e1.printStackTrace();
			}
		 }else{
			 System.out.println("ano:");
			 System.out.println(this.appLogica.buscaAno());
			 
		 }
		
	}
	
	@Override
	public void setLogic(VeiculoLogicaIF app) {
		this.appLogica = app;
		
	}

	@Override
	public void setDisplay() throws Exception {
		this.montarTela();
		setSize(600,400);
		setLocationRelativeTo(null);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*public static void main (String args[]){
		Principal app = new Principal();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} */
}
