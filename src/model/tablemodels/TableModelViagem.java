/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tablemodels;

import interfaces.TableModelI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entidades.Viagem;

/**
 *
 * @author Clony
 */
public class TableModelViagem extends AbstractTableModel implements TableModelI<Viagem>{
    
    private List<Viagem> listViagem = new ArrayList<>();

    static class Constantes{
        private static final String[] colunas = {"Código", "Destino", "Km inicial" , "Km Final" , "Carro" , "Distancia"};
        private static final int CODIGO = 0;
        private static final int DESTINO = 1;
        private static final int KM_INICIAL = 2;
        private static final int KM_FINAL = 3;
        private static final int CARRO = 4;
        private static final int DISTANCIA = 5;
    }
    
    @Override
    public int getRowCount() {
        return listViagem.size();
    }

    @Override
    public int getColumnCount() {
        return Constantes.colunas.length;
    }

     /**
     * Retorna o Nome das colunas.
     *
     * @param columnIndex
     * @return
     */
    @Override
    public String getColumnName(int columnIndex) {
        return Constantes.colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case Constantes.CODIGO:
                return Integer.class;
            case Constantes.DESTINO:
                return String.class;
            case Constantes.KM_INICIAL:
                return Float.class;
            case Constantes.KM_FINAL:
                return Float.class;
            case Constantes.CARRO:
                return String.class;
            case Constantes.DISTANCIA:
                return Float.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    /**
     * Pega o Valor de uma Coluna/Linha da Tabela
     *
     * @param linha
     * @param coluna
     * @return
     */
    @Override
    public Object getValueAt(int linha, int coluna) {
        Viagem viagem = listViagem.get(linha);
        switch (coluna) {
            case Constantes.CODIGO:
                return viagem.getId();
            case Constantes.DESTINO:
                return viagem.getDestino();
            case Constantes.KM_INICIAL:
                return viagem.getKmInicial();
            case Constantes.KM_FINAL:
                return viagem.getKmFinal();
            case Constantes.CARRO:
                return viagem.getCarro().getModelo();
            case Constantes.DISTANCIA:
                return viagem.getDistancia();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    /**
     * Altera Valor de uma Coluna / Linha da tabela
     *
     * @param valor
     * @param linha
     * @param coluna
     */
    @Override
public void setValueAt(Object valor, int linha, int coluna) {
        Viagem viagem = listViagem.get(linha);
        switch (coluna) {
            case Constantes.CODIGO:
                viagem.setId(Integer.valueOf((String) valor));
                break;
            case Constantes.DESTINO:
                viagem.setDestino((String) valor);
                break;
            case Constantes.KM_INICIAL:
                viagem.setKmInicial((Float) valor);
                break;
            case Constantes.KM_FINAL:
                viagem.setKmInicial((Float) valor);
                break;
            case Constantes.CARRO:
                viagem.getCarro().setModelo((String) valor);
                break;
            case Constantes.DISTANCIA:
                viagem.setDistancia((Float) valor);
                break;
            default:
                // Não deve ocorrer, pois só existem 4 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    @Override
    public Viagem getRow(int indiceLinha) {
        return listViagem.get(indiceLinha);
    }

    @Override
    public void addRow(Viagem obj) {
        listViagem.add(obj);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void removeRow(int indiceLinha) {
        listViagem.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha); // atualiza delete
    }
    
    
    @Override
    public void updateRow(int indiceLinha, Viagem obj) {
        listViagem.set(indiceLinha , obj);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    public void adicionar(List<Viagem> viagens) {
        int indice = getRowCount();
        listViagem.addAll(viagens);
        fireTableRowsInserted(indice, indice + viagens.size());
        fireTableDataChanged();
    }

    public void limpar() {
        listViagem.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

    
  
}