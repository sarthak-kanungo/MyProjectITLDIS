import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EmployeeMasterCreatePagePresenter } from '../employee-master-create-page/employee-master-page.presenter';
// import {Moment} from 'moment/moment';
import * as moment from 'moment/moment';
import { ToastrService } from 'ngx-toastr';
import {MatDatepickerInput } from '@angular/material';
@Component({
  selector: 'app-employment',
  templateUrl: './employment.component.html',
  styleUrls: ['./employment.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})
export class EmploymentComponent implements OnInit {
  labelPosition = 'before';
  employmentForm: FormGroup;
  today = new Date();
  pretoDate=new Date()
  Today = new Date();
  totalMonth:any
  data:any
  @Output() StartEndDatesOutput = new EventEmitter();
  constructor( private fb: FormBuilder,private toastr: ToastrService,
    private employeeMasterCreatePagePresenter:EmployeeMasterCreatePagePresenter
    ) { }

  ngOnInit() {
    this.employmentForm = this.employeeMasterCreatePagePresenter.employmentForm
  }
  startDateClicked(event:MatDatepickerInput<Date>) {
    this.StartEndDatesOutput.emit(this.getDates());
  }
  
  endDateClicked(event:MatDatepickerInput<Date>) {
    
    if (this.employmentForm.get('preFromDate').value) {
      let eventStartTime = new Date(this.employmentForm.get('preFromDate').value);
      let eventEndTime = new Date(this.employmentForm.get('preToDate').value);
     
      let m = moment(eventEndTime);
      // console.log()
      let years = m.diff(eventStartTime, 'years',true);   
     
     this.data=Math.trunc(years*12);
    //  console.log(this.data,'data')
    } else {
      this.employmentForm.controls['preToDate'].setValue('');
    }
  }
  getDates() {
    let startEndDates = {
      'preFromDate': this.employmentForm.get('preFromDate').value,
      'preToDate': this.employmentForm.get('preToDate').value,
     
    };
   return startEndDates;
    
  }
  joinDate(event:MatDatepickerInput<Date>){
    
  
    if (this.employmentForm.get('joiningDate').value) {
      let joiningDate = new Date(this.employmentForm.get('joiningDate').value);
      let Today = new Date();
     
      let m = moment(Today);
      let month = m.diff(joiningDate, 'month',true); 
      // console.log(month,)
      this.totalMonth=month 
      var total=Math.trunc(this.totalMonth+this.data)
      // console.log(total)
       this.employmentForm.get('totalExperience').patchValue(Number.isNaN(total) ? 0 : total)
     
    }
    
  }
 
}


