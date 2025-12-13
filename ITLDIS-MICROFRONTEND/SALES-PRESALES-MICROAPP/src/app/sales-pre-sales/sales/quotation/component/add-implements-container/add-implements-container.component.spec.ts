import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddImplementsContainerComponent } from './add-implements-container.component';

describe('AddImplementsContainerComponent', () => {
  let component: AddImplementsContainerComponent;
  let fixture: ComponentFixture<AddImplementsContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddImplementsContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddImplementsContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
