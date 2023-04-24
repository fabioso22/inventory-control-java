package classes;

public class Movimento {
	
	private Integer idmovimento;
	private String data;
	private Integer quant;
	private String tipo;
	private Produto produto;
	private Funcionario funcionario;
	
	public Movimento() {
	}

	public Movimento(Integer idmovimento, String data, Integer quant, String tipo, Produto produto,
			Funcionario funcionario) {
		super();
		this.idmovimento = idmovimento;
		this.data = data;
		this.quant = quant;
		this.tipo = tipo;
		this.produto = produto;
		this.funcionario = funcionario;
	}

	public Integer getIdmovimento() {
		return idmovimento;
	}

	public void setIdmovimento(Integer idmovimento) {
		this.idmovimento = idmovimento;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getQuant() {
		return quant;
	}

	public void setQuant(Integer quant) {
		this.quant = quant;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	
	
	

}