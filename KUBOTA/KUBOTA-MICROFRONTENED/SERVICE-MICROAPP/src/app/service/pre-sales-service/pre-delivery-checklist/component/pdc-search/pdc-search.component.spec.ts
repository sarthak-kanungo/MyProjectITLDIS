import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PdcSearchComponent } from './pdc-search.component';

describe('PdcSearchComponent', () => {
  let component: PdcSearchComponent;
  let fixture: ComponentFixture<PdcSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PdcSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PdcSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
