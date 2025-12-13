import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PcrViewModalComponent } from './pcr-view-modal.component';

describe('PcrViewModalComponent', () => {
  let component: PcrViewModalComponent;
  let fixture: ComponentFixture<PcrViewModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PcrViewModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PcrViewModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
