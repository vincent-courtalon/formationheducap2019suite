import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from "@angular/forms";
import { AppComponent } from './app.component';
import { CompteurAfficheComponent } from './components/compteur-affiche/compteur-affiche.component';
import { ShoppingListeComponent } from './components/shopping-liste/shopping-liste.component';
import { ShoppingSummaryComponent } from './components/shopping-summary/shopping-summary.component';
import { ShoppingFilterComponent } from './components/shopping-filter/shopping-filter.component';

@NgModule({
  declarations: [
    AppComponent,
    CompteurAfficheComponent,
    ShoppingListeComponent,
    ShoppingSummaryComponent,
    ShoppingFilterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
