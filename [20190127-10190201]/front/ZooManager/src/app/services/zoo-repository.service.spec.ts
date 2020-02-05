import { TestBed } from '@angular/core/testing';

import { ZooRepositoryService } from './zoo-repository.service';

describe('ZooRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ZooRepositoryService = TestBed.get(ZooRepositoryService);
    expect(service).toBeTruthy();
  });
});
