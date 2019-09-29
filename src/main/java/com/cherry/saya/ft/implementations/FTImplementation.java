package com.cherry.saya.ft.implementations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cherry.saya.ft.model.CreateTableRequest;
import com.cherry.saya.ft.services.FTInterface;
import com.cherry.saya.ft.util.SchedulePolling;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

@Service
public class FTImplementation implements FTInterface {

	private int count = 0;

	@Autowired
	ObjectMapper mapper;

	@Override
	public String getValue(String value) {
		String newValue = getCollectedValues(value);
		return "Hello " + newValue;
	}

	@Override
	public String pollService() {
		count++;
		System.out.println("Count=" + count);
		if (count == 2) {
			return "COMPLETED";
		} else {
			return "PROGRESS";
		}
	}

	private String getCollectedValues(String value) {
		// TomyUtil.getData();
		Timer timer = new Timer();
		TimerTask timerTask = new SchedulePolling();
		timer.schedule(timerTask, 0, 3000);
		synchronized (timerTask) {
			try {
				timerTask.wait();
				timer.cancel();
				System.out.println("purge : " + timer.purge());
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		return "Completed";
	}

	@Override
	public String createNewSheet(CreateTableRequest createTableRequest) throws IOException {
		ObjectReader reader = mapper.readerFor(new TypeReference<List<Map<String, String>>>() {
		});
		List<Map<String, String>> requestedData = reader.readValue(createTableRequest.getData());
		System.out.println(requestedData);
		createSheet(createTableRequest.getTableName(), createTableRequest.getSheetName(), requestedData);
		return "";
	}

	private void createSheet(String fileArg, String sheetArg, List<Map<String, String>> requestedData)
			throws IOException {
		String fileName = fileNameConvertion(fileArg);
		createNewSheetFromTable(fileName, sheetArg, requestedData);
	}

	private void createNewSheetFromTable(String fileName, String sheetName, List<Map<String, String>> requestedData)
			throws IOException {
		Workbook wb = new HSSFWorkbook();
		OutputStream fileOut = new FileOutputStream("files/".concat(fileName + ".xls"));
		Sheet sheet1 = wb.createSheet(sheetName);
		int rowIndex = 0;
		Row row = sheet1.createRow(rowIndex);

		Set<String> keys = requestedData.get(0).keySet();
		int keyIndex = 0;
		for (String k : keys) {
			Cell cell = row.createCell(keyIndex);
			cell.setCellValue(k);
			keyIndex++;
		}
		rowIndex++;
		for (Map<String, String> o : requestedData) {
			row = sheet1.createRow(rowIndex);
			keyIndex = 0;
			for (String k : keys) {
				Cell cell = row.createCell(keyIndex);
				cell.setCellValue(o.get(k));
				keyIndex++;
			}
			rowIndex++;
		}
		
		wb.write(fileOut);
		wb.close();
		fileOut.close();
	}

	public String fileNameConvertion(String fileArg) {
		File folder = new File("files");
		File[] listOfFiles = folder.listFiles();
		String a = fileArg.replaceAll(" ", "_").toLowerCase();

		for (File f : listOfFiles) {
			if (f.getName().equalsIgnoreCase(a.concat(".xls"))) {
				throw new RuntimeException("Table exist");
			}
		}
		return a;
	}
}
