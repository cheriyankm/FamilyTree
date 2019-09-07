package com.cherry.saya.ft;

import com.cherry.saya.ft.util.SheetCreater;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FtApplicationTests {

	@InjectMocks
	SheetCreater sheetCreater;

	@Test
	public void contextLoads() throws IOException {
		sheetCreater.createNewSheet();
	}
	
	@Test
	public void testUpdateExcelSheet() throws IOException {
		for(int i=0;i<10;i++) {
		sheetCreater.updateExcelSheet(i);
		}
	}
	
	@Test
	public void testDeleteRow() throws IOException {
		sheetCreater.deleteSheetRow(2);
	}
	
	@Test
	public void testReadSheet() throws IOException {
		sheetCreater.readSheet();
	}

}
