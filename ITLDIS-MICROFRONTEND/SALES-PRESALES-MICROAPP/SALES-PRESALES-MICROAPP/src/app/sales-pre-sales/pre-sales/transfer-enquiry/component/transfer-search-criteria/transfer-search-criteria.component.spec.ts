import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferSearchCriteriaComponent } from './transfer-search-criteria.component';

describe('TransferSearchCriteriaComponent', () => {
  let component: TransferSearchCriteriaComponent;
  let fixture: ComponentFixture<TransferSearchCriteriaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransferSearchCriteriaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransferSearchCriteriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
