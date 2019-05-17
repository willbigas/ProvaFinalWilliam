package factory;


import factory.Conexao;
import java.sql.Connection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Alunos
 */
public class Dao {
    protected Connection conexao;

    public Dao() {
        conexao = Conexao.getConexao();
    }
    
}
