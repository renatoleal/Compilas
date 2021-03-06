package com.pilas.lexico;

public class Token {

	private Integer tipo;
	private Integer valor;
	
	public Token() {		
	}
	
	public Token(Integer tipo, Integer valor) {
		this.tipo = tipo;
		this.valor = valor;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	public boolean isEqualToken(Token token) {
		boolean resposta = false;
		if(token.getTipo().equals(this.tipo)) {
			if((token.getValor() == null && this.valor == null) || (token.getValor() == this.valor)) {
				resposta = true;				
			}
		}
		return resposta;
	}
}
