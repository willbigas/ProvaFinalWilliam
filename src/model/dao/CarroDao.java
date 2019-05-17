/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import factory.Dao;
import interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.entidades.Carro;

/**
 *
 * @author Clony
 */
public class CarroDao extends Dao implements DaoI<Carro> {

    public CarroDao() {
        super();
    }

    @Override
    public List<Carro> listar() {
        String sql = "SELECT id, modelo , km_atual, placa , ativo FROM carro ORDER BY id ASC ";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();
            List<Carro> list = new ArrayList<>();
            while (res.next()) {
                Carro c = new Carro();
                c.setId(res.getInt("id"));
                c.setModelo(res.getString("modelo"));
                c.setKilometroAtual(res.getFloat("km_atual"));
                c.setPlaca(res.getString("placa"));
                res.getInt("ativo");
                list.add(c);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public boolean atualizarKmAtual(float distancia, int carro_id) {
        String sql = "UPDATE carro SET km_atual = km_atual + ? WHERE id = ?";
         try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setFloat(1, distancia);
            stmt.setInt(2, carro_id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }        
        
        
    }

    @Override
    public int cadastrar(Carro obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean alterar(Carro obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Carro lerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Carro> pesquisar(String termo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
