import { TestBed } from '@angular/core/testing';

import { InventoryAdjustmentService } from './inventory-adjustment.service';

describe('InventoryAdjustmentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InventoryAdjustmentService = TestBed.get(InventoryAdjustmentService);
    expect(service).toBeTruthy();
  });
});
