package veicular.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import veicular.logica.app.ProprietarioLogica;
import veicular.logica.app.ProprietarioLogicaIF;
import veicular.logica.app.VeiculoLogicaIF;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class JDadosEstatisticosUI extends JFrame implements VeiculoUIIF, ActionListener{

	private JPanel contentPane;
	private VeiculoLogicaIF appLogica;
	private ProprietarioLogicaIF appLogicaPropietario;
	JComboBox comboProprietario;
	private JTextField textValorMedio;
	private JTextField textValorTotal;
	private JButton btnCalcular;
	
	public JDadosEstatisticosUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Propriet\u00E1rio");
		
		comboProprietario = new JComboBox();
		
		btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(this);
		
		textValorMedio = new JTextField();
		textValorMedio.setColumns(10);
		
		textValorTotal = new JTextField();
		textValorTotal.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addComponent(btnCalcular, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
					.addGap(62))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboProprietario, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(137)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textValorMedio)
						.addComponent(textValorTotal, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(150, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(comboProprietario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(44)
					.addComponent(textValorMedio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textValorTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
					.addComponent(btnCalcular)
					.addGap(47))
		);
		contentPane.setLayout(gl_contentPane);
		this.textValorMedio.setEditable(false);
		this.textValorTotal.setEditable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			this.textValorMedio.setText(String.valueOf(this.appLogica.valorMedioImpostoFrota((String)this.comboProprietario.getSelectedItem())));		
			this.textValorTotal.setText(String.valueOf(this.appLogica.valorTotalImpostoFrota((String)this.comboProprietario.getSelectedItem())));
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
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
