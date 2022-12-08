package br.ucsal.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.ucsal.model.TabelaSimbolos;

public class TabelaSimbolosUtil {

	private Map<String, String> listaTabelaReservados = new HashMap<>();
	private static List<TabelaSimbolos> listaTabelaSimbolos = new LinkedList<>();

	public List<TabelaSimbolos> getTabelaSimbolos() {
		return listaTabelaSimbolos;
	}

	public Map<String, String> getTabelaReservados() {
		if (this.getTabelaReservados().isEmpty()) {
			this.listaTabelaReservados.put("001", "programa");
			this.listaTabelaReservados.put("002", "declaracoes");
			this.listaTabelaReservados.put("0O3", "fim-declaracoes");
			this.listaTabelaReservados.put("0O4", "funcoes");
			this.listaTabelaReservados.put("0O5", "fim-funcoes");
			this.listaTabelaReservados.put("0O6", "fim-programa");
			this.listaTabelaReservados.put("0O7", "tipo-var");
			this.listaTabelaReservados.put("0O8", "vazio");
			this.listaTabelaReservados.put("0O9", "real");
			this.listaTabelaReservados.put("01O", "inteiro");
			this.listaTabelaReservados.put("011", "cadeia");
			this.listaTabelaReservados.put("012", "logico");
			this.listaTabelaReservados.put("013", "caracter");
			this.listaTabelaReservados.put("014", "tipo-func");
			this.listaTabelaReservados.put("015", "fim-func");
			this.listaTabelaReservados.put("016", "tipo-param");
			this.listaTabelaReservados.put("017", "se");
			this.listaTabelaReservados.put("018", "fim-se");
			this.listaTabelaReservados.put("019", "senao");
			this.listaTabelaReservados.put("020", "enquanto");
			this.listaTabelaReservados.put("021", "fim-enquanto");
			this.listaTabelaReservados.put("022", "retorna");
			this.listaTabelaReservados.put("023", "pausa");
			this.listaTabelaReservados.put("024", "imprime");
			this.listaTabelaReservados.put("025", "true");
			this.listaTabelaReservados.put("026", "false");
			this.listaTabelaReservados.put("101", ";");
			this.listaTabelaReservados.put("102", "[");
			this.listaTabelaReservados.put("103", "]");
			this.listaTabelaReservados.put("104", ":");
			this.listaTabelaReservados.put("105", ",");
			this.listaTabelaReservados.put("106", "(");
			this.listaTabelaReservados.put("107", ")");
			this.listaTabelaReservados.put("108", "?");
			this.listaTabelaReservados.put("109", "{");
			this.listaTabelaReservados.put("110", "}");
			this.listaTabelaReservados.put("111", "<=");
			this.listaTabelaReservados.put("112", "<");
			this.listaTabelaReservados.put("113", ">");
			this.listaTabelaReservados.put("114", ">=");
			this.listaTabelaReservados.put("115", "==");
			this.listaTabelaReservados.put("116", "!=");
			this.listaTabelaReservados.put("116.2", "#");
			this.listaTabelaReservados.put("117", "+");
			this.listaTabelaReservados.put("118", "-");
			this.listaTabelaReservados.put("119", "*");
			this.listaTabelaReservados.put("120", "/");
			this.listaTabelaReservados.put("121", "%");
			this.listaTabelaReservados.put("122", ":=");
			this.listaTabelaReservados.put("201", "nom-programa");
			this.listaTabelaReservados.put("202", "variavel");
			this.listaTabelaReservados.put("203", "nom-funcao");
			this.listaTabelaReservados.put("204", "cons-inteiro");
			this.listaTabelaReservados.put("205", "cons-real");
			this.listaTabelaReservados.put("206", "cons-cadeia");
			this.listaTabelaReservados.put("207", "cons-caracter");
		}
		return listaTabelaReservados;
	}

	public TabelaSimbolos getLexeme(String lexeme) {
		Optional<TabelaSimbolos> result = listaTabelaSimbolos.stream().parallel().filter(x -> x.lexeme.equals(lexeme))
				.findFirst();
		return result.isPresent() ? result.get() : null;
	}

	public void addLexeme(String codAtomo, String lexeme, String tipo, String ocorrenciaLinhas, Integer qtdAntesTrunc,
			Integer qtdDpsTrunc) {
		TabelaSimbolos elemento = new TabelaSimbolos(codAtomo, listaTabelaSimbolos.size(), lexeme, tipo,
				ocorrenciaLinhas, qtdAntesTrunc, qtdDpsTrunc);
		listaTabelaSimbolos.add(elemento);
	}

	public void updateLexeme(String posicao, TabelaSimbolos simbolosModel) {
		String linhasOcorrencia = simbolosModel.getOcorrenciaLinhas();
		linhasOcorrencia += ", " + posicao;
		simbolosModel.setOcorrenciaLinhas(linhasOcorrencia);
	}
}
