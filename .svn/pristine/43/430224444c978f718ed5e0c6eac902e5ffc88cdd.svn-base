import { Component, Input, OnChanges, OnInit, SimpleChanges, ChangeDetectionStrategy, ChangeDetectorRef} from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { Observable } from 'rxjs';
import { AutoCustomerOrder } from '../../domain/pl.domain';
import { PLPagePresenter } from '../../pick-list-page/pick-list-page.presenter';
import { PickListServiceService } from '../pick-list-service.service';

@Component({
  selector: 'app-pick-list',
  templateUrl: './pick-list.component.html',
  styleUrls: ['./pick-list.component.css'],
  providers:[PickListServiceService]
})
export class PickListComponent implements OnInit, OnChanges{
    
  @Input() public isView: boolean;
  @Input() public isEdit: boolean;
  @Input() pickListForm: FormGroup
  customerOrderNoList$: Observable<Array<AutoCustomerOrder>>
  customerOrderNoList: AutoCustomerOrder[]=[]

  constructor(private pickListService:PickListServiceService,
    private plpresenter: PLPagePresenter) { }


  ngOnChanges() {
  }

  ngOnInit() {
    this.valueChangesForAutoComplete();
    if(this.isView || this.isEdit){
        this.pickListForm.controls.saleOrderNumber.disable();
    }
    this.plpresenter.saleOrderNumberSubject.subscribe(val => {
      if(val!=null){
        this.pickListService.getCustomerOrdeById(val).subscribe(res => {
          this.pickListForm.patchValue(res.headerResponse);
          this.plpresenter.getItemDetailsForm.clear();
          res.partDetails.forEach(partDtl => {
              this.plpresenter.addNewRowInItemDetails(partDtl);
          });
        });
      }
    })
  }
  

  onKeyDownForQuotation(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.pickListForm.reset();
      this.onFocusGetCustomerOrdeRList('');
      this.plpresenter.getItemDetailsForm.clear();  
    }
  }
  onFocusGetCustomerOrdeRList(value){
    if (value == null || value == undefined)
        value = '';
    
    if(typeof value !== 'object'){
        this.autoCustomerOrderNo(value);
        this.plpresenter.customerOrdeNoSetValidation;
    }else{
        this.customerOrderNoList = null;
    }
    
  }
  valueChangesForAutoComplete() {
    this.pickListForm.get('saleOrderNumber').valueChanges.subscribe(value => {
      this.onFocusGetCustomerOrdeRList(value);
    });
  }

  autoCustomerOrderNo(saleOrderNumber: string) {
    this.pickListService.getCustomerOrderNo(saleOrderNumber).subscribe(res=>{
      this.customerOrderNoList = res;
    });
  }

  customerOrderNoSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.pickListForm.get('saleOrderNumber').setErrors(null);
    }
    this.getCustomerDetailsById(event);
  }

  displayFnSalesOrderNo(saleOrderNumber: AutoCustomerOrder) {
    return saleOrderNumber ? saleOrderNumber.saleOrderNumber : undefined;
  }

  getCustomerDetailsById(event: MatAutocompleteSelectedEvent) {
    this.plpresenter.getItemDetailsForm.clear();  
    this.pickListService.getCustomerOrdeById(event.option.value.id).subscribe(res => {
      this.pickListForm.patchValue(res.headerResponse);
      res.partDetails.forEach(partDtl => {
          this.plpresenter.addNewRowInItemDetails(partDtl);
      });
    })
  }

}
