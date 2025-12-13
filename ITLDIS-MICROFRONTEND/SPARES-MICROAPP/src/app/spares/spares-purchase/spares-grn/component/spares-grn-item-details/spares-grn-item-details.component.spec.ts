import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SparesGrnItemDetailsComponent } from './spares-grn-item-details.component';

describe('SparesGrnItemDetailsComponent', () => {
  let component: SparesGrnItemDetailsComponent;
  let fixture: ComponentFixture<SparesGrnItemDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SparesGrnItemDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SparesGrnItemDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
