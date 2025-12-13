import { Subject } from 'rxjs';
import { Component, OnInit, forwardRef, Input } from '@angular/core';
import { FormBuilder, Validators, FormControl, NG_VALIDATORS, NG_VALUE_ACCESSOR, ControlValueAccessor, Validator, AbstractControl, ValidationErrors, FormGroup } from '@angular/forms';
import { TableConfig, ETValidationError } from 'editable-table';
import { AddImplementsContainerService } from '../../../quotation/component/add-implements-container/add-implements-container.service';
import { ImplementsAndAccessoriesService } from './implements-and-accessories.service';
import { ImplementsAccessoriesTableHandlerService } from './implements-accessories-table-handler.service';
import { DeliveryChallanCreateService } from '../../pages/delivery-challan-create/delivery-challan-create.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-implements-and-accessories',
  templateUrl: './implements-and-accessories.component.html',
  styleUrls: ['./implements-and-accessories.component.scss'],
  providers: [
    ImplementsAccessoriesTableHandlerService,
    ImplementsAndAccessoriesService,AddImplementsContainerService,
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => ImplementsAndAccessoriesComponent),
      multi: true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => ImplementsAndAccessoriesComponent),
      multi: true
    }
  ]
})
export class ImplementsAndAccessoriesComponent implements OnInit, ControlValueAccessor, Validator {
  public implementsFormControl: FormControl = new FormControl();
  public getFormData = new Subject<boolean>();
  public tableConfig: Array<TableConfig>;
  public editableTableFormGroup: object;
  public tableData = [];
  public isCancel: boolean = false;
  public isApprove: boolean = false;
  public isEdit: boolean = false;
 @Input() public isView: boolean = false;
  public patchValue: any;
  public assignTo: any;
  public tableFieldValidationError: ETValidationError;
  itemsformGroup: FormGroup;
  public duplicatItemNo: any;
  public deleteDuplicatItemNo: [];
  


  constructor(
    private formBuilder: FormBuilder,
    private implementsDetailService: ImplementsAndAccessoriesService,
    private deliveryChallanCreateService: DeliveryChallanCreateService,
    private editableTableHandeler: ImplementsAccessoriesTableHandlerService,
    private addImplementsContainerService:AddImplementsContainerService,
    private toasterService: ToastrService
  ) {
    this.tableConfig = this.editableTableHandeler.getTableConfigurationJSON();
    this.editableTableFormGroup = this.editableTableHandeler.getTableFormGroupJSON();
  }

  ngOnInit() {
    if(!this.isView){  
        this.saveDataAfterFormValid();
        /*this.deliveryChallanCreateService.getMachineCustomerAndCheklistDataFromEnq.subscribe(data => {
          if (data !== null) {
            data['machineDetailsResponse'].forEach(element => {
              element.machineInventory = { id: element.machineInventory }
            });
            this.tableData = data['machineDetailsResponse'].filter(ele => ele.productGroup.toLowerCase() === 'implements');
          }
        })*/
        this.clearTouchedForm();
    }
  }
  private clearTouchedForm() {
    this.deliveryChallanCreateService.clearOrMarkAsTouchedAll.subscribe(value => {
      if (value && value['key'] === 'mark') {
        this.checkFieldValidity();
        this.implementsFormControl.markAllAsTouched();
        return;
      }
      if (value && value['key'] === 'clear') {
        this.implementsFormControl.patchValue({ resetForm: true }, { emitEvent: false })
        // this.tableData = [];
        return;
      }
    })
  }
  private checkFieldValidity() {
    if (this.implementsFormControl.value) {
      this.implementsFormControl.value.forEach(element => {
        if (element && (!element.itemNumber)) {
          setTimeout(() => {
            this.tableFieldValidationError = new ETValidationError('itemNumber', element.tableRowId, 'This field is required');
          }, 200);
        }
        if (element && (element.quantity === null || element.quantity === 0)) {
          setTimeout(() => {
            this.tableFieldValidationError = new ETValidationError('quantity', element.tableRowId, 'This field is required');
          }, 400);
        }
      });
    }
  }
  private saveDataAfterFormValid() {
    this.implementsFormControl.valueChanges.subscribe(changedValue => {
      let deliveryChallanMainData = this.deliveryChallanCreateService.deliveryChallanMainData;
      deliveryChallanMainData.isImplementsFormValid = this.implementsFormControl.status === 'VALID' ? true : false;
      if (this.implementsFormControl.status === 'VALID') {
        this.deliveryChallanCreateService.deliveryChallanMainData = { ...deliveryChallanMainData, ...{ dcAccessoriesDetails: this.implementsFormControl.value } };
      }
    })
  }

  validate(control: AbstractControl): ValidationErrors {
    // throw new Error("Method not implemented.");
    return
  }
  registerOnValidatorChange?(fn: () => void): void {
    // throw new Error("Method not implemented.");
  }
  writeValue(patchObjectData: any): void {
    if (patchObjectData) {
      this.isView = patchObjectData.isView;
      this.isEdit = patchObjectData.isEdit;
      this.isCancel = patchObjectData.isCancel;
      this.isApprove = patchObjectData.isApprove;
      if (patchObjectData.dcAccessoriesDetails!=null && patchObjectData.dcAccessoriesDetails.length > 0) {
        this.tableData = patchObjectData.dcAccessoriesDetails;
        this.tableData.forEach(element => {
            element.itemNumber = element.machineMaster.itemNumber;
            element.itemDescription = element.machineMaster.itemDescription;
        })
      }
    }
  }
  registerOnChange(fn: any): void {
    // throw new Error("Method not implemented.");
  }
  registerOnTouched(fn: any): void {
    // throw new Error("Method not implemented.");
  }
  setDisabledState?(isDisabled: boolean): void {
    if (isDisabled) {
      this.implementsFormControl.disable();
    }
  }

  editableTableSearchValueChanges(event) {
    if (event && event.key && event.config.formControlName === 'itemNumber') {
      //this.searchcByItemNumber(event);
        this.addImplementsContainerService.searchItemNo(event.key, '', 'DC').subscribe(res => {
            this.assignTo = {
                list: res['result'],
                config: event.config,
                searchKey: event.key
              };
            }, err => {
                
            }
       );
    }

 
    if (event.config.formControlName === 'quantity') {
        // event.tableRow
        if(!this.isView){
            const isValid = this.checkReceiptQtyIsLessThanInvoiceQty(event.tableRow['quantity'], event.tableRow['itemQty']);
            this.receiptQuantityValidationHandeler(isValid, event.tableRow)
        }
      }
  }

  /* added by vinay */
  toCheckDuplicateItemNo(event){
    let flag:boolean = true;
    //this.duplicatItemNo.forEach(items =>{
      if(this.duplicatItemNo===(event.tableRow['itemNumber']['itemNo'])){
        this.toasterService.warning('Item Number already exist');
        flag= false;
        return;
       }
  //})
  return true;
  }

  deletedTableRecords(event){
    this.duplicatItemNo='';
  }
  /* end by vinay */
  
  private checkReceiptQtyIsLessThanInvoiceQty(receiptQty: any, stockQty: any) {
      if ((parseFloat(receiptQty) || 0) <= (parseFloat(stockQty) || 0)) {
        return true;
      }
      this.toasterService.warning(`The Quntity should not greter than Stock Qty (${stockQty})`, 'Wrong Value');
      return false;
  }
  
  private receiptQuantityValidationHandeler(isValid: boolean, tableRowData) {
      if (!isValid) {
        this.patchValue = [{
          tableRowId: tableRowData['tableRowId'],
          patchValue: {
            quantity: 0
          }
        }];
      }
    }
  
  /*searchcByItemNumber(event: object) {
    this.implementsDetailService.searchByItemNumber(event['key']).subscribe(res => {
      this.assignTo = {
        list: res['result'],
        config: event['config'],
        searchKey: event['key']
      };
    })
  }*/
  optionSelected(event) {
    this.getDetailsFromItemNumber(event);

  }
  private getDetailsFromItemNumber(event) {

  /* added by vinay */
     let getItemNo= event['option']['itemNo'];
     if(this.toCheckDuplicateItemNo(event)){
       //this.duplicatItemNo.push(im);
       this.duplicatItemNo= getItemNo;
       console.log('itemNo',this.duplicatItemNo)
 /* end by vinay */

    this.implementsDetailService.getItemDetailsFromItemNumber(event['option']['itemNo']).subscribe(res => {
      if (res) {
        let msg = res['result']['msg'];  
        if(msg!=null && msg!=''){
            this.toasterService.warning(msg);
        }  else{
            let createInventoryObj = res['result'];
            createInventoryObj['machineMaster'] = { id: res['result']['machine_master_id'] }
            this.patchValue = [
              {
                rowIndex: event.rowIndex,
                tableRowId: event.tableRow.tableRowId,
                patchValue: createInventoryObj
              }
            ]
     
        }
      }
    })
  }
  }
  tableValueChange(event) {
  }

  itemDetailsRow() {
    return this.formBuilder.group({
      itemNumber: this.formBuilder.control(null, Validators.required),
      itemDescription: this.formBuilder.control(null, Validators.required),
      itemQty : this.formBuilder.control(null),
      quantity: this.formBuilder.control(null),
      isSelected: this.formBuilder.control(false)
    }, {
      //validator: this.itemDetailsRowValidator('itemNumber', 'confirmitemNumber')
  })
  }
}
