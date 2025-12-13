import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BaySearchResultComponent } from './bay-search-result.component';

describe('BaySearchResultComponent', () => {
  let component: BaySearchResultComponent;
  let fixture: ComponentFixture<BaySearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BaySearchResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BaySearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
