import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllDealerAutionlistComponent } from './all-dealer-autionlist.component';

describe('AllDealerAutionlistComponent', () => {
  let component: AllDealerAutionlistComponent;
  let fixture: ComponentFixture<AllDealerAutionlistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllDealerAutionlistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllDealerAutionlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
