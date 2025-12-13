import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BayMasterComponent } from './bay-master.component';

describe('BayMasterComponent', () => {
  let component: BayMasterComponent;
  let fixture: ComponentFixture<BayMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BayMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BayMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
