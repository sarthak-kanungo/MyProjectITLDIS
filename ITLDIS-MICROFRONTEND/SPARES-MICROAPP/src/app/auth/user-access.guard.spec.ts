import { TestBed, async, inject } from '@angular/core/testing';

import { UserAccessGuard } from './user-access.guard';

describe('UserAccessGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserAccessGuard]
    });
  });

  it('should ...', inject([UserAccessGuard], (guard: UserAccessGuard) => {
    expect(guard).toBeTruthy();
  }));
});
