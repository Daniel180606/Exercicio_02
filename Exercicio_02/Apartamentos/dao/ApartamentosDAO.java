package dao;

import model.Apartamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartamentosDAO extends DAO {
    
    public boolean insert(Apartamento apartamento) {
        String sql = "INSERT INTO apartamentos (numero, andar, quartos, metragem) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, apartamento.getNumero());
            stmt.setInt(2, apartamento.getAndar());
            stmt.setInt(3, apartamento.getQuartos());
            stmt.setDouble(4, apartamento.getMetragem());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir apartamento: " + e.getMessage());
            return false;
        }
    }
    
    public List<Apartamento> getAll() {
        List<Apartamento> apartamentos = new ArrayList<>();
        String sql = "SELECT * FROM apartamentos";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Apartamento apartamento = new Apartamento(
                    rs.getInt("numero"),
                    rs.getInt("andar"),
                    rs.getInt("quartos"),
                    rs.getDouble("metragem")
                );
                apartamentos.add(apartamento);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar apartamentos: " + e.getMessage());
        }
        return apartamentos;
    }
    
    public Apartamento getByNumero(int numero) {
        String sql = "SELECT * FROM apartamentos WHERE numero = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Apartamento(
                        rs.getInt("numero"),
                        rs.getInt("andar"),
                        rs.getInt("quartos"),
                        rs.getDouble("metragem")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar apartamento por número: " + e.getMessage());
        }
        return null;
    }

    public boolean update(Apartamento apartamento) {
        String sql = "UPDATE apartamentos SET andar = ?, quartos = ?, metragem = ? WHERE numero = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, apartamento.getAndar());
            stmt.setInt(2, apartamento.getQuartos());
            stmt.setDouble(3, apartamento.getMetragem());
            stmt.setInt(4, apartamento.getNumero());
            return stmt.executeUpdate() > 0; 
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar apartamento: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int numero) {
        String sql = "DELETE FROM apartamentos WHERE numero = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir apartamento: " + e.getMessage());
            return false;
        }
    }
}
