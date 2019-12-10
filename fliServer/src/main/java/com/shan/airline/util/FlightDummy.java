package com.shan.airline.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

//Utility Class to get the flight details 
public class FlightDummy {

	//method to convert json array to object
	public <T> List<T> jsonArrayToObjectList(String jsonFileName, Class<T> tClass) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		final File file = ResourceUtils.getFile("classpath:" + jsonFileName);
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
		List<T> ts = mapper.readValue(file, listType);
		return ts;
	}	

}
