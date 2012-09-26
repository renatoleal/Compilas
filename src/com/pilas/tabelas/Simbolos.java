package com.pilas.tabelas;

import java.util.HashMap;

public class Simbolos {

	private static Simbolos instance;
	private HashMap<String, Integer> simbolos;
	
	private Simbolos() {
		simbolos = new HashMap<String, Integer>();
	}
	
	public static Simbolos getInstance(){
		if(instance==null)
			instance = new Simbolos();
		return instance;
	}
	
	public int addSimbolo(String simbolo) {
		int index;
		if(!this.simbolos.containsKey(simbolo)) {
			index = this.simbolos.size();
			this.simbolos.put(simbolo, this.simbolos.size());			
		} else {
			index = this.simbolos.get(simbolo);
		}
		return index;
	}
}
