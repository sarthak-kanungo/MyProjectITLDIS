import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { itldisEmpolyeeMasterSearchComponent } from './itldis-empolyee-master-search.component';

describe('itldisEmpolyeeMasterSearchComponent', () => {
  let component: itldisEmpolyeeMasterSearchComponent;
  let fixture: ComponentFixture<itldisEmpolyeeMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ itldisEmpolyeeMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(itldisEmpolyeeMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
