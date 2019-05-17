/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Alunos
 * @param <T>
 */
public interface TableModelI<T> {
   public T getRow(int row);
   public void addRow(T c);
   public void removeRow(int row);
   public void updateRow(int row, T c);
}
