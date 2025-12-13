import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KubotaEmpolyeeMasterSearchComponent } from './kubota-empolyee-master-search.component';

describe('KubotaEmpolyeeMasterSearchComponent', () => {
  let component: KubotaEmpolyeeMasterSearchComponent;
  let fixture: ComponentFixture<KubotaEmpolyeeMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KubotaEmpolyeeMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KubotaEmpolyeeMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
