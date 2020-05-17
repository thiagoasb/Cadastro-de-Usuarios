package beans;

public class ProdutoBean {
	
	private Long id;
	private String nome;
	private Long quantidade;
	private Double valor;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		valor = valor;
	}
	public String getValorEmTexto() {
		return Double.toString(valor).replace('.',',');
	}
	
	
	
	
	
	
}
