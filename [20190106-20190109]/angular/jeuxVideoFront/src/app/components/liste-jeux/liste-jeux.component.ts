import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Page } from 'src/app/metier/page';
import { JeuxVideo } from 'src/app/metier/jeux-video';

@Component({
  selector: 'app-liste-jeux',
  templateUrl: './liste-jeux.component.html',
  styleUrls: ['./liste-jeux.component.css']
})
export class ListeJeuxComponent implements OnInit {

  public pageJv : Page<JeuxVideo>;
  public currentPage : number;

  @Input() 
  set jeuxvideos (jv: Page<JeuxVideo>) {
    if (jv) {
      
      this.pageJv = jv;
      this.currentPage = jv.number + 1;
      console.log(this.pageJv.totalElements);
    }
  };

  @Output() changePage  = new EventEmitter<number>();


  pageChanged(event) {
    console.log(event);
    this.changePage.emit(event.page - 1);
  }

 
  constructor() { 
    this.currentPage = 0;
  }

  ngOnInit() {

  }

}
