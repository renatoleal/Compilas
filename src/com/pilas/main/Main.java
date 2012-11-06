package com.pilas.main;

import com.pilas.lexico.Lexico;
import com.pilas.sintatico.Sintatico;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Executa analisador l�xico
		Lexico lexico = null;
		if(args[0] != null) {
			lexico = new Lexico(args[0]);
		} else {
			lexico = new Lexico("./source.txt");
		}
		
		// Executa analisador sint�tico
		Sintatico sintatico = new Sintatico(lexico);
		
	}

}
