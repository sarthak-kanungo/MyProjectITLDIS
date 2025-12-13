import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { itldisEmpolyeeMasterCreateComponent } from './itldis-empolyee-master-create.component';

describe('itldisEmpolyeeMasterCreateComponent', () => {
  let component: itldisEmpolyeeMasterCreateComponent;
  let fixture: ComponentFixture<itldisEmpolyeeMasterCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ itldisEmpolyeeMasterCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(itldisEmpolyeeMasterCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
