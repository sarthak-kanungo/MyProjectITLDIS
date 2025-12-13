import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NgswSearchTableComponent } from './ngsw-search-table.component';

describe('NgswSearchTableComponent', () => {
  let component: NgswSearchTableComponent;
  let fixture: ComponentFixture<NgswSearchTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NgswSearchTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NgswSearchTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
