package classes;

public class Funcionario {

	private Integer idfuncionario;
	private String login;
	private String senha;
	private String nome;
	private Boolean ativo;
	private static Funcionario funcionario;
	
	public static Funcionario getInstance() {
		if(funcionario == null) {
			funcionario = new Funcionario();
		}
		return funcionario;
	}

	public Funcionario() {

	}

	public Funcionario(Integer idfuncionario, String login, String senha, String nome, Boolean ativo) {
		this.idfuncionario = idfuncionario;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.ativo = ativo;
	}

	public Integer getIdfuncionario() {
		return idfuncionario;
	}

	public void setIdfuncionario(Integer idfuncionario) {
		this.idfuncionario = idfuncionario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
