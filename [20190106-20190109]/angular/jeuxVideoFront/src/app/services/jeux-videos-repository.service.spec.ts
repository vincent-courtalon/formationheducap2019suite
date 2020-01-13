import { TestBed } from '@angular/core/testing';

import { JeuxVideosRepositoryService } from './jeux-videos-repository.service';

describe('JeuxVideosRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: JeuxVideosRepositoryService = TestBed.get(JeuxVideosRepositoryService);
    expect(service).toBeTruthy();
  });
});
