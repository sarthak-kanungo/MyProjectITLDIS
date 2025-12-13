import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateDealerTerritoryMappingComponent } from './create-dealer-territory-mapping.component';

describe('CreateDealerTerritoryMappingComponent', () => {
  let component: CreateDealerTerritoryMappingComponent;
  let fixture: ComponentFixture<CreateDealerTerritoryMappingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateDealerTerritoryMappingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateDealerTerritoryMappingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
