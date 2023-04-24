package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	public static Connection conectar() throws Exception {
		// carregando a classe com o drive de conexao com o mysql
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// parametros de conexao com o banco de dados
		return DriverManager.getConnection("jdbc:mysql://localhost/estoque", "root", "%ivoid138malta");
	}
}

