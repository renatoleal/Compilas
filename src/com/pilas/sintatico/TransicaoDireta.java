package com.pilas.sintatico;

import com.pilas.lexico.Token;

public class TransicaoDireta {
	
	private Integer estadoAtual;
	private Token tokenRecebido;
	private Integer proximoEstado;
	public Integer getEstadoAtual() {
		return estadoAtual;
	}
	public void setEstadoAtual(Integer estadoAtual) {
		this.estadoAtual = estadoAtual;
	}
	public Token getTokenRecebido() {
		return tokenRecebido;
	}
	public void setTokenRecebido(Token tokenRecebido) {
		this.tokenRecebido = tokenRecebido;
	}
	public Integer getProximoEstado() {
		return proximoEstado;
	}
	public void setProximoEstado(Integer proximoEstado) {
		this.proximoEstado = proximoEstado;
	}

}
