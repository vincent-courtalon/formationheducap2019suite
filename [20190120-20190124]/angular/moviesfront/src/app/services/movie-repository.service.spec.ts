import { TestBed } from '@angular/core/testing';

import { MovieRepositoryService } from './movie-repository.service';

describe('MovieRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MovieRepositoryService = TestBed.get(MovieRepositoryService);
    expect(service).toBeTruthy();
  });
});
