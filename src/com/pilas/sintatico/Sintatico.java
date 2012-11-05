package com.pilas.sintatico;

public class Sintatico {
	
	private static Sintatico instance;
	
	public static Sintatico getInstance() {
		if(instance == null)
			instance = new Sintatico();
		return instance;
	}

}
