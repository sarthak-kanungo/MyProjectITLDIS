import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WcrPartsStatusComponent } from './wcr-parts-status.component';

describe('WcrPartsStatusComponent', () => {
  let component: WcrPartsStatusComponent;
  let fixture: ComponentFixture<WcrPartsStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WcrPartsStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WcrPartsStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
