package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import classes.Funcionario;
import classes.Produto;
import util.Conexao;

public class ProdutoDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public ProdutoDao() throws Exception {
		con = Conexao.conectar();
	}

	// Incluir //

	public void incluir(Produto produto) throws Exception {
		if (produto == null) {
			throw new Exception("Objeto vazio");
		}
		try {

			String sql = "INSERT INTO produto (marca, nome, descricao, categoria, quant, funcionario_idfuncionario) VALUES (?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, produto.getMarca());
			ps.setString(2, produto.getNome());
			ps.setString(3, produto.getDescricao());
			ps.setString(4, produto.getCategoria());
			ps.setInt(5, produto.getQuant());
			ps.setInt(6, produto.getFuncionario().getIdfuncionario());

			ps.executeUpdate();
		} catch (Exception ex) {
			throw new Exception("Erro ao incluir dados" + ex);
		} finally {
			ps.close();
		}
	}

	// Excluir //

	public void excluir(Produto produto) throws Exception {
		if (produto == null) {
			throw new Exception("Objeto vazio");
		}
		try {
			String sql = "delete from produto where idproduto = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, produto.getIdproduto());
			ps.executeUpdate();
		} catch (Exception ex) {
			throw new Exception("erro ao excluir dados" + ex);
		} finally {
			ps.close();
		}

	}

	// Alterar //

	public void alterar(Produto produto) throws Exception {
		if (produto == null) {
			throw new Exception("Campos obrigatórios vazios");
		}
		try {

			String sql = "UPDATE produto SET marca = ?, nome = ?, descricao = ?, categoria = ?, quant = ?, funcionario_idfuncionario = ? where idproduto = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, produto.getMarca());
			ps.setString(2, produto.getNome());
			ps.setString(3, produto.getDescricao());
			ps.setString(4, produto.getCategoria());
			ps.setInt(5, produto.getQuant());
			ps.setInt(6, produto.getFuncionario().getIdfuncionario());
			ps.setInt(7, produto.getIdproduto());

			ps.executeUpdate();

		} finally {
			ps.close();
		}
	}

	// Buscar Um //

	public Produto buscarUm(Integer id) throws Exception {
		if (id == null) {
			throw new Exception("ID Vazio");
		}

		try {
			String sql = "select * from produto where idproduto = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new Exception("Nao foi encontrado registro");
			}
			String marca = rs.getString("marca");
			String nome = rs.getString("nome");
			String descricao = rs.getString("descricao");
			String categoria = rs.getString("categoria");
			Integer quant = rs.getInt("quant");
			Integer idfuncionario = rs.getInt("funcionario_idfuncionario");

			FuncionarioDao fd = new FuncionarioDao();
			Funcionario funcionario = fd.buscarUm(idfuncionario);

			return new Produto(id, marca, nome, descricao, categoria, quant, funcionario);
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			//ps.close();
			//rs.close();
		}
	}

	// Buscar Todos //

	public List<Produto> buscarTodos() throws Exception {
		try {
			String sql = "select * from produto";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Produto> lista = new ArrayList<Produto>();

			while (rs.next()) {
				Integer id = rs.getInt("idproduto");
				String nome = rs.getString("nome");
				String marca = rs.getString("marca");
				String desc = rs.getString("descricao");
				String cat = rs.getString("categoria");
				Integer quant = rs.getInt("quant");
				Integer idfunc = rs.getInt("funcionario_idfuncionario");

				FuncionarioDao fd = new FuncionarioDao();
				Funcionario funcionario = fd.buscarUm(idfunc);

				lista.add(new Produto(id, marca, nome, desc, cat, quant, funcionario));
			}
			return lista;

		} catch (Exception ex) {
			throw new Exception(ex);
		} finally {
			//ps.close();
			//rs.close();
		}
	}

	public List<Produto> buscarCompativeis(Integer id) throws Exception {
		try {
			String sql = "select * from compatibilidade where idproduto = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			List<Produto> lista = new ArrayList<Produto>();

			while (rs.next()) {
				Integer idprocomp = rs.getInt("idprodcomp");
				Produto produto = buscarUm(idprocomp);
				lista.add(produto);
			}
			return lista;

		} catch (Exception ex) {
			throw new Exception(ex);
		} finally {
			//ps.close();
			//rs.close();
		}
	}

	public List<Produto> consultarPersonalizada(String pista, String tipo) throws Exception {
		Connection con = Conexao.conectar();
		PreparedStatement ps;
		ResultSet rs;
		String sql;
		if (tipo.equals("Nome")) {
			sql = "select * from produto where nome like ?";
		} else if (tipo.equals("Categoria")) {
			sql = "select * from produto where categoria like ?";
		} else {
			sql = "select * from produto where marca like ?";
		}
		ps = con.prepareStatement(sql);
		ps.setString(1, "%" + pista + "%");
		rs = ps.executeQuery();
		List<Produto> lista = new ArrayList<Produto>();
		while (rs.next()) {
			Integer id = rs.getInt("idproduto");
			String nome = rs.getString("nome");
			String marca = rs.getString("marca");
			String desc = rs.getString("descricao");
			String cat = rs.getString("categoria");
			Integer quant = rs.getInt("quant");
			Integer idfunc = rs.getInt("funcionario_idfuncionario");
			FuncionarioDao fd = new FuncionarioDao();
			Funcionario funcionario = fd.buscarUm(idfunc);

			lista.add(new Produto(id, marca, nome, desc, cat, quant, funcionario));
		}
		return lista;
	}
}
