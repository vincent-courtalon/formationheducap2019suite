import { TestBed } from '@angular/core/testing';

import { AchatsRepositoryService } from './achats-repository.service';

describe('AchatsRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AchatsRepositoryService = TestBed.get(AchatsRepositoryService);
    expect(service).toBeTruthy();
  });
});
