import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KaiBranchDepotSearchResultComponent } from './kai-branch-depot-search-result.component';

describe('KaiBranchDepotSearchResultComponent', () => {
  let component: KaiBranchDepotSearchResultComponent;
  let fixture: ComponentFixture<KaiBranchDepotSearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KaiBranchDepotSearchResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KaiBranchDepotSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
