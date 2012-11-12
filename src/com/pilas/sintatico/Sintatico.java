package com.pilas.sintatico;

import com.pilas.lexico.Lexico;
import com.pilas.lexico.Token;
import com.pilas.sintatico.tabelas.EstadosFinais;
import com.pilas.sintatico.tabelas.TransicoesMaquinas;

public class Sintatico {
	
	private Lexico lexico;
	
	public Sintatico(Lexico lexico) {
		this.lexico = lexico;
		TransicoesMaquinas tm = TransicoesMaquinas.getInstance();
		EstadosFinais ef = EstadosFinais.getInstance();
	}
	
	public void execute() {
		Token token = null;
		while((token = lexico.getNextToken()) != null) {
			
		}
	}
}
