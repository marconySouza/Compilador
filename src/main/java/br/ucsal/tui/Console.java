package br.ucsal.tui;

import java.io.File;
import java.util.Scanner;

import br.ucsal.util.Constants;

public class Console {
	private static final String extensao = Constants.ORM_PLUS_EXTENSION;
	
	/**
	 * Pergunta ao usuário o caminho para o arquivo fonte a ser compilado.
	 * @return String - caminho completo do arquivo com a extensão .222
	 */
	public static String askFilePath() {
		String filePath = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("======================== Static Checker 1.0 - E05 ========================" + "\n");

		do {
			System.out.println("Digite o caminho para o arquivo fonte:");
			filePath = sc.nextLine();
			if (!isValid(filePath)) {
				System.out.println("Arquivo " + filePath + extensao + " não encontrado no diretório informado.  Por favor, tente novamente." + "\n");
			}
		} while (!isValid(filePath));

		System.out.println("Arquivo encontrado! Processando " + filePath + extensao + "\n");
		sc.close();
		
		String fullFilePath = filePath + extensao;
		return fullFilePath;
	}

	/**
	 * Exibe o diretório em que os relatórios LEX e TAB foram salvos.
	 * @param reportsPath - Caminho do diretório onde foram salvors os relatórios.
	 * */
	public static void displayReportsPath(String reportsPath) {
		int aux = 0;
		for(int i=0; i < reportsPath.length(); i++ ) {
			if(reportsPath.charAt(i) == '\\') {
				aux = i;
			}
		}
		System.out.println("Processamento concluído :D \n" + "Os relatórios da compilação foram salvos em: " + reportsPath.substring(0, aux));
	}
	
	/**
	 * Verifica se um caminho de arquivo é válido.
	 */
	private static boolean isValid(String filePath) {
		File file = new File(filePath + extensao);
		return file.exists() ? true : false;

	}
}