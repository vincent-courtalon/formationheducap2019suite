import { TestBed } from '@angular/core/testing';

import { AuthManagerService } from './auth-manager.service';

describe('AuthManagerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthManagerService = TestBed.get(AuthManagerService);
    expect(service).toBeTruthy();
  });
});
