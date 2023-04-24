package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import classes.Funcionario;
import classes.Movimento;
import classes.Produto;
import util.Conexao;

public class MovimentoDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	// CONSTUTOR //
	public MovimentoDao() throws Exception {
		con = Conexao.conectar();
	}
	
	// MOVIMENTO //

	public void incluir(Movimento movimento) throws Exception {
		if (movimento == null) {
			throw new Exception("Objeto Vazio");
		}

		try {
			String mov = "insert into movimento(data, quant, tipo, produto_idproduto, funcionario_idfuncionario) values (?,?,?,?,?)";
			ps = con.prepareStatement(mov);
			ps.setString(1, movimento.getData());
			ps.setInt(2, movimento.getQuant());
			ps.setString(3, movimento.getTipo());
			ps.setInt(4, movimento.getProduto().getIdproduto());
			ps.setInt(5, movimento.getFuncionario().getIdfuncionario());
			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception("Erro ao modificar dados " + e);

		} finally {
			ps.close();
		}
	}

	// BUSCAR UM //

	public Movimento buscarUm(Integer id) throws Exception {
		if (id == null) {
			throw new Exception("ID Vazio");
		}

		try {
			String sql = "select * from movimento where idmovimento = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new Exception("Nao foi encontrado registro");
			}
			String data = rs.getString("data");
			Integer quant = rs.getInt("quant");
			String tipo = rs.getString("tipo");
			Integer idproduto = rs.getInt("produto_idproduto");
			Integer idfuncionario = rs.getInt("funcionario_idfuncionario");

			ProdutoDao pd = new ProdutoDao();
			Produto produto = pd.buscarUm(idproduto); // buscando um curso pelo id

			FuncionarioDao fd = new FuncionarioDao();
			Funcionario funcionario = fd.buscarUm(idfuncionario);

			return new Movimento(id, data, quant, tipo, produto, funcionario);

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			ps.close();
			rs.close();
		}

	}
	// Buscar Todos //
	
	public List<Movimento> buscarTodos() throws Exception {
		try {
			String sql = "select * from movimento";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Movimento> lista = new ArrayList<Movimento>(); // Criando uma lista de cursos

			while (rs.next()) {
				Integer id = rs.getInt("idmovimento");
				String data = rs.getString("data");
				Integer quant = rs.getInt("quant");
				String tipo = rs.getString("tipo");
				Integer idproduto = rs.getInt("produto_idproduto");
				Integer idfuncionario = rs.getInt("funcionario_idfuncionario");

				ProdutoDao pd = new ProdutoDao();
				Produto produto = pd.buscarUm(idproduto);

				FuncionarioDao fd = new FuncionarioDao();
				Funcionario funcionario = fd.buscarUm(idfuncionario);

				lista.add(new Movimento(id, data, quant, tipo, produto, funcionario));

			}
			return lista;
		} catch (Exception e) {
			throw new Exception(e);

		} finally {
			ps.close();
			rs.close();

		}
	}
}
