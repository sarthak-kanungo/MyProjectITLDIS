import { Component, OnInit } from '@angular/core';
import { DateAdapter, MatDatepickerInput, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-branch-transfer-request',
  templateUrl: './search-branch-transfer-request.component.html',
  styleUrls: ['./search-branch-transfer-request.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})
export class SearchBranchTransferRequestComponent implements OnInit {

  searchbranchtransferrequest: FormGroup
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  constructor(private fb:FormBuilder) { }

  ngOnInit() {
  this.createsearchbranchtransferrequest()
  }
  ngAfterViewInit() {
    this.maxDate = this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;
    this.searchbranchtransferrequest.get('requestfromdate').patchValue(backDate);
    this.searchbranchtransferrequest.get('requesttodate').patchValue(new Date());

  }
  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate){
        this.maxDate = this.newdate;
      }
      else
     {   this.maxDate = maxDate;
      if (this.searchbranchtransferrequest.get('requesttodate').value > this.maxDate)
        this.searchbranchtransferrequest.get('requesttodate').patchValue(this.maxDate);
     }
    }
  }
  createsearchbranchtransferrequest(){
    this.searchbranchtransferrequest=this.fb.group({
      requestno:['',Validators.compose([])],
      requestfromdate:['',Validators.compose([])],
      requesttodate:['',Validators.compose([])]
    })
  }

}
