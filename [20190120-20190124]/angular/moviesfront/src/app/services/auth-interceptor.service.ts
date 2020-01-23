import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from "rxjs/operators";
import { Router } from '@angular/router';
import { AuthManagerService } from './auth-manager.service';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor
 {

  constructor(private router: Router, private authManager : AuthManagerService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("requette sortante: " + req.method + " - " + req.url);
    // verigier si on est loggé
    if (this.authManager.isLoggedIn()) {
      // petit piege, req est immutable
      // on ajoute le header authorization si on est loggé
      req = req.clone({setHeaders : {Authorization: `Basic ${this.authManager.getCredentials()}`}});
    }

    return next.handle(req)
               .pipe(catchError((error, caught) => {
                 console.log("erreur " + error);
                 if (error instanceof HttpErrorResponse) {
                   let resp : HttpErrorResponse = error;
                   console.log("statut = " +  resp.status + ", " + resp.statusText)
                   if (resp.status == 401 || resp.status == 403) {
                     // on doit s'authentifier, car non loggé ou pas de droits suffisants
                    this.router.navigateByUrl("/login");
                   }
                 }
                 return throwError(error);
               }));
  }

}
