package veicular.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import veicular.logica.dominio.Veiculo;

public class TableModelos extends AbstractTableModel{

	public List<Veiculo> lista; //gente
	
	String colunas[] = {"Placa", "Fabricação", "Valor", "Proprietário"}; //embarcados
	
	public TableModelos(List<Veiculo> veiculos){
		this.lista = new ArrayList<Veiculo>(veiculos); 
	}
	
	public Veiculo getVeiculo(int indiceLinha){
		return lista.get(indiceLinha); 
	} 
			
			
	public void addVeiculo(Veiculo veiculo){
		this.lista.add(veiculo); 
		int ultimoIndice = getRowCount() - 1; 
		this.fireTableRowsInserted(ultimoIndice, ultimoIndice); 
	} 
	
	public void updateVeiculo(int indiceLinha, Veiculo veiculo){
		this.lista.set(indiceLinha, veiculo);
		this.fireTableRowsUpdated(indiceLinha, indiceLinha); 
	} 
			
	public void removeVeiculo(int indiceLinha) {
		this.lista.remove(indiceLinha); 
		this.fireTableRowsDeleted(indiceLinha, indiceLinha); 
	} 
	
	@Override
	public int getColumnCount() {		
		return colunas.length;
	}

	@Override
	public int getRowCount() {		
		return lista.size();
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex){ 
		return false; 
	} 
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String retorno = "";
		
		Veiculo veiculo = lista.get(rowIndex);
		switch (columnIndex) {
		case 0: retorno = veiculo.getPlaca();
		break;
		case 1: retorno = String.valueOf(veiculo.getAnoFabricacao());
		break;
		case 2: retorno = String.valueOf(veiculo.getValorCompra());
		break;
		case 3: retorno = String.valueOf(veiculo.getProprietario().getNome());
		}
		return retorno;
	}
	


}
