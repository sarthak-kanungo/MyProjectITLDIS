import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentStockPageComponent } from './current-stock-page.component';

describe('CurrentStockPageComponent', () => {
  let component: CurrentStockPageComponent;
  let fixture: ComponentFixture<CurrentStockPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentStockPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentStockPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
