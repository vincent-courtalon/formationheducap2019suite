import { Component, OnInit } from '@angular/core';
import { MessageRepositoryService } from './services/message-repository.service';
import { Observable } from 'rxjs';
import { Message } from './metier/message';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'xssfront';
  public messages : Observable<Message[]>;

  constructor(private messageRepository: MessageRepositoryService) {}

  ngOnInit(): void {
    this.messages = this.messageRepository.gelListeMessages();
  }

}
