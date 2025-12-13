import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KaiInspectionSheetSearchPageComponent } from './kai-inspection-sheet-search-page.component';

describe('KaiInspectionSheetSearchPageComponent', () => {
  let component: KaiInspectionSheetSearchPageComponent;
  let fixture: ComponentFixture<KaiInspectionSheetSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KaiInspectionSheetSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KaiInspectionSheetSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
