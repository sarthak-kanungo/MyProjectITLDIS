import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KaiInspectionSheetKQuicDetailsComponent } from './kai-inspection-sheet-k-quic-details.component';

describe('KaiInspectionSheetKQuicDetailsComponent', () => {
  let component: KaiInspectionSheetKQuicDetailsComponent;
  let fixture: ComponentFixture<KaiInspectionSheetKQuicDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KaiInspectionSheetKQuicDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KaiInspectionSheetKQuicDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
