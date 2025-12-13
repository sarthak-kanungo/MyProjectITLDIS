import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FunctionMasterPageComponent } from './function-master-page.component';

describe('FunctionMasterPageComponent', () => {
  let component: FunctionMasterPageComponent;
  let fixture: ComponentFixture<FunctionMasterPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FunctionMasterPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FunctionMasterPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
