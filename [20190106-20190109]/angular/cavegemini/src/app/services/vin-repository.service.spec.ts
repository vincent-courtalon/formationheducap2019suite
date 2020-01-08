import { TestBed } from '@angular/core/testing';

import { VinRepositoryService } from './vin-repository.service';

describe('VinRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VinRepositoryService = TestBed.get(VinRepositoryService);
    expect(service).toBeTruthy();
  });
});
