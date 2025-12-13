import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchDealerTerritoryMappingComponent } from './search-dealer-territory-mapping.component';

describe('SearchDealerTerritoryMappingComponent', () => {
  let component: SearchDealerTerritoryMappingComponent;
  let fixture: ComponentFixture<SearchDealerTerritoryMappingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchDealerTerritoryMappingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchDealerTerritoryMappingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
