import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WcrViewModalComponent } from './wcr-view-modal.component';

describe('WcrViewModalComponent', () => {
  let component: WcrViewModalComponent;
  let fixture: ComponentFixture<WcrViewModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WcrViewModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WcrViewModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
