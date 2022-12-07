package br.ucsal.util;

import java.util.Map;

import br.ucsal.model.TabelaSimbolos;

public class AnalisadorUtil {

	private static TabelaSimbolosUtil util = new TabelaSimbolosUtil();
	private static Map<String, String> tabelaReservada = util.getTabelaReservados();

	public String getTipoLexame(String lexame) {

		try {
			if (lexame.chars().allMatch(Character::isDigit)) {
				return "inteiro";
			} else if (Float.parseFloat(lexame) != (int) Float.parseFloat(lexame)) {
				return "flutuante";
			} else if (lexame.trim().equals("true") || lexame.trim().equals("false")) {
				return "booleano";
			} else if (lexame.chars().allMatch(Character::isLetterOrDigit)
					|| lexame.startsWith("\"") && lexame.endsWith("\"")) {
				return "string";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Tipo inconclusivo";
	}

	public boolean validaDigito(char atomo) {
		return atomo >= '0' && atomo <= '9';
	}

	public boolean validaCaracter(char atomo) {
		return (atomo >= 'a' && atomo <= 'z') || (atomo >= 'A' && atomo <= 'Z' || atomo == '.');
	}

	public boolean validaOperador(char atomo) {
		return atomo == '<' || atomo == '>' || atomo == '=' || atomo == '!' || atomo == '#' || atomo == ':';
	}

	public boolean validaEspaco(char atomo) {
		return atomo == ' ' || atomo == '\t' || atomo == '\n' || atomo == '\r';
	}

	public boolean validaOperadorMult(char atomo) {
		return atomo == '*' || atomo == '/' || atomo == '%';
	}

	public boolean validaOperadorSoma(char atomo) {
		return atomo == '+' || atomo == '-';
	}

	public boolean validaBloco(char atomo) {
		return atomo == '{' || atomo == '}' || atomo == '[' || atomo == ']' || atomo == '(' || atomo == ')';
	}

	public boolean validaAspas(char atomo) {
		return atomo == '"';
	}

	public boolean validaListaParam(char atomo) {
		return atomo == ',' || atomo == ';' || atomo == '?';
	}

	public boolean validaVariavel(char atomo) {
		return atomo == '_';
	}

	// valida final de linha e fim de arquivo
	public boolean eof(String line, int aux) {
		return line.length() == aux;
	}

	public boolean validaComentarioInicioLinha(char atomo, char proxAtomo) {
		return (atomo == '/' && proxAtomo == '/');
	}

	public boolean validaComentarioInicioBloco(char atomo, char proxAtomo) {
		return (atomo == '/' && proxAtomo == '*');
	}

	public boolean validaComentarioFimBloco(char atomo, char proxAtomo) {
		return (atomo == '*' && proxAtomo == '/');
	}

	public boolean validaPalavrasReservadasSeguidoDeFuncao(char atomo, StringBuffer lexame) {
		return tabelaReservada.containsValue(lexame.toString()) || validaBloco(atomo);
	}

	public boolean validaCaracterValidoSeguidoPorOperacao(char atomo, char proxAtomo) {
		if (!validaOperador(proxAtomo)) {
			return false;
		}
		return validaDigito(atomo) || validaCaracter(atomo) && validaOperador(proxAtomo);
	}

	public boolean validaCaracterValidoAposOperador(char atomo, char proxAtomo) {
		if (!validaOperador(atomo)) {
			return false;
		}
		return validaOperador(atomo) && validaCaracter(proxAtomo) || validaDigito(proxAtomo);
	}

	public String getCodAtomo(String lexame) {
		for (Map.Entry<String, String> entry : tabelaReservada.entrySet()) {
			if (entry.getValue().equals(lexame)) {
				return entry.getKey();
			}
		}
		return "0O2";
	}

	public String truncLexame(String lexame) {
		return lexame.substring(0, 29);
	}

	public void clearTabelaSimbolos() {
		util.clearTabelaSimbolos();
	}

	public void addLexame(String lexame, int numLinha, int posicao) {
		TabelaSimbolos lexameExistente = util.getLexame(lexame);
		String ocorrenciaLinha = numLinha + ":" + (posicao - lexame.length() + 1);
		if (lexameExistente == null) {
			String codAtomo = getCodAtomo(lexame);
			String tipo = getTipoLexame(lexame);
			Integer tamanhoAntesTrunc = lexame.length();
			Integer tamanhoDpsTruncLexame = null;
			if (tamanhoAntesTrunc > 30) {
				lexame = truncLexame(lexame);
				tamanhoDpsTruncLexame = lexame.length();
			}
			util.addLexame(codAtomo, lexame, tipo, ocorrenciaLinha, tamanhoAntesTrunc, tamanhoDpsTruncLexame);
		} else {
			util.updateLexame(ocorrenciaLinha, lexameExistente);
		}

	}
}