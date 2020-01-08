import { TestBed } from '@angular/core/testing';

import { CaracteristiqueRepositoryService } from './caracteristique-repository.service';

describe('CaracteristiqueRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CaracteristiqueRepositoryService = TestBed.get(CaracteristiqueRepositoryService);
    expect(service).toBeTruthy();
  });
});
