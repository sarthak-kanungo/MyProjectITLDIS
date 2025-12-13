import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceClaimCreateComponent } from './service-claim-create.component';

describe('ServiceClaimCreateComponent', () => {
  let component: ServiceClaimCreateComponent;
  let fixture: ComponentFixture<ServiceClaimCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceClaimCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceClaimCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
