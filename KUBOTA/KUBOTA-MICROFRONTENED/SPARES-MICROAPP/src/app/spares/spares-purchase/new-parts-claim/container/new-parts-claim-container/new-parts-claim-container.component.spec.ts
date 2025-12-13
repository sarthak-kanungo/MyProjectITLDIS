import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewPartsClaimContainerComponent } from './new-parts-claim-container.component';

describe('NewPartsClaimContainerComponent', () => {
  let component: NewPartsClaimContainerComponent;
  let fixture: ComponentFixture<NewPartsClaimContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewPartsClaimContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewPartsClaimContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
