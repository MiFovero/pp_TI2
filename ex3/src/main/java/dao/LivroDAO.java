package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Livro;

public class LivroDAO extends DAO {

    public boolean insert(Livro l) {
        String sql = "INSERT INTO livro (id, titulo, autor, status) VALUES (?, ?, ?, ?)";
        try {
            conectar();
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, l.getId());
            st.setString(2, l.getTitulo());
            st.setString(3, l.getAutor());
            st.setString(4, String.valueOf(l.getStatus()));
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro INSERT: " + e.getMessage());
            return false;
        } finally {
            close();
        }
    }

    public boolean update(Livro l) {
        String sql = "UPDATE livro SET titulo=?, autor=?, status=? WHERE id=?";
        try {
            conectar();
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, l.getTitulo());
            st.setString(2, l.getAutor());
            st.setString(3, String.valueOf(l.getStatus()));
            st.setInt(4, l.getId());
            int rows = st.executeUpdate();
            st.close();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Erro UPDATE: " + e.getMessage());
            return false;
        } finally {
            close();
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM livro WHERE id=?";
        try {
            conectar();
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, id);
            int rows = st.executeUpdate();
            st.close();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Erro DELETE: " + e.getMessage());
            return false;
        } finally {
            close();
        }
    }

    public List<Livro> getAll() {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT * FROM livro ORDER BY id";
        try {
            conectar();
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lista.add(new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("status").charAt(0)
                ));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.err.println("Erro SELECT: " + e.getMessage());
        } finally {
            close();
        }
        return lista;
    }

    public Livro getById(int id) {
        Livro l = null;
        String sql = "SELECT * FROM livro WHERE id=?";
        try {
            conectar();
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                l = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("status").charAt(0)
                );
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.err.println("Erro SELECT by ID: " + e.getMessage());
        } finally {
            close();
        }
        return l;
    }
}
