package br.ucsal.model;

public class TabelaSimbolos {

	public String idAtomo;
	public Integer numEntrada;
	public String lexame;
	public String tipo;
	public String ocorrenciaLinhas;
	public Integer qtdCaracAntesTrunc;
	public Integer qtdCaracDpsTrunc;

	public TabelaSimbolos() {
	}

	public TabelaSimbolos(String idAtomo, Integer numEntrada, String lexame, String tipo, String ocorrenciaLinhas,
			Integer qtdCaracAntesTrunc, Integer qtdCaracDpsTrunc) {
		super();
		this.idAtomo = idAtomo;
		this.numEntrada = numEntrada;
		this.lexame = lexame;
		this.tipo = tipo;
		this.ocorrenciaLinhas = ocorrenciaLinhas;
		this.qtdCaracAntesTrunc = qtdCaracAntesTrunc;
		this.qtdCaracDpsTrunc = qtdCaracDpsTrunc;
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

	public String getOcorrenciaLinhas() {
		return ocorrenciaLinhas;
	}

	public void setOcorrenciaLinhas(String ocorrenciaLinhas) {
		this.ocorrenciaLinhas = ocorrenciaLinhas;
	}

	public Integer getQtdCaracAntesTrunc() {
		return qtdCaracAntesTrunc;
	}

	public void setQtdCaracAntesTrunc(Integer qtdCaracAntesTrunc) {
		this.qtdCaracAntesTrunc = qtdCaracAntesTrunc;
	}

	public Integer getQtdCaracDpsTrunc() {
		return qtdCaracDpsTrunc;
	}

	public void setQtdCaracDpsTrunc(Integer qtdCaracDpsTrunc) {
		this.qtdCaracDpsTrunc = qtdCaracDpsTrunc;
	}
}
