import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KaiInspectionSheetCreatePageComponent } from './kai-inspection-sheet-create-page.component';

describe('KaiInspectionSheetCreatePageComponent', () => {
  let component: KaiInspectionSheetCreatePageComponent;
  let fixture: ComponentFixture<KaiInspectionSheetCreatePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KaiInspectionSheetCreatePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KaiInspectionSheetCreatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
