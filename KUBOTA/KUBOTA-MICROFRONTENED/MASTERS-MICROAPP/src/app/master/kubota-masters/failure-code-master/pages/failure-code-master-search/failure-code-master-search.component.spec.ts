import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FailureCodeMasterSearchComponent } from './failure-code-master-search.component';

describe('FailureCodeMasterSearchComponent', () => {
  let component: FailureCodeMasterSearchComponent;
  let fixture: ComponentFixture<FailureCodeMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FailureCodeMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FailureCodeMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
