package com.pilas.sintatico;

import java.util.Stack;

import com.pilas.lexico.Lexico;
import com.pilas.lexico.Token;

public class Sintatico {
	
	private Lexico lexico;
	private Stack<Estado> pilha_estados = new Stack<Estado>();
	private Stack<Estado> pilha_retorno = new Stack<Estado>();
	private Stack<TransicaoIndireta> pilha_indiretas = new Stack<TransicaoIndireta>();
	private Estado estado_atual = new Estado(0, 1);
	
	public Sintatico(Lexico lexico) {
		this.lexico = lexico;
	}
	
	public void execute() {
		Token token = lexico.getNextToken();
		while(token != null) {
			Estado next_estado = estado_atual.getNextEstado(token);
			
			if(next_estado != null) { // Verifica se existe transição direta
				System.out.println("Transicao: (M:"+ this.estado_atual.getMaquina() +", E:"+ this.estado_atual.getIndice() +")" +
						" -> (M:"+ next_estado.getMaquina() +", E:"+ next_estado.getIndice() +")");
				this.estado_atual = next_estado;
				this.pilha_indiretas.clear();
				this.pilha_retorno.clear();
				token = lexico.getNextToken();
			
			} else {
				if(this.estado_atual.isFinal()) { // Verifica se é estado final
					next_estado = this.pilha_estados.pop();

					System.out.println("Transicao: (M:"+ this.estado_atual.getMaquina() +", E:"+ this.estado_atual.getIndice() +")" +
							" -> (M:"+ next_estado.getMaquina() +", E:"+ next_estado.getIndice() +")");
					this.estado_atual = next_estado;
					
				} else if(this.estado_atual.getTransicoesIndiretas().size() > 0) { // Verifica se existem transições indiretas
					
					for (TransicaoIndireta ti : this.estado_atual.getTransicoesIndiretas()) {
						this.pilha_retorno.push(new Estado(ti.getEstadoRetorno(), this.estado_atual.getMaquina()));
						this.pilha_indiretas.push(ti);
					}
					
					TransicaoIndireta transicao = this.pilha_indiretas.pop();
					Estado estado_retorno = this.pilha_retorno.pop();
					next_estado = new Estado(0, transicao.getSubMaquina());
					this.pilha_estados.push(estado_retorno);
					this.estado_atual = next_estado;
					
				} else {
					if(this.pilha_indiretas.empty()) {
						System.out.println("Erro: Não existem transições válidas para o autômato.");
						throw new RuntimeException("ERRO");						
					
					} else {	
						this.pilha_estados.pop(); // Descarta estado de retorno da submáquina que falhou

						TransicaoIndireta transicao = this.pilha_indiretas.pop();
						Estado estado_retorno = this.pilha_retorno.pop();
						next_estado = new Estado(0, transicao.getSubMaquina());
						this.pilha_estados.push(estado_retorno);
						this.estado_atual = next_estado;
					}
				}
		
			}
			
		}
		
		if(this.pilha_estados.empty()) {
			System.out.println("SUCESSO");
		} else {
			System.out.println("ERRO");
		}
		
	}
}
