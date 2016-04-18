package veicular.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import veicular.logica.app.VeiculoLogicaIF;
import veicular.logica.dominio.Aeronave;
import veicular.logica.dominio.Proprietario;
import veicular.logica.dominio.Veiculo;

public class JListaDeVeiculo extends JFrame implements VeiculoUIIF, ActionListener{

	private JPanel contentPane;
	private VeiculoLogicaIF appLogica;
	private JTable table;
	private List<Veiculo> lista;
	private JScrollPane scrollPane;
	private JTextField textBusca;
	private JButton btnRemover;
	private JButton btnAtualizar;
	private TableModelos modelo;
	private JButton btnFiltrar;
	
	public JListaDeVeiculo() throws Exception {
		setTitle("Lista de Veiculos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		
		btnAtualizar = new JButton("Atualizar");		
		btnRemover = new JButton("Remover");
		
		textBusca = new JTextField();
		textBusca.setColumns(10);
		
		JLabel lblPlaca = new JLabel("Placa");
		
		btnFiltrar = new JButton("Filtrar");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(206, Short.MAX_VALUE)
					.addComponent(btnAtualizar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRemover)
					.addGap(105))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPlaca)
					.addGap(18)
					.addComponent(textBusca, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(btnFiltrar)
					.addContainerGap(21, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlaca)
						.addComponent(btnFiltrar)
						.addComponent(textBusca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRemover)
						.addComponent(btnAtualizar))
					.addGap(7))
		);
		
		btnRemover.addActionListener(this);
		btnAtualizar.addActionListener(this);
		btnFiltrar.addActionListener(this);
		contentPane.setLayout(gl_contentPane);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  
	}

	private void criarTabela(Collection<Veiculo> listas) throws Exception {
		table = new JTable();
		this.lista = new ArrayList<Veiculo>(listas); 		
		modelo = new TableModelos(lista);
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
	}

	@Override
	public void setLogic(VeiculoLogicaIF app) {
		this.appLogica = app;
		
	}

	@Override
	public void setDisplay() throws Exception {
		criarTabela(this.appLogica.listarVeiculo());
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
			if(e.getSource() == btnAtualizar){
				int linhaSelecionada = table.getSelectedRow();
				JCadastroDeVeiculoUI cadastro = new JCadastroDeVeiculoUI();
			    cadastro.setLogic(appLogica);	
			    
			    try {
					//cadastro.setDisplay();
					cadastro.setDisplayMofificado(table);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			    this.dispose();
			    try {
					//this.criarTabela(this.appLogica.listarVeiculo());
			        //Proprietario p = new Proprietario("Maria", "12222");
					//this.modelo.updateVeiculo(linhaSelecionada, new Aeronave((String) table.getValueAt(linhaSelecionada, 0), (Integer.parseInt((String)table.getValueAt(linhaSelecionada, 1))), (Double.parseDouble((String) table.getValueAt(linhaSelecionada, 2))), p));
					
					//System.out.println("ok");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}else
				if(e.getSource() == btnRemover){
					int linhaSelecionada = -1; 
					linhaSelecionada = table.getSelectedRow();
					
					if (linhaSelecionada >= 0) { 
						String placa = (String) table.getValueAt(linhaSelecionada, 0); 
						try {
							this.appLogica.removerVeiculo(placa);
							this.modelo.removeVeiculo(linhaSelecionada);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
					}
					else { 
						JOptionPane.showMessageDialog(null, "É necesário selecionar uma linha."); } 
					}
				else
					if(e.getSource() == btnFiltrar){
						try {							
							System.out.println(textBusca.getText());
							criarTabela(this.appLogica.buscaPorPartesVeiculo(textBusca.getText()));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
	}
	
	
}
