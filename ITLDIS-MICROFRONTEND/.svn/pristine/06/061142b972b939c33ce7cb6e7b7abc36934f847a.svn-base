import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/app.module';

import { SearchTollFreeComponent } from './search-toll-free.component';

describe('SearchDelearCustomerCareExCallComponent', () => {
  let component: SearchTollFreeComponent;
  let fixture: ComponentFixture<SearchTollFreeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchTollFreeComponent ],
      imports: [ReactiveFormsModule, MaterialModule, FormsModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchTollFreeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  fit('form is invalid', async(()=> {
    component.callSearchForm.controls.mobileNo.setValue('');
    component.callSearchForm.controls.fromDate.setValue('');
    component.callSearchForm.controls.toDate.setValue('');
    expect(component.callSearchForm.valid).toBeFalsy();
  }));

});
