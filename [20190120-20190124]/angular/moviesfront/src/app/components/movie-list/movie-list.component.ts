import { Component, OnInit, OnDestroy } from '@angular/core';
import { MovieRepositoryService } from 'src/app/services/movie-repository.service';
import { Movie } from 'src/app/metier/movie';
import { Subscription } from 'rxjs';
import { Page } from 'src/app/metier/page';
import { AuthManagerService } from 'src/app/services/auth-manager.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit, OnDestroy {


  public movies : Movie[] = [];
  private moviesSubscription : Subscription;

  constructor( private moviesRepository : MovieRepositoryService,
               private authManager : AuthManagerService) { }

  ngOnInit() {
    this.moviesSubscription = this.moviesRepository.getMoviesAsObservable()
                                  .subscribe(p => this.movies = p.content, 
                                    error => this.movies = []);
    this.moviesRepository.refreshPageMovie();  
  }

  ngOnDestroy(): void {
    this.moviesSubscription.unsubscribe();
  }

  public isAdmin() : boolean {
    return this.authManager.userHasRole('ROLE_ADMIN');
  }

  /*creerFilm() : void {
    console.log("création de film");

  }*/
}
