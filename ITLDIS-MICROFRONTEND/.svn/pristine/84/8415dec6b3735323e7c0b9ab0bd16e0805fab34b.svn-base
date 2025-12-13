import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Injectable()
export class SalesPurchaseOrderSearchServiceService {
  public searchForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder
  ) {
  
   }

  createForm() {
    if (this.searchForm) {
      return this.searchForm;
    }
    this.buildForm();
    return this.searchForm;
  }
  private buildForm() {
    this.searchForm = this.formBuilder.group({
      poNumber: [null],
      poType: [null],
      depot: [null],
      itemNo: [null],
      fromDate: [null],
      toDate: [null],
      poStatus: [null],
      dealer: [null],
      product: [null],
      series: [null],
      model: [null],
      subModel: [null],
      variant: [null],
      dealerCode: [null],
      dealerName : [{value:'', disabled:true}],
      orgHierLevel1 : [null],
      orgHierLevel2 : [null],
      orgHierLevel3 : [null],
      orgHierLevel4 : [null],
      orgHierLevel5 : [null],
      dealerId : [null],
      hierId: [null]
      
    })
  }
}
