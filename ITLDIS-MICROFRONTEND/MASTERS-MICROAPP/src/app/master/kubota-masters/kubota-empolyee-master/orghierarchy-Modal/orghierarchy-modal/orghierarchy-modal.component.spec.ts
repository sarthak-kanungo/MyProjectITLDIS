import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrghierarchyModalComponent } from './orghierarchy-modal.component';

describe('OrghierarchyModalComponent', () => {
  let component: OrghierarchyModalComponent;
  let fixture: ComponentFixture<OrghierarchyModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrghierarchyModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrghierarchyModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
