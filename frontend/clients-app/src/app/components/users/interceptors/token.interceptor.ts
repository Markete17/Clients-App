import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
} from '@angular/common/http';
import { LoginService } from '../login.service';

import { Observable } from 'rxjs';

/** Pass untouched request through to the next request handler. */
@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private loginService: LoginService) {}
  

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {

      let token = this.loginService.token;
      //Si está autenticado, se añade la cabecera Authorization en las peticiones http
      if(token!=null){
        const authReq = req.clone({
          headers: req.headers.set('Authorization','Bearer '+token)
        })
        return next.handle(authReq);
      }
    return next.handle(req);
  }
}