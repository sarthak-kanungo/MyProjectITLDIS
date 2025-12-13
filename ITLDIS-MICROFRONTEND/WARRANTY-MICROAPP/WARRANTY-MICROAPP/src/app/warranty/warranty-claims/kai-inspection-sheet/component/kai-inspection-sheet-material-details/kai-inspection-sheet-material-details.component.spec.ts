import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KaiInspectionSheetMaterialDetailsComponent } from './kai-inspection-sheet-material-details.component';

describe('KaiInspectionSheetMaterialDetailsComponent', () => {
  let component: KaiInspectionSheetMaterialDetailsComponent;
  let fixture: ComponentFixture<KaiInspectionSheetMaterialDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KaiInspectionSheetMaterialDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KaiInspectionSheetMaterialDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
