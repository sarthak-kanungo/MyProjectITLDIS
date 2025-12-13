import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncentiveSchemeMasterCreateComponent } from './incentive-scheme-master-create.component';

describe('IncentiveSchemeMasterCreateComponent', () => {
  let component: IncentiveSchemeMasterCreateComponent;
  let fixture: ComponentFixture<IncentiveSchemeMasterCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncentiveSchemeMasterCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncentiveSchemeMasterCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
