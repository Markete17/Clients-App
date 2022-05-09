import { Injectable } from '@angular/core';
import { HttpHeaders,HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Region } from './region';
import { catchError, Observable, throwError } from 'rxjs';
import { LoginService } from '../users/login.service';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class RegionService {

  private urlEndPoint:string = 'http://localhost:8080/api/regions'
  

  
  constructor(private http:HttpClient) { }

  /*
  private httpHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  })

  private addAuthorizationHeader(){
    let token = this.loginService.token;
    if(token!=null){
      return this.httpHeaders.append('Authorization','Bearer '+token)
    }
    return this.httpHeaders;
  }
  */

  /*
  Lo maneja el auth interceptor
  
  private isNotAuthorized(e):boolean{
    if(e.status==401){
      this.router.navigate(['/login']);
      return true;
    } 
    if(e.status==403){
      Swal.fire('Denied Access',`Hi ${this.loginService.user.username} you do not have access in this resource`,'warning')
      this.router.navigate(['/clients']);
      return true;
    } 
    
      return false;
  }
*/
  getRegions():Observable<Region[]>{
    return this.http.get<Region[]>(this.urlEndPoint);
  }
}
