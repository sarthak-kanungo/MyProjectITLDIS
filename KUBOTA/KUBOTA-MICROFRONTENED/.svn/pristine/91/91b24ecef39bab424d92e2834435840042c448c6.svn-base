import { Component, OnInit, ViewChild,ChangeDetectionStrategy } from '@angular/core';
import { SoPagePresenter } from './so-page.presenter';
import { FormGroup, AbstractControl, FormArray } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { SoPageWebService } from './so-page-web.service';
import { SaveSalesOrder, HeaderResponse, SoPatchJson } from '../../domain/so.domain';
import { Operation, OperationsUtil } from '../../../../../utils/operation-util';
import { ItemDetailTableConfig } from '../item-details-table/item-details-table.component';
import { DateService } from '../../../../../root-service/date.service';
import { SoPageStore } from './so-page.store';

import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { Location } from '@angular/common';
@Component({
  selector: 'app-so-page',
  templateUrl: './so-page.component.html',
  styleUrls: ['./so-page.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [SoPagePresenter, SoPageWebService, SoPageStore]
})
export class SoPageComponent implements OnInit {
  soForm: FormGroup
  customerOrderForm: FormGroup
  // itemDetailsForm: FormGroup
  partsTotalForm: FormGroup
  itemDetailsTable: AbstractControl
  isEdit: boolean;
  picklist:boolean=false;
  showInvoice:boolean=false;
  isView: boolean;
  dialogMsg: string;
  isDraft: boolean;
  isbuttonsShow: boolean = true;
  idForUpdate: number
  headerData: HeaderResponse
  customerType: string
  isNewExisting: boolean
  isMachine: boolean
  isDealerCode: boolean
  isRetailer: boolean
  itemDetailTableConfig: ItemDetailTableConfig;
  discountRate: number;
  state: string;
  addNewPartForm: FormGroup;
  machineDetailList: object[];
  hideLinks:boolean;
  exampleParent: any;
  deletedPartsIds = [];
  isSubmitDisable:boolean = false;
  userType:string
  constructor(
    private presenter: SoPagePresenter,
    private soPageWebService: SoPageWebService,
    private activateRoute: ActivatedRoute,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private router: Router,
    private dateService: DateService,
    private userService: LoginFormService,
    public location: Location
  ) { 
    this.userType = userService.getLoginUserType();
  }
  ngOnInit() {
    this.presenter.operation = OperationsUtil.operation(this.activateRoute)
    this.soForm = this.presenter.createSoForm();
    this.customerOrderForm = this.soForm.get('customerOrderForm') as FormGroup
    // this.itemDetailsForm = this.soForm.get('itemDetailsForm') as FormGroup
    this.partsTotalForm = this.presenter.partsTotalForm
    this.itemDetailsTable = this.presenter.itemDetailsTableRow.get('itemDetailsDataTable');
    this.addNewPartForm = this.presenter.addNewPartForm;
    
    this.itemDetailTableConfig = new ItemDetailTableConfig(this.presenter.createItemDetailsTableRowFn);
    this.getIdFromUrl();
    this.viewOrEditOrCreate();
    this.stateValueChanges();
    this.discountRateValueChanges();
  }
  private discountRateValueChanges() {
    this.customerOrderForm.get('discountRate').valueChanges.subscribe(val => {
      if (val <= 100) {
        this.discountRate = (val && parseInt(val)) || 0;
      }
      else {
        this.toastr.error(`Discount Rate is More Than 100%`)
        this.customerOrderForm.get('discountRate').patchValue(0);
        this.discountRate = 0;
      }
    });
  }
  private stateValueChanges() {
    this.customerOrderForm.get('state').valueChanges.subscribe(val => {
      this.state = val;
    });
  }
  getIdFromUrl() {
    this.activateRoute.paramMap.subscribe(param => {
      
      if (param.has('id')) {
        this.getSoForView(atob(param.get('id')));
        this.idForUpdate = + atob(param.get('id'));
      }
    })
  }
  private viewOrEditOrCreate() {
    if (this.presenter.operation === Operation.VIEW) {
      
      this.isView = true
      this.isbuttonsShow = false
      this.isEdit = false
    } else if (this.presenter.operation === Operation.EDIT) {
      
      this.isbuttonsShow = true
      this.isEdit = true
    } else if (this.presenter.operation === Operation.CREATE) {
      
    }
  }
  exit() {
    this.router.navigate([(this.isEdit || this.isView) ? '../../' : '../'], { relativeTo: this.activateRoute });
    // this.router.navigate(['/spares/countersales/customerorder']);
  }
  deletedRowIds(event) {
    this.deletedPartsIds.push(event)
  }
  submitData() {
    
    // this.jsonForSo()
    // if (this.isEdit) {
    //   this.soPageWebService.deletePart(this.deletedPartsIds.join()).subscribe(res => {
    
    //   })
    // }
    this.soPageWebService.postSaveSoDetails(this.jsonForSo(this.deletedPartsIds.join())).subscribe(response => {
      let displayMsg = response['message']
      if (response) {
        this.toastr.success(displayMsg, 'Success')
        this.router.navigate([this.isEdit ? '../../' : '../'], { relativeTo: this.activateRoute });
      }else{
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
      }
    },error => {
      this.isSubmitDisable = false;
      this.toastr.error('Error generated while saving','Transaction failed');
    })
  }
  private jsonForSo(dealetparts) {
    const salesOrder = {
      ... this.buildJsonForSo(),
      ...this.buildJsonForItemDetails(),
      'deleteParts':dealetparts
    }
    return salesOrder;
  }
  buildJsonForSo() {
    const soRowValue = this.soForm.getRawValue()
    const saveAndSubmitSo = {} as SaveSalesOrder;
    if (this.isDraft === true) {
      saveAndSubmitSo.draftFlag = true;
    } else {
      saveAndSubmitSo.draftFlag = false;
    }
    saveAndSubmitSo.customerOrderDate = this.dateService.getDateIntoYYYYMMDD(soRowValue.customerOrderForm.customerOrderDate);
    saveAndSubmitSo.contactNumber = soRowValue.customerOrderForm.mobileNumber ? (soRowValue.customerOrderForm.mobileNumber.mobileNumber?soRowValue.customerOrderForm.mobileNumber.mobileNumber:soRowValue.customerOrderForm.mobileNumber):null
    saveAndSubmitSo.customerAddress1 = soRowValue.customerOrderForm.customerAddress1 ? soRowValue.customerOrderForm.customerAddress1 : null
    saveAndSubmitSo.customerAddress2 = soRowValue.customerOrderForm.customerAddress2 ? soRowValue.customerOrderForm.customerAddress2 : null
    if (soRowValue.customerOrderForm.customerName != null) {
      saveAndSubmitSo.customerName = soRowValue.customerOrderForm.customerName ? soRowValue.customerOrderForm.customerName : null
    }
    if (soRowValue.customerOrderForm.retailerName != null) {
      saveAndSubmitSo.customerName = soRowValue.customerOrderForm.retailerName ? soRowValue.customerOrderForm.retailerName : null
    }
    if (soRowValue.customerOrderForm.mechanicName != null) {
      saveAndSubmitSo.customerName = soRowValue.customerOrderForm.mechanicName ? soRowValue.customerOrderForm.mechanicName : null
    }
    if (soRowValue.customerOrderForm.dealerName != null) {
      saveAndSubmitSo.customerName = soRowValue.customerOrderForm.dealerName ? soRowValue.customerOrderForm.dealerName : null
    }
    saveAndSubmitSo.customerType = soRowValue.customerOrderForm.customerType ? soRowValue.customerOrderForm.customerType : null
    saveAndSubmitSo.dicountType = soRowValue.customerOrderForm.discountType ? soRowValue.customerOrderForm.discountType : null
    saveAndSubmitSo.discountRate = +(soRowValue.customerOrderForm.discountRate ? soRowValue.customerOrderForm.discountRate : null)
    saveAndSubmitSo.country = soRowValue.customerOrderForm.country ? soRowValue.customerOrderForm.country : null
    saveAndSubmitSo.state = soRowValue.customerOrderForm.state ? soRowValue.customerOrderForm.state.state : null
    saveAndSubmitSo.district = soRowValue.customerOrderForm.district ? soRowValue.customerOrderForm.district.district : null
    saveAndSubmitSo.tehsil = soRowValue.customerOrderForm.tehsil ? soRowValue.customerOrderForm.tehsil.tehsil : null;
    saveAndSubmitSo.village = soRowValue.customerOrderForm.village ? soRowValue.customerOrderForm.village.city : null
    saveAndSubmitSo.pinCode = soRowValue.customerOrderForm.pinCode ? soRowValue.customerOrderForm.pinCode.pinCode : null
    saveAndSubmitSo.pinId = soRowValue.customerOrderForm.pinId? soRowValue.customerOrderForm.pinId:null
    saveAndSubmitSo.postOffice = soRowValue.customerOrderForm.postOffice ? soRowValue.customerOrderForm.postOffice.postOffice : null
    saveAndSubmitSo.totalBasicValue = + soRowValue.customerOrderForm.totalBasicValue;
    saveAndSubmitSo.totalDiscountValue = + soRowValue.customerOrderForm.totalDiscountValue;
    saveAndSubmitSo.totalSalesOrderAmount = + soRowValue.customerOrderForm.totalQuotationAmount;
    saveAndSubmitSo.totalTaxValue = + soRowValue.customerOrderForm.totalTaxValue;
    if (soRowValue.customerOrderForm.customerMasterId) {
      saveAndSubmitSo.customerMaster = {
        id: soRowValue.customerOrderForm ? soRowValue.customerOrderForm.customerMasterId : null
      }
    }
    if (soRowValue.customerOrderForm.prospectMasterId) {
      saveAndSubmitSo.prospectMasterId = {
        id: soRowValue.customerOrderForm ? soRowValue.customerOrderForm.prospectMasterId : null
      }
    }
    if (soRowValue.customerOrderForm.quotationNoId) {
      saveAndSubmitSo.spareQuotation = {
        id: soRowValue.customerOrderForm ? soRowValue.customerOrderForm.quotationNoId : null
      }
    }
    if (soRowValue.customerOrderForm.partyMasterId) {
      saveAndSubmitSo.partyMaster = {
        id: soRowValue.customerOrderForm ? soRowValue.customerOrderForm.partyMasterId : null
      }
    }
    if (soRowValue.customerOrderForm.coDealerId) {
      saveAndSubmitSo.coDealer = {
        id: soRowValue.customerOrderForm ? soRowValue.customerOrderForm.coDealerId : null
      }
    }
    if (this.presenter.operation === Operation.EDIT) {
      saveAndSubmitSo.id = this.idForUpdate;
    }
    
    return saveAndSubmitSo;
  }
  private buildJsonForItemDetails() {
    const soRowValue = this.soForm.getRawValue();
    
    const saveAndSubmitSo = {} as SaveSalesOrder;
    const soPartDetail = [];
    soRowValue.itemDetailsTableData.itemDetailsDataTable.forEach(element => {
      soPartDetail.push({
        amount: element.amount || null,
        hsnCode: element.hsnCode,
        itemDescription: element.itemDescription,
        itemNo: element.itemNo.itemNo,
        netAmount: (element.netAmount && parseFloat(element.netAmount)),
        quantity: (element.quantity && parseFloat(element.quantity)),
        discountPercent: (element.discountPercent && parseFloat(element.discountPercent)) || null,
        discountAmount: (element.discountAmount && parseFloat(element.discountAmount)),
        cgstPercent: element.cgstPercent || null,
        cgstAmount: (element.cgstAmount && parseFloat(element.cgstAmount)) || null,
        igstPercent: element.igstPercent || null,
        igstAmount: (element.igstAmount && parseFloat(element.igstAmount)) || null,
        sgstPercent: element.sgstPercent || null,
        sgstAmount: (element.sgstAmount && parseFloat(element.sgstAmount)) || null,
        unitPrice: element.unitPrice,
        sparePartMaster: {
          id: element.id ? element.id : (element.itemNo.id ? element.itemNo.id : element.sparePartMasterId),
          itemNo: element.itemNo.itemNo
        },
        binLocation: element.binLocation || null,
        binLocationMaster: element.binId ? { id: (element.binId && element.binId.value) ? element.binId.value : element.binId } : null,
        store: element.store || null,
        gstAmount: (soRowValue.partsTotal.gstAmount && parseFloat(soRowValue.partsTotal.gstAmount)),
        subTotal: soRowValue.partsTotal.subTotal,
        issueQuantity: element.issueQuantity,
        totalInvoiceAmount: (soRowValue.partsTotal.totalInvoiceAmount && parseFloat(soRowValue.partsTotal.totalInvoiceAmount)) || null,
        id: element.rowId
      });
    });
    soPartDetail.forEach(ele => {
      if (ele.issueQuantity == null) {
        delete ele.binLocationMaster;
      }
    });
    if (this.presenter.operation === Operation.CREATE) {
      soPartDetail.forEach(ele => {
        delete ele.id;
      });
    }
    saveAndSubmitSo.spareSalesOrderPartDetailList = soPartDetail;
    return saveAndSubmitSo;
  }
  getSoForView(id: string) {
    this.soPageWebService.getSoViewdata(+id).subscribe(viewResult => {
      
      this.headerData = viewResult.headerResponse
      this.presenter.patchDataForViewOrEdit(viewResult)
      if (this.isEdit) {
        this.customerOrderForm.get('quotationNo').setErrors(null);
      }
      this.toCheckCustomerType(viewResult)
      // viewResult.partDetails.forEach(parts => {
      
      //   this.presenter.addRow(parts)
      // })
      this.machineDetailList = viewResult.partDetailsForSalesOrder
      this.presenter.partsTotalForm.get('subTotal').patchValue(viewResult.headerResponse.totalBasicValue)
      this.presenter.partsTotalForm.get('gstAmount').patchValue(viewResult.headerResponse.totalTaxValue);
      // this.presenter.partsTotalForm.get('totalInvoiceAmount').patchValue(viewResult.headerResponse.totalQuotationAmount)
      this.presenter.partsTotalForm.get('totalInvoiceAmount').patchValue(viewResult.headerResponse.totalSalesOrderAmount)
      this.presenter.disabledFieldForCustomerName()
      if (this.presenter.operation === Operation.VIEW) {
        this.customerOrderForm.disable();
        this.presenter.itemDetailsTableRow.disable();
      }
      if(this.userType.toLocaleLowerCase()=='dealer' && this.headerData.salesOrderStatus.toLocaleLowerCase()=='open'){
        this.picklist = true;
      }
      if(this.userType.toLocaleLowerCase()=='dealer' && this.headerData.showInvoice){
        this.showInvoice = true;
      }
    })
  }
  backBtn(){
    this.location.back();
  }
  picklistGenearte(){
    console.log(this.customerOrderForm.controls)
    let custNo = btoa(this.customerOrderForm.controls.customerOrderNo.value);
    this.router.navigate(['../../../picklist/create'], { relativeTo: this.activateRoute, queryParams: { custId: custNo}});
  }
  generateInvoice(){
    let custNo = btoa(this.customerOrderForm.controls.customerOrderNo.value);
    this.router.navigate(['../../../salesinvoice/create'], { relativeTo: this.activateRoute, queryParams: { custId: custNo}});
  }

  toCheckCustomerType(viewResult: SoPatchJson) {
    this.customerType = viewResult.headerResponse.customerType
    if (viewResult.headerResponse.customerType == 'New/Existing') {
      this.isNewExisting = true;
      this.isMachine = false;
      this.isDealerCode = false;
      this.isRetailer = false;
    }
    if (viewResult.headerResponse.customerType == 'Mechanic') {
      this.isNewExisting = false;
      this.isMachine = true;
      this.isDealerCode = false;
      this.isRetailer = false;
    }
    if (viewResult.headerResponse.customerType == 'Retailer') {
      this.isNewExisting = false;
      this.isMachine = false;
      this.isDealerCode = false;
      this.isRetailer = true;
    }
    if (viewResult.headerResponse.customerType == 'Co-Dealer') {
      this.isNewExisting = false;
      this.isMachine = false;
      this.isDealerCode = true;
      this.isRetailer = false;
    }
  }
  saveAndSubmitForm(isSave?: boolean) {
    
    this.isDraft = isSave
    this.dialogMsg = isSave ? 'save' : 'submit'
    this.validateForm();
  }
  validateForm() {
    this.presenter.markFormAsTouched();
    console.log(this.customerOrderForm.get('pinCode').value)
    if(this.customerOrderForm.get('pinCode').value === '' || this.customerOrderForm.get('pinCode').value.pinCode === undefined){
      this.toastr.error(`Pincode is required`, 'Report mandatory field')
      return false;
    }
    /* itemDetailsvalue Added by vinay to prevent submit if itemRow is zero */
    let itemDetailsvalue=this.presenter.itemDetailsTableRow.value.itemDetailsDataTable.length;
    if (this.customerOrderForm.invalid) {
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    } else if (itemDetailsvalue===0) {
      this.toastr.error('Enter Item Details')
    }else{
      this.openConfirmDialog()
    }
  }
  private openConfirmDialog(): void | any {
    let message = `Do you want to ${this.dialogMsg} Customer Order Details?`;
    if (this.isEdit) {
      message = 'Do you want to update Customer Order Details?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      
      if (result === 'Confirm' && !this.isEdit) {
        this.isSubmitDisable = true;
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
        this.isSubmitDisable = true;
        this.submitData();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
    // confirmationData.buttonAction.push(this.cancelButtonAction());
    return confirmationData;
  }
  
  viewPrint(printStatus:string){
      this.soPageWebService.print(this.customerOrderForm.get('customerOrderNo').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              let headerContentDispostion = resp.headers.get('content-disposition');
              let fileName = headerContentDispostion.split("=")[1].trim();
              const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
              saveAs(file);
            }
       })
   }
   public clearForm() {
    this.customerOrderForm.reset();
    (this.itemDetailsTable as FormArray).clear();
    this.partsTotalForm.reset();
    this.presenter.enabledFieldForCustomerName();
    this.soPageWebService.getSystemGeneratedDate().subscribe(dateRes => {
      if (dateRes.result) {
        this.customerOrderForm.get('customerOrderDate').patchValue(new Date(dateRes.result));
      }
    });
  }
}
