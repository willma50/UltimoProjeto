package veicular.ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import veicular.logica.app.ProprietarioLogica;
import veicular.logica.app.ProprietarioLogicaIF;
import veicular.logica.app.VeiculoLogicaIF;
import veicular.logica.dominio.DAFIndividual;

public class JImprimirDAFNormalUI extends JFrame implements VeiculoUIIF, ActionListener{

	private JPanel contentPane;
	private VeiculoLogicaIF appLogica;
	private ProprietarioLogicaIF appLogicaPropietario;
	private JComboBox comboProprietario;
	private final TextArea textArea = new TextArea();
    private JButton button;
	
	public JImprimirDAFNormalUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		comboProprietario = new JComboBox();
		
		JLabel label = new JLabel("Propriet\u00E1rio");
		
		button = new JButton("Imprimir");
		
		//JTextPane textPane = new JTextPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(comboProprietario, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(175)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(textArea, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(label))
						.addComponent(comboProprietario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(button)
					.addGap(39)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
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

	@Override
	public void actionPerformed(ActionEvent e) {
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
		
	}
}
