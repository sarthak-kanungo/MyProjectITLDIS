import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { itldisSourceMasterSearchComponent } from './itldis-source-master-search.component';

describe('itldisSourceMasterSearchComponent', () => {
  let component: itldisSourceMasterSearchComponent;
  let fixture: ComponentFixture<itldisSourceMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ itldisSourceMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(itldisSourceMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
