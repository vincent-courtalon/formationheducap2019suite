import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Caracteristique } from '../metier/caracteristique';

@Injectable({
  providedIn: 'root'
})
export class CaracteristiqueRepositoryService {
  private serviceurl: string = 'http://localhost:8080/caracteristiques';

  constructor(private http : HttpClient) { }


  public getListeCaracteristiques() : Observable<Caracteristique[]> {
    return this.http.get<Caracteristique[]>(this.serviceurl);
  }

}
