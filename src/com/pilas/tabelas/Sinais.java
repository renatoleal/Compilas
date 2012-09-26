package com.pilas.tabelas;

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

public class Sinais {

	private static Sinais instance;
	private ArrayList<String> sinais = new ArrayList<String>();
	
	protected Sinais(){
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

	    Sheet sheet = wb.getSheet("Sinais");
	    for (Row row : sheet) {
	    	sinais.add((int)row.getCell(0).getNumericCellValue(), row.getCell(1).getStringCellValue());
		}
	    for (int i = 0; i < sinais.size(); i++) {
			System.out.println("Indice: "+i+" Sinal: "+sinais.get(i));
		}
	}
	
	public static Sinais getInstance(){
		if(instance==null)
			instance = new Sinais();
		return instance;
	}
}
