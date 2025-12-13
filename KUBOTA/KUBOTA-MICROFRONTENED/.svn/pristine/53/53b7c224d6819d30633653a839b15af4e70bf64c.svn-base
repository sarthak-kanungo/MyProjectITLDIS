import { Observable, BehaviorSubject } from 'rxjs';
import { FormGroup, FormControl } from '@angular/forms';
import { Component, OnInit, Input, ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { SparesSalesInvoiceCreatePresenter } from '../spares-sales-invoice-create-page/spares-sales-invoice-create-page.presenter';
import { EditableTableHandlerService } from './editable-table-handler.service';
import { SparesSalesInvoiceWebService } from '../spares-sales-invoice/spares-sales-invoice-web.service';

@Component({
  selector: 'app-spares-sales-invoice-item-details',
  templateUrl: './spares-sales-invoice-item-details.component.html',
  styleUrls: ['./spares-sales-invoice-item-details.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [EditableTableHandlerService, SparesSalesInvoiceWebService]
})
export class SparesSalesInvoiceItemDetailsComponent implements OnInit {
  @Input() public markAllAsTouchedObserv: BehaviorSubject<boolean>;
  @Input() public poItemDetailForm: Observable<Array<FormGroup>>;
  @Input() public machineDetailsFormControl: FormControl;
  @Input() public poTotalForm: FormGroup;
  @Input() public isView: boolean;
  @Input() public isEdit: boolean;
  @Input() public tcsPerc:number;
  @Input() public draftFlag:boolean;
  tcsdraftFlag:boolean;
  public itemDetailsArray: Array<FormGroup> = [];
  public patchValue: any;

  public tcsPercList = [];
  public isSalesOrder:boolean = true;
  constructor(
    private _changeDetectorRef: ChangeDetectorRef,
    private presenter : SparesSalesInvoiceCreatePresenter,
    private sparesSalesInvoiceWebService: SparesSalesInvoiceWebService,
    private editableTableHandlerService: EditableTableHandlerService
  ) {
    
  }

  ngOnInit() {
    this.tcsdraftFlag = this.draftFlag;
    this.markAllAsTouchedObserv.subscribe((value: boolean) => {
      if (value) {
        this._changeDetectorRef.markForCheck();
      }
    });

    this.sparesSalesInvoiceWebService.tcsPercList().subscribe(response => {
        this.tcsPercList = response['result'];
    });
    
    this.poItemDetailForm.subscribe((res: Array<FormGroup>) => {
       
       this.itemDetailsArray = res as Array<FormGroup>
    });
    this.presenter.refernceDocChange.subscribe(docType => {
      //alert(docType.toLowerCase())
       if(docType === 'Job Card'){
           this.isSalesOrder = false; 
       }else{
           this.isSalesOrder = true;
       } 
       if(docType && docType.toLowerCase() === 'wcr'){
        this.machineDetailsFormControl.get('tcsPercent').patchValue(0);
        this.tcsdraftFlag = false;
      }
    });
    this.presenter.tcsPercChange.subscribe(tcs => {
        this.tcsPerc = tcs;
        this.machineDetailsFormControl.get('tcsPercent').patchValue(tcs);
    })
    /*if (!this.isView && !this.isEdit) {
        this.presenter.addNewRowInItemDetails();      //Adding one default row while creating the form
    }*/
  }
  public deleteRow() {
      this.presenter.deleteRowFromItemDetail();
    }
  
  calculateTcsSelection(){
      let tcsPercent = this.machineDetailsFormControl.get('tcsPercent').value;
      if(this.machineDetailsFormControl.get('totalBaseAmount').value!=null){
          if(tcsPercent!=null){
              let totalBaseAmount = this.machineDetailsFormControl.get('totalBaseAmount').value;
              let totalGstAmount = this.machineDetailsFormControl.get('totalTaxAmount').value;
              if(totalGstAmount==null)
                  totalGstAmount=0.0;
              console.log(totalBaseAmount, totalGstAmount, tcsPercent);
              let subtotal = parseFloat(totalBaseAmount) + parseFloat(totalGstAmount);
              console.log(subtotal);
              let tcsAmount =  subtotal * parseFloat(tcsPercent)/100;
              console.log(tcsAmount);
              let totalAmount = subtotal + tcsAmount;

              this.machineDetailsFormControl.get('tcsAmount').patchValue(tcsAmount.toFixed(2));
              this.machineDetailsFormControl.get('totalInvoiceAmount').patchValue(totalAmount.toFixed(2));
          }
      }
  }
}
