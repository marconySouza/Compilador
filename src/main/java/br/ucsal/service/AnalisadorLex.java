package br.ucsal.service;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import br.ucsal.tui.Console;
import br.ucsal.util.AnalisadorUtil;
import br.ucsal.util.PlotArquivos;
import br.ucsal.util.TabelaSimbolosUtil;

public class AnalisadorLex {

	private static AnalisadorUtil analisador = new AnalisadorUtil();
	private static TabelaSimbolosUtil util = new TabelaSimbolosUtil();
	private static char[] atomos;

	public static void main(String[] args) {

		util.inicializarTabelaReservados();
		
		String caminhoArquivoLido = Console.askFilePath();// Arquivo de texto fonte inserido pelo usuário
		try {
			analise(caminhoArquivoLido);// Metodo de analise lexica passando como parametro o caminho do arquivo de
										// entrada
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void analise(String caminhoArquivoEntrada) throws IOException {
		File arquivoEntrada = new File(caminhoArquivoEntrada);
		String pastaArquivoEntrada = arquivoEntrada.getCanonicalPath(); 
		Scanner sc = new Scanner(new File(caminhoArquivoEntrada));
		int numLinha = 0;
		boolean filtrarComentarioBloco = false;
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
					if(atomos.length - 1 != posicao)proxAtomo = atomos[posicao + 1];
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
				if (analisador.validaPalavrasReservadasSeguidoDeFuncao(atomo, aux, util)) {
					auxString = aux.toString();
					analisador.addLexeme(auxString, numLinha, posicao, util);
					aux.delete(0, aux.length());

				}

				// Valida se existe uma caracter valida pós simbolo de operação
				else if (analisador.validaCaracterValidoAposOperador(atomo, proxAtomo)) {
					aux.append(atomo);
					System.out.println(aux);
					auxString = aux.toString();
					analisador.addLexeme(auxString, numLinha, posicao, util);
					aux.delete(0, aux.length());
				}
				// Valida se existe um caracter valido seguido por um simbolo de operação
				else if (analisador.validaCaracterValidoSeguidoPorOperacao(atomo, proxAtomo)) {
					aux.append(atomo);
					System.out.println(aux);
					auxString = aux.toString();
					analisador.addLexeme(auxString, numLinha, posicao, util);
					aux.delete(0, aux.length());
				} else if (analisador.validaBloco(atomo) || analisador.validaCaracter(atomo)
						|| analisador.validaListaParam(atomo) || analisador.validaNumero(atomo)
						|| analisador.validaVariavel(atomo) || analisador.validaSomaSubtracao(atomo)
						|| analisador.validaOperador(atomo)
						|| analisador.validaOperadorMult(atomo) && !analisador.validaEspaco(atomo)) {

					// valida se chegou ao final da linha
					if (analisador.eof(line, (posicao + 1))) {
						aux.append(atomo);
					}
					// se não chegou ao final da linha, adiciona lexeme
					else {
						aux.append(atomo);
						System.out.println(aux);
						auxString = aux.toString();
						analisador.addLexeme(auxString, numLinha, posicao, util);
					}
				}
				// cenario para se encontrar um caracter delimitador
				else {

					if (auxAspas.length() > 1) {
						System.out.println(aux);
						auxString = auxAspas.toString();
						analisador.addLexeme(auxString, numLinha, posicao, util);
						auxAspas.delete(0, auxAspas.length());
					}

					if (aux.length() > 0) {
						System.out.println(auxAspas);
						auxString = aux.toString();
						analisador.addLexeme(auxString, numLinha, posicao, util);
						aux.delete(0, aux.length());
					}
				}
					posicao++;
			}
		}
		// removendo o cod 116.2 (#) e deixando apenas o cod 116 (!=)
		util.getTabelaReservados().remove("116.2");
		PlotArquivos.tab(caminhoArquivoEntrada, AnalisadorUtil.getUtil());
		PlotArquivos.lex(caminhoArquivoEntrada, AnalisadorUtil.getUtil());
		Console.displayReportsPath(pastaArquivoEntrada);
		analisador.cleanTabelaSimbolos();
	}
}
