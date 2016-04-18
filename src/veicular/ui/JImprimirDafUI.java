package veicular.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import veicular.logica.app.ProprietarioLogica;
import veicular.logica.app.ProprietarioLogicaIF;
import veicular.logica.app.VeiculoLogicaIF;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class JImprimirDafUI extends JFrame implements VeiculoUIIF, ActionListener{

	private VeiculoLogicaIF appLogica;
	private ProprietarioLogicaIF appLogicaPropietario;
	private JPanel contentPane;
	private ButtonGroup grupoRadio;
	private JRadioButton radioDafIndividual;
	private JRadioButton radioDafFrota;
	private JComboBox comboProprietario;
	private JButton btnImprimir;
	
	public JImprimirDafUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		comboProprietario = new JComboBox();
		
		JLabel label = new JLabel("Propriet\u00E1rio");
		
		radioDafIndividual = new JRadioButton("DAF Indiviaual");		
		radioDafFrota = new JRadioButton("DAF Frota");
		this.grupoRadio = new ButtonGroup();
		grupoRadio.add(radioDafIndividual);
		grupoRadio.add(radioDafFrota);
		JLabel lblOpo = new JLabel("Op\u00E7\u00E3o");
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(comboProprietario, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(67)
					.addComponent(radioDafIndividual)
					.addGap(18)
					.addComponent(radioDafFrota)
					.addContainerGap(169, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblOpo, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(367, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(116)
					.addComponent(btnImprimir)
					.addContainerGap(219, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(label))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(comboProprietario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(radioDafIndividual)
								.addComponent(radioDafFrota)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(lblOpo)))
					.addGap(62)
					.addComponent(btnImprimir)
					.addContainerGap(69, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnImprimir){
			
			if(verificaRadio() == 0){
				System.out.println("individual teste");
				try {
					this.appLogica.listarSumarioDAFIndividual((String)this.comboProprietario.getSelectedItem());
				
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				
			}else{				
				try {
					
					this.appLogica.listarSumarioDAFFrota((String)this.comboProprietario.getSelectedItem());
				} catch (Exception e1) {
					System.out.println("imprimir erro");
					e1.printStackTrace();
				}
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
