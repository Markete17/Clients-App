import { Injectable } from '@angular/core';
import { Client } from './client';
import { Observable,of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs';

@Injectable()
export class ClientService {
  private urlEndPoint:string = 'http://localhost:8080/api/clients'
  private httpHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  })
  
  constructor(private http:HttpClient) { }

  getClients(): Observable<Client[]>{
    return this.http.get(this.urlEndPoint).pipe(
      map( response => response as Client[])
    )
  }
  create(client:Client):Observable<Client>{
    return this.http.post(this.urlEndPoint,client,{headers:this.httpHeaders}).pipe(
      map( response => response as Client)
    )
  }

  getClient(id):Observable<Client>{
    return this.http.get(`${this.urlEndPoint}/${id}`).pipe(
      map(response => response as Client)
    )
  }

  update(client:Client):Observable<Client>{
    return this.http.put(`${this.urlEndPoint}/${client.id}`,client,{headers:this.httpHeaders}).pipe(
      map(response => response as Client)
    )
  }

  delete(id):Observable<Client>{
    return this.http.delete(`${this.urlEndPoint}/${id}`,{headers:this.httpHeaders}).pipe(
      map(response => response as Client)
    )
  }
}
