package com.pilas.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.pilas.tabelas.PalavrasReservadas;
import com.pilas.tabelas.Transicoes;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PalavrasReservadas pr = PalavrasReservadas.getInstance();
		Transicoes t = Transicoes.getInstance();
		readFile("./source.txt");
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
