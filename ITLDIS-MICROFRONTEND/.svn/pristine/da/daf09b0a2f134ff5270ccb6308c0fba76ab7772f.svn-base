import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder, FormArray } from '@angular/forms';
import { TableConfig } from 'editable-table';
import { CustomValidators, ValidateInt } from '../../../../../utils/custom-validators';
import { HttpClient, HttpParams } from '@angular/common/http';
import { urlService } from '../../../../../webservice-config/baseurl';
import { environment } from '../../../../../../environments/environment';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateMachineTransferRequestService {
  private createTransferForm: FormGroup

  private readonly saveMAchineTransferUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.addDealerMachineTransfer}`;

  private readonly searchByItemNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}/machineMaster/autoCompleteItems`;

  private readonly getItemDetailsFromItemNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.getItemInMachineTransfer}`;

  private readonly getViewDataUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.findByRequestNumber}`;

  private readonly getApprovalDetailsUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}/machineTransferApprovalDetails`;

  private readonly approveRejectTransferRequestUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}/machineTransferApproval`;
  
  totalQtySubject:BehaviorSubject<number> = new BehaviorSubject(0);

  constructor(private httpClient: HttpClient, private fb: FormBuilder) { }
  
  public generateCreateTransferForm() {
    this.createTransferForm = new FormGroup({
      transferForm: this.searchMachineTrnasferForm(),
      itemDetails: new FormArray([])
    });
    return this.createTransferForm;
  }

  public getTransferForm(){
    return this.createTransferForm.get('transferForm') as FormGroup;
  }

  public getTransferItemDetailsForm(){
    return this.createTransferForm.get('itemDetails') as FormArray;
  }

  searchMachineTrnasferForm() {
    return this.fb.group({
      requestNo: [{ value: null, disabled: true }],
      requestDate: [{ value: null, disabled: true }],
      requestStatus: [{ value: null, disabled: true }],
      dealerCode: [{ value: null, disabled: true }],
      dealerName: [{ value: null, disabled: true }],
      transferFromdealerCode: [null, Validators.required],
      transferFromdealerName: [{value: null, disabled: true}],
      issuedBy: [{ value: null, disabled: true }],
      gstinNumber: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      totalQuantity: [{ value: 1, disabled: false }, Validators.compose([Validators.required])],
      invoiceNumber: [null, Validators.compose([])]
    })
  }

  getControlsConfigForTableFormGroup() {
    return this.fb.group({
      id: [null],
      isSelected: [{ value: null, disabled: false }],
      itemNo: [{ value: null, disabled: false }, Validators.required],
      product: [{ value: null, disabled: true }],
      series: [{ value: null, disabled: true }],
      model: [{ value: null, disabled: true }],
      variant: [{ value: null, disabled: true }],
      itemDescription: [{ value: null, disabled: true }],
      quantity: [{ value: '1', disabled: false }, [Validators.required,CustomValidators.numberOnly,Validators.min(1)]]
    })
  }

  addRow(data?:any){
    if(this.getTransferItemDetailsForm().valid || data){
      let fg:FormGroup = this.getControlsConfigForTableFormGroup();
      if(data){
        fg.patchValue(data);
      }
      this.getTransferItemDetailsForm().push(fg);
      return fg;
    }
    return null;
  }

  deleteRow(){
    const items = this.getTransferItemDetailsForm().controls.filter(fg => !(fg as FormGroup).controls.isSelected.value)
    this.getTransferItemDetailsForm().clear();
    if(items.length==0){
      this.addRow();
    }else{
      items.forEach(item => this.getTransferItemDetailsForm().push(item))
    }
  }

  saveMAchineTransfer(data) {
    return this.httpClient.post(this.saveMAchineTransferUrl, data)
  }


  searchByItemNumber(searchKey: string) {
    return this.httpClient.get(this.searchByItemNumberUrl, {
      params: new HttpParams()
        .set('itemNo', searchKey)
        .set('productGroup', '')
        .set('functionality', 'MachineTransfer')
    })
  }

  getItemDetailsFromItemNumber(searchKey: string) {
    return this.httpClient.get(this.getItemDetailsFromItemNumberUrl, {
      params: new HttpParams()
        .set('itemNumber', searchKey)
    })
  }
  getViewData(requestNumber) {
    return this.httpClient.get(this.getViewDataUrl, {
      params: new HttpParams()
        .set('requestNumber', requestNumber)
    })
  }

  getApprovalDetails(requestNumber){
    return this.httpClient.get(this.getApprovalDetailsUrl, {
      params: new HttpParams()
        .set('requestNumber', requestNumber)
    })
  }

  rejectOrApprove(data:object){
    return this.httpClient.post(this.approveRejectTransferRequestUrl, data);
  }
}
