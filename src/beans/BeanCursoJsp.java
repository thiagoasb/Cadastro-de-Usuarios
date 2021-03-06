package beans;

public class BeanCursoJsp {
	
	private Long id;
	private String login;
	private String senha;
	private String nome;
	private String email;
	private String fone;
	private String cep;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String ibge;
	private String fotoBase64;
	private String fotoBase64Miniatura;
	private String contentType;
	private String curriculoBase64;
	private String contentTypeCurriculo;
	private boolean ativo;
	private String sexo;
	private String perfil;
	//////////////IMAGEM//////////////
	private String temporarioFotoUser;
	private boolean atualizarImage = true;
	private boolean atualizarPdf = true;
	
	public String getTemporarioFotoUser() {
		
		temporarioFotoUser = "data:" + contentType + ";base64," + fotoBase64;
		return temporarioFotoUser;
	}
	public boolean isAtualizarImage() {
		return atualizarImage;
	}
	public void setAtualizarImage(boolean atualizarImage) {
		this.atualizarImage = atualizarImage;
	}
	public boolean isAtualizarPdf() {
		return atualizarPdf;
	}
	public void setAtualizarPdf(boolean atualizarPdf) {
		this.atualizarPdf = atualizarPdf;
	}
	///////////////////////////////////////////////
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getBairro() {
		return bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getIbge() {
		return ibge;
	}
	public void setIbge(String ibge) {
		this.ibge = ibge;
	}
	public String getFotoBase64() {
		return fotoBase64;
	}
	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public void setCurriculoBase64(String curriculoBase64) {
		this.curriculoBase64 = curriculoBase64;
	}
	public String getCurriculoBase64() {
		return curriculoBase64;
	}
	public void setContentTypeCurriculo(String contentTypeCurriculo) {
		this.contentTypeCurriculo = contentTypeCurriculo;
	}
	public String getContentTypeCurriculo() {
		return contentTypeCurriculo;
	}
	public String getFotoBase64Miniatura() {
		return fotoBase64Miniatura;
	}
	public void setFotoBase64Miniatura(String fotoBase64Miniatura) {
		this.fotoBase64Miniatura = fotoBase64Miniatura;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getPerfil() {
		return perfil;
	}
	
}
