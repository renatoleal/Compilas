package com.pilas.sintatico.tabelas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class EstadosFinais {

	private static EstadosFinais instance;
	private ArrayList<ArrayList<Integer>> estadosFinais = new ArrayList<ArrayList<Integer>>();
	
	private EstadosFinais(){
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

		estadosFinais.add(new ArrayList<Integer>());
		
	    Sheet sheet = wb.getSheet("EstadosFinais");
	    for (int i=1; i <= sheet.getLastRowNum(); i++) {
	    	Row row = sheet.getRow(i);
	    	Integer indiceMaquina = (int) row.getCell(0).getNumericCellValue();
	    	String[] estadosArray = row.getCell(2).getStringCellValue().split(",");
	    	ArrayList<Integer> ef = new ArrayList<Integer>();
	    	
	    	for (int j = 0; j < estadosArray.length; j++) {
	    		ef.add(Integer.valueOf(estadosArray[j]));
	    	}
	    	
	    	estadosFinais.add(indiceMaquina, ef);
		}
	    for (int i = 1; i < estadosFinais.size(); i++) {
	    	for (Integer in : estadosFinais.get(i)) {
	    		System.out.println("Automato: "+i+" EF: " + in);
			}
		}
	}
	
	public static EstadosFinais getInstance(){
		if(instance==null)
			instance = new EstadosFinais();
		return instance;
	}
	
	public boolean isEstadoFinal(int maquina, int estado) {
		boolean resposta = false;
		for (Integer e : estadosFinais.get(maquina)) {
			if(estado == e) {
				resposta = true;
			}
		}
		
		return resposta;
	}
}
