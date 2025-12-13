import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartReturnComponent } from './part-return.component';

describe('PartReturnComponent', () => {
  let component: PartReturnComponent;
  let fixture: ComponentFixture<PartReturnComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartReturnComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartReturnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
