package veicular.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import veicular.logica.app.ProprietarioLogica;
import veicular.logica.app.ProprietarioLogicaIF;
import veicular.logica.app.VeiculoLogicaIF;
import veicular.logica.dominio.DAFIndividual;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.TextArea;


public class JImprimirDafUI extends JFrame implements VeiculoUIIF, ActionListener{

	private VeiculoLogicaIF appLogica;
	private ProprietarioLogicaIF appLogicaPropietario;
	private JPanel contentPane;
	private ButtonGroup grupoRadio;
	private JRadioButton radioDafIndividual;
	private JRadioButton radioDafFrota;
	private JComboBox comboProprietario;
	private JButton btnImprimir;
	//private JTextPane textPaneResultado;
	/**
	 * @wbp.nonvisual location=54,269
	 */
	private final TextArea textArea = new TextArea();
	
	public JImprimirDafUI() {
		setTitle("Impress\u00E3o da DAF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		comboProprietario = new JComboBox();
		
		JLabel label = new JLabel("Propriet\u00E1rio");
		this.grupoRadio = new ButtonGroup();
		JLabel lblOpo = new JLabel("Op\u00E7\u00E3o");
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(this);
		
		JPanel panel = new JPanel();
		
		radioDafIndividual = new JRadioButton("DAF Indiviaual");		
		panel.add(radioDafIndividual);
		grupoRadio.add(radioDafIndividual);
		radioDafFrota = new JRadioButton("DAF Frota");
		panel.add(radioDafFrota);
		grupoRadio.add(radioDafFrota);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(comboProprietario, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblOpo, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(39)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(185)
							.addComponent(btnImprimir))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(label))
						.addComponent(comboProprietario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOpo)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnImprimir)
					.addGap(22)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
		);
		contentPane.setLayout(gl_contentPane);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnImprimir){
			
			try {
				if(this.appLogica.esProprietarioFrota((String)this.comboProprietario.getSelectedItem())){
				
				if(verificaRadio() == 0){
					
					String texto = "";
					try {
						this.appLogica.listarSumarioDAFIndividual((String)this.comboProprietario.getSelectedItem());
						Iterator<DAFIndividual> sumarios = this.appLogica.listarSumarioDAFIndividual((String)this.comboProprietario.getSelectedItem()).iterator();;

						while(sumarios.hasNext()){
							DAFIndividual sumario = sumarios.next();
						
							texto = sumario.toString();
						}
						textArea.setText(texto);
					
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
					
				}else{			
					String texto = "";
					try {
						
						texto = this.appLogica.listarSumarioDAFFrota((String)this.comboProprietario.getSelectedItem());
						textArea.setText(texto);
					} catch (Exception e1) {
						System.out.println("imprimir erro");
						e1.printStackTrace();
					}
				}
				
				}
				else
					System.out.println("Não é proprietário de frota");
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		
	}

	
	private int verificaRadio() {
		int tipo =-1;
		Enumeration<AbstractButton> bg = this.grupoRadio.getElements();
		while(bg.hasMoreElements()){
			 JRadioButton jrd = (JRadioButton) bg.nextElement();
			 if(jrd.isSelected()){
				 if(jrd.getText() == "DAF Indiviaual")
					 tipo =  0;
				 else
					 if(jrd.getText() == "DAF Frota")
						 tipo =  1;
					
			 }
		 }
		System.out.println(tipo+" tipos");
		return tipo;
	}
	
	@Override
	public void setLogic(VeiculoLogicaIF app) {
		this.appLogica = app;
		
	}

	@Override
	public void setDisplay() throws Exception {
		this.appLogicaPropietario = new  ProprietarioLogica();
		Collection<String> proprietario = this.appLogicaPropietario.findAll();
		this.comboProprietario.setModel(new DefaultComboBoxModel<String>(new Vector<String>(proprietario)));
		this.setVisible(true);
		
	}
}
