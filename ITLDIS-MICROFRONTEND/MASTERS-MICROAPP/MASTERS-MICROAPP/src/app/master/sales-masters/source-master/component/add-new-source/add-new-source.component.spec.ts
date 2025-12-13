import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewSourceComponent } from './add-new-source.component';

describe('AddNewSourceComponent', () => {
  let component: AddNewSourceComponent;
  let fixture: ComponentFixture<AddNewSourceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddNewSourceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewSourceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
