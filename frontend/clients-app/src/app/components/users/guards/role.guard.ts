import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from '../login.service';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  
  constructor(private loginService:LoginService,private router:Router){}
  
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
    if(!this.loginService.isAuthenticated()){
      this.router.navigate(['/login']);
      return false;
    }
      let role = route.data['role'] as string;
    if(this.loginService.hasRole(role)){
      return true
    }
    Swal.fire('Denied Access',`Hi ${this.loginService.user.username} you do not have access in this resource`,'warning')
    this.router.navigate(['/clients']);
    return false;
  }
  
}
