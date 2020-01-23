import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/metier/movie';
import { MovieRepositoryService } from 'src/app/services/movie-repository.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movie-edit',
  templateUrl: './movie-edit.component.html',
  styleUrls: ['./movie-edit.component.css']
})
export class MovieEditComponent implements OnInit {

  public editMovie : Movie;
  constructor(private movieRepository : MovieRepositoryService, private router : Router) {


   }

  ngOnInit() {
    this.editMovie = new Movie(0, "", null,60);
  }

  public trySave() : void{
    this.movieRepository.saveMovie(this.editMovie)
                        .subscribe( m => this.router.navigateByUrl("/"));
  }

}
