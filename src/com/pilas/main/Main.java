package com.pilas.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pilas.tabelas.PalavrasReservadas;
import com.pilas.tabelas.Simbolos;
import com.pilas.tabelas.Transicoes;

public class Main {
	
	private static BufferedReader input;
	private static Integer estado = 1;
	private static char simbolo;
	private static Integer simbolo_tipo;
	private static String cadeia;
	private static Integer token_tipo;
	private static Integer token_valor;
	
	private static List<Token> tokens = new ArrayList<Token>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PalavrasReservadas pr = PalavrasReservadas.getInstance();
		Transicoes transicoes = Transicoes.getInstance();
		Simbolos simbolos = Simbolos.getInstance();
				
		try {
		    if(args.length > 0) {
			    input = new BufferedReader(new FileReader(args[0]));		    	
		    } else {
				input = new BufferedReader(new FileReader("./source.txt"));
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(leSimbolo()) {
			
			simbolo_tipo = classificaSimbolo(simbolo);
//			System.out.println("int: "+(int)simbolo+ " char:"+simbolo+" tipo:"+ simbolo_tipo);
			Integer ps = transicoes.getProximoEstado(estado, simbolo_tipo);
			Integer as = transicoes.getAcaoSemantica(estado, simbolo_tipo);
			
			// Muda para o próximo estado
			estado = ps;
			
			// Executa Ações Semânticas
			switch (as) {
				case 1:
					cadeia = String.valueOf(simbolo);
					break;
					
				case 2:
					token_tipo = (int)cadeia.charAt(0);
					token_valor = null;
					funcaoSaida();
					break;
	
				case 3:
					cadeia = cadeia + String.valueOf(simbolo);
					break;

				case 4:
					if(pr.contains(cadeia) != -1) {
						token_tipo = 259 + pr.contains(cadeia);
						token_valor = null;
					} else {
						int index = simbolos.addSimbolo(cadeia);
						token_tipo = 256;
						token_valor = index;						
					}
					funcaoSaida();
					break;

				case 5:
					token_tipo = 257;
					token_valor = Integer.valueOf(cadeia);
					funcaoSaida();
					break;

				case 6:
					token_tipo = 258;
					token_valor = null;
					funcaoSaida();
					break;

				default:
					break;
			}
		}
		
		// Imprime Resultados
		System.out.println("Lista de Tokens gerados:");
		for (Token t : tokens) {
			System.out.println("<tipo:"+ t.getTipo()+",valor:"+t.getValor()+">");			
		}
		
		ArrayList<String> simbolo_list = simbolos.getSimbolos();
		System.out.println("\nLista de Símbolos Indexados:");
		for (int i=0; i < simbolo_list.size(); i++) {
			System.out.println("indice:"+i+" simbolo:"+simbolo_list.get(i));
		}
		
	}
	
	
	private static boolean leSimbolo() {
	    boolean resposta = false;
	    int reader;
	    
	    try {
			input.mark(1); // Marca posição no buffer de leitura
			if((reader = input.read()) != -1) {
				simbolo = (char) reader;
				resposta = true;
			} else {
			    input.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    return resposta;
	}

	
	private static Integer classificaSimbolo(char s) {
		Integer tipo = Transicoes.OUTROS;
		
		if (String.valueOf(s).matches("[a-zA-Z]"))
			tipo = Transicoes.LETRAS;
		else if (String.valueOf(s).matches("[0-9]"))
			tipo = Transicoes.NUMEROS;
		else if (String.valueOf(s).matches(":"))
			tipo = Transicoes.DOIS_PONTOS;
		else if (String.valueOf(s).matches("="))
			tipo = Transicoes.IGUAL;
		else if (String.valueOf(s).matches("%"))
			tipo = Transicoes.PORCENTAGEM;
		else if (String.valueOf(s).matches(" ")
				|| (int)s == (int)('\t'))
			tipo = Transicoes.BRANCO;
		else if ((int)s == (int)('\n')
				|| (int)s == (int)('\r'))
			tipo = Transicoes.QUEBRA;
		
		return tipo;
	}
	
	
	private static void funcaoSaida() {
		Token t = new Token(token_tipo, token_valor);
		tokens.add(t);
		
		try {
			input.reset(); // volta para o último caractere marcado no buffer de leitura
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
