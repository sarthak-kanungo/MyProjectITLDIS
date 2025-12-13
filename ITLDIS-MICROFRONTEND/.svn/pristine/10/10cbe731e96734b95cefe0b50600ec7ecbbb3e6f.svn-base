import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDatepickerInput } from '@angular/material';

@Component({
  selector: 'app-search-view-trasit-detail-search',
  templateUrl: './search-view-trasit-detail-search.component.html',
  styleUrls: ['./search-view-trasit-detail-search.component.css']
})
export class SearchViewTrasitDetailSearchComponent implements OnInit {
 @Input() seachTransit:FormGroup
  constructor() { }
  
  StatusList=['All','Pending For GRN','GRN Done']
  todayDate = new Date()
minDate: Date;
newdate= new Date()
maxDate: Date
  ngOnInit() {
  }


  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate)
        this.maxDate = this.newdate;
      else
        this.maxDate = maxDate;
      if (this.seachTransit.get('toInvoiceDate').value > this.maxDate)
        this.seachTransit.get('toInvoiceDate').patchValue(this.maxDate);
    }
  }
}
