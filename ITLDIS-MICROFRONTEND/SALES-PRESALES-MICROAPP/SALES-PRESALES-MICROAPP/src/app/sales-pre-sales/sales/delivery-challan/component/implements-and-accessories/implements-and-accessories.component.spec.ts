import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImplementsAndAccessoriesComponent } from './implements-and-accessories.component';

describe('ImplementsAndAccessoriesComponent', () => {
  let component: ImplementsAndAccessoriesComponent;
  let fixture: ComponentFixture<ImplementsAndAccessoriesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImplementsAndAccessoriesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImplementsAndAccessoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
