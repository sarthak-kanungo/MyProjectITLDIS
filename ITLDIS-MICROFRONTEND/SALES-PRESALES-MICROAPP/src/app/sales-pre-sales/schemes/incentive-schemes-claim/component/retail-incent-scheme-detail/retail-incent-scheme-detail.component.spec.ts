import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RetailIncentSchemeDetailComponent } from './retail-incent-scheme-detail.component';

describe('RetailIncentSchemeDetailComponent', () => {
  let component: RetailIncentSchemeDetailComponent;
  let fixture: ComponentFixture<RetailIncentSchemeDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RetailIncentSchemeDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RetailIncentSchemeDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
