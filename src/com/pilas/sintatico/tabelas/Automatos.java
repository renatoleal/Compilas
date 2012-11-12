package com.pilas.sintatico.tabelas;

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

public class Automatos {
	
	private static Automatos instance;
	private ArrayList<List<HashMap<Integer, Integer>>> maquinas = new ArrayList<List<HashMap<Integer, Integer>>>();
	
	public static Automatos getInstance(){
		if(instance==null)
			instance = new Automatos();
		return instance;
	}
	
	private Automatos() {
		maquinas.add(new ArrayList<HashMap<Integer, Integer>>());
		maquinas.add(1, importSheet("1.Programa"));
		maquinas.add(2, importSheet("2.Dec_Parametros"));
		maquinas.add(3, importSheet("3.Dec_Variavel"));
		maquinas.add(4, importSheet("4.Comando"));
		maquinas.add(5, importSheet("5.Exp_Booleana"));
		maquinas.add(6, importSheet("6.Termo_Booleano"));
		maquinas.add(7, importSheet("7.Exp_Aritmetica"));
	}

	private List<HashMap<Integer, Integer>> importSheet (String sheetName) {
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

	    Sheet sheet = wb.getSheet(sheetName);
	    int lastColumnNum = 1;
	    try {
	    	while (!sheet.getRow(0).getCell(lastColumnNum).getStringCellValue().isEmpty()){
	    		lastColumnNum++;
	    	}
	    } catch (NullPointerException e) {
	    	//Handle exception
	    }
	    
	    List<HashMap<Integer, Integer>> proximoEstado = new ArrayList<HashMap<Integer,Integer>>();
		proximoEstado.add(new HashMap<Integer, Integer>());
		
	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	    	Row row = sheet.getRow(i);
	    	HashMap<Integer, Integer> pe = new HashMap<Integer, Integer>();
    		
			for (int j = 1; j < lastColumnNum; j++) {
				String cellValue = row.getCell(j).getStringCellValue();
				if (!cellValue.isEmpty()) {
					System.out.println(i-1 + ","+ cellValue);
					pe.put(j, Integer.valueOf(cellValue));
				}
			}
			
			proximoEstado.add(i-1, pe);
	    }
	    
	    return proximoEstado;
	}
}
