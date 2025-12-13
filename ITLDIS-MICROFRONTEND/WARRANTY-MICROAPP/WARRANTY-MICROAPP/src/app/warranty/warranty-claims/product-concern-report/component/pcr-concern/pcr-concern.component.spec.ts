import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PcrConcernComponent } from './pcr-concern.component';

describe('PcrConcernComponent', () => {
  let component: PcrConcernComponent;
  let fixture: ComponentFixture<PcrConcernComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PcrConcernComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PcrConcernComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
