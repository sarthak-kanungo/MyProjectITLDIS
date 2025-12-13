import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelfRunComponent } from './self-run.component';

describe('SelfRunComponent', () => {
  let component: SelfRunComponent;
  let fixture: ComponentFixture<SelfRunComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelfRunComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelfRunComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
