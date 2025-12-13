import { Component, OnInit, Input, EventEmitter, Output, OnChanges, SimpleChanges, OnDestroy } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { SavePurchaseOrder } from '../../pages/purchase-order-create/purchase-order-create';
import { AutocompleteService } from '../../../../../root-service/autocomplete.service';
import { PoMachineDetailsService } from './po-machine-details.service';
import { PurchaseOrderService } from '../../purchase-order.service';
import { ToastrService } from 'ngx-toastr';
import { PurchaseOrderCreateService } from '../../pages/purchase-order-create/purchase-order-create.service';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { SalesPurchaseOrderCreateService } from '../sales-purchase-order-create/sales-purchase-order-create.service';


@Component({
  selector: 'app-po-machine-details',
  templateUrl: './po-machine-details.component.html',
  styleUrls: ['./po-machine-details.component.scss']
})
export class PoMachineDetailsComponent implements OnInit, OnChanges, OnDestroy {
  public filteredItemNumberList: Observable<(string | object)[]>;
  public uuidAddedInForm: object = {} as object;
  public poMachineDetailsForm: FormGroup;
  private deletedItems: object[] = [];
  public isApproveOrReject: boolean;
  public discountTypeList = [];
  public isView: boolean;
  public isEdit: boolean;
  
  public toSelect: any;
  public isApprovalRequired:string;
  public managementCheck:boolean = false;
  public toSelectValue: any;
  salesPoApprovalDetailListFinal 
  @Input() tableTitle = [];
  @Input() finalValueMachineType:any
  @Input() public set salesPoApprovalDetailList(v:(string | object)[]){
    if (!v) {
      return;
    }
    of(v).subscribe(response => {
      this.salesPoApprovalDetailListFinal = response['list'];
     // console.log('response', this.salesPoApprovalDetailListFinal)
      this.isApprovalRequired = this.salesPoApprovalDetailListFinal[0]['mgmtApprovalCheck'];
    });
  }
  @Input() poDetailsFromId: SavePurchaseOrder = {} as SavePurchaseOrder;
  @Input() public set itemNumberAutocompleteList(v: (string | object)[]) {
    if (!v) {
      return;
    }

    if (this.poMachineDetailsForm && this.poMachineDetailsForm.get('machineDetails').value.length > 0) {
      const selectedList: any[] = this.poMachineDetailsForm.get('machineDetails').value;
      const filter = v.filter(res => !selectedList.find(selRes => selRes.machineMaster === res['code']));
  
      if (!this.isView && !this.isEdit) {
          if (this.finalValueMachineType === undefined || this.finalValueMachineType === null) {
              this.toasterService.warning("Please Select Machine Type");
              return; // Stop further execution
          }
      }
  
      // Proceed with setting filteredItemNumberList
      this.filteredItemNumberList = of(filter);
      return;
  }
  
  // If the above condition fails, set filteredItemNumberList to original data
  this.filteredItemNumberList = of(v);
  
 
    
  };
  @Output() itemNumberSelected: EventEmitter<object> = new EventEmitter<object>();
  @Output() getFormStatusAndData = new EventEmitter<object>();
  isffe: boolean;
  @Input() public set selectedItemNumberDataForPatch(dataForPatch) {
    if (dataForPatch) {
      let formArray = this.poMachineDetailsForm.controls.machineDetails['controls'] as Array<FormGroup>;
      let groupToPatch = formArray.filter(formGroup => {
        if (formGroup.controls.uuid.value === dataForPatch['uuid']) {
          formGroup.patchValue(dataForPatch.itemData);
          formGroup.controls.quantity.patchValue(1);
          if (dataForPatch.itemData.unitCost == 0 || dataForPatch.itemData.unitCost==null||dataForPatch.itemData.unitCost==undefined ) {
            this.toasterService.warning("Unit Price Can't be '0' ");
            this.poMachineDetailsForm.controls.machineDetails.reset();
            return;
          }
          else{
            formGroup.controls.unitPrice.patchValue(dataForPatch.itemData.unitCost);
            this.purchaseOrderCreateService.itemSelectionSubject.next(true);
          }
          formGroup.controls.amount.patchValue(dataForPatch.itemData.unitCost);
        }
      });
    }
  }    
  @Input() public tcsPercent: number;
  @Input() public tcsPercList = [];
  constructor(
    private toasterService: ToastrService,
    private autocompleteService: AutocompleteService,
    private purchaseOrderService: PurchaseOrderService,
    private poMachineDetailsService: PoMachineDetailsService,
    private purchaseOrderCreateService: PurchaseOrderCreateService,
    private loginService : LoginFormService,
    private salesPurchaseOrderCreateService:SalesPurchaseOrderCreateService,
  ) { }
  loginType:string 
  
  
  ngOnInit() {
    
    this.poMachineDetailsForm = this.poMachineDetailsService.createMachineDetailsForm();
    this.loginType = this.loginService.getLoginUserType();
    // this.poTotalForm.controls.tcsPercent.patchValue(this.tcsPercent+"");
    this.purchaseOrderService.submitOrResetFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        this.poMachineDetailsForm.valid ? '' : this.toasterService.error('Please fill machine details properly');
        let formData = { isMachineDetailsFormValid: this.poMachineDetailsForm.valid, ...this.poMachineDetailsForm.getRawValue(), deletedParts: this.deletedItems };
        this.getFormStatusAndData.emit({ formData });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.poMachineDetailsForm.reset();
      }
    })
    this.poMachineDetailsService.tcsPercList().subscribe(response => {
      this.tcsPercList = response['result'];
      if (this.tcsPercent) {
        this.poMachineDetailsService.createMachineDetailsForm().controls.tcsPercent.patchValue(this.tcsPercent + "");
      }
      if (this.isView == undefined)
        this.isView = false;
      if (this.isEdit == undefined)
        this.isEdit = false;
      if (this.isView == false && this.isEdit == false) {
        this.toSelect = this.tcsPercList.find(c => c.tcsValue == "0.1");
        this.toSelectValue = this.toSelect.tcsValue;
        // this.poMachineDetailsForm.controls.tcsPercent.setValue("")
      }
    })
    this.getTotalBaseAmountAndGst();

    this.poMachineDetailsService.tcsPercentageList().subscribe(response => {
      // this.tcsAmount = response['result'].tcsPercent
      this.poMachineDetailsForm.controls.tcsPercent.setValue(response['result'].tcsPercent)
    })

    
  }
  checkManagment(event){
    this.managementCheck = !(this.managementCheck);
    this.purchaseOrderCreateService.managementApprovalCheckSubject.next(this.managementCheck);
  }
//   onBlurEventDiscountAmount(event: any){
//     for(let i=0;i<this.poMachineDetailsForm.controls.machineDetails['controls'].length;i++)
//     {
//       if(i==event){
//         this.poMachineDetailsForm.controls.machineDetails['controls'][i].controls.discountPercentage.disable();
//       }
//     }
//    }
//  onBlurEventDiscountPercentage(event: any){
//   for(let i=0;i<this.poMachineDetailsForm.controls.machineDetails['controls'].length;i++)
//   {
//     if(i==event){
//       this.poMachineDetailsForm.controls.machineDetails['controls'][i].controls.discountAmount.disable();
//     }
//   }
// }
  getTotalBaseAmountAndGst() {
    this.poMachineDetailsService.getTotalBaseAmountAndGst$.subscribe(totalObj => {
      if (totalObj !== null) {
        this.poMachineDetailsForm.controls.basicAmount.patchValue(parseFloat(totalObj['totalBase']).toFixed(2));
        this.poMachineDetailsForm.controls.totalGstAmount.patchValue(parseFloat(totalObj['totalGst']).toFixed(2));
        this.poMachineDetailsForm.controls.totalAmount.patchValue(parseFloat(Number(totalObj['totalBase']) + totalObj['totalGst']).toFixed(2));
        // this.poMachineDetailsForm.controls.tcsPercent.patchValue(parseFloat(totalObj['tcsPercent'] + totalObj['tcsPerc']).toFixed(2));
        this.calculateTcsSelection();
      }
    })
  }
  ngOnChanges(changes: SimpleChanges) {
    
    if(this.poMachineDetailsForm!=undefined){
    if (this.poMachineDetailsForm.get('basicAmount').value != null) {
      this.calculateTcsSelection();
    }
  }
    if (changes.poDetailsFromId && changes.poDetailsFromId.currentValue && this.poMachineDetailsForm) {
      this.poMachineDetailsForm = this.poMachineDetailsService.createMachineDetailsForm(changes.poDetailsFromId.currentValue.salesAdmin);
      this.getDiscountTypeList();
      this.isApproveOrReject = changes.poDetailsFromId.currentValue.isApproveOrReject;
      let machineDetails = changes.poDetailsFromId.currentValue.machineDetails;

      if (machineDetails && machineDetails.length > 0) {
        machineDetails.forEach((element, index) => {
          element['variant'] = element['machineMaster']['variant'];
          element['itemDescription'] = element['machineMaster']['itemDescription'];
          if (index === 0) { //because empty row is pushed at the time of form creation,which is removed
            return
          }
          let newForm = this.isApproveOrReject ? this.poMachineDetailsService.initApproveMachineForm(changes.poDetailsFromId.currentValue.salesAdmin) : this.poMachineDetailsService.initMachineForm();
          // newForm.patchValue(element);
          (this.poMachineDetailsForm.get('machineDetails') as FormArray).push(newForm);
        });
      }
      this.poMachineDetailsForm.controls.machineDetails.patchValue(machineDetails);
      this.getTotalBaseAmountAndGst();
      this.poMachineDetailsForm.patchValue({
        basicAmount: changes.poDetailsFromId.currentValue.basicAmount,
        totalGstAmount: changes.poDetailsFromId.currentValue.totalGstAmount,
        totalAmount: changes.poDetailsFromId.currentValue.totalAmount,
        tcsPercent: changes.poDetailsFromId.currentValue.tcsPercent,
        tcsValue: changes.poDetailsFromId.currentValue.tcsValue
      })
      this.isView = changes.poDetailsFromId.currentValue.isView;
      this.isEdit = changes.poDetailsFromId.currentValue.isEdit;
      if (this.isView) {
        // this.poMachineDetailsForm.disable();
      }
      if (this.isView == undefined)
        this.isView = false;
      if (this.isEdit == undefined)
        this.isEdit = false;
      if (this.isView == true || this.isEdit == true || this.isApproveOrReject==true) {
        this.poMachineDetailsService.tcsPercList().subscribe(response => {
          this.tcsPercList = response['result'];
          for (let i = 0; i < this.tcsPercList.length; i++) {
            if (this.tcsPercList[i].tcsValue == changes.poDetailsFromId.currentValue.tcsPercent) {
              this.poMachineDetailsForm.controls.tcsPercent.setValue(this.tcsPercList[i].tcsValue)
            }
          }
        })
      }
    }
  }
  // typeChange(event, rowControl) {
  //   if (event && event.value && event.value.toLowerCase() === 'discount amount') {
  //     rowControl.get('discountAmount').enable();
  //     rowControl.get('discountPercentage').reset();
  //     rowControl.get('discountPercentage').disable();
  //     rowControl.get('amount').patchValue(rowControl.get('quantity').value * rowControl.get('unitPrice').value)
  //   } else {
  //     rowControl.get('discountAmount').reset();
  //     rowControl.get('discountAmount').disable();
  //     rowControl.get('discountPercentage').enable();
  //     rowControl.get('amount').patchValue(rowControl.get('quantity').value * rowControl.get('unitPrice').value)
  //   }
  // }
  private getDiscountTypeList() {
    this.poMachineDetailsService.getDiscountTypeList().subscribe(res => {
      if (res) {
        this.discountTypeList = res['result'];
      }
    })
  }
  private markFormAsTouched() {
    this.poMachineDetailsForm.markAllAsTouched();
    for (const key in this.poMachineDetailsForm.controls) {
      if (this.poMachineDetailsForm.controls.hasOwnProperty(key)) {
        this.poMachineDetailsForm.controls[key].markAsTouched();
      }
    }
  }
  resetFormControlNotHavingObject = this.autocompleteService.resetFormControlNotHavingObject

  displayItemNumberFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }
  itemNumberSelection(event, index) {
    console.log('value from item--------',event)
    if (this.isView == false && this.isEdit == false) {
      // this.poMachineDetailsForm.controls.tcsPercent.setValue("0.1")      
    }
    if (event && event['option']['value']) {
      let selectedUuidControl = this.poMachineDetailsForm.controls.machineDetails['controls'][index].get('uuid').value;
      //alert(event['option']['value']['code'])
      //this.poMachineDetailsForm.controls.machineDetails['controls'][index].get('machineMaster').value.code
      if (!this.checkduplicate(index, event['option']['value']['code'])) {
        this.itemNumberSelected.emit({ event: event, uuid: selectedUuidControl });
      } else {
        this.toasterService.error("Item No can not be duplicate");
        //this.poMachineDetailsForm.controls.machineDetails['controls'][index].get('machineMaster').reset();
        this.poMachineDetailsForm.controls.machineDetails['controls'][index].reset();
      }
    }
  }
  checkduplicate(index: number, itemNo: string) {
    if (index > 0) {
      for (let i: number = 0; i < index; i++) {
        if (this.poMachineDetailsForm.controls.machineDetails['controls'][i].get('machineMaster').value.code === itemNo) {
          return true;
        }
      }
    }
    return false;
  }
  addRow() {
    let formArray = this.poMachineDetailsForm.controls.machineDetails['controls'] as Array<FormGroup>;
    let groupToPatch = formArray.filter(formGroup => {
      if (formGroup.controls.unitPrice.value == 0 ||
        formGroup.controls.unitPrice.value == null ||
        formGroup.controls.unitPrice.value == undefined) {
        this.toasterService.warning("Unit Price Can't be '0' ");
        return;
      }
      else {
        const newUuid = this.poMachineDetailsService.addMachineDetailsRow();
        if (newUuid !== undefined) {
          this.uuidAddedInForm[newUuid] = [];
          this.filteredItemNumberList = of([]);
        }
      }
    });

  }
  deleteRow() {
    const selectedToDelete = this.poMachineDetailsForm.getRawValue().machineDetails.filter(val => val.isSelected === true);
    if ((this.poMachineDetailsForm.value.machineDetails.length - selectedToDelete.length) >= 1) {
      selectedToDelete.filter(item => item && item['id']).map(filtered => {
        const modifiedObj = Object.assign({}, filtered);
        modifiedObj['isDelete'] = true;
        this.deletedItems.push(modifiedObj);
      })
      console.log("this.deletedItems ", this.deletedItems);
    }
    this.poMachineDetailsService.deleteRow();
  }
  ngOnDestroy() {
    this.purchaseOrderService.destroySubscription();
  }
  public calculateTcsSelection() {
    if (this.poMachineDetailsForm.get('basicAmount').value != null) {
      let tcsPercent = this.poMachineDetailsForm.get('tcsPercent').value;
      if (tcsPercent != null) {
        let totalBaseAmount = this.poMachineDetailsForm.get('basicAmount').value;
        let totalGstAmount = this.poMachineDetailsForm.get('totalGstAmount').value;
        if (totalGstAmount == null)
          totalGstAmount = 0.0;
        console.log(totalBaseAmount, totalGstAmount, tcsPercent);
        let subtotal = parseFloat(totalBaseAmount) + parseFloat(totalGstAmount);
        console.log(subtotal);
        let tcsAmount = subtotal * parseFloat(tcsPercent) / 100;
        console.log(tcsAmount);
        let totalAmount = subtotal + tcsAmount;

        this.poMachineDetailsForm.get('tcsValue').patchValue( parseFloat(tcsAmount.toFixed(2)));
        this.poMachineDetailsForm.get('totalAmount').patchValue(parseFloat(totalAmount.toFixed(2)));
        // let tcsPercentFloat=parseFloat(tcsPercent).toFixed(2);
        // this.poMachineDetailsForm.get('tcsPercent').patchValue(tcsPercentFloat);
      }
    }
    else {
      this.toasterService.warning("Base Amount not found");
      this.poMachineDetailsForm.get('tcsPercent').reset()
    }
  }
}
