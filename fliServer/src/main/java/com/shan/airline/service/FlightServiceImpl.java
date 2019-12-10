package com.shan.airline.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shan.airline.component.Flight;
import com.shan.airline.repository.FlightDaoImpl;

@Service
public class FlightServiceImpl {

	@Autowired
	FlightDaoImpl flightdao;

	//Method to return all the flights
	public List<Flight> getAllFlights() {
		return flightdao.getAllFlightsFromDb();
	}
	

	//Method to return flight based on Flight Number and Date
	public Flight getByFlightNumber(String flightNumber, String date) {		

		Flight flight = null;		

		for (Flight fl : flightdao.getAllFlightsFromDb()) {

			if ((fl != null) && (fl.getFlightNumber().contentEquals(flightNumber) && fl.getDeparture().contains(date))) {

				// System.out.println("Found!");
				flight = fl;
				
				break;
			}

		}

		return flight;
	}
	

	//Method to return flights based on Origin, Destination and Date
	public List<Flight> getByFlightOrgAndDest(String origin, String destination, String date) {
		
		List<Flight> flights = new ArrayList<Flight>();

		for (Flight fl : flightdao.getAllFlightsFromDb()) {

			if (fl != null && (fl.getOrigin().contentEquals(origin) && fl.getDestination().contentEquals(destination)
					&& fl.getDeparture().contains(date))) {

				// System.out.println("Found!");
				flights.add(fl);
			}

		}
		return flights;

	}

}