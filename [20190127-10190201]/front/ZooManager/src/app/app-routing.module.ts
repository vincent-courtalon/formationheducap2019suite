import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ZooListeComponent } from './components/zoo-liste/zoo-liste.component';
import { AnimauxListeComponent } from './components/animaux-liste/animaux-liste.component';


const routes: Routes = [
  {path: 'zoos', component: ZooListeComponent},
  {path: 'animaux', component: AnimauxListeComponent},
  {path: '', pathMatch: 'full', redirectTo: '/zoos'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
