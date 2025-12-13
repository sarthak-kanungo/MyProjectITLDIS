import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeAllotmentSearchComponent } from './de-allotment-search.component';

describe('DeAllotmentSearchComponent', () => {
  let component: DeAllotmentSearchComponent;
  let fixture: ComponentFixture<DeAllotmentSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeAllotmentSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeAllotmentSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
