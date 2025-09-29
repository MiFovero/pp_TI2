package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    protected Connection conexao;

    public boolean conectar() {
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "teste";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "ti2cc";
        String password = "ti@cc";

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão efetuada com o Postgres!");
            return true;
        } catch (ClassNotFoundException e) {
            System.err.println("Driver não encontrado: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            System.err.println("Erro de conexão: " + e.getMessage());
            return false;
        }
    }

    public boolean close() {
        try {
            if (conexao != null) conexao.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao fechar: " + e.getMessage());
            return false;
        }
    }
}
