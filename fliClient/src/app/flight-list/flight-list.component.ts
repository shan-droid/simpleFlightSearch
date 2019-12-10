import { Component, OnInit } from '@angular/core';
import { FlightService } from '../shared/flight/flight.service';

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrls: ['./flight-list.component.css']
})

export class FlightListComponent implements OnInit {

  flights: Array<any>;
  flightNumber: String;
  date: String;
  
  origin: String;
  destination: String;
  date2: String;
  
  constructor(private flightService: FlightService) { }

  ngOnInit() {
	  
	/*this.flightService.getAll().subscribe(data => {
      this.flights = data;
    }); */	
	
	this.flightNumber = "0638";	
	this.date = "2018-01-31";
	
	 this.flightService.getByFlightNumber(this.flightNumber, this.date).subscribe(data => {		 
		  this.flights = Array(data);		
    });	
	
	 this.origin = "IAH";
	 // this.origin = "ORD";
	 this.destination = "ORD";
	 //this.destination = "IAH";
	 this.date2 = "2018-01-31";
	
	 this.flightService.getByOrgAndDate(this.origin, this.destination, this.date2).subscribe(data => {		 
		  this.flights = data;		
    });
	
  }

}
