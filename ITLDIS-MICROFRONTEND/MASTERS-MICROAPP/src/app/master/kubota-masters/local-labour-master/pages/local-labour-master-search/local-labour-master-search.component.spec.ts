import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalLabourMasterSearchComponent } from './local-labour-master-search.component';

describe('LocalLabourMasterSearchComponent', () => {
  let component: LocalLabourMasterSearchComponent;
  let fixture: ComponentFixture<LocalLabourMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LocalLabourMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LocalLabourMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
