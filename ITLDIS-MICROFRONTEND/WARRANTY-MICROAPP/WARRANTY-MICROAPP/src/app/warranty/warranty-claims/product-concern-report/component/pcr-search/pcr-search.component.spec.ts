import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PcrSearchComponent } from './pcr-search.component';

describe('PcrSearchComponent', () => {
  let component: PcrSearchComponent;
  let fixture: ComponentFixture<PcrSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PcrSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PcrSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
