import { Component } from "@angular/core";
import { Router } from "@angular/router";
import Swal from "sweetalert2";
import { LoginService } from "../users/login.service";

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: []
})
export class HeaderComponent{
    title: string = 'ClientsApp'

    constructor(public loginService:LoginService,private router:Router) {
    }


    isAuthenticated():boolean{
        return this.loginService.isAuthenticated();
    }

    logout():void{
        
        Swal.fire("Logout",`Hi ${this.loginService.user.username}, you has logout successfully`,'success');
        this.loginService.logout();
        this.router.navigate(['/login'])
    }
    
}