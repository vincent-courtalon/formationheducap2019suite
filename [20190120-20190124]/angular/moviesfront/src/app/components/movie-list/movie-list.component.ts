import { Component, OnInit, OnDestroy } from '@angular/core';
import { MovieRepositoryService } from 'src/app/services/movie-repository.service';
import { Movie } from 'src/app/metier/movie';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit, OnDestroy {


  public movies : Movie[] = [];
  private moviesSubscription : Subscription;

  constructor( private moviesRepository : MovieRepositoryService) { }

  ngOnInit() {
    this.moviesSubscription = this.moviesRepository.getMoviesAsObservable()
                                  .subscribe(p => this.movies = p.content);
    this.moviesRepository.refreshPageMovie();  
  }

  ngOnDestroy(): void {
    this.moviesSubscription.unsubscribe();
  }
}
