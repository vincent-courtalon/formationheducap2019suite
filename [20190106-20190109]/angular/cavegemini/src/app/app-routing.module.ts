import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AffichageVinsComponent } from './components/affichage-vins/affichage-vins.component';
import { EditVinComponent } from './components/edit-vin/edit-vin.component';


const routes: Routes = [
  {path : 'vins', component: AffichageVinsComponent },
  {path : 'edit/:id', component: EditVinComponent},
  {path : '', pathMatch : 'full', redirectTo: '/vins'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
