import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-model-list',
  templateUrl: './model-list.component.html',
  styleUrls: ['./model-list.component.css']
})
export class ModelListComponent implements OnInit {

  public rotationSpeedX : number = 0.005;
  public rotationSpeedY : number = 0.01;
  public couleur : string = "#11FF22";
  public scale: number = 1.0;
  
  constructor() { }

  ngOnInit() {
  }

}
