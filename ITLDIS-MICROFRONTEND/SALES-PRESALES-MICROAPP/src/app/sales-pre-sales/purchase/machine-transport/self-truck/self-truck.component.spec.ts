import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelfTruckComponent } from './self-truck.component';

describe('SelfTruckComponent', () => {
  let component: SelfTruckComponent;
  let fixture: ComponentFixture<SelfTruckComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelfTruckComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelfTruckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
