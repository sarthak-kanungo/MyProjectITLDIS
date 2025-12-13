import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WarrentyClaimRequestComponent } from './warrenty-claim-request.component';

describe('WarrentyClaimRequestComponent', () => {
  let component: WarrentyClaimRequestComponent;
  let fixture: ComponentFixture<WarrentyClaimRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WarrentyClaimRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WarrentyClaimRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
