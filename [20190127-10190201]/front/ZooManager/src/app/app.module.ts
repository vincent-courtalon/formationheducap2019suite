import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
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
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
