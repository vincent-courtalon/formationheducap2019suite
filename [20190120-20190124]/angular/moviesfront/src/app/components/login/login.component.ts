import { Component, OnInit } from '@angular/core';
import { environment } from "../../../environments/environment";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Utilisateur } from 'src/app/metier/Utilisateur';
import { AuthManagerService } from 'src/app/services/auth-manager.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private loginUrl : string;
  public userLogin : any;

  constructor(private http : HttpClient, private router : Router, private authmanager : AuthManagerService) { }

  ngOnInit() {
    this.loginUrl = `${environment.restapibaseurl}/mylogin`;
    this.userLogin = { login: "", password: ""};

  }

  trylogin() : void {
    console.log("tentative de login avec " + this.userLogin.login + " - " + this.userLogin.password);
    let credentials : string = window.btoa(this.userLogin.login + ":" + this.userLogin.password);
    console.log("credentials : " + credentials);
    // on enleve l'utilisateur courant si présent
    this.authmanager.setCurrentUser(null);
    let headers : HttpHeaders  = new HttpHeaders({Authorization : `Basic ${credentials}`});
    this.http.get<Utilisateur>(this.loginUrl, {headers : headers}).subscribe( u => {
      console.log(u);
      // remettre le pass
      u.password = this.userLogin.password;
      this.authmanager.setCurrentUser(u);
      this.router.navigateByUrl("/");
    });
  }

}
