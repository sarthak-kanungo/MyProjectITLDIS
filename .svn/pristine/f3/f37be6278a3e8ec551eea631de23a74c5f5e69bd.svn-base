import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDatepickerInput } from '@angular/material';

@Component({
  selector: 'app-search-branch-transfer-receipt',
  templateUrl: './search-branch-transfer-receipt.component.html',
  styleUrls: ['./search-branch-transfer-receipt.component.scss']
})
export class SearchBranchTransferReceiptComponent implements OnInit {

  searchbranchtransferreceipt: FormGroup
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  constructor(private fb:FormBuilder) { }

  ngOnInit() {
    this.searchbranchtransferreceiptcreate()
  }
  ngAfterViewInit() {
    this.maxDate = this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;
    this.searchbranchtransferreceipt.get('receiptfromdate').patchValue(backDate);
    this.searchbranchtransferreceipt.get('receipttodate').patchValue(new Date());

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
      if (this.searchbranchtransferreceipt.get('receipttodate').value > this.maxDate)
        this.searchbranchtransferreceipt.get('receipttodate').patchValue(this.maxDate);
     }
    }
  }
  searchbranchtransferreceiptcreate(){
    this.searchbranchtransferreceipt=this.fb.group({
      branchtransferreceiptno:['',Validators.compose([])],
      branchtransferissueno:['',Validators.compose([])],
      receiptfromdate:['',Validators.compose([])],
      receipttodate:['',Validators.compose([])]
    })
  }
}
