import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DealerMasterDetailsComponent } from './dealer-master-details.component';

describe('DealerMasterDetailsComponent', () => {
  let component: DealerMasterDetailsComponent;
  let fixture: ComponentFixture<DealerMasterDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DealerMasterDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DealerMasterDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
