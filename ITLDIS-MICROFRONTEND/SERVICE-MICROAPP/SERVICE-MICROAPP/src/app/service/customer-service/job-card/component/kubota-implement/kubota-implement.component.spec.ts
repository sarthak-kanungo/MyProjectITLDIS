import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { itldisImplementComponent } from './itldis-implement.component';

describe('itldisImplementComponent', () => {
  let component: itldisImplementComponent;
  let fixture: ComponentFixture<itldisImplementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ itldisImplementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(itldisImplementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
