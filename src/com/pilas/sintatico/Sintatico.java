package com.pilas.sintatico;

import java.util.Stack;

import com.pilas.lexico.Lexico;
import com.pilas.lexico.Token;

public class Sintatico {
	
	private Lexico lexico;
	private Stack<Estado> pilha_estados = new Stack<Estado>();
	private Estado estado_atual = new Estado(0, 1);
	
	public Sintatico(Lexico lexico) {
		this.lexico = lexico;
	}
	
	public void execute() {
		Token token = null;
		while((token = lexico.getNextToken()) != null) {
			Estado next_estado = estado_atual.getNextEstado(token);
			if(next_estado != null) { // Verifica se existe transição direta
				System.out.println("Transicao: (M:"+ this.estado_atual.getMaquina() +", E:"+ this.estado_atual.getIndice() +")" +
						" -> (M:"+ next_estado.getMaquina() +", E:"+ next_estado.getIndice() +")");
				this.estado_atual = next_estado;
			
			} else {
				if(this.estado_atual.isFinal()) { // Verifica se é estado final
					next_estado = this.pilha_estados.pop();

					System.out.println("Transicao: (M:"+ this.estado_atual.getMaquina() +", E:"+ this.estado_atual.getIndice() +")" +
							" -> (M:"+ next_estado.getMaquina() +", E:"+ next_estado.getIndice() +")");
					this.estado_atual = next_estado;
					
				} else if(this.estado_atual.getTransicoesIndiretas().size() > 0) {
					boolean transicao_valida = false;
					
					for (TransicaoIndireta ti : this.estado_atual.getTransicoesIndiretas()) {
						Estado estado_inicial_submaquina = new Estado(0, ti.getSubMaquina());
						Estado next_estado_submaquina = estado_inicial_submaquina.getNextEstado(token);
						if(next_estado_submaquina != null) {
							this.pilha_estados.push(this.estado_atual);
							next_estado = next_estado_submaquina;
							transicao_valida = true;

							System.out.println("Transicao: (M:"+ this.estado_atual.getMaquina() +", E:"+ this.estado_atual.getIndice() +")" +
									" -> (M:"+ estado_inicial_submaquina.getMaquina() +", E:"+ estado_inicial_submaquina.getIndice() +")");
							System.out.println("Transicao: (M:"+ estado_inicial_submaquina.getMaquina() +", E:"+ estado_inicial_submaquina.getIndice() +")" +
									" -> (M:"+ next_estado.getMaquina() +", E:"+ next_estado.getIndice() +")");
							this.estado_atual = next_estado;
							
							break;
						}
					}
					
					if(!transicao_valida) {
						System.out.println("Erro: Não existem transições válidas para o autômato.");
						throw new RuntimeException("ERRO");
					}
				
				} else {
					System.out.println("Erro: Não existem transições válidas para o autômato.");
					throw new RuntimeException("ERRO");
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
