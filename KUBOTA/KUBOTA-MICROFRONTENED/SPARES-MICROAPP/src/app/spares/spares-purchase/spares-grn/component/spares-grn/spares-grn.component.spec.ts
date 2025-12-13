import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SparesGrnComponent } from './spares-grn.component';

describe('SparesGrnComponent', () => {
  let component: SparesGrnComponent;
  let fixture: ComponentFixture<SparesGrnComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SparesGrnComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SparesGrnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
