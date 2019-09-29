package com.cherry.saya.ft.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.JsonNode;

public class CreateTableRequest {

	@NotNull
	private String tableName;
	@NotNull
	private String sheetName;
	@NotNull
	private JsonNode data;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public JsonNode getData() {
		return data;
	}

	public void setData(JsonNode data) {
		this.data = data;
	}
}
