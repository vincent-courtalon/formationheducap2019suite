import { TestBed } from '@angular/core/testing';

import { AnimalRepositoryService } from './animal-repository.service';

describe('AnimalRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AnimalRepositoryService = TestBed.get(AnimalRepositoryService);
    expect(service).toBeTruthy();
  });
});
