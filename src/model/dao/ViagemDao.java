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
import model.entidades.Viagem;

/**
 *
 * @author Clony
 */
public class ViagemDao extends Dao implements DaoI<Viagem> {

    @Override
    public List<Viagem> listar() {
        String sql = "SELECT "
                + "v.id , v.destino, v.km_inicial, v.km_final, (v.km_final-v.km_inicial) as distancia, c.id, c.modelo, c.placa, c.km_atual "
                + "FROM "
                + "viagem v "
                + "INNER JOIN "
                + "carro c ON c.id = v.carro_id "
                + "WHERE "
                + "v.ativo = c.ativo "
                + "ORDER BY "
                + "v.id DESC ";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();
            List<Viagem> list = new ArrayList<>();
            while (res.next()) {
                Viagem v = new Viagem();
                v.setId(res.getInt("id"));
                v.setDestino(res.getString("destino"));
                v.setKmInicial(res.getFloat("km_inicial"));
                v.setKmFinal(res.getFloat("km_final"));
                v.setDistancia(res.getFloat("distancia"));
                Carro c = new Carro();
                c.setId(res.getInt("c.id"));
                c.setModelo(res.getString("modelo"));
                c.setPlaca(res.getString("placa"));
                c.setKilometroAtual(res.getFloat("km_atual"));
                v.setCarro(c);
                list.add(v);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int cadastrar(Viagem obj) {
        String sql = "INSERT INTO viagem(destino, km_inicial, km_final, carro_id) VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getDestino());
            stmt.setFloat(2, obj.getKmInicial());
            stmt.setFloat(3, obj.getKmFinal());
            stmt.setFloat(4, obj.getCarro().getId());
            ResultSet res;
            if (stmt.executeUpdate() > 0) {
                res = stmt.getGeneratedKeys();
                res.next();
                return res.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    @Override
    public boolean alterar(Viagem obj) {
        String sql = "UPDATE "
                + "viagem "
                + "SET "
                + "destino = ?, "
                + "km_inicial = ?, "
                + "km_final = ?, "
                + "carro_id = ? "
                + "WHERE "
                + "id = ? ";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getDestino());
            stmt.setFloat(2, obj.getKmInicial());
            stmt.setFloat(2, obj.getKmFinal());
            stmt.setInt(4, obj.getCarro().getId());
            stmt.setInt(5, obj.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        String sql = "UPDATE viagem SET ativo = 0 WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public Viagem lerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Viagem> pesquisar(String termo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
