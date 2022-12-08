package br.ucsal.util;

import java.util.Map;

import br.ucsal.model.TabelaSimbolos;

public class AnalisadorUtil {

	private static TabelaSimbolosUtil util = new TabelaSimbolosUtil();

	public String getTipoLexeme(String lexeme) {

		try {
			if (lexeme.chars().allMatch(Character::isDigit)) {
				return "inteiro";
			} else if (lexeme.chars().allMatch(Character::isLetterOrDigit)
					|| lexeme.startsWith("\"") && lexeme.endsWith("\"")) {
				return "string";
			} else if (lexeme.trim().equals("true") || lexeme.trim().equals("false")) {
				return "booleano";
			} else if (Float.parseFloat(lexeme) != (int) Float.parseFloat(lexeme)) {
				return "flutuante";
			}

		} catch (Exception e) {
		}

		return "operador";
	}

	public boolean validaNumero(char atomo) {
		return atomo >= '0' && atomo <= '9';
	}

	public boolean validaCaracter(char atomo) {
		return (atomo >= 'a' && atomo <= 'z') || (atomo >= 'A' && atomo <= 'Z' || atomo == '.');
	}

	public boolean validaOperador(char atomo) {
		return atomo == '<' || atomo == '>' || atomo == '=' || atomo == '!' || atomo == '#' || atomo == ':';
	}

	public boolean validaOperadorMult(char atomo) {
		return atomo == '*' || atomo == '/' || atomo == '%';
	}

	public boolean validaSomaSubtracao(char atomo) {
		return atomo == '+' || atomo == '-';
	}

	public boolean validaBloco(char atomo) {
		return atomo == '{' || atomo == '}' || atomo == '[' || atomo == ']' || atomo == '(' || atomo == ')';
	}

	public boolean validaEspaco(char atomo) {
		return atomo == ' ' || atomo == '\t' || atomo == '\n' || atomo == '\r';
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

	public boolean validaAspas(char atomo) {
		return atomo == '"';
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

	public boolean validaPalavrasReservadasSeguidoDeFuncao(char atomo, StringBuffer lexeme, TabelaSimbolosUtil util) {
		return util.getTabelaReservados().containsValue(lexeme.toString()) || validaBloco(atomo);
	}

	public String getCodAtomo(String atomo, TabelaSimbolosUtil util) {
		for (Map.Entry<String, String> entry : util.getTabelaReservados().entrySet()) {
			if (entry.getValue().equals(atomo)) {
				return entry.getKey();
			}
		}
		return "0O2";
	}

	public boolean validaCaracterValidoSeguidoPorOperacao(char atomo, char proxAtomo) {
		if (!validaOperador(proxAtomo)) {
			return false;
		}
		return validaNumero(atomo) || validaCaracter(atomo) && validaOperador(proxAtomo);
	}

	public boolean validaCaracterValidoAposOperador(char atomo, char proxAtomo) {
		if (!validaOperador(atomo)) {
			return false;
		}
		return validaOperador(atomo) && validaCaracter(proxAtomo) || validaNumero(proxAtomo);
	}

	public void addLexeme(String lexeme, int numLinha, int posicao, TabelaSimbolosUtil util) {
		TabelaSimbolos lexemeExistente = util.getLexeme(lexeme);
		String ocorrenciaLinha = numLinha + ":" + (posicao - lexeme.length() + 1);
		if (lexemeExistente == null) {
			String codAtomo = getCodAtomo(lexeme, util);
			String tipo = getTipoLexeme(lexeme);
			Integer tamanhoAntesTrunc = lexeme.length();
			Integer tamanhoDpsTruncLexeme = null;
			if (tamanhoAntesTrunc > 30) {
				lexeme = truncLexeme(lexeme);
				tamanhoDpsTruncLexeme = lexeme.length();
			}
			//Validando se o atomo é #, caso seja, entende-se que # é o mesmo que !=, portanto deve agregar no codigo 116 
			codAtomo = codAtomo.equals("116.2") ? "116" : codAtomo;
			util.addLexeme(codAtomo, lexeme, tipo, ocorrenciaLinha, tamanhoAntesTrunc, tamanhoDpsTruncLexeme);
		} else {
			util.updateLexeme(ocorrenciaLinha, lexemeExistente);
		}

	}
	
	public void cleanTabelaSimbolos() {
		util.getTabelaSimbolos().clear();
	}

	public String truncLexeme(String lexeme) {
		return lexeme.substring(0, 29);
	}

	public static TabelaSimbolosUtil getUtil() {
		return util;
	}

	public static void setUtil(TabelaSimbolosUtil util) {
		AnalisadorUtil.util = util;
	}
}