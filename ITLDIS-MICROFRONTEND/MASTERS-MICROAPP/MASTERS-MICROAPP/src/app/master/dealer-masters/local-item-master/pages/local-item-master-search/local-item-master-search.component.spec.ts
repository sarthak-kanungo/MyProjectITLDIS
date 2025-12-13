import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalItemMasterSearchComponent } from './local-item-master-search.component';

describe('LocalItemMasterSearchComponent', () => {
  let component: LocalItemMasterSearchComponent;
  let fixture: ComponentFixture<LocalItemMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LocalItemMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LocalItemMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
