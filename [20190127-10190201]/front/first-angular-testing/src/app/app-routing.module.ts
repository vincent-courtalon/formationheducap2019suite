import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CompteurAfficheComponent } from './components/compteur-affiche/compteur-affiche.component';


const routes: Routes = [
  { path: 'compteur', component: CompteurAfficheComponent},
  { path: '', pathMatch: 'full', redirectTo: '/compteur'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
