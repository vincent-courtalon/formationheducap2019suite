import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { PaginationModule } from "ngx-bootstrap/pagination";

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AnimauxListeComponent } from './components/animaux-liste/animaux-liste.component';
import { ZooListeComponent } from './components/zoo-liste/zoo-liste.component';

@NgModule({
  declarations: [
    AppComponent,
    AnimauxListeComponent,
    ZooListeComponent
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
