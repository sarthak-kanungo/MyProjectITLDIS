import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BasicMrcDetailsComponent } from './basic-mrc-details.component';

describe('BasicMrcDetailsComponent', () => {
  let component: BasicMrcDetailsComponent;
  let fixture: ComponentFixture<BasicMrcDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BasicMrcDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BasicMrcDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
