import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PdcSearchPageComponent } from './pdc-search-page.component';

describe('PdcSearchPageComponent', () => {
  let component: PdcSearchPageComponent;
  let fixture: ComponentFixture<PdcSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PdcSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PdcSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
