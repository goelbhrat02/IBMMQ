package com.bluespire.citizensmq.service;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluespire.citizensmq.model.AccountDetails;
import com.bluespire.citizensmq.model.SavingsAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class GetAccountDetailsService {
	
	
	public byte[] jsonToEbcdic(Object object) throws IOException {
//        String jsonString = new ObjectMapper().writeValueAsString(jsonObject);
		String objectToString=object.toString();
        return objectToString.getBytes(Charset.forName("IBM1047")); 
    }
	
	public JsonObject ebcdicToJson(byte[] ebcdicData) throws IOException {
        String jsonData = new String(ebcdicData, Charset.forName("IBM1047")); 
        JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = jsonParser.parse(jsonData).getAsJsonObject();
        return jsonObject;
    }
	
	
	

}
