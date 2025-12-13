import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingnominationCreatePageComponent } from './training-nomination-create-page.component';

describe('TrainingnominationCreatePageComponent', () => {
  let component: TrainingnominationCreatePageComponent;
  let fixture: ComponentFixture<TrainingnominationCreatePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainingnominationCreatePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingnominationCreatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
