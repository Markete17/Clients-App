import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Invoice } from '../models/invoice';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

  private urlEndpoint:string = "http://localhost:8080/api/invoices"
  private urlEndpointProduct:string = "http://localhost:8080/api/products/filter"

  constructor(private http: HttpClient) { }

  getInvoice(id:number):Observable<Invoice>{
    return this.http.get<Invoice>(`${this.urlEndpoint}/${id}`)
  }

  delete(id:number):Observable<void>{
    return this.http.delete<void>(`${this.urlEndpoint}/${id}`);
  }

  filterProducts(term:string):Observable<Product[]>{
    return this.http.get<Product[]>(`${this.urlEndpointProduct}/${term}`)
  }

  create(invoice:Invoice):Observable<Invoice>{
    return this.http.post<Invoice>(this.urlEndpoint,invoice)
  }
}
