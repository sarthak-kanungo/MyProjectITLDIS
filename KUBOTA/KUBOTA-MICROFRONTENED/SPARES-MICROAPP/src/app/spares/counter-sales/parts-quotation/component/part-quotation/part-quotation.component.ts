import { Component, OnInit, Input, OnChanges,ChangeDetectorRef } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatAutocompleteSelectedEvent, MatSelectChange } from '@angular/material';
import { Observable } from 'rxjs';
import { PartQuotationWebService } from './part-quotation-web.service';
import { PartQuotationPagePresenter } from '../part-quotation-page/part-quotation-page-presenter';
import { AutoCompleteMobileNo, CustomerType, AutoState, AutoDistrict, AutoTehsil, AutoCity, AutoPinCode, AutoPostOffice, SaveAndSubmitQuotation, AutoRetailer, AutoMachine } from '../../domain/part-quotation-domain';
import { Operation } from '../../../../../utils/operation-util';
import { PartQuotationCommonWebService } from '../../service/part-quotation-common-web.service';
import { ItemDetailsWebService } from 'src/app/spares/spares-purchase/spares-purchase-order/component/item-details/item-details-web.service';
import { saveAs } from 'file-saver';
import { HttpResponse } from '@angular/common/http';
import { MatDialog, MatDialogConfig  } from '@angular/material';
import { ModalFileUploadComponent } from 'src/app/spares/Utility/modal-file-upload/modal-file-upload.component';
import { ToastrService } from 'ngx-toastr';
import { QuotationPartDetail } from '../../domain/part-quotation-domain';
import { ItemErrorReportComponent } from 'src/app/spares/Utility/item-error-report/item-error-report.component';
import {BehaviorSubject} from 'rxjs';

@Component({
  selector: 'app-part-quotation',
  templateUrl: './part-quotation.component.html',
  styleUrls: ['./part-quotation.component.scss'],
  providers: [PartQuotationWebService, ItemDetailsWebService]
})
export class PartQuotationComponent implements OnInit, OnChanges {
  @Input() markForCheck: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  isView: boolean
  isEdit: boolean
  isNewExisting: boolean = false;
  isMachine: boolean = false;
  isRetailer: boolean = false;
  countryId: number
  stateId: number
  districtId: number
  tehsilId: number
  cityId: number
  pinCodeId: number
  @Input() partQuotationDetailsForm: FormGroup
  customerName$: Observable<Array<AutoCompleteMobileNo>>
  state$: Observable<Array<AutoState>>
  district$: Observable<Array<AutoDistrict>>
  tehsil$: Observable<Array<AutoTehsil>>
  city$: Observable<Array<AutoCity>>
  pinCode$: Observable<Array<AutoPinCode>>
  postOffice$: Observable<Array<AutoPostOffice>>
  retailerList$: Observable<Array<AutoRetailer>>
  machineList$: Observable<Array<AutoMachine>>
  customersType: Array<CustomerType>
  customerType: string
  @Input() headerData: SaveAndSubmitQuotation
  // @Input() State_id: number;
  // @Input() District_id: number;
  // @Input() Tehsil_id: number;
  // @Input() City_id: number;

  constructor(
    private partQuotationWebService: PartQuotationWebService,
    private partQuotationPagePresenter: PartQuotationPagePresenter,
    private partQuotationCommonWebService: PartQuotationCommonWebService,
    private itemDetailsWebService: ItemDetailsWebService,
    private matDialog : MatDialog,
    private changeDetectorRef: ChangeDetectorRef,
    private toasterService: ToastrService,
  ) { }

  ngOnChanges() {
    this.patchHeaderData()
  }

  patchHeaderData(){
    if (this.headerData) {
      this.stateId = this.headerData.stateId
      this.districtId = this.headerData.districtId
      this.tehsilId = this.headerData.tehsilId
      this.cityId = this.headerData.cityId
      this.pinCodeId = this.headerData.pinCodeId
      this.customerType = this.headerData.customerType
      this.displayFieldByCustomerTypeForView()
    }
  }
  private changeDetectorMarkForCheck() {
      if (this.markForCheck) {
        this.markForCheck.subscribe(res => {
          if (res) {
            console.log('res', res);
            this.changeDetectorRef.markForCheck();
          }
        });
      }
  }
  displayFieldByCustomerTypeForView() {
    if (this.headerData.customerType == 'New/Existing') {
      this.isNewExisting = true
      this.isMachine = false
      this.isRetailer = false
    } else if (this.headerData.customerType == 'Mechanic') {
      this.isNewExisting = false
      this.isMachine = true
      this.isRetailer = false
    } else if (this.headerData.customerType == 'Retailer') {
      this.isNewExisting = false
      this.isMachine = false
      this.isRetailer = true
    }

  }

  ngOnInit() {
    this.viewAndCreate()
    this.getSystemDate()
    this.valueChangesForAutoComplete()
    this.loadDropDown()
    this.getCountry()
    this.getStateList()
    this.getDistrictList()
    this.getTehsilList()
    this.getCityList()
    this.getPinCodeList()
    this.getPostOfficeList()
    this.patchHeaderData()
    this.changeDetectorMarkForCheck();
     /*this.partQuotationPagePresenter.onClickClearBehaviorCall.subscribe(val => {
        if(val=='clear'){
            this.partQuotationDetailsForm.reset();
        }
    })*/
  }

  viewAndCreate() {
    if (this.partQuotationPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    } else if (this.partQuotationPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true
    }
  }
  loadDropDown() {
    this.partQuotationCommonWebService.getSpareCustomerTypeDropdown('Quotation').subscribe(response => {
      this.customersType = response
    })
  }
  selectionCustomerType(event: MatSelectChange) {
    if (event.value) {
      this.customerType = event.value
      this.displayFieldByCustomerType(event)
      this.partQuotationPagePresenter.enabledFieldForCustomerName()
      this.partQuotationPagePresenter.resetFieldForCustomerType()
      if(this.customerType == 'Mechanic'){
        this.partQuotationDetailsForm.controls.retailer.disable();
        this.partQuotationDetailsForm.controls.mechanic.enable();
      }else if(this.customerType == 'Retailer'){
        this.partQuotationDetailsForm.controls.mechanic.disable();
        this.partQuotationDetailsForm.controls.retailer.enable();
      }else {
        this.partQuotationDetailsForm.controls.mechanic.disable();
        this.partQuotationDetailsForm.controls.retailer.disable();
      }
    }
  }

  displayFieldByCustomerType(event: MatSelectChange) {
    if (event.value == 'New/Existing') {
      this.isNewExisting = true
      this.isMachine = false
      this.isRetailer = false
    } else if (event.value == 'Mechanic') {
      this.isNewExisting = false
      this.isMachine = true
      this.isRetailer = false
    } else if (event.value == 'Retailer') {
      this.isNewExisting = false
      this.isMachine = false
      this.isRetailer = true
    }
    this.changeDetectorRef.markForCheck();
  }
  getCountry() {
    this.partQuotationWebService.getCountry().subscribe(response => {
      this.partQuotationDetailsForm.get('country').patchValue(response.country)
      this.countryId = response.id
    })
  }
  getSystemDate() {
    if (this.partQuotationPagePresenter.operation === Operation.CREATE) {
      this.partQuotationWebService.getSystemGeneratedDate().subscribe(response => {
        const quotationDate = response['result']
        this.partQuotationDetailsForm.get('quotationDate').patchValue(new Date(quotationDate))
      })
    }
  }
  private valueChangesForAutoComplete() {
    this.partQuotationDetailsForm.get('mobileNumber').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        console.log("valueChangesForAutoComplete--->", valueChange)
        const customerName = typeof valueChange == 'object' ? valueChange.mobileNumber : valueChange
        this.autoCustomerName(customerName)
      }
    })
    this.partQuotationDetailsForm.get('retailer').valueChanges.subscribe(valueChange => {
      this.partQuotationDetailsForm.get('retailer').setErrors({required:'Please select from list'})
      if (valueChange) {
        if(typeof valueChange == 'object'){
          this.partQuotationDetailsForm.get('retailer').setErrors(null);
        }
        const retailer = typeof valueChange == 'object' ? valueChange.value : valueChange
        console.log("retailer-->", retailer)
        this.autoRetailer(retailer)
      }else{
        this.retailerList$ = null;
      }
    })
    this.partQuotationDetailsForm.get('mechanic').valueChanges.subscribe(valueChange => {
      this.partQuotationDetailsForm.get('mechanic').setErrors({required:'Please select from list'})
      if (valueChange) {
        if(typeof valueChange == 'object'){
          this.partQuotationDetailsForm.get('mechanic').setErrors(null);
        }
        const machanic = typeof valueChange == 'object' ? valueChange.value : valueChange
        this.autoMachine(machanic)
      }else{
        this.machineList$ = null;
      }
    })
  }
  autoCustomerName(customerName: string) {
    this.customerName$ = this.partQuotationWebService.autoCompleteMobileNumber(customerName)
    console.log("customerName$--->", this.customerName$)
  }
  selectedCustomerName(event: MatAutocompleteSelectedEvent) {
    let code : string
    if(event.option.value.prospectCode){
      code = event.option.value.prospectCode
    }else if(event.option.value.customerCode){
      code = event.option.value.customerCode
    }else if(event.option.value.oldCustomerCode){
      code = event.option.value.oldCustomerCode
    }
    this.partQuotationWebService.getCustomerDetails(code).subscribe(response => {
      this.partQuotationPagePresenter.patchForCustomerMaster(response)
      this.stateId = response.stateId
      this.districtId = response.districtId
      this.tehsilId = response.tehsilId
      this.cityId = response.cityId
      this.pinCodeId = response.pinCodeId
      if (response) {
        this.partQuotationPagePresenter.disabledFieldForCustomerName()
      }
    })
  }
  onKeyDownCustomerName(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.partQuotationPagePresenter.enabledFieldForCustomerName()
      this.partQuotationPagePresenter.resetFieldForCustomerName()
    }
  }
  displayFnCustomerName(name: AutoCompleteMobileNo) {
    return name ? name.mobileNumber : undefined
  }
  autoRetailer(retailer: string) {
    console.log("selected customer type--->", this.customerType)
    this.retailerList$ = this.partQuotationWebService.getRetailerOrMechanicAutocomplete(retailer, this.customerType)
  }
  retailerSelect(event: MatAutocompleteSelectedEvent) {
    this.partQuotationWebService.getRetailerOrMechanicDetails(event.option.value.id).subscribe(res => {
      console.log("res ", res);
      if (res) {
        if(res.pinCode === null || res.pinCode === '' ){
          this.partQuotationDetailsForm.controls.retailer.setErrors({pincodeRequired: 'Address Pincode not available'});
        }else{
          this.partQuotationDetailsForm.controls.retailer.setErrors(null);
          this.partQuotationPagePresenter.patchValueForStateToCity(res);
          this.partQuotationDetailsForm.get('partyMasterId').patchValue(event.option.value.id)
          this.partQuotationPagePresenter.disabledFieldForMechanicNRetailer()
        }
      }
    })
  }
  onKeyDown(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.partQuotationPagePresenter.enableFieldForMechanicNRetailer()
      this.partQuotationPagePresenter.resetFieldForMechanicNRetailer()
    }
  }
  displayFnRetailer(retailer: AutoRetailer) {
    return retailer ? (retailer.code ? retailer.code : retailer['retailer']) : undefined
  }
  autoMachine(mechanic: string) {
    this.machineList$ = this.partQuotationWebService.getRetailerOrMechanicAutocomplete(mechanic, this.customerType)
  }
  machineSelect(event: MatAutocompleteSelectedEvent) {
    this.partQuotationWebService.getRetailerOrMechanicDetails(event.option.value.id).subscribe(res => {
      if(res.pinCode === null || res.pinCode === '' ){
        this.partQuotationDetailsForm.controls.mechanic.setErrors({pincodeRequired: 'Address Pincode not available'});
      }else{
        this.partQuotationDetailsForm.controls.mechanic.setErrors(null);
        this.partQuotationPagePresenter.patchValueForStateToCity(res);
        this.partQuotationDetailsForm.get('partyMasterId').patchValue(event.option.value.id)
        this.partQuotationPagePresenter.disabledFieldForMechanicNRetailer()
      }
    });
  }
  displayFnMachine(mechanic: AutoMachine) {
    console.log("mechanic--->", mechanic);
    return mechanic ? (mechanic.code ? mechanic.code : mechanic['mechanic']) : undefined
  }
  private getStateList() {
    this.partQuotationDetailsForm.get('state').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const state = typeof valueChange == 'object' ? valueChange.state : valueChange
        this.autoState(state)
      }
    })
  }
  autoState(state: string) {
    if (this.countryId) {
      this.state$ = this.partQuotationWebService.getStateAutocomplete(this.countryId, state)
    }
  }
  selectedState(event: MatAutocompleteSelectedEvent) {
    console.log("event ", event);
    this.stateId = event.option.value.id
    this.partQuotationDetailsForm.get('district').reset();
    this.partQuotationDetailsForm.get('tehsil').reset();
    this.partQuotationDetailsForm.get('city').reset();
    this.partQuotationDetailsForm.get('pinCode').reset();
    this.partQuotationDetailsForm.get('postOffice').reset();
  }
  displayFnState(value: AutoState) {
    return value ? value.state : undefined
  }
  private getDistrictList() {
    this.partQuotationDetailsForm.get('district').valueChanges.subscribe(valueChange => {
      console.log("values i/p--->", valueChange)
      if (valueChange) {
        const district = typeof valueChange == 'object' ? valueChange.district : valueChange
        this.autoDistrict(district)
      }
    })
  }
  stateValue(event){
    if (typeof event=='string') {
      this.partQuotationDetailsForm.get('state').setErrors({
        stateError:'Please select from List'
      })
    }
  }
  autoDistrict(district: string) {
    if (this.stateId) {
      this.district$ = this.partQuotationWebService.getDistrictAutocomplete(this.stateId, district)
    }
  }
  districtValue(event){
    if (typeof event=='string') {
      this.partQuotationDetailsForm.get('district').setErrors({
        districtError:'Please select from List'
      })
    }
  }


  selectedDistrict(event: MatAutocompleteSelectedEvent) {
    this.districtId = event.option.value.id
    this.partQuotationDetailsForm.get('tehsil').reset();
    this.partQuotationDetailsForm.get('city').reset();
    this.partQuotationDetailsForm.get('pinCode').reset();
    this.partQuotationDetailsForm.get('postOffice').reset();
  }
  displayFnDistrict(value: AutoDistrict) {
    return value ? value.district : undefined
  }
  private getTehsilList() {
    this.partQuotationDetailsForm.get('tehsil').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const tehsil = typeof valueChange == 'object' ? valueChange.tehsil : valueChange
        this.autoTehsil(tehsil)
      }
    })
  }

  tehsilValue(event){
    if (typeof event=='string') {
      this.partQuotationDetailsForm.get('tehsil').setErrors({
        tehsilError:'Please select from List'
      })
    }
  }
  autoTehsil(tehsil: string) {
    if (this.districtId) {
      this.tehsil$ = this.partQuotationWebService.getTehsilAutocomplete(this.districtId, tehsil)
    }
  }
  selectedTehsil(event: MatAutocompleteSelectedEvent) {
    this.tehsilId = event.option.value.id
    this.partQuotationDetailsForm.get('city').reset();
    this.partQuotationDetailsForm.get('pinCode').reset();
    this.partQuotationDetailsForm.get('postOffice').reset();
  }
  displayFnTehsil(value: AutoTehsil) {
    return value ? value.tehsil : undefined
  }
  private getCityList() {
    this.partQuotationDetailsForm.get('city').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const city = typeof valueChange == 'object' ? valueChange.city : valueChange
        this.autoCity(city)
      }
    })
  }
  villageValue(event){
    if (typeof event=='string') {
      this.partQuotationDetailsForm.get('city').setErrors({
        cityError:'Please select from List'
      })
    }
  }
  autoCity(city: string) {
    if (this.tehsilId) {
      this.city$ = this.partQuotationWebService.getCityAutocomplete(this.tehsilId, city)
    }
  }
  selectedCity(event: MatAutocompleteSelectedEvent) {
    this.cityId = event.option.value.id
    this.partQuotationDetailsForm.get('pinCode').reset();
    this.partQuotationDetailsForm.get('postOffice').reset();
  }
  displayFnCity(value: AutoCity) {
    return value ? value.city : undefined
  }
  private getPinCodeList() {
    this.partQuotationDetailsForm.get('pinCode').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const pinCode = typeof valueChange == 'object' ? valueChange.pinCode : valueChange
        this.autoPinCode(pinCode)
      }
    })
  }
  autoPinCode(pinCode: string) {
    if (this.cityId) {
      this.pinCode$ = this.partQuotationWebService.getPinCodeAutocomplete(this.cityId, pinCode)
    }
  }
  selectedPinCode(event: MatAutocompleteSelectedEvent) {
    this.pinCodeId = event.option.value.id
    this.partQuotationDetailsForm.get('postOffice').patchValue({postOffice: event.option.value.post_office})
  }

  pinCodeValue(event){
    if (typeof event=='string') {
      this.partQuotationDetailsForm.get('pinCode').setErrors({
        pinCodeError:'Please select from List'
      })
    }
  }

  displayFnPinCode(value: AutoPinCode) {
    return value ? value.pinCode : undefined
  }
  private getPostOfficeList() {
    this.partQuotationDetailsForm.get('postOffice').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const postOffice = typeof valueChange == 'object' ? valueChange.postOffice : valueChange
        this.autoPostOffice(postOffice)
      }
    })
  }
  autoPostOffice(postOffice: string) {
    if (this.pinCodeId) {
      //this.postOffice$ = this.partQuotationWebService.getPostOfficeAutocomplete(this.pinCodeId, postOffice)
    }
  }

  postOfficeValue(event){
    if (typeof event=='string') {
      this.partQuotationDetailsForm.get('postOffice').setErrors({
        postOfficeError:'Please select from List'
      })
    }
  }
  
  displayFnPostOffice(value: AutoPostOffice) {
    return value ? value.postOffice : undefined
  }

  public downloadTemplate() {
    this.itemDetailsWebService.downloadTemplateForPoItemDetail("PartQuotation.xlsx").subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
            // let headerContentDispostion = resp.headers.get('content-disposition');
            // let fileName = headerContentDispostion.split("=")[1].trim();            
            const file = new File([resp.body], "PartQuotation.xlsx", { type: 'application/vnd.ms-excel' });
            saveAs(file);
          }
    })
  }

  public uploadExcel() {
    if(this.partQuotationDetailsForm.controls.customerType.value === null){
      this.toasterService.warning('Please Select Customer Type');
      return false;
    }
    if(this.partQuotationDetailsForm.controls.customerType.value === 'Mechanic' && this.partQuotationDetailsForm.controls.mechanic.value == null){
        this.toasterService.warning('Please Select Mechanic');
        return false;
    }else if(this.partQuotationDetailsForm.controls.customerType.value === 'Retailer' && this.partQuotationDetailsForm.controls.retailer.value == null){
        this.toasterService.warning('Please Select Retailer');
        return false;
    }else if(this.partQuotationDetailsForm.controls.customerType.value === 'New/Existing' && this.partQuotationDetailsForm.controls.state.value == null){
        this.toasterService.warning('Please Enter Customer Details');
        return false;
    }
    // else if(this.partQuotationDetailsForm.controls.discountRate.value == null || this.partQuotationDetailsForm.controls.discountRate.value == undefined){
    //   this.toasterService.warning('Please Enter Discount Rate');
    //   return false;
    // }
      /*  commented by vinay  and patch discountRate 0 if value is null*/
    const state = this.partQuotationDetailsForm.get('state').value;
    
    let discountRate = this.partQuotationDetailsForm.get('discountRate').value;

    
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.id = "modal-component";
    dialogConfig.width = "500px";
    const modalDialog = this.matDialog.open(ModalFileUploadComponent, dialogConfig);
    if(this.partQuotationDetailsForm.controls.discountRate.value == null || this.partQuotationDetailsForm.controls.discountRate.value == undefined){
      this.partQuotationDetailsForm.get('discountRate').patchValue(0)
      discountRate=0
    }
    modalDialog.afterClosed().subscribe(result => {
      if(result.event === 'upload'){
          let file:File = result.data;
          const errorItemDetailsArray:Array<QuotationPartDetail> = [];
          const itemDetailsIdArray = []
          const quotationForm = this.partQuotationPagePresenter.partQuotationForm.getRawValue()
          if (quotationForm.itemDetails.length > 0) {
              quotationForm.itemDetails.forEach(element => {
                  if (typeof element.itemNo === 'object' && element.itemNo) {
                    itemDetailsIdArray.push(element.itemNo.id);
                    console.log("itemIdArray ", itemDetailsIdArray);
                  }
              })
          }
          let uploadableFile = { file: file, state: state.state, discountRate:discountRate, existingItems : itemDetailsIdArray.join() }
          this.partQuotationCommonWebService.uploadExcelPQItemDetail(uploadableFile).subscribe(response => {
              let items:Array<QuotationPartDetail> = response['result'];
              
          
              let totalBaseAmount:number = this.partQuotationPagePresenter.partQuotationTotalForm.controls.subTotal.value;
              let totalGSTAmount:number = this.partQuotationPagePresenter.partQuotationTotalForm.controls.gstAmount.value;
              let totalAmount:number = this.partQuotationPagePresenter.partQuotationTotalForm.controls.totalInvoiceAmount.value;
          
             
              if(items!=null && items.length > 0){
                  
                  items.forEach(data => {
                      if(data.isValidItem === 'Y'){
                          //this.partQuotationPagePresenter.addNewRowInItemDetails(data,true);
                          totalBaseAmount = (totalBaseAmount==null?0:totalBaseAmount) + data.netAmount;
                          totalGSTAmount = (totalGSTAmount==null?0:totalGSTAmount) + data.gstAmount;
                          totalAmount = totalBaseAmount + totalGSTAmount;
                          
                          data.sparePartMaster = {id:data.id, itemNo:data.itemNo};
                          data.sparePartMasterId = data.id;
                          data.id = null;
                      }else{
                          errorItemDetailsArray.push(data);
                      }
                  })
                  
                  this.partQuotationPagePresenter.partQuotationTableRow.patchValue(items.filter(item => item.isValidItem == 'Y'));
                  
              }
              this.partQuotationPagePresenter.partQuotationTotalForm.controls.subTotal.patchValue(this.convertToTwoDigitsAfterDecimal(totalBaseAmount));
              this.partQuotationPagePresenter.partQuotationTotalForm.controls.gstAmount.patchValue(this.convertToTwoDigitsAfterDecimal(totalGSTAmount));
              this.partQuotationPagePresenter.partQuotationTotalForm.controls.totalInvoiceAmount.patchValue(this.convertToTwoDigitsAfterDecimal(totalAmount));
              
              this.toasterService.success(response['message']);
              if(errorItemDetailsArray.length>0){
                  
                  const dialogConfig1 = new MatDialogConfig();
                  dialogConfig1.disableClose = true;
                  dialogConfig1.id = "item-error-component";
                  dialogConfig1.width = "500px";
                  dialogConfig1.height = "350px";
                  dialogConfig1.data = errorItemDetailsArray;
                  const modalDialog = this.matDialog.open(ItemErrorReportComponent, dialogConfig1);
                  
              }
          });
        }
     });
   }
   private convertToTwoDigitsAfterDecimal(val: number): number {
      return val!=null && parseFloat(val.toFixed(2));
   }
}
