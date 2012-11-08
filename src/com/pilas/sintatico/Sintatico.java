package com.pilas.sintatico;

import com.pilas.lexico.Lexico;
import com.pilas.lexico.Token;

public class Sintatico {
	
	private Lexico lexico;
	
	public Sintatico(Lexico lexico) {
		this.lexico = lexico;
	}
	
	public void execute() {
		Token token = null;
		while((token = lexico.getNextToken()) != null) {
			
		}
	}
}
