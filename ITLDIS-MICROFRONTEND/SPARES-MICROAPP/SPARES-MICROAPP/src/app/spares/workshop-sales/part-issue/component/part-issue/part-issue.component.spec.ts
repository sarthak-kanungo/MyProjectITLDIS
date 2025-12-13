import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartIssueComponent } from './part-issue.component';

describe('PartIssueComponent', () => {
  let component: PartIssueComponent;
  let fixture: ComponentFixture<PartIssueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartIssueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartIssueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
