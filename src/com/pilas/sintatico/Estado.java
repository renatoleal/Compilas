package com.pilas.sintatico;

import java.util.List;

import com.pilas.lexico.Token;
import com.pilas.sintatico.tabelas.EstadosFinais;
import com.pilas.sintatico.tabelas.TransicoesMaquinas;

public class Estado {
	
	private int indice;
	private int maquina;
	private List<TransicaoDireta> tDiretas;
	private List<TransicaoIndireta> tIndiretas;
	private boolean isFinal;
	
	public Estado(int indice, int maquina) {
		this.indice = indice;
		this.maquina = maquina;
		
		// Leitura de transições diretas
		TransicoesMaquinas transicoes = TransicoesMaquinas.getInstance();
		this.tDiretas = transicoes.getTransicoesDiretas(this.maquina, this.indice);
		this.tIndiretas = transicoes.getTransicoesIndiretas(this.maquina, this.indice);
		
		// Verificação do estado final
		EstadosFinais finais = EstadosFinais.getInstance();
		this.isFinal = finais.isEstadoFinal(this.maquina, this.indice);
	}
	
	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public int getMaquina() {
		return maquina;
	}

	public void setMaquina(int maquina) {
		this.maquina = maquina;
	}

	public List<TransicaoDireta> getTransicoesDiretas() {
		return tDiretas;
	}

	public void setTransicoesDiretas(List<TransicaoDireta> tDiretas) {
		this.tDiretas = tDiretas;
	}

	public List<TransicaoIndireta> getTransicoesIndiretas() {
		return tIndiretas;
	}

	public void setTransicoesIndiretas(List<TransicaoIndireta> tIndireta) {
		this.tIndiretas = tIndireta;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public Estado getNextEstado(Token token) {
		Estado resposta = null;

		// Caso o tipo do token não seja uma palavra reservada, não é necessário utilizar o campo 'valor' para efetuar a busca
		Token token_busca = null;
		if(token.getTipo() != 259) { 
			token_busca = new Token(token.getTipo(), null);
		} else {
			token_busca = token;			
		}
		
		for (TransicaoDireta t : this.tDiretas) {
			if(t.getTokenRecebido().isEqualToken(token_busca)) {
				resposta = new Estado(t.getProximoEstado(), this.maquina);
				break;
			}
		}
		
		return resposta;
	}
	
}
