import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-shopping-summary',
  templateUrl: './shopping-summary.component.html',
  styleUrls: ['./shopping-summary.component.css']
})
export class ShoppingSummaryComponent implements OnInit {

  @Input("totalCount") totalCount : number;

  constructor() { }

  ngOnInit() {
  }

}
