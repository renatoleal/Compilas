package com.pilas.lexico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.pilas.lexico.tabelas.PalavrasReservadas;
import com.pilas.lexico.tabelas.Simbolos;
import com.pilas.lexico.tabelas.Sinais;
import com.pilas.lexico.tabelas.Transicoes;

public class Lexico {
	
	private static BufferedReader input;
	private static Integer estado = 1;
	private static char simbolo;
	private static Integer simbolo_tipo;
	private static String cadeia;
	private static Integer token_tipo;
	private static Integer token_valor;
	
	private static PalavrasReservadas pr;
	private static Transicoes transicoes;
	private static Simbolos simbolos;
	private static Sinais sinais;
	
	public Lexico(String input_file) {
		pr = PalavrasReservadas.getInstance();
		transicoes = Transicoes.getInstance();
		simbolos = Simbolos.getInstance();
		sinais = Sinais.getInstance();

		try {
		    input = new BufferedReader(new FileReader(input_file));		    	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public Token getNextToken() {
		Token resposta = null;
		boolean hasSimbolo = false;
		while((hasSimbolo = leSimbolo()) && resposta == null) {
			simbolo_tipo = classificaSimbolo(simbolo);
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
					if(sinais.contains(cadeia) != -1) {
						token_tipo = 259;
						token_valor = sinais.contains(cadeia);						
					} else {
						token_tipo = (int)cadeia.charAt(0);
						token_valor = null;						
					}
					funcaoSaida();
					break;

				case 3:
					cadeia = cadeia + String.valueOf(simbolo);
					break;

				case 4:
					if(pr.contains(cadeia) != -1) {
						token_tipo = 260 + pr.contains(cadeia);
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
					resposta = funcaoSaida();
					break;

				default:
					break;
			}
		
		}
		
		if(!hasSimbolo) {
			return null;
		} else {
			return resposta;
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
		
		if (String.valueOf(s).matches("[a-zA-Z_]"))
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
	
	
	private static Token funcaoSaida() {
		Token t = new Token(token_tipo, token_valor);
		try {
			input.reset(); // volta para o último caractere marcado no buffer de leitura
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return t;
	}
}
