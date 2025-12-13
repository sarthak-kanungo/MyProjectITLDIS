import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDatepickerInput } from '@angular/material';

@Component({
  selector: 'app-search-branch-transfer-issue',
  templateUrl: './search-branch-transfer-issue.component.html',
  styleUrls: ['./search-branch-transfer-issue.component.scss']
})
export class SearchBranchTransferIssueComponent implements OnInit {
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  searchbranchtransferissue: FormGroup

  constructor(private fb:FormBuilder) { }

  ngOnInit() {
    this.searchbranchtransferissuecreate()
  }
  ngAfterViewInit() {
    this.maxDate = this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;
    this.searchbranchtransferissue.get('issuefromdate').patchValue(backDate);
    this.searchbranchtransferissue.get('issuetodate').patchValue(new Date());

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
      if (this.searchbranchtransferissue.get('issuetodate').value > this.maxDate)
        this.searchbranchtransferissue.get('issuetodate').patchValue(this.maxDate);
     }
    }
  }
  search(){
    
  }
  searchbranchtransferissuecreate(){
    this.searchbranchtransferissue=this.fb.group({
      branchtransferissueno:['',Validators.compose([])],
      requestissueno:['',Validators.compose([])],
      issuefromdate:['',Validators.compose([])],
      issuetodate:['',Validators.compose([])]
    })
  }
  clear(){
    this.searchbranchtransferissue.reset();
  }
}
