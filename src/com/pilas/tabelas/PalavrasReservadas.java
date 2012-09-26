package com.pilas.tabelas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class PalavrasReservadas {

	private static PalavrasReservadas instance;
	private ArrayList<String> palavrasReservadas = new ArrayList<String>();
	
	private PalavrasReservadas(){
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

	    Sheet sheet = wb.getSheet("PalavrasReservadas");
	    for (Row row : sheet) {
	    	palavrasReservadas.add((int)row.getCell(0).getNumericCellValue(), row.getCell(1).getStringCellValue());
		}
	    for (int i = 0; i < palavrasReservadas.size(); i++) {
			System.out.println("Indice: "+i+" Palavra: "+palavrasReservadas.get(i));
		}
	}
	
	public static PalavrasReservadas getInstance(){
		if(instance==null)
			instance = new PalavrasReservadas();
		return instance;
	}
}
