package br.ucsal.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.ucsal.model.TabelaSimbolos;

public class PlotArquivos {

	public static void tab(String nomeArquivo, TabelaSimbolosUtil util) throws IOException {
		File file = new File(nomeArquivo + ".TAB");
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write("Codigo identificador da equipe: E05\n");
		bufferedWriter.write("############## EQUIPE ##############\n");
		bufferedWriter.write("Marcony Jefferson Silva Souza\t | \tmarcony.souza@ucsal.edu.br\t | \t(71) 99303-4324\n");
		bufferedWriter.write("Natalia de Lima Conceicao\t | \tnatalia.conceicao@ucsal.edu.br\t | \t(71) 99611-9180\n");
		bufferedWriter.write("Roberto Nascimento dos Santos Junior\t | \troberton.junior@ucsal.edu.br\t | \t(71) 99622-0258\n");
		bufferedWriter.write("Thiago de Melo Santos Gomes\t | \tthiago.gomes@ucsal.edu.br\t | \t(71) 98628-4163\n");

		//NATALIA DE LIMA CONCEICAO - natalia.conceicao - (71) 99611-9180
		//Thiago de Melo Santos Gomes - thiago.gomes - (71) 98628-4163
		//Roberto Nascimento dos Santos Junior - roberton.junior - (71) 99622-0258
		bufferedWriter.write("==================================");
		bufferedWriter.write("############## TABELA DE SIMBOLOS ##############\n");

		for (TabelaSimbolos tab : util.getTabelaSimbolos()) {
			bufferedWriter.write("indice: " + tab.numEntrada + "\t" + "| codigo atomo: " + tab.getIdAtomo() + "\t"
					+ "| lexeme: " + tab.getLexeme() + "\t" + "| qtd. antes truncagem: " + tab.getQtdCaracAntesTrunc()
					+ "\t" + "| qtd. depois truncagem" + tab.getQtdCaracDpsTrunc() + "\t" + tab.getTipo() + "\t"
					+ "| linhas que houveram ocorrencias: " + tab.getOcorrenciaLinhas());
			bufferedWriter.newLine();
		}
		bufferedWriter.write("############## FIM ARQUIVO .TAB ##############");
		bufferedWriter.close();
		fileWriter.close();
	}

	public static void lex(String nomeArquivo, TabelaSimbolosUtil util) throws IOException {
		File file = new File(nomeArquivo + ".TAB");
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write("Codigo identificador da equipe: E05\n");
		bufferedWriter.write("############## EQUIPE ##############\n");
		bufferedWriter.write("Marcony Jefferson Silva Souza\t | \tmarcony.souza@ucsal.edu.br\t | \t(71) 99303-4324\n");
		bufferedWriter.write("Natalia de Lima Conceicao\t | \tnatalia.conceicao@ucsal.edu.br\t | \t(71) 99611-9180\n");
		bufferedWriter.write("Roberto Nascimento dos Santos Junior\t | \troberton.junior@ucsal.edu.br\t | \t(71) 99622-0258\n");
		bufferedWriter.write("Thiago de Melo Santos Gomes\t | \tthiago.gomes@ucsal.edu.br\t | \t(71) 98628-4163\n");
		bufferedWriter.write("####### ANALISE LEXICA #######\n");
		for (TabelaSimbolos tab : util.getTabelaSimbolos()) {
			bufferedWriter.write("Lexeme:" + tab.getLexeme() + "\t" + "| codigo atomo: " + tab.getIdAtomo());
			if (util.getTabelaSimbolos().contains(tab)) {
				bufferedWriter.write("\t" + "| indice: " + tab.getNumEntrada());
			}
			bufferedWriter.newLine();
		}
		bufferedWriter.write("############## FIM ARQUIVO .LEX ##############");
		bufferedWriter.close();
		fileWriter.close();
	}
}
