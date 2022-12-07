package br.ucsal.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.ucsal.model.TabelaSimbolos;

public class TabelaSimbolosUtil {
	
	private Map<String, String> listaTabelaReservados = new HashMap<>();
    private static List<TabelaSimbolos> listaTabelaSimbolos = new LinkedList<>();

    public Map<String, String> getTabelaReservada() {
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
        listaTabelaReservados.put("020", "enquanto");
        listaTabelaReservados.put("021", "fim-enquanto");
        listaTabelaReservados.put("022", "retorna");
        listaTabelaReservados.put("023", "pausa");
        listaTabelaReservados.put("024", "imprime");
        listaTabelaReservados.put("025", "true");
        listaTabelaReservados.put("026", "false");
        listaTabelaReservados.put("101", ";");
        listaTabelaReservados.put("102", "[");
        listaTabelaReservados.put("103", "]");
        listaTabelaReservados.put("104", ":");
        listaTabelaReservados.put("105", ",");
        listaTabelaReservados.put("106", "(");
        listaTabelaReservados.put("107", ")");
        listaTabelaReservados.put("108", "?");
        listaTabelaReservados.put("109", "{");
        listaTabelaReservados.put("110", "}");
        listaTabelaReservados.put("111", "<=");
        listaTabelaReservados.put("112", "<");
        listaTabelaReservados.put("113", ">");
        listaTabelaReservados.put("114", ">=");
        listaTabelaReservados.put("115", "==");
        listaTabelaReservados.put("116", "!= ou #");
        listaTabelaReservados.put("117", "+");
        listaTabelaReservados.put("118", "-");
        listaTabelaReservados.put("119", "*");
        listaTabelaReservados.put("120", "/");
        listaTabelaReservados.put("121", "%");
        listaTabelaReservados.put("122", ":=");
        listaTabelaReservados.put("201", "nom-programa");
        listaTabelaReservados.put("202", "variavel");
        listaTabelaReservados.put("203", "nom-funcao");
        listaTabelaReservados.put("204", "cons-inteiro");
        listaTabelaReservados.put("205", "cons-real");
        listaTabelaReservados.put("206", "cons-cadeia");
        listaTabelaReservados.put("207", "cons-caracter");
      
        return listaTabelaReservados;
    }

    public List<TabelaSimbolos> getTabelaSimbolos() {
        return listaTabelaSimbolos;
    }

    public TabelaSimbolos getUltimoElemento() {
        return listaTabelaSimbolos.get(listaTabelaSimbolos.size() - 1);
    }

    public void limparTabelaSimbolos(){
       listaTabelaSimbolos.clear();
    }
}

