import { Component, OnInit, Input, ChangeDetectorRef, Output, EventEmitter, } from '@angular/core';
import { MatAutocompleteSelectedEvent, MatSelectChange, MatDialogConfig, MatDialog } from '@angular/material';
import { FormGroup, FormArray } from '@angular/forms';
import { CustomerOrderWebService } from './customer-order-web.service';
import { Observable } from 'rxjs';
import { CustomerType, AutoCompleteCustomerNMobileNo, StateDetails, DistrictDetails, TehsilDetails, CityDetails, PinCodeDetails, PostOfficeDetails, AutoQuotationNo, HeaderResponse, AutoCompleteDealerCode, AutoCompleteRetailer, AutoCompleteMachine, QuotationPatchJson, PartDetail } from '../../domain/so.domain';
import { SoPagePresenter } from '../so-page/so-page.presenter';
import { Operation, OperationsUtil } from '../../../../../utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { ToastrService } from 'ngx-toastr';
import { ItemDetailsWebService } from 'src/app/spares/spares-purchase/spares-purchase-order/component/item-details/item-details-web.service';

@Component({
  selector: 'app-customer-order',
  templateUrl: './customer-order.component.html',
  styleUrls: ['./customer-order.component.scss'],
  providers: [CustomerOrderWebService,ItemDetailsWebService]
})
export class CustomerOrderComponent implements OnInit {
  @Input() customerOrderForm: FormGroup
  isEdit: boolean =false;
  @Input() isView: boolean
  @Input() isNewExisting: boolean = false;
  @Input() isMachine: boolean = false;
  @Input() isDealerCode: boolean = false;
  @Input() isRetailer: boolean = false;
  @Input() customerType: string;
  @Output() hideLinks = new EventEmitter();
  linkHide:boolean = true;
  customerTypeFormView: string;
  customersList: Array<CustomerType>;
  countryId: number;
  stateId: number;
  districtId: number;
  tehsilId: number;
  cityId: number;
  pinCodeId: number;
  postOfficeId: number;
  quotationNoList$: Observable<Array<AutoQuotationNo>>
  customerName$: Array<AutoCompleteCustomerNMobileNo>
  dealerCodeList$: Observable<Array<AutoCompleteDealerCode>>
  retailerList$: Observable<Array<AutoCompleteRetailer>>
  machineList$: Observable<Array<AutoCompleteMachine>>
  stateList$: Observable<Array<StateDetails>>
  districtList$: Observable<Array<DistrictDetails>>
  tehsilList$: Observable<Array<TehsilDetails>>
  cityList$: Observable<Array<CityDetails>>
  pinCodeList$: Observable<Array<PinCodeDetails>>
  postOfficeList$: Observable<Array<PostOfficeDetails>>
  @Input() headerData: HeaderResponse
  @Input() markForCheck: Observable<boolean>;
  @Output() machineDetailChange = new EventEmitter<object[]>();
  temp: any;
  constructor(
    private presenter: SoPagePresenter,
    private customerOrderWebService: CustomerOrderWebService,
    private changeDetectorRef: ChangeDetectorRef,
    private activateRoute: ActivatedRoute,
    private itemDetailsWebService: ItemDetailsWebService,
    private toastr : ToastrService,
    private matDialog : MatDialog) { }
  ngOnChanges() {
    this.customerTypeFormView = this.customerType
    if (this.headerData) {
      this.countryId = this.headerData.countryId
      this.stateId = this.headerData.stateId
      this.districtId = this.headerData.districtId
      this.tehsilId = this.headerData.tehsilId
      this.cityId = this.headerData.cityId
      this.pinCodeId = this.headerData.pinCodeId
    }
    if(this.presenter.operation === Operation.EDIT && this.customerOrderForm.get('quotationNo')){
      this.customerOrderForm.get('quotationNo').disable();
    }
  }
  ngOnInit() {
    this.presenter.operation = OperationsUtil.operation(this.activateRoute)
    this.changeDetectorMarkForCheck();
    this.valueChangesForAutoComplete();
    this.getCustomerTypeDropDown();
    this.countryToPostOfficeList();
    this.toPatchValueFormTable();
    if (this.presenter.operation === Operation.CREATE) {
      this.getDateFromServer();
    }else if(this.presenter.operation === Operation.EDIT){
      this.isEdit = true
    }
    
    this.customerOrderForm.get('discountRate').valueChanges.subscribe(val => {
        this.presenter.onDiscountRateChange.next(val);
    });
    
  }
  toPatchValueFormTable() {
    this.presenter.partsTotalForm.get('subTotal').valueChanges.subscribe(value => {
      this.customerOrderForm.get('totalBasicValue').patchValue(value)
    })
    this.presenter.partsTotalForm.get('gstAmount').valueChanges.subscribe(value => {
      this.customerOrderForm.get('totalTaxValue').patchValue(value)
    })
    this.presenter.partsTotalForm.get('totalInvoiceAmount').valueChanges.subscribe(value => {
      this.customerOrderForm.get('totalQuotationAmount').patchValue(value)
      if (value) {
        this.toCalculateTotalDiscountAmount()
      }
    })
  }
  toCalculateTotalDiscountAmount() {
    const tableData = this.presenter.itemDetailsTableRow.get('itemDetailsDataTable') as FormArray
    let discountAmount = 0
    const tempTableData = tableData.getRawValue()
    if (tempTableData.length != 0) {
      tempTableData.forEach(element => {
        discountAmount += (element.discountAmount && parseFloat(element.discountAmount)) || 0
      });
      this.customerOrderForm.get('totalDiscountValue').patchValue(discountAmount.toFixed(2))
    }
  }
  countryToPostOfficeList() {
    this.getCountry()
    this.getStateList()
    this.getDistrictList()
    this.getTehsilList()
    this.getVillageList()
    this.getPinCodeList()
    this.getPostOfficeList()
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
  valueChangesForAutoComplete() {
    this.customerOrderForm.get('quotationNo').valueChanges.subscribe(value => {
      //if (value) {
        const quotationNumber = (value && typeof value == 'object') ? value.quotationNumber : value;
        this.autoQuotationNo(quotationNumber);
      //}
      // if (typeof value == 'object') {
      //   this.getCustomerDetailsByQuotation(value.id)
      // }
      if (value !== null) {
        this.presenter.quotationNoSetValidation();
      }
    });
    this.customerOrderForm.get('mobileNumber').valueChanges.subscribe(value => {
      if(this.customerOrderForm.get('quotationNo').value){
        return false;
      }
      if (value) {
        const customerName = typeof value == 'object' ? value.mobileNumber : value;
        this.autoCustomerName(customerName);
        if (typeof value != 'object') {
          this.presenter.enabledFieldForCustomerName('mobile');
          this.presenter.resetFieldForCustomerName('mobile');
        }
      }
    });

    this.customerOrderForm.get('dealerCode').valueChanges.subscribe(value => {
      if (value) {
        const dealerCode = typeof value == 'object' ? value.value : value;
        this.autoDealerCode(dealerCode);
      }
      if (value !== null) {
        this.presenter.dealerCodeValidation();
      }
    });
    this.customerOrderForm.get('retailer').valueChanges.subscribe(value => {
      this.customerOrderForm.get('retailer').setErrors({required:'Please select from list'})
      if (value) {
        if(typeof value == 'object'){
          this.customerOrderForm.get('retailer').setErrors(null);
        }
        const retailer = typeof value == 'object' ? value.value : value;
        this.autoRetailer(retailer);
      }else{
        this.retailerList$ = null;
      }
      if (value !== null) {
        this.presenter.retailerValidation();
      }
    });
    this.customerOrderForm.get('mechanic').valueChanges.subscribe(value => {
      this.customerOrderForm.get('mechanic').setErrors({required:'Please select from list'})
      if (value) {
        if(typeof value == 'object'){
          this.customerOrderForm.get('mechanic').setErrors(null);
        }
        const mechanic = typeof value == 'object' ? value.value : value;
        this.autoMachine(mechanic);
      }else{
        this.machineList$ = null;
      }
      if (value !== null) {
        this.presenter.mechanicValidation();
      }
    });
  }
  getStateList() {
    this.customerOrderForm.get('state').valueChanges.subscribe(value => {
      if (value) {
        const state = typeof value == 'object' ? value.state : value;
        this.autoState(state)
      }
      if (value !== null) {
        this.presenter.stateValidation();
      }
    })
  }
  getDistrictList() {
    this.customerOrderForm.get('district').valueChanges.subscribe(value => {
      if (value) {
        const district = typeof value == 'object' ? value.district : value;
        this.autoDistrict(this.stateId, district)
      }
      if (value !== null) {
        this.presenter.districtValidation();
      }
    })
  }
  getTehsilList() {
    this.customerOrderForm.get('tehsil').valueChanges.subscribe(value => {
      if (value) {
        const tehsil = typeof value == 'object' ? value.tehsil : value
        this.autoTehsil(this.districtId, tehsil)
      }
      if (value !== null) {
        this.presenter.tehsilValidation();
      }
    })
  }
  getVillageList() {
    this.customerOrderForm.get('village').valueChanges.subscribe(value => {
      if (value) {
        const city = typeof value == 'object' ? value.city : value
        this.autoCity(this.tehsilId, city)
      }
      if (value !== null) {
        this.presenter.villageValidation();
      }
    })
  }
  getPinCodeList() {
    this.customerOrderForm.get('pinCode').valueChanges.subscribe(value => {
      if (value) {
        const pinCode = typeof value == 'object' ? value.pinCode : value
        console.log("getPinCodeList--->", "cityId-->", this.cityId, "pinCode-->", pinCode);
        this.autoPin(this.districtId, pinCode)
      }
      if (value !== null) {
        this.presenter.pinCodeValidation();
      }
    })
  }
  getPostOfficeList() {
    this.customerOrderForm.get('postOffice').valueChanges.subscribe(value => {
      if (value) {
        const postOffice = typeof value == 'object' ? value.postOffice : value
        this.autoPostOffice(this.pinCodeId, postOffice)
      }
      // if (value !== null) {
      //   this.presenter.postOfficeValidation();
      // }
    })
  }
  postOfficeSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.customerOrderForm.get('postOffice').setErrors(null);
    }
  }
  customerTypeSelect(event: MatSelectChange) {
    this.customerType = event.value
    if (event.value == 'New/Existing') {
      this.isNewExisting = true
      this.isMachine = false
      this.isDealerCode = false
      this.isRetailer = false
    }
    if (event.value == 'Mechanic') {
      this.isNewExisting = false
      this.isMachine = true
      this.isDealerCode = false
      this.isRetailer = false
    }
    if (event.value == 'Retailer') {
      this.isNewExisting = false
      this.isMachine = false
      this.isDealerCode = false
      this.isRetailer = true
    }
    if (event.value == 'Co-Dealer') {
      this.isNewExisting = false
      this.isMachine = false
      this.isDealerCode = true
      this.isRetailer = false
    }
    
    this.presenter.enabledFieldForCustomerName();
    this.presenter.resetFieldForCustomerType();
    this.changeDetectorRef.markForCheck();
    if(this.customerType == 'Mechanic'){
      this.customerOrderForm.controls.retailer.disable();
      this.customerOrderForm.controls.mechanic.enable();
    }else if(this.customerType == 'Retailer'){
      this.customerOrderForm.controls.mechanic.disable();
      this.customerOrderForm.controls.retailer.enable();
    }else {
      this.customerOrderForm.controls.mechanic.disable();
      this.customerOrderForm.controls.retailer.disable();
    }
  }
  autoState(stateName: string) {
    if (this.countryId) {
      this.stateList$ = this.customerOrderWebService.getState(this.countryId, stateName)
    }
  }
  selectedState(event: MatAutocompleteSelectedEvent) {
    this.stateId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.customerOrderForm.get('state').setErrors(null);
    }
  }
  displayFnState(state: StateDetails) {
    return state ? state.state : undefined;
  }
  autoDistrict(id: number, name: string) {
    if (this.stateId) {
      this.districtList$ = this.customerOrderWebService.getDistrict(id, name)
    }
  }
  selectedDistrict(event: MatAutocompleteSelectedEvent) {
    this.districtId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.customerOrderForm.get('district').setErrors(null);
    }
  }
  displayFnDistrict(district: DistrictDetails) {
    return district ? district.district : undefined;
  }
  autoTehsil(id: number, name: string) {
    if (this.districtId) {
      this.tehsilList$ = this.customerOrderWebService.getTehsil(id, name);
    }
  }
  selectedTehsil(event: MatAutocompleteSelectedEvent) {
    this.tehsilId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.customerOrderForm.get('tehsil').setErrors(null);
    }
  }
  displayFnTehsil(tehsil: TehsilDetails) {
    return tehsil ? tehsil.tehsil : undefined;
  }
  autoCity(id: number, name: string) {
    if (this.tehsilId) {
      this.cityList$ = this.customerOrderWebService.getCity(id, name)
    }
  }
  selectedCity(event: MatAutocompleteSelectedEvent) {
    this.cityId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.customerOrderForm.get('village').setErrors(null);
    }
  }
  displayFnCity(city: CityDetails) {
    return city ? city.city : undefined;
  }
  autoPin(id: number, name: string) {
    if (this.cityId) {
      this.pinCodeList$ = this.customerOrderWebService.getPinCode(id, name)
    }
  }
  selectedPin(event: MatAutocompleteSelectedEvent) {
    //this.pinCodeId = event.option.value.id;
    this.pinCodeId = event.option.value.pinID;   /* added by vinay to get pinCodeId and set pinId value in customerOrderForm*/
    this.customerOrderForm.controls.pinId.setValue(this.pinCodeId);
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.customerOrderForm.get('pinCode').setErrors(null);
    }
  }
  displayFnPin(pin: PinCodeDetails) {
    return pin ? pin.pinCode : undefined;
  }
  autoPostOffice(id: number, name: string) {
    if (this.pinCodeId) {
      this.postOfficeList$ = this.customerOrderWebService.getPostOffice(id, name)
    }
  }
  selectedPostOffice(event: MatAutocompleteSelectedEvent) {
    this.postOfficeId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.customerOrderForm.get('pinCode').setErrors(null);
    }
  }
  displayFnPostOffice(postOffice: PostOfficeDetails) {
    return postOffice ? postOffice.postOffice : undefined;
  }
  autoCustomerName(customerName: string) {
   this.customerOrderWebService.autoCompleteCustomerNMobileN(customerName).subscribe(response => {
    this.customerName$ = response;
   })
  }
  displayFnCustomerName(customerNMobileN: AutoCompleteCustomerNMobileNo) {
    return customerNMobileN ? customerNMobileN.mobileNumber : undefined;
  }
  autoQuotationNoFun(quotationNo: string) {
      this.autoQuotationNo(quotationNo);
  }
  autoQuotationNo(quotationNo: string) {
    this.quotationNoList$ = this.customerOrderWebService.getQuotationNo(quotationNo);
  }
  quotationNoSelect(event: MatAutocompleteSelectedEvent) {
    this.linkHide = false;
    this.hideLinks.emit(this.hideLinks);
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.customerOrderForm.get('quotationNo').setErrors(null);
    }
    this.getCustomerDetailsByQuotation(event);
  }
  displayFnQuotationNo(quotationNo: AutoQuotationNo) {
    return quotationNo ? quotationNo.quotationNumber : undefined;
  }
  getCustomerDetailsByQuotation(event: MatAutocompleteSelectedEvent) {
    this.customerOrderWebService.getQuotationById(event.option.value.id).subscribe(res => {
      this.toCheckCustomerType(res);
      this.customerOrderForm.patchValue(res.headerResponse);
      this.customerOrderForm.get('mobileNumber').patchValue({ mobileNumber: res.headerResponse.mobileNumber });
      this.customerOrderForm.get('retailer').patchValue({ retailer: res.headerResponse.retailer });
      this.customerOrderForm.get('retailerName').patchValue(res.headerResponse.customerName);
      this.customerOrderForm.get('mechanic').patchValue({ mechanic: res.headerResponse.retailer });
      this.customerOrderForm.get('mechanicName').patchValue(res.headerResponse.customerName);
      this.customerOrderForm.get('dealerCode').patchValue({ dealerCode: res.headerResponse.dealerCode });
      this.customerOrderForm.get('dealerName').patchValue(res.headerResponse.customerName);
      this.presenter.patchValueForStateToCity(res.headerResponse);
      this.customerOrderForm.get('village').patchValue({ city: res.headerResponse.city });
      this.customerOrderForm.get('quotationNoId').patchValue(res.headerResponse.id);
      // this.customerOrderForm.get('customerMasterId').patchValue(res.headerResponse.customerMasterId)
      this.machineDetailChange.emit(res.partDetailsForSalesOrder);
      // res.partDetails.forEach(parts => {
      //   this.presenter.addRow(parts)
      // })
      this.presenter.partsTotalForm.get('subTotal').patchValue(res.headerResponse.totalBasicValue)
      this.presenter.partsTotalForm.get('gstAmount').patchValue(res.headerResponse.totalTaxValue);
      this.presenter.partsTotalForm.get('totalInvoiceAmount').patchValue(res.headerResponse.totalQuotationAmount)
      this.presenter.disabledFieldForCustomerName();
      this.presenter.quotationNoSelectEvent(event);
    })
  }
  toCheckCustomerType(res: QuotationPatchJson) {
    this.customerType = res.headerResponse.customerType;
    if (res.headerResponse.customerType == 'New/Existing') {
      this.isNewExisting = true;
      this.isMachine = false;
      this.isDealerCode = false;
      this.isRetailer = false;
    }
    if (res.headerResponse.customerType == 'Mechanic') {
      this.isNewExisting = false;
      this.isMachine = true;
      this.isDealerCode = false;
      this.isRetailer = false;
    }
    if (res.headerResponse.customerType == 'Retailer') {
      this.isNewExisting = false;
      this.isMachine = false;
      this.isDealerCode = false;
      this.isRetailer = true;
    }
    if (res.headerResponse.customerType == 'Co-Dealer') {
      this.isNewExisting = false;
      this.isMachine = false;
      this.isDealerCode = true;
      this.isRetailer = false;
    }
  }
  autoDealerCode(dealerCode: string) {
    this.dealerCodeList$ = this.customerOrderWebService.getDealerCodeAutocomplete(dealerCode)
  }
  dealerCodeSelect(event: MatAutocompleteSelectedEvent) {
    // console.log("event ", event);
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.customerOrderForm.get('dealerCode').setErrors(null);
    }
    this.customerOrderWebService.getDealerById(event.option.value.id).subscribe(res => {
      if(res['pinCode'] == null || res['pinCode'] === ''){
        this.toastr.error('Pincode is not define for selected dealer');
        this.customerOrderForm.get('dealerCode').reset();
        return;
      }
      this.customerOrderForm.patchValue(res);
      this.customerOrderForm.get('mobileNumber').patchValue({ mobileNumber: res.contactNumber });
      this.customerOrderForm.get('dealerName').patchValue(res.dealerName);
      this.presenter.patchValueForStateToCity(res);
      this.customerOrderForm.get('coDealerId').patchValue(event.option.value.id);
      if (res) {
        this.presenter.disabledFieldForCustomerName();
      }
    })
  }
  displayFnDealerCode(dealerCode: AutoCompleteDealerCode) {
    return dealerCode ? (dealerCode.value ? dealerCode.value : dealerCode['dealerCode']) : undefined
  }
  autoRetailer(retailer: string) {
    this.retailerList$ = this.customerOrderWebService.getRetailerOrMechanicAutocomplete(retailer, (this.customerType ? this.customerType : this.customerTypeFormView))
  }
  retailerSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.customerOrderForm.get('retailer').setErrors(null);
    }
    this.customerOrderWebService.getRetailerOrMechanicDetails(event.option.value.id).subscribe(res => {
      console.log("res ", res);
      if(res.pinCode === null || res.pinCode === '' ){
        this.customerOrderForm.controls.retailer.setErrors({pincodeRequired: 'Address Pincode not available'});
      }else{
        this.customerOrderForm.controls.retailer.setErrors(null);
        this.customerOrderForm.patchValue(res)
        this.customerOrderForm.get('mobileNumber').patchValue({ mobileNumber: res.contactNumber })
        this.presenter.patchValueForStateToCity(res)
        this.customerOrderForm.get('partyMasterId').patchValue(event.option.value.id)
        this.presenter.disabledFieldForCustomerName()
      }
    })
  }
  displayFnRetailer(retailer: AutoCompleteRetailer) {
    return retailer ? (retailer.code ? retailer.code : retailer['retailer']) : undefined
  }
  autoMachine(mechanic: string) {
    this.machineList$ = this.customerOrderWebService.getRetailerOrMechanicAutocomplete(mechanic, (this.customerType ? this.customerType : this.customerTypeFormView))
  }
  displayFnMachine(mechanic: AutoCompleteMachine) {
    return mechanic ? (mechanic.code ? mechanic.code : mechanic['mechanic']) : undefined
  }
  machineSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.customerOrderForm.get('mechanic').setErrors(null);
    }
    this.customerOrderWebService.getRetailerOrMechanicDetails(event.option.value.id).subscribe(res => {
      if(res.pinCode === null || res.pinCode === '' ){
        this.customerOrderForm.controls.mechanic.setErrors({pincodeRequired: 'Address Pincode not available'});
      }else{
        this.customerOrderForm.controls.mechanic.setErrors(null);
        this.customerOrderForm.patchValue(res);
        this.customerOrderForm.get('mechanicName').patchValue(res.retailerName);
        this.customerOrderForm.get('mobileNumber').patchValue({ mobileNumber: res.contactNumber });
        this.presenter.patchValueForStateToCity(res);
        this.customerOrderForm.get('partyMasterId').patchValue(event.option.value.id);
        this.presenter.disabledFieldForCustomerName();
      }
    });
  }
  onKeyDown(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.presenter.enabledFieldForCustomerName();
      this.presenter.resetFieldForCustomerName();
    }
  }
  onKeyDownForQuotation(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.customerOrderForm.get('quotationNo').reset()
      this.presenter.enabledFieldForCustomerName();
      this.presenter.resetFieldForCustomerName();
    }
  }
  onKeyDownForDealerCode(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.customerOrderForm.get('dealerCode').reset()
      this.presenter.enabledFieldForCustomerName();
      this.presenter.resetFieldForCustomerName();
    }
  }
  onKeyDownForMechanic(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.customerOrderForm.get('mechanic').reset()
      this.presenter.enabledFieldForCustomerName();
      this.presenter.resetFieldForCustomerName();
    }
  }
  onKeyDownForRetailer(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.customerOrderForm.get('retailer').reset()
      this.presenter.enabledFieldForCustomerName();
      this.presenter.resetFieldForCustomerName();
    }
  }
  selectedCustomerName(event: MatAutocompleteSelectedEvent) {
    console.log("event ", event);
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.customerOrderForm.get('mobileNumber').setErrors(null);
    }
    if (event.option.value.prospectCode) {
      this.getCustomerDetails(event.option.value.prospectCode);
    }else if (event.option.value.customerCode) {
      this.getCustomerDetails(event.option.value.customerCode);
    }else if (event.option.value.oldCustomerCode) {
      this.getCustomerDetails(event.option.value.oldCustomerCode);
    }

  }
  getCustomerDetails(prospectCode: string) {
    console.log("prospectCode ", prospectCode);
    this.customerOrderWebService.getCustomerDetails(prospectCode).subscribe(res => {
      console.log("res ", res);
      this.countryId = res.countryId;
      this.stateId = res.stateId;
      this.districtId = res.districtId;
      this.tehsilId = res.tehsilId;
      this.cityId = res.cityId;
      this.pinCodeId = res.pinCodeId;
      this.customerOrderForm.get('mobileNumber').patchValue({ mobileNumber: res.mobileNumber });
      this.customerOrderForm.get('customerName').patchValue(res.customerName);
      this.customerOrderForm.get('customerAddress1').patchValue(res.address1);
      this.customerOrderForm.get('customerAddress2').patchValue(res.address2);
      // this.customerOrderForm.get('country').patchValue(res.country)
      this.presenter.patchValueForStateToCity(res);
      this.customerOrderForm.get('village').patchValue({ city: res.city });
      this.customerOrderForm.get('prospectMasterId').patchValue(res.prospectMasterId);
      this.customerOrderForm.get('customerMasterId').patchValue(res.customerMasterId);
      this.presenter.disabledFieldForMobileNumber();
    })
  }
  getCustomerTypeDropDown() {
    const documentType = 'SaleOrder';
    this.customerOrderWebService.getCustomerType(documentType).subscribe(res => {
      this.customersList = res;
    });
  }
  getCountry() {
    this.customerOrderWebService.getCountry().subscribe(res => {
      this.countryId = res.id;
      this.customerOrderForm.get('country').patchValue(res.country);
      this.customerOrderForm.get('country').disable();
    });
  }
  private getDateFromServer() {
    if (this.presenter.operation === Operation.CREATE || this.presenter.operation === Operation.EDIT) {
      this.customerOrderWebService.getSystemGeneratedDate().subscribe(dateRes => {
        // console.log("dateRes ", dateRes);
        if (dateRes.result) {
          this.customerOrderForm.get('customerOrderDate').patchValue(new Date(dateRes.result));
        }
      });
    }
  }
checkMobileNoNull(event){
  
  this.customerOrderForm.get('mobileNumber').setErrors(null);

    if (typeof this.customerOrderForm.get('mobileNumber').value != 'object' && this.customerOrderForm.get('mobileNumber').value.length==10) {
      const custlist = this.customerName$.find(cust => cust.mobileNumber == this.customerOrderForm.get('mobileNumber').value);
      if(custlist){
        this.presenter.resetFieldForCustomerName('mobile');
        this.customerOrderForm.get('mobileNumber').setErrors({
          mobileError:'Please select from list'
        })
      }
    }  else if (typeof this.customerOrderForm.get('mobileNumber').value != 'object' && this.customerOrderForm.get('mobileNumber').value.length!=10) {
      this.customerOrderForm.get('mobileNumber').setErrors({
        invalidmobileError:'Enter Valid Mobile Number of 10 digits'
      })
    }
}

}



// let subject = new Subject<string>();
// subject.subscribe(res => {
//   console.log("res ", res);

// })
// subject.next("dadada")
// subject.next("lalala")

// let behaviorSubject=new BehaviorSubject<string>('first Val');
// behaviorSubject.asObservable().subscribe(val=>{
//  console.log("val ", val);

// })
// behaviorSubject.next('lalal')
// behaviorSubject.next('kikiki')

// let replySubject=new ReplaySubject<string>(1);
// replySubject.next('fisrt')
// replySubject.next('second')
// replySubject.asObservable().subscribe(res=>{
//  console.log("res ", res);

// })
// replySubject.next('third')

// const user1 = [{
//   id: 100,
//   name: 'samy',
//   password: 'password'
// },
// {
//   id: 100,
//   name: 'don',
//   password: 'password'
// },
// ]
// const removeProperty = prop => ({ [prop]:_, ...rest }) => rest;
// const removePassword = removeProperty("password")
// removePassword(user1)
// console.log("user1 ", user1);

// let output = user1.map(obj => {
//   return Object.keys(obj).sort();
// });
// console.log("output ", output);

// var array = [{ key1: "value1" }, { key2: "value2" }],
//   object = Object.assign([], ...array);

// console.log(object);
