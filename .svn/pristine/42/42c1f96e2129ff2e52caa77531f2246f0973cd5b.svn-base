import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDatepickerInput } from '@angular/material';
import { hotlineReport } from '../hotline-report/hotline-service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-hotline-report-search',
  templateUrl: './hotline-report-search.component.html',
  styleUrls: ['./hotline-report-search.component.scss'],
  providers :[hotlineReport]
  
})
export class HotlineReportSearchComponent implements OnInit {
   @Input() hotlineSearchForm:FormGroup
  public status: Array<string> = [];
  newdate = new Date()
  fromDate: Date
  toDate: Date
  statusList: any[];
  obs: Subscription;
  hotlineData:any
  constructor(private service:hotlineReport) { }

  ngOnInit() {
      this.getStatus()
      this.formValueChanges()
  }
  private formValueChanges() {
    this.hotlineSearchForm.get('hotlineNo').valueChanges.subscribe(val => {
      if (val) {
        this.searchHotlineNo(val);
      }
    });
  }
  private searchHotlineNo(txt: string) {
    this.service.searchHotlineNo(txt).subscribe(res => {
      this.hotlineData = res;
    });
  }
  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.fromDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate)
        this.toDate = this.newdate;
      else
        this.toDate = maxDate;
      if (this.hotlineSearchForm.get('endDate').value > this.toDate)
        this.hotlineSearchForm.get('endDate').patchValue(this.toDate);
    }
  }
  getStatus(){
    this.obs=this.service.statusList().subscribe(response => {
      this.statusList = response;
  })
  }
  ngOnDestroy() {
    
    this.obs.unsubscribe();
  }
}
