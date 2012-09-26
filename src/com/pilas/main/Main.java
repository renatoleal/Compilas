package com.pilas.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.pilas.tabelas.PalavrasReservadas;
import com.pilas.tabelas.Sinais;
import com.pilas.tabelas.Transicoes;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		readFile("./source.txt");
		PalavrasReservadas pr = PalavrasReservadas.getInstance();
		Sinais s = Sinais.getInstance();
		Transicoes t = Transicoes.getInstance();
	}
	
	private static void readFile(String filePath){
		try {
		    BufferedReader in = new BufferedReader(new FileReader(filePath));
		    String str;
		    while((str = in.readLine()) != null){
		    	char[] c = str.toCharArray();
		    	for (char d : c) {
		    		if(d=='\n')
		    			System.out.println("true");
				}
		    }
		    in.close();
		} catch (IOException e) {
		}
	}
}
