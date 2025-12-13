import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewPartsClaimSearchComponent } from './new-parts-claim-search.component';

describe('NewPartsClaimSearchComponent', () => {
  let component: NewPartsClaimSearchComponent;
  let fixture: ComponentFixture<NewPartsClaimSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewPartsClaimSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewPartsClaimSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
