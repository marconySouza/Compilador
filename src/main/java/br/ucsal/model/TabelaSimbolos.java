package br.ucsal.model;

public class TabelaSimbolos {

	public String idAtomo;
	public Integer numEntrada;
	public String lexame;
	public String tipo;
	public String ocorrenciaLinha;
	public Integer QtdCaracAntesTrunc;
	public Integer QtdCaracDepoisTrunc;

	public TabelaSimbolos() {
	}

	public TabelaSimbolos(String idAtomo, Integer numEntrada, String lexame, String tipo, String ocorrenciaLinha,
			Integer qtdCaracAntesTrunc, Integer qtdCaracDepoisTrunc) {
		super();
		this.idAtomo = idAtomo;
		this.numEntrada = numEntrada;
		this.lexame = lexame;
		this.tipo = tipo;
		this.ocorrenciaLinha = ocorrenciaLinha;
		QtdCaracAntesTrunc = qtdCaracAntesTrunc;
		QtdCaracDepoisTrunc = qtdCaracDepoisTrunc;
	}

	public String getIdAtomo() {
		return idAtomo;
	}

	public void setIdAtomo(String idAtomo) {
		this.idAtomo = idAtomo;
	}

	public Integer getNumEntrada() {
		return numEntrada;
	}

	public void setNumEntrada(Integer numEntrada) {
		this.numEntrada = numEntrada;
	}

	public String getLexame() {
		return lexame;
	}

	public void setLexame(String lexame) {
		this.lexame = lexame;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getOcorrenciaLinha() {
		return ocorrenciaLinha;
	}

	public void setOcorrenciaLinha(String ocorrenciaLinha) {
		this.ocorrenciaLinha = ocorrenciaLinha;
	}

	public Integer getQtdCaracAntesTrunc() {
		return QtdCaracAntesTrunc;
	}

	public void setQtdCaracAntesTrunc(Integer qtdCaracAntesTrunc) {
		QtdCaracAntesTrunc = qtdCaracAntesTrunc;
	}

	public Integer getQtdCaracDepoisTrunc() {
		return QtdCaracDepoisTrunc;
	}

	public void setQtdCaracDepoisTrunc(Integer qtdCaracDepoisTrunc) {
		QtdCaracDepoisTrunc = qtdCaracDepoisTrunc;
	}
}
