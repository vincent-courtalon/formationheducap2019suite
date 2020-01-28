import { Injectable } from '@angular/core';
import { Utilisateur } from '../metier/Utilisateur';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthManagerService {

  private currentUser : Utilisateur;

  private userSubject : BehaviorSubject<[Utilisateur, boolean]>;


  constructor() { 
    this.currentUser = null;
    this.userSubject = new BehaviorSubject([this.currentUser, false]);
  }

  public getUserAsObservable() : Observable<[Utilisateur, boolean]> {
    return this.userSubject.asObservable();
  }

  public userHasRole(rolename : string) : boolean {
    if (this.isLoggedIn() && this.currentUser.roles) {
      return this.currentUser.roles.findIndex(r => r.roleName == rolename) != -1;
    }
    return false;
  }

  public getCurrentUser() : Utilisateur {
    return this.currentUser;
  }

  public setCurrentUser(utilisateur : Utilisateur) : void {
    this.currentUser = utilisateur;
    if (this.currentUser == null) 
      this.userSubject.next([this.currentUser, false]);
    else 
      this.userSubject.next([this.currentUser, true]);
  }

  public isLoggedIn() : boolean {
    return this.currentUser != null;
  }

  public getCredentials() : string {
    return window.btoa(this.currentUser.login + ":" + this.currentUser.password);
  }

}
