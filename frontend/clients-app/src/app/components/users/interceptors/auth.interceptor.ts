import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
} from '@angular/common/http';
import { LoginService } from '../login.service';

import { Observable,throwError,catchError} from 'rxjs';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

/** Pass untouched request through to the next request handler. */
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private loginService: LoginService, private router:Router) {}
  

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {

    return next.handle(req).pipe(
      catchError(e=>{
        if(e.status==401){
          if(this.loginService.isAuthenticated()){
            this.loginService.logout()
          }
          this.router.navigate(['/login']);
        } 
        if(e.status==403){
          Swal.fire('Denied Access',`Hi ${this.loginService.user.username} you do not have access in this resource`,'warning')
          this.router.navigate(['/clients']);
        } 
        
          return throwError(()=>e)
      })
    )
  }
}