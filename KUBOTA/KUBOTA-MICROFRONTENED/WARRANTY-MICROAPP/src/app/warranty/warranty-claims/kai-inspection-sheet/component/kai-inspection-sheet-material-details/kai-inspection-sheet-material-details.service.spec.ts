import { TestBed } from '@angular/core/testing';

import { KaiInspectionSheetMaterialDetailsService } from './kai-inspection-sheet-material-details.service';

describe('KaiInspectionSheetMaterialDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: KaiInspectionSheetMaterialDetailsService = TestBed.get(KaiInspectionSheetMaterialDetailsService);
    expect(service).toBeTruthy();
  });
});
