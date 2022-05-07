import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from '../login.service';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  
  constructor(private loginService:LoginService,private router:Router){}
  
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(this.loginService.isAuthenticated()){
        return true
      }
      if(this.isExpiredToken()){
        this.loginService.logout();
        this.router.navigate(['/login'])
        return false;
      }
      Swal.fire('Login',`You need to log in`,'warning')
      this.router.navigate(['/login'])
      return false;
  }

  isExpiredToken():boolean{
    let token = this.loginService.token;
    if(!token){
      return true
    }
    let payload = this.loginService.getTokenPayload(token);
    let now = new Date().getTime() /1000;
    if(payload.exp<now){
      return true
    } else {
      return false
    }
  }
  
}
