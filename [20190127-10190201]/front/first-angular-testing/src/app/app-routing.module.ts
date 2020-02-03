import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CompteurAfficheComponent } from './components/compteur-affiche/compteur-affiche.component';
import { ShoppingListeComponent } from './components/shopping-liste/shopping-liste.component';


const routes: Routes = [
  { path: 'compteur', component: CompteurAfficheComponent},
  { path: 'shoppingliste', component: ShoppingListeComponent},
  { path: '', pathMatch: 'full', redirectTo: '/compteur'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
