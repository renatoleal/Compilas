package com.pilas.lexico.tabelas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Transicoes {

	public static final Integer LETRAS = 1;
	public static final Integer NUMEROS = 2;
	public static final Integer DOIS_PONTOS = 3;
	public static final Integer IGUAL = 4;
	public static final Integer PORCENTAGEM = 5;
	public static final Integer BRANCO = 6;
	public static final Integer QUEBRA = 7;
	public static final Integer OUTROS = 8;
	
	private static Transicoes instance;
	private List<HashMap<Integer, Integer>> proximoEstado = new ArrayList<HashMap<Integer,Integer>>();
	private List<HashMap<Integer, Integer>> acaoSemantica = new ArrayList<HashMap<Integer,Integer>>();
	
	private Transicoes(){
		proximoEstado.add(new HashMap<Integer, Integer>());
		acaoSemantica.add(new HashMap<Integer, Integer>());
		
	    InputStream inp = null;
	    Workbook wb = null;
		try {
			inp = new FileInputStream("./config/input.xlsx");
			wb = WorkbookFactory.create(inp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	    Sheet sheet = wb.getSheet("Transicoes");
	    String transicao;
	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	    	Row row = sheet.getRow(i);
	    	HashMap<Integer, Integer> pe = new HashMap<Integer, Integer>();
    		HashMap<Integer, Integer> as = new HashMap<Integer, Integer>();
    		
			for (int j = 1; j < 9; j++) {
				transicao = row.getCell(j).getStringCellValue();
				String[] peas = transicao.split(",");
				pe.put(j, Integer.valueOf(peas[0]));
				as.put(j, Integer.valueOf(peas[1]));
			}
			
			proximoEstado.add(i, pe);
			acaoSemantica.add(i, as);
			
//			System.out.println("Estado "+i+";Pr—ximos Estados: "+pe.toString());
//			System.out.println("Estado "+i+";A‹o Semm‰ntica: "+as.toString());
		}
	}
	
	public static Transicoes getInstance(){
		if(instance==null)
			instance = new Transicoes();
		return instance;
	}
	
	public Integer getProximoEstado(Integer estadoAtual, Integer tipoSimbolo) {
		return proximoEstado.get(estadoAtual).get(tipoSimbolo);
	}

	public Integer getAcaoSemantica(Integer estadoAtual, Integer tipoSimbolo) {
		return acaoSemantica.get(estadoAtual).get(tipoSimbolo);
	}
	
}
