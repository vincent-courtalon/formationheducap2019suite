import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";

import { PaginationModule } from "ngx-bootstrap/pagination";
import { AffichageJeuxComponent } from './components/affichage-jeux/affichage-jeux.component';
import { ListeJeuxComponent } from './components/liste-jeux/liste-jeux.component';
import { FiltreJeuxComponent } from './components/filtre-jeux/filtre-jeux.component';
import { EditJeuxComponent } from './components/edit-jeux/edit-jeux.component';

@NgModule({
  declarations: [
    AppComponent,
    AffichageJeuxComponent,
    ListeJeuxComponent,
    FiltreJeuxComponent,
    EditJeuxComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    PaginationModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
