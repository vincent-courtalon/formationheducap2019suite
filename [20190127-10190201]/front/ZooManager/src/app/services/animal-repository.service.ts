import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Page } from '../metier/page';
import { Animal } from '../metier/Animal';

@Injectable({
  providedIn: 'root'
})
export class AnimalRepositoryService {

  private serviceUrl : string = "http://localhost:8080/animaux"

  private animauxSubject : BehaviorSubject<Page<Animal>>;
  private pageNo : number;

  public setPageNo(pageNo : number) {
    this.pageNo = pageNo;
  }

  constructor(private http: HttpClient) { 
    this.animauxSubject = new BehaviorSubject<Page<Animal>>(Page.emptyPage());
  }

  public getAnimauxAsObservable() : Observable<Page<Animal>> {
    return this.animauxSubject.asObservable();
  }

  public refreshAnimaux() : void {
    let urlParams : HttpParams = new HttpParams().set("page", ''+ this.pageNo);
    this.http.get<Page<Animal>>(this.serviceUrl, {params: urlParams}).subscribe( p => this.animauxSubject.next(p));
  }



}
