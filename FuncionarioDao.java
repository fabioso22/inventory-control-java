package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import classes.Funcionario;
import util.Conexao;

public class FuncionarioDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public FuncionarioDao() throws Exception {
		con = Conexao.conectar();
	}

	// INCLUIR //
	
	public void incluir(Funcionario funcionario) throws Exception {
		if (funcionario == null) {
			throw new Exception("Objeto vazio");
		}
		
		try {
			String sql = " INSERT INTO funcionario (login, senha, nome, ativo) VALUES (?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, funcionario.getLogin());
			ps.setString(2, funcionario.getSenha());
			ps.setString(3, funcionario.getNome());
			ps.setBoolean(4, funcionario.getAtivo());
			ps.executeUpdate();
			
		} catch (Exception ex) {
			throw new Exception("Erro ao incluir dados" + ex);
		} finally {
			ps.close();
		}
	}

	// BUSCAR UM //

	public Funcionario buscarUm(Integer id) throws Exception {
		if (id == null) {
			throw new Exception("ID Vazio");
		}

		try {
			String sql = "select * from funcionario where idfuncionario = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new Exception("Nao foi encontrado registro");
			}
			String login = rs.getString("login");
			String nome = rs.getString("nome");
			String senha = rs.getString("senha");
			Boolean ativo = rs.getBoolean("ativo");

			return new Funcionario(id, nome, login, senha, ativo);

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			ps.close();
			rs.close();
		}
	}

	// FAZER LOGIN //

	public Boolean validarFuncionario(String login, String senha) throws Exception {
		if (login == null) {
			throw new Exception("Objeto Vazio");
		}
		try {
			String sql = "select * from funcionario where login = ? and senha = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, senha);
				
			rs = ps.executeQuery();

			if (rs.next()) {
				Funcionario funcionario = Funcionario.getInstance();
				funcionario.setLogin(rs.getString("login"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setIdfuncionario(rs.getInt("idfuncionario"));
				return true;
			} else {
				return false;
			}
		} catch (Exception a) {
			throw new Exception("Erro ao validar dados " + a);

		} finally {
			ps.close();
			rs.close();
		}
	}
	
	
	

}
