import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AffichageJeuxComponent } from './components/affichage-jeux/affichage-jeux.component';
import { EditJeuxComponent } from './components/edit-jeux/edit-jeux.component';


const routes: Routes = [
  { path:'liste' , component: AffichageJeuxComponent},
  { path: 'edit/:id', component : EditJeuxComponent},
  { path: '', pathMatch:'full', redirectTo: '/liste'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
