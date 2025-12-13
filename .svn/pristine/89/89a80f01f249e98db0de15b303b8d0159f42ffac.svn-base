import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDatepickerInput } from '@angular/material';
import { BranchTransferRecieptService } from '../../service/branch-transfer-reciept.service';

@Component({
  selector: 'app-search-branch-transfer-reciept',
  templateUrl: './search-branch-transfer-reciept.component.html',
  styleUrls: ['./search-branch-transfer-reciept.component.css']
})
export class SearchBranchTransferRecieptComponent implements OnInit {
    @Input() searchForm:FormGroup

    StatusList=[
      {'value':'Pending'},
      {'value':'Submit'}
    ]
    todayDate = new Date()
  minDate: Date;
  newdate= new Date()
  maxDate: Date
  recieptData: any;
  issueData:any
  constructor(private fb:FormBuilder,
    private service: BranchTransferRecieptService,) {
    
   }
  
  ngOnInit() {
    
 this.formValueChanges()
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
      if (this.searchForm.get('toDate').value > this.maxDate)
        this.searchForm.get('toDate').patchValue(this.maxDate);
    }
  }
  private formValueChanges() {
    this.searchForm.get('recieptNo').valueChanges.subscribe(val => {
      if (val) {
        this.autoGenrateRecieptNumber(val);
      }
    });
    this.searchForm.get('issueNo').valueChanges.subscribe(val => {
      if (val) {
        this.autoGenerateIssueNumber(val);
      }
    });
  }
  private autoGenrateRecieptNumber(txt: string) {
    this.service.autoGeneateRecieptNoSearch(txt).subscribe(res => {
      this.recieptData=res;
    });
  }
  private autoGenerateIssueNumber(txt: string) {
    this.service.autoGenerateIssueNoSearch(txt).subscribe(res => {
      this.issueData=res;
    });
  }
  displayRecieptNo(obj) {
    console.log(obj,'obj')
    
    return obj ? obj : undefined
  }
  displayIssueNo(objs){
    return objs?objs.issueNo:undefined
  }


}
