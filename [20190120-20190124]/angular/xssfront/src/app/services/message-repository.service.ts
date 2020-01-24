import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Message } from '../metier/message';

@Injectable({
  providedIn: 'root'
})
export class MessageRepositoryService {

  private serviceUrl : string = "http://localhost:8080/messages";

  constructor(private http: HttpClient) { }

  public gelListeMessages() : Observable<Message[]> {
    return this.http.get<Message[]>(this.serviceUrl);
  }

}
