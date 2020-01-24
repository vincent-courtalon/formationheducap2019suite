import { TestBed } from '@angular/core/testing';

import { MessageRepositoryService } from './message-repository.service';

describe('MessageRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MessageRepositoryService = TestBed.get(MessageRepositoryService);
    expect(service).toBeTruthy();
  });
});
