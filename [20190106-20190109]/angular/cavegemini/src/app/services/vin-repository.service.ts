import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Vin } from '../metier/vin';
import { Terroir } from '../metier/terroir';

@Injectable({
  providedIn: 'root'
})
export class VinRepositoryService {

  private serviceurl: string = 'http://localhost:8080/vins';

  private vinsSubject : BehaviorSubject<Vin[]>;
  private filtreTerroirId : number;
  private filtreCaracteristiquesId : number[];


  constructor(private http : HttpClient ) { 
    this.vinsSubject = new BehaviorSubject([]);
    this.filtreTerroirId = 0;
    this.filtreCaracteristiquesId = [];
  }

  public setFiltreTerroirId(id : number) {
    this.filtreTerroirId = id;
    this.refreshListe();
  }

  public setFiltreCaracteristiquesId(caracteristiquesId : number[]) : void {
    this.filtreCaracteristiquesId = caracteristiquesId;
    this.refreshListe();
  }

  public refreshListe() : void {
    let urlParam : HttpParams = new HttpParams();
    if (this.filtreTerroirId > 0) {
      urlParam = urlParam.set('terroirId', '' + this.filtreTerroirId);
    }
    if (this.filtreCaracteristiquesId && this.filtreCaracteristiquesId.length > 0){
      urlParam = urlParam.set('caracteristiquesId', this.filtreCaracteristiquesId.join(','));
    }
    this.http.get<any>(this.serviceurl, {params: urlParam}).subscribe(
      data => this.vinsSubject.next(data.content)
    );
  }

  public getVinsAsObservable() : Observable<Vin[]> {
    return this.vinsSubject.asObservable();
  }

  public findVinById(id : number) : Observable<Vin> {
    return this.http.get<Vin>(`${this.serviceurl}/${id}`);
  }

  public listeTerroirs() : Observable<Terroir[]> {
    return this.http.get<Terroir[]>(`${this.serviceurl}/terroirs`);
  }
/*
  public updateVin(vin : Vin, idTerroir : number) : Observable<Vin> {
    let urlParam : HttpParams = new HttpParams().set('nom' , vin.nom)
                                                .set('annee', '' + vin.annee)
                                                .set('idTerroir', '' + idTerroir);
    return this.http.put<Vin>(`${this.serviceurl}/${vin.id}`, {}, {params : urlParam});

  }
*/
  public updateVin(vin : Vin, idTerroir : number, idCaracteristiques : number[]) : Observable<Vin> {
    let urlParam : HttpParams = 
          new HttpParams().set('idTerroir', '' + idTerroir)
                          .set('idCaracteristiques', idCaracteristiques.join(','));
    return this.http.put<Vin>(`${this.serviceurl}/${vin.id}`, vin, {params : urlParam});

  }


}
