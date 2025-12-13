import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FunctionalityTableComponent } from './functionality-table.component';

describe('FunctionalityTableComponent', () => {
  let component: FunctionalityTableComponent;
  let fixture: ComponentFixture<FunctionalityTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FunctionalityTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FunctionalityTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
