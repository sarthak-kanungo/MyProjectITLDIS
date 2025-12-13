import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DealerDesignationMasterComponent } from './dealer-designation-master.component';

describe('DealerDesignationMasterComponent', () => {
  let component: DealerDesignationMasterComponent;
  let fixture: ComponentFixture<DealerDesignationMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DealerDesignationMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DealerDesignationMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
