import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KubotaSourceMasterSearchComponent } from './kubota-source-master-search.component';

describe('KubotaSourceMasterSearchComponent', () => {
  let component: KubotaSourceMasterSearchComponent;
  let fixture: ComponentFixture<KubotaSourceMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KubotaSourceMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KubotaSourceMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
