import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { BtBtSearchPagePresenter } from '../btbt-search-page/btbt-search.presenter';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { BtbtSearchPageWebService } from '../btbt-search-page/btbt-search-page-web.service';
@Component({
  selector: 'app-btbt-search',
  templateUrl: './btbt-search.component.html',
  styleUrls: ['./btbt-search.component.css']
})
export class BtbtSearchComponent implements OnInit {
  transferNoList$:Observable<Array<any>>;
  btBtSearchForm: FormGroup;
  today = new Date();
  minDate: Date;
  maxDate: Date
  constructor(
    private presenter: BtBtSearchPagePresenter,
    private activateRoute: ActivatedRoute,
    private webService :BtbtSearchPageWebService
  ) { }
  ngOnInit() {
    this.btBtSearchForm = this.presenter.btBtSearch
    console.log(" this.btBtSearchForm ", this.btBtSearchForm);
    
    this.btBtSearchForm.controls.binTransferNo.valueChanges.subscribe(tn => {
        if(typeof this.btBtSearchForm.controls.binTransferNo.value !== 'object'){
            this.transferNoList$ = this.webService.searchTransferNumber(tn)
            this.btBtSearchForm.get('binTransferNo').setErrors({ selectFromList: 'Please select from list' });
        }else{
            this.btBtSearchForm.get('binTransferNo').setErrors(null);
        } 
    });
    
  }
  ngAfterViewInit(){
   
  }
  fromDateSelected(event) {
    // if (event && event['value']) {
    //   this.selectedFromDate = new Date(event['value']);
    // }
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.btBtSearchForm.get('binTransferToDate').value > this.maxDate)
        this.btBtSearchForm.get('binTransferToDate').patchValue(this.maxDate);
    }
  }
  displayFnNo(transferNo){
      if (typeof transferNo === 'string') {
          return transferNo;
        }
        return transferNo ? transferNo.binTransferNo : undefined
  }
}
