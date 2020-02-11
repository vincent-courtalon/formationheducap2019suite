import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-model-list',
  templateUrl: './model-list.component.html',
  styleUrls: ['./model-list.component.css']
})
export class ModelListComponent implements OnInit {

  public rotationSpeedX : number = 0.005;
  public rotationSpeedY : number = 0.01;

  constructor() { }

  ngOnInit() {
  }

}
