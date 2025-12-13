import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchSpareDescripancyClaimComponent } from './search-spare-descripancy-claim.component';

describe('SearchSpareDescripancyClaimComponent', () => {
  let component: SearchSpareDescripancyClaimComponent;
  let fixture: ComponentFixture<SearchSpareDescripancyClaimComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchSpareDescripancyClaimComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchSpareDescripancyClaimComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
