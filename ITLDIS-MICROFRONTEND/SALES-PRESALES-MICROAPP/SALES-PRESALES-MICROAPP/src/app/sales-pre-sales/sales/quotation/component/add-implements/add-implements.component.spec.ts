import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddImplementsComponent } from './add-implements.component';

describe('AddImplementsComponent', () => {
  let component: AddImplementsComponent;
  let fixture: ComponentFixture<AddImplementsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddImplementsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddImplementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
