import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingnominationSearchPageComponent } from './training-nomination-search-page.component';

describe('TrainingnominationSearchPageComponent', () => {
  let component: TrainingnominationSearchPageComponent;
  let fixture: ComponentFixture<TrainingnominationSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainingnominationSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingnominationSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
