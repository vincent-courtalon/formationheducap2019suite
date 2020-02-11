import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ModelListComponent } from './components/model-list/model-list.component';


const routes: Routes = [
  { path: 'models', component: ModelListComponent},
  { path: '', pathMatch: 'full', redirectTo: '/models'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
