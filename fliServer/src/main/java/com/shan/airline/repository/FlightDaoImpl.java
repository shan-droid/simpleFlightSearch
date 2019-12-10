package com.shan.airline.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.shan.airline.component.Flight;
import com.shan.airline.util.FlightDummy;

@Repository
public class FlightDaoImpl {

	// Method to get the flight records - no DB now 
	public List<Flight> getAllFlightsFromDb() {

		// Utility Class to get the flight data.
		FlightDummy flightDummy = new FlightDummy();

		// Flight list.
		List<Flight> flights = new ArrayList<Flight>();

		try {

			String path = "static/flights.json";

			flights = flightDummy.jsonArrayToObjectList(path, Flight.class);
			

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return flights;
	}
}
