import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";

import { AppComponent } from './app.component';
import { EditVinComponent } from './components/edit-vin/edit-vin.component';
import { ListeVinsComponent } from './components/liste-vins/liste-vins.component';
import { FiltreVinsComponent } from './components/filtre-vins/filtre-vins.component';
import { AffichageVinsComponent } from './components/affichage-vins/affichage-vins.component';
import { VinRepositoryService } from './services/vin-repository.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    EditVinComponent,
    ListeVinsComponent,
    FiltreVinsComponent,
    AffichageVinsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule
  ],
  providers: [
    VinRepositoryService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
