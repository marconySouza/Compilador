package br.ucsal.service;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import br.ucsal.util.AnalisadorUtil;
import br.ucsal.util.PlotArquivos;

public class AnalisadorLex {

	private static AnalisadorUtil analisador = new AnalisadorUtil();
	private static char[] atomos;

	public static void main(String[] args) {

		/*
		 * TODO adicionar aqui a interação com o usuário
		 * 
		 * ENTRADA:
		 * Caso seja fornecido apenas o nome do arquivo, este deve ser procurado no
		 * diretório corrente onde o AnalisadorLexico.exe está sendo executado. Caso
		 * seja fornecido o caminho completo mais o nome do arquivo como parâmetro de
		 * entrada, o arquivo deve ser procurado neste caminho indicado na entrada.
		 * 
		 * 
		 * SAÍDA:
		 * deverão ser gerados obrigatoriamente dois arquivos de saída em
		 * separado na mesma pasta onde o texto fonte parâmetro se encontra: .TAB e .LEX
		 */

		String caminhoArquivoLido = "";//Arquivo de texto fonte inserido pelo usuário
		try {
			analise(caminhoArquivoLido);//Metodo de analise lexica passando como parametro o caminho do arquivo de entrada
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void analise(String caminhoArquivoEntrada) throws IOException {
		Scanner sc = new Scanner(new File(caminhoArquivoEntrada + ".222"));
		int numLinha = 0;
		boolean filtrarComentarioBloco = true;
		boolean filtrarAspas = false;

		// Loop de iteração no texto fonte
		while (sc.hasNextLine()) {
			int posicao = 0;
			numLinha++;
			String line = sc.nextLine();
			String auxString;
			atomos = line.toCharArray();
			char atomo;
			char proxAtomo = 0;
			StringBuffer aux = new StringBuffer();
			StringBuffer auxAspas = new StringBuffer();

			// Loop de iteração nos atomos do texto fonte
			while (posicao < atomos.length) {
				atomo = atomos[posicao];
				try {
					proxAtomo = atomos[posicao + 1];
				} catch (Exception e) {
					e.printStackTrace();
				}

				// Filtragem de comentários
				if (filtrarComentarioBloco) {
					if (analisador.validaComentarioFimBloco(atomo, proxAtomo)) {
						filtrarComentarioBloco = false;
						posicao++;
						aux.delete(0, aux.length());
					}
					posicao++;
					continue;
				} else {
					if (analisador.validaComentarioInicioLinha(atomo, proxAtomo)) {
						break;
					}
					if (analisador.validaComentarioInicioBloco(atomo, proxAtomo)) {
						filtrarComentarioBloco = true;
						continue;
					}
				}

				// Filtragem de aspas
				if (filtrarAspas) {
					if (analisador.validaAspas(atomo)) {
						filtrarAspas = false;
					}
					auxAspas.append(atomo);
					posicao++;
					continue;
				} else {
					if (analisador.validaAspas(atomo)) {
						auxAspas.append(atomo);
						filtrarAspas = true;
					}
				}

				// Vai na tabela validar as palavras reservadas que são seguidas de uma função
				if (analisador.validaPalavrasReservadasSeguidoDeFuncao(atomo, aux)) {
					auxString = aux.toString();
					analisador.addLexeme(auxString, numLinha, posicao);
					aux.delete(0, aux.length());

				}

				// Valida se existe uma caracter valida pós simbolo de operação
				else if (analisador.validaCaracterValidoAposOperador(atomo, proxAtomo)) {
					aux.append(atomo);
					auxString = aux.toString();
					analisador.addLexeme(auxString, numLinha, posicao);
					aux.delete(0, aux.length());
				}
				// Valida se existe um caracter valido seguido por um simbolo de operação
				else if (analisador.validaCaracterValidoSeguidoPorOperacao(atomo, proxAtomo)) {
					aux.append(atomo);
					auxString = aux.toString();
					analisador.addLexeme(auxString, numLinha, posicao);
					aux.delete(0, aux.length());
				} else if (analisador.validaBloco(atomo) || analisador.validaCaracter(atomo)
						|| analisador.validaListaParam(atomo) || analisador.validaNumero(atomo)
						|| analisador.validaVariavel(atomo) || analisador.validaSomaSubtracao(atomo)
						|| analisador.validaOperador(atomo)
						|| analisador.validaOperadorMult(atomo) && !analisador.validaEspaco(atomo)) {

					// valida se chegou ao final da linha
					if (!analisador.eof(line, (posicao + 1))) {
						aux.append(atomo);
					}
					// se não chegou ao final da linha, adiciona lexeme
					else {
						aux.append(atomo);
						auxString = aux.toString();
						analisador.addLexeme(auxString, numLinha, posicao);
					}
				}
				// cenario para se encontrar um caracter delimitador
				else {

					if (auxAspas.length() > 1) {
						auxString = auxAspas.toString();
						analisador.addLexeme(auxString, numLinha, posicao);
						auxAspas.delete(0, auxAspas.length());
					}

					if (aux.length() > 0) {
						auxString = aux.toString();
						analisador.addLexeme(auxString, numLinha, posicao);
						aux.delete(0, aux.length());
					}
				}

			}
		}
		//removendo o cod 116.2 (#) e deixando apenas o cod 116 (!=) 
		AnalisadorUtil.getTabelaReservada().remove("116.2");
		PlotArquivos.tab(caminhoArquivoEntrada, AnalisadorUtil.getUtil());
        PlotArquivos.lex(caminhoArquivoEntrada, AnalisadorUtil.getUtil());
        analisador.cleanTabelaSimbolos();
	}
}
