import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SapSearchComponent } from './sap-search.component';

describe('SapSearchComponent', () => {
  let component: SapSearchComponent;
  let fixture: ComponentFixture<SapSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SapSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SapSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
