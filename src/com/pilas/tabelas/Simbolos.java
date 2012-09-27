package com.pilas.tabelas;

import java.util.ArrayList;

public class Simbolos {

	private static Simbolos instance;
	private ArrayList<String> simbolos = new ArrayList<String>();
	
	private Simbolos() {
	}
	
	public static Simbolos getInstance(){
		if(instance==null)
			instance = new Simbolos();
		return instance;
	}
	
	public int addSimbolo(String simbolo) {
		String simbolo_lower = simbolo.toLowerCase();
		int index;		
		if(!this.simbolos.contains(simbolo_lower)) {
			index = this.simbolos.size();
			this.simbolos.add(simbolo_lower);			
		} else {
			index = this.simbolos.indexOf(simbolo_lower);
		}
		return index;
	}
	
	public ArrayList<String> getSimbolos() {
		return this.simbolos;
	}
}
