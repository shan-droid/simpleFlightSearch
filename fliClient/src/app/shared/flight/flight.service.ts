import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
//import { Observable } from 'rxjs/Observable';

import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor(private http: HttpClient) { }
  
  getAll(): Observable<any> {	   
    return this.http.get('//localhost:8080/flights');
  }
  
  getByFlightNumber(flightNumber :String, date: String) : Observable<any> {	 
	  return this.http.get('//localhost:8080/getflight?flightNumber='+flightNumber+'&date='+date).pipe(
       retry(1),
       catchError(this.handleError)
     );
  }
  
  getByOrgAndDate(origin :String, destination:String, date: String) : Observable<any> {	 
	  return this.http.get('//localhost:8080/getflight?origin='+origin+'&destination='+destination+'&date='+date).pipe(
       retry(1),
       catchError(this.handleError)
     );
  }
  
  handleError(error) {
   let errorMessage = '';
   if (error.error instanceof ErrorEvent) {
     // client-side error
     errorMessage = `Error: ${error.error.message}`;
   } else {
     // server-side error
     errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
   }
   window.alert(errorMessage);
   return throwError(errorMessage);
 }
 
}
