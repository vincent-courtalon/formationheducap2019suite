import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthManagerService } from 'src/app/services/auth-manager.service';
import { Utilisateur } from 'src/app/metier/Utilisateur';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-utilisateur-status',
  templateUrl: './utilisateur-status.component.html',
  styleUrls: ['./utilisateur-status.component.css']
})
export class UtilisateurStatusComponent implements OnInit, OnDestroy {

  constructor(private authManager : AuthManagerService, private router : Router) { }

  public currentUser : [Utilisateur, boolean];
  private userSubscription : Subscription;

  ngOnInit() {
    this.currentUser = [null, false];
    this.userSubscription = this.authManager.getUserAsObservable()
                                            .subscribe(u => this.currentUser = u);
  }
  ngOnDestroy(): void {
    this.userSubscription.unsubscribe();
  }

  logout() : void {
    this.authManager.setCurrentUser(null);
    this.router.navigateByUrl("/login");
  }

}
