import { Injectable } from '@angular/core';
import { HttpHeaders,HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Region } from './region';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegionService {

  private urlEndPoint:string = 'http://localhost:8080/api/regions'
  
  private httpHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  })
  
  constructor(private http:HttpClient,private router:Router) { }

  getRegions():Observable<Region[]>{
    return this.http.get<Region[]>(this.urlEndPoint)
  }
}
