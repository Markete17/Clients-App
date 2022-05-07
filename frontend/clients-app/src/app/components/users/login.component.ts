import { Component, OnInit } from '@angular/core';
import { User } from './user';
import Swal from 'sweetalert2';
import { LoginService } from './login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  title: string = "Log In"
  user:User;

  constructor(private loginService:LoginService, private router:Router ) {
    this.user = new User();
  }

  

  ngOnInit(): void {
    if(this.loginService.isAuthenticated()){
      Swal.fire('Login',`Hi! ${this.loginService.user.username} you are already authenticated`,'info')
      this.router.navigate(['/clients'])
    }
  }

  login():void{
    console.log(this.user);
    if(this.user.username == null || this.user.password == null){
      Swal.fire("Login Error","Empty username or password",'error')
      return;
    }

    this.loginService.login(this.user).subscribe({
      next: response =>{
      
      let payload = JSON.parse(atob(response.access_token.split(".")[1]))
      
      this.loginService.saveUser(response.access_token);
      this.loginService.saveToken(response.access_token);

      let user = this.loginService.user


      this.router.navigate(['/clients'])
      Swal.fire('Login',`Hi ${user.username} login successfully`,'success')
    },
    error: e =>{
      if(e.status == 400){
        Swal.fire("Login Error","Incorrect username or password",'error')
      }
    }
    })
  }
}
