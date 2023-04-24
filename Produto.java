package classes;

public class Produto {
	private Integer idproduto;
	private String marca;
	private String nome;
	private String descricao;
	private String categoria;
	private Integer quant;
	private Funcionario funcionario;
	
	public Produto() {
	}
	
	public Produto(Integer idproduto, String marca, String nome, String descricao, String categoria, Integer quant,
			Funcionario funcionario) {
		this.idproduto = idproduto;
		this.marca = marca;
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
		this.quant = quant;
		this.funcionario = funcionario;
	}

	public Integer getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(Integer idproduto) {
		this.idproduto = idproduto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getQuant() {
		return quant;
	}

	public void setQuant(Integer quant) {
		this.quant = quant;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	
	
}

	