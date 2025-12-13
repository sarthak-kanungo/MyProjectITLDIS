import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MailMasterSearchComponent } from './mail-master-search.component';

describe('MailMasterSearchComponent', () => {
  let component: MailMasterSearchComponent;
  let fixture: ComponentFixture<MailMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MailMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MailMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
