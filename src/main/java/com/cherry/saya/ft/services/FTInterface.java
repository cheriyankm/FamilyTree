package com.cherry.saya.ft.services;

import java.io.IOException;

import com.cherry.saya.ft.model.CreateTableRequest;
import com.fasterxml.jackson.databind.JsonNode;

public interface FTInterface {

    String getValue(String value);

    String pollService();

	String createNewSheet(CreateTableRequest createTableRequest) throws IOException;
}
