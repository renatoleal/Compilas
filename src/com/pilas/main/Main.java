package com.pilas.main;

import com.pilas.lexico.Lexico;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Executa analisador léxico
		Lexico lexico = Lexico.getInstance();
		if(args[0] != null) {
			lexico.execute(args[0]);
		} else {
			lexico.execute("./source.txt");
		}
		
		// Executa analisador sintático
		
	}

}
