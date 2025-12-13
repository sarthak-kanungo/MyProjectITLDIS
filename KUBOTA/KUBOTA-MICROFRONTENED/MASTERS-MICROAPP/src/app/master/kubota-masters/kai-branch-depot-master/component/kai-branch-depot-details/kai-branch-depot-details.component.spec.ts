import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KaiBranchDepotDetailsComponent } from './kai-branch-depot-details.component';

describe('KaiBranchDepotDetailsComponent', () => {
  let component: KaiBranchDepotDetailsComponent;
  let fixture: ComponentFixture<KaiBranchDepotDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KaiBranchDepotDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KaiBranchDepotDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
