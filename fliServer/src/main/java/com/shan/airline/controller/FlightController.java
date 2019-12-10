package com.shan.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shan.airline.component.Flight;
import com.shan.airline.service.FlightServiceImpl;

@RestController
public class FlightController {

	@Autowired
	FlightServiceImpl flightService;

	// Testing the angular fetch call.
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value = "/flights", method = RequestMethod.GET)
	public List<Flight> getAllFlights() {
		return flightService.getAllFlights();
	}
	
	

	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value = "/getflight", params = { "flightNumber", "date" }, method = RequestMethod.GET)
	public ResponseEntity<Object> getFlight(@RequestParam("flightNumber") String flightNumber,
			@RequestParam("date") String date) {

		if ((flightNumber.equals("") && date.equals("")) || (!flightNumber.equals("") && date.equals(""))
				|| (flightNumber.equals("") && !date.equals(""))) {
			return new ResponseEntity<Object>(("A flight number and date is required"), HttpStatus.BAD_REQUEST);
		}

		Flight flight = flightService.getByFlightNumber(flightNumber, date);

		if (flight != null)
			return new ResponseEntity<>(flight, HttpStatus.OK);
		else
			return new ResponseEntity<Object>(("Request is not valid").toString(), HttpStatus.BAD_REQUEST);

	}

	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value = "/getflight", params = { "origin", "destination", "date" }, method = RequestMethod.GET)
	public ResponseEntity<Object> getFlightOrg(@RequestParam("origin") String origin,
			@RequestParam("destination") String destination, @RequestParam("date") String date) {

		if ((origin.equals("") && destination.equals("") && date.equals(""))
				|| (!origin.equals("") && destination.equals("") && date.equals(""))
				|| (!origin.equals("") && !destination.equals("") && date.equals(""))
				|| (!origin.equals("") && destination.equals("") && !date.equals(""))
				|| (origin.equals("") && !destination.equals("") && !date.equals(""))) {

			return new ResponseEntity<Object>(("Origin, Destination and date is required").toString(),
					HttpStatus.BAD_REQUEST);

		}

		List<Flight> flights = flightService.getByFlightOrgAndDest(origin, destination, date);

		if (flights != null && flights.size()>0)
			return new ResponseEntity<>(flights, HttpStatus.OK);
		else
			return new ResponseEntity<Object>(("Request is not valid").toString(), HttpStatus.BAD_REQUEST);

	}

}
