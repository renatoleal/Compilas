package com.pilas.sintatico;

public class TransicaoIndireta {
	
	private Integer estadoAtual;
	private Integer subMaquina;
	private Integer estadoRetorno;
	
	public Integer getEstadoAtual() {
		return estadoAtual;
	}
	public void setEstadoAtual(Integer estadoAtual) {
		this.estadoAtual = estadoAtual;
	}
	public Integer getSubMaquina() {
		return subMaquina;
	}
	public void setSubMaquina(Integer subMaquina) {
		this.subMaquina = subMaquina;
	}
	public Integer getEstadoRetorno() {
		return estadoRetorno;
	}
	public void setEstadoRetorno(Integer estadoRetorno) {
		this.estadoRetorno = estadoRetorno;
	}

}
