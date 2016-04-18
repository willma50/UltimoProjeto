package veicular.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import veicular.logica.app.VeiculoLogicaIF;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JInicialUI extends JFrame implements VeiculoUIIF, ActionListener{

	private JPanel contentPane;
	private JMenuItem cadastrarMenuItem;
	private JMenuItem consultarMenuItem;
	private JMenuItem consultarDados;
	private JMenuItem imprimirDaf;
	private JMenuItem imprimirDaf2;
	
	private VeiculoLogicaIF appLogica;

	
	public JInicialUI() {
		setTitle("Projeto de Poo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu manutencaoMenu = new JMenu("Manutenção");
		menuBar.add(manutencaoMenu);
		
		JMenu dadosEstatisticoMenu = new JMenu("Dados Estatisticos");
		menuBar.add(dadosEstatisticoMenu);
			
		consultarDados = new JMenuItem("Consultar");
		consultarDados.addActionListener(this);
		dadosEstatisticoMenu.add(consultarDados);
		
		JMenu impressaoMenu = new JMenu("Imprimir");
		menuBar.add(impressaoMenu);
		
		imprimirDaf = new JMenuItem("DAF/Proprietário");
		imprimirDaf.addActionListener(this);
		impressaoMenu.add(imprimirDaf);
		
		imprimirDaf2 = new JMenuItem("DAF Normal");
		imprimirDaf2.addActionListener(this);
		impressaoMenu.add(imprimirDaf2);
		
		JMenu veiculoMenu = new JMenu("Veículo");
		manutencaoMenu.add(veiculoMenu);
		
		cadastrarMenuItem = new JMenuItem("Cadastrar");
		cadastrarMenuItem.addActionListener(this);
		veiculoMenu.add(cadastrarMenuItem);
		
		consultarMenuItem = new JMenuItem("Consultar");
		consultarMenuItem.addActionListener(this);
		veiculoMenu.add(consultarMenuItem);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manutenção");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Ricardo\\workspace\\ProjetoFinal\\imagens\\MANUTEN\u00C7\u00C3O.jpg"));
		lblNewLabel.setBounds(0, 0, 432, 242);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == cadastrarMenuItem){
		    JCadastroDeVeiculoUI cadastro = new JCadastroDeVeiculoUI();
		    cadastro.setLogic(appLogica);		   
				try {
					cadastro.setDisplay();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
		}else
			if (e.getSource() == consultarMenuItem){
				JListaDeVeiculo lista = null;
				try {
					lista = new JListaDeVeiculo();
				} catch (Exception e2) {
					
				}
				lista.setLogic(appLogica);
				try {
					lista.setDisplay();
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				
			}else
				if(e.getSource() == consultarDados){
					JDadosEstatisticosUI dados = new JDadosEstatisticosUI();
					dados.setLogic(appLogica);
					try {
						dados.setDisplay();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}else
					if(e.getSource() == imprimirDaf){
						JImprimirDafUI imp = new JImprimirDafUI();
						imp.setLogic(appLogica);
						try {
							imp.setDisplay();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}else
						if(e.getSource() == imprimirDaf2){
							
							JImprimirDAFNormalUI imp = new JImprimirDAFNormalUI();
							imp.setLogic(appLogica);
							try {
								imp.setDisplay();
							} catch (Exception e1) {
								
								e1.printStackTrace();
							}
						}
		
	}

	@Override
	public void setLogic(VeiculoLogicaIF app) {
		this.appLogica = app;
		
	}

	@Override
	public void setDisplay() throws Exception {
		this.setVisible(true);
		
	}
}
