package br.com.alura.gerenciador.modelo;

public class Empresa {

	private Integer id;
	private String nome;
	private String municipio;
//	private Calendar dataAbertura;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

//	public Calendar getDataAbertura() {
//		return dataAbertura;
//	}
//
//	public void setDataAbertura(Calendar dataAbertura) {
//		this.dataAbertura = dataAbertura;
//	}
}
