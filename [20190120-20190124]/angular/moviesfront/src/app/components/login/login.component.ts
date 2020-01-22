import { Component, OnInit } from '@angular/core';
import { environment } from "../../../environments/environment";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private loginUrl : string;

  constructor() { }

  ngOnInit() {
    this.loginUrl = `${environment.restapibaseurl}/mylogin`;
    
  }

}
