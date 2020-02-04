import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AchatsRepositoryService {
  
  constructor() { }

  public getListeAchats() : string[] {
    // this.http.get .....   !!!! ne marche pas en test
    return ["fraise tagada", "nouvelle poignee porte", "coca"];
  }

}
