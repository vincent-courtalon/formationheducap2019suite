import { Injectable } from '@angular/core';
import { Utilisateur } from '../metier/Utilisateur';

@Injectable({
  providedIn: 'root'
})
export class AuthManagerService {

  private currentUser : Utilisateur;


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
  }

  public isLoggedIn() : boolean {
    return this.currentUser != null;
  }

  public getCredentials() : string {
    return window.btoa(this.currentUser.login + ":" + this.currentUser.password);
  }

  constructor() { 
    this.currentUser = null;
  }
}
