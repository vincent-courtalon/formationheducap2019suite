import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-shopping-filter',
  templateUrl: './shopping-filter.component.html',
  styleUrls: ['./shopping-filter.component.css']
})
export class ShoppingFilterComponent implements OnInit {

  @Output() filterTerm : EventEmitter<string>;

  public searchTerm : string;

  constructor() {
    this.filterTerm = new EventEmitter<string>();
    this.searchTerm = "";
   }

  ngOnInit() {
  }

  public searchTermChanged(evt : string) : void {
    this.filterTerm.emit(evt);
  }

}
