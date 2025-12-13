import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BtbtPageComponent } from './btbt-page.component';

describe('BtbtPageComponent', () => {
  let component: BtbtPageComponent;
  let fixture: ComponentFixture<BtbtPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BtbtPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BtbtPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
