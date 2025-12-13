import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PageSpareDescripancyClaimComponent } from './page-spare-descripancy-claim.component';

describe('PageSpareDescripancyClaimComponent', () => {
  let component: PageSpareDescripancyClaimComponent;
  let fixture: ComponentFixture<PageSpareDescripancyClaimComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PageSpareDescripancyClaimComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PageSpareDescripancyClaimComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
