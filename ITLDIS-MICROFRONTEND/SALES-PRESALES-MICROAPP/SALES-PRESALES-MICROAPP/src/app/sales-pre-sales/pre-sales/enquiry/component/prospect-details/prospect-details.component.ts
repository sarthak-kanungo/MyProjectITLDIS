import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { ProspectDetailsService } from './prospect-details.service';
import { DropDownprospectType, AutoComplateMobileNo, PinCode, AutoComplatePostOffice, ProspectDetailsObj, AutocomplatePopulateDatabyPinCode, SaveProspectDetailsObj } from 'ProspectDetails';
import { ProspectDetailsContainerService } from '../prospect-details-container/prospect-details-container.service';
import { EnquiryService } from '../../enquiry.service';
import { ViewEnquiryDomain } from 'EnquiryCreation';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { EnquiryCreationContainerService } from '../enquiry-creation-container/enquiry-creation-container.service';
import { ActivatedRoute } from '@angular/router';
import { Observable, forkJoin, interval } from 'rxjs';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { BaseDto } from 'BaseDto';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';

@Component({
  selector: 'app-prospect-details',
  templateUrl: './prospect-details.component.html',
  styleUrls: ['./prospect-details.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    ProspectDetailsService, ProspectDetailsContainerService, EnquiryCreationContainerService]
})
export class ProspectDetailsComponent implements OnInit {
  @Input()
  max: Date | null
  tomorrow = new Date();
  isView: boolean
  isEdit: boolean
  isViewMobile: boolean
  prospectDetailsForm: FormGroup;
  id: number
  prosId: number;
  custId: number
  customerCode: string
  oldCustomerCode: string
  languages = [
    { language: 'English' },
    { language: 'Marathi' },
    { language: 'Hindi' }

  ];
  showbyProspectType: boolean
  dropDownprospectType: BaseDto<Array<DropDownprospectType>>
  @Output() autoMobileNo = new EventEmitter<string>()
  @Input() autoComplateMobileNo: BaseDto<Array<AutoComplateMobileNo>>
  @Output() autoPopulatebyMobileNo = new EventEmitter<AutoComplateMobileNo>()
  @Input() set autoPopulateDatabyMobileNoList(list: BaseDto<ProspectDetailsObj>) {
    //console.log('autoPopulateDatabyMobileNoList', list);
    if (this.prospectDetailsForm) {
      this.prospectDetailsForm.patchValue(list.result)
      this.prospectDetailsForm.controls.pinCode.patchValue({ pinCode: list.result.pinCode })
      this.prospectDetailsForm.controls.postOffice.patchValue({ postOffice: list.result.postOffice })
      this.prospectDetailsForm.controls.mobileNumber.patchValue({ mobileNumber: list.result.mobileNumber })
      this.prospectDetailsForm.controls.prospectCode.patchValue(list.result.prospectCode)
      this.prospectDetailsForm.controls.dob.patchValue(this.convertToPatchFormat(list.result.dob))
      this.prospectDetailsForm.controls.anniversaryDate.patchValue(new Date(list.result.anniversaryDate))
      this.dropDownprospectType.result.findIndex(res => res.prospectType === list.result.prospectType)
      this.prospectDetailsForm.controls.prospectType.patchValue(list.result.prospectType)
      this.languages.findIndex(res => res.language === list.result.language)
      this.prospectDetailsForm.controls.language.patchValue(list.result.language)
      if (list.result.prospectType === 'Institution') {
        this.showbyProspectType = true
      }
      if (list.result.prospectCode || list.result.customerCode) {
        this.disableField()
      }
    }
  }
  @Input() getmodelValue: string
  databyMobileNoListForViewMobile: BaseDto<ProspectDetailsObj>
  @Output() autoPincode = new EventEmitter<number>()
  @Output() autopopulateDatabyPincode = new EventEmitter<PinCode>()
  @Input() pinCode: BaseDto<Array<PinCode>>
  @Input() autoComplatePostOffice: BaseDto<Array<AutoComplatePostOffice>>
  @Input() set autocomplatePopulateDatabyPinCode(value: BaseDto<AutocomplatePopulateDatabyPinCode>) {
    console.log('autocomplatePopulateDatabyPinCode', value)
    if (value) {
      this.prospectDetailsForm.controls.city.patchValue(value.result.city)
      this.prospectDetailsForm.controls.tehsil.patchValue(value.result.tehsil)
      this.prospectDetailsForm.controls.district.patchValue(value.result.district)
      this.prospectDetailsForm.controls.state.patchValue(value.result.state)
      this.prospectDetailsForm.controls.country.patchValue(value.result.country)
    }
  }
  @Output() keyDownMobileNo = new EventEmitter<string>()
  @Output() keyDownProspectCode = new EventEmitter<string>()
  @Output() getFormStatusandData = new EventEmitter<object>()
  checkEnquiry: BaseDto<number>

  constructor(
    private prospectDetailsService: ProspectDetailsService,
    private enquiryService: EnquiryService,
    private prospectDetailsContainerService: ProspectDetailsContainerService,
    private enqRt: ActivatedRoute,
    private enquiryCommonService: EnquiryCommonService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.checkOperationType()
    this.intiOperationForm()
    this.loadAllDropDownData().subscribe(dt => {
      this.dropDownprospectType = dt[0] as BaseDto<Array<DropDownprospectType>>
      this.patchOrCreate()
    })
  }
  onKeyPressMobileNo(event: any) {
    //console.log('event', event);
    this.prospectDetailsService.keyPress(event)
  }

  keyPresswhatsapp(event: any) {
    this.prospectDetailsService.keyPresswhatsapp(event)
  }
  keyPressalt(event: any) {
    this.prospectDetailsService.keyPressalt(event)
  }
  keyStd(event: any) {
    this.prospectDetailsService.keyStd(event)
  }
  keytel(event: any) {
    this.prospectDetailsService.keytel(event)
  }

  keyPressFirstName(event: any) {
    this.prospectDetailsService.keyPressFirstName(event)
  }

  keyPressMiddleName(event: any) {
    this.prospectDetailsService.keyPressMiddleName(event)
  }

  keyPressLastName(event: any) {
    this.prospectDetailsService.keyPressLastName(event)
  }

  keyPressFatherName(event: any) {
    this.prospectDetailsService.keyPressFatherName(event)
  }
  keyPressPanNumber(event: any) {
    this.prospectDetailsService.keyPressPanNumber(event)
  }

  onKeyDownPinCode(event: any) {
    console.log(event);
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.prospectDetailsForm.controls.city.reset()
      this.prospectDetailsForm.controls.postOffice.reset()
      this.prospectDetailsForm.controls.postOffice.enable()
      this.prospectDetailsForm.controls.state.reset()
      this.prospectDetailsForm.controls.district.reset()
      this.prospectDetailsForm.controls.tehsil.reset()
      this.prospectDetailsForm.controls.country.reset()
    }
  }

  private patchOrCreate() {
    if (this.isView) {
      this.parseIdAndViewForm()
    } else if (this.isViewMobile) {
      this.parseIdAndViewMobileForm()
    } else {
      this.formForCreateSetup()
    }
  }
  getPanNumber(event: FocusEvent) {
    let str = event.target['value']
    let panNum = str.slice(2, 12)
    this.prospectDetailsForm.controls.panNumber.patchValue(panNum)
  }
  selection(value) {
    if (value === 'Institution') {
      this.showbyProspectType = true
      this.prospectDetailsForm.controls.companyName.setValidators(Validators.compose([Validators.required]))
      this.prospectDetailsForm.controls.companyName.updateValueAndValidity();
      this.prospectDetailsForm.controls.gstNumber.setValidators(Validators.compose([Validators.required, Validators.pattern(/^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/)]))
      this.prospectDetailsForm.controls.gstNumber.updateValueAndValidity();
      this.prospectDetailsForm.controls.companyName.reset()
      this.prospectDetailsForm.controls.gstNumber.reset()
    } else {
      this.showbyProspectType = false
      this.prospectDetailsForm.controls.companyName.clearValidators()
      this.prospectDetailsForm.controls.companyName.updateValueAndValidity();
      this.prospectDetailsForm.controls.gstNumber.clearValidators()
      this.prospectDetailsForm.controls.gstNumber.updateValueAndValidity();
    }
  }

  private formForCreateSetup() {
    this.prospectDetailsForm = this.prospectDetailsService.createprospectDetailsForm()

    this.prospectDetailsForm.controls.mobileNumber.valueChanges.subscribe(value => {
      //console.log('mobileNumber', value);
      if (value) {
        let mobileNumber = typeof value == 'object' ? value.mobileNumber : value
        this.autoMobileNo.emit(mobileNumber)
      }
    })
    this.prospectDetailsForm.controls.pinCode.valueChanges.subscribe(value => {
      // console.log('pinCode', value)
      if (value) {
        let pinCode = typeof value == 'object' ? value.pinCode : value
        this.autoPincode.emit(pinCode)
      }
    });

    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        let pros: SaveProspectDetailsObj = this.prospectDetailsForm.getRawValue()
        pros.dob = this.convertDateToServerFormat(pros.dob)
        pros.anniversaryDate = this.convertDateToServerFormat(pros.anniversaryDate)
        this.getFormStatusandData.emit({ validStatus: this.prospectDetailsForm.valid, formData: pros });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.prospectDetailsForm.reset();
      }
    })
  }
  displayFnForMObileNo(mobNo: AutoComplateMobileNo) {
    return mobNo ? mobNo.mobileNumber : undefined
  }

  optionSelectedMobileNo(event) {
    console.log(event.option.value);
    console.log(this.getmodelValue);
    if (this.getmodelValue) {
      this.prospectDetailsContainerService.checkItemNumberModelInEnquiry(this.getmodelValue, event.option.value.mobileNumber).subscribe(response => {
        console.log(response);
        this.checkEnquiry = response as BaseDto<number>
        console.log(this.checkEnquiry.message);
        if (this.checkEnquiry.result === 0) {
          this.openConfirmDialog()
        }
      })
    }
    this.autoPopulatebyMobileNo.emit(event.option.value.mobileNumber)
  }

  onKeyDownMobileNo(event: any) {
    // console.log('KeyDown', event);
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.resetFieldForMobileNo()
    }
    this.keyDownMobileNo.emit(event.key)
  }

  displayFnForPinCode(pin: PinCode) {
    return pin ? pin.pinCode : undefined
  }
  optionSelectedPinCode(event) {
    console.log('optionSelectedPinCode', event)
    this.autopopulateDatabyPincode.emit(event.option.value)
  }
  optionSelectedpostOffice(event) {
    console.log('optionSelectedpostOffice', event)

  }

  displayFnForPostOffice(postOfficeList: AutoComplatePostOffice) {
    return postOfficeList ? postOfficeList.postOffice : undefined
  }

  private formForViewSetup(domain: ViewEnquiryDomain) {
    if (domain) {
      this.prospectDetailsForm.patchValue(domain)
      this.id = domain.prospectMaster.id
      console.log('prosId', this.id);
      //  this.customerCode = domain.customerMaster.customerCode
      this.prospectDetailsForm.controls.pinCode.patchValue({ pinCode: domain.pinCode })
      this.prospectDetailsForm.controls.postOffice.patchValue({ postOffice: domain.postOffice })
      this.prospectDetailsForm.controls.mobileNumber.patchValue({ mobileNumber: domain.mobileNumber })
      this.prospectDetailsForm.controls.prospectCode.patchValue(domain.prospectMaster.prospectCode)
      this.prospectDetailsForm.controls.dob.patchValue(domain.dob ? new Date(domain.dob) : null)
      this.prospectDetailsForm.controls.anniversaryDate.patchValue(domain.anniversaryDate ? new Date(domain.anniversaryDate) : null)
      this.dropDownprospectType.result.findIndex(res => res.prospectType === domain.prospectType)
      this.prospectDetailsForm.controls.prospectType.patchValue(domain.prospectType)
      this.languages.findIndex(res => res.language === domain.language)
      this.prospectDetailsForm.controls.language.patchValue(domain.language)
      if (domain.prospectType === 'Institution') {
        this.showbyProspectType = true
      }
    }
  }
  private formForViewMobileSetup(domain: ViewEnquiryDomain) {
    if (domain) {
      Object.keys(domain).map(key => domain[key] = domain[key] == 'NA' ? null : domain[key])
      this.prospectDetailsForm.patchValue(domain)
      this.prospectDetailsForm.controls.mobileNumber.patchValue({ mobileNumber: domain.mobileNumber })
      this.prospectDetailsForm.controls.prospectCode.patchValue(domain.prospectCode)
      this.dropDownprospectType.result.findIndex(res => res.prospectType === domain.prospectType)
      this.prospectDetailsForm.controls.prospectType.patchValue(domain.prospectType)
      this.languages.findIndex(res => res.language === domain.language)
      this.prospectDetailsForm.controls.language.patchValue(domain.language)
    }
    this.prospectDetailsContainerService.searchbyMobileNo(domain.mobileNumber).subscribe(response => {
      console.log('mobielData', response);
      this.databyMobileNoListForViewMobile = response as BaseDto<ProspectDetailsObj>
      if (this.databyMobileNoListForViewMobile) {
        this.id = this.databyMobileNoListForViewMobile.result.id
        this.customerCode = this.databyMobileNoListForViewMobile.result.customerCode
        this.oldCustomerCode = this.databyMobileNoListForViewMobile.result.oldCustomerCode
        this.prospectDetailsForm.patchValue(this.databyMobileNoListForViewMobile.result)
        this.prospectDetailsForm.controls.pinCode.patchValue({ pinCode: this.databyMobileNoListForViewMobile.result.pinCode })
        this.prospectDetailsForm.controls.postOffice.patchValue({ postOffice: this.databyMobileNoListForViewMobile.result.postOffice })
        this.prospectDetailsForm.controls.mobileNumber.patchValue({ mobileNumber: this.databyMobileNoListForViewMobile.result.mobileNumber })
        this.prospectDetailsForm.controls.prospectCode.patchValue(this.databyMobileNoListForViewMobile.result.prospectCode)
        this.prospectDetailsForm.controls.dob.patchValue(this.convertToPatchFormat(this.databyMobileNoListForViewMobile.result.dob))
        this.prospectDetailsForm.controls.anniversaryDate.patchValue(this.convertToPatchFormat(this.databyMobileNoListForViewMobile.result.anniversaryDate))
        this.dropDownprospectType.result.findIndex(res => res.prospectType === this.databyMobileNoListForViewMobile.result.prospectType)
        this.prospectDetailsForm.controls.prospectType.patchValue(this.databyMobileNoListForViewMobile.result.prospectType)
      }
    })

  }
  private parseIdAndViewForm() {
    this.enqRt.params.subscribe(prms => this.fatchDataForEnqNO(prms['enqNo']))
    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'update') {
        let prosDetails: SaveProspectDetailsObj = this.prospectDetailsForm.getRawValue()
        prosDetails.dob = this.convertDateToServerFormat(prosDetails.dob)
        prosDetails.anniversaryDate = this.convertDateToServerFormat(prosDetails.anniversaryDate)
        this.getFormStatusandData.emit({ validStatus: this.prospectDetailsForm.valid, formData: prosDetails, customerCode: this.customerCode, oldCustomerCode: this.oldCustomerCode, id: this.id });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.prospectDetailsForm.reset();
      }
    })
  }
  private parseIdAndViewMobileForm() {
    this.enqRt.params.subscribe(prms => this.fatchDataForViewMobileEnqNo(prms['mobenqNo']))
    this.prospectDetailsForm = this.prospectDetailsService.viewMobileprospectDetailsForm()
    this.prospectDetailsForm.controls.pinCode.valueChanges.subscribe(value => {
      console.log('pinCode', value)
      if (value) {
        let pinCode = typeof value == 'object' ? value.pinCode : value
        this.autoPincode.emit(pinCode)
      }
    });
    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        let prosDetails: SaveProspectDetailsObj = this.prospectDetailsForm.getRawValue()
        prosDetails.dob = this.convertDateToServerFormat(prosDetails.dob)
        prosDetails.anniversaryDate = this.convertDateToServerFormat(prosDetails.anniversaryDate)
        this.getFormStatusandData.emit({ validStatus: this.prospectDetailsForm.valid, formData: prosDetails, customerCode: this.customerCode, oldCustomerCode: this.oldCustomerCode, id: this.id });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.prospectDetailsForm.reset();
      }
    })
  }

  private fatchDataForEnqNO(enqNo: string) {
    this.enquiryCommonService.getEnquiryByeEnquiryNumber(`` + enqNo).subscribe(dto => { this.formForViewSetup(dto.result) })
  }
  private fatchDataForViewMobileEnqNo(mobenqNo: string) {
    this.enquiryCommonService.getEnquiryByeEnquiryNumber(`` + mobenqNo).subscribe(dto => { this.formForViewMobileSetup(dto.result) })
  }
  private checkOperationType() {
    this.isView = this.enqRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isViewMobile = this.enqRt.snapshot.routeConfig.path.split('/')[0] == 'viewMobile'
  }

  private intiOperationForm() {
    if (this.isView) {
      this.prospectDetailsForm = this.prospectDetailsService.viewprospectDetailsForm()
    } else if (this.isViewMobile) {
      this.prospectDetailsForm = this.prospectDetailsService.viewMobileprospectDetailsForm()
    } else {
      this.prospectDetailsForm = this.prospectDetailsService.createprospectDetailsForm()
    }
  }

  private loadAllDropDownData(): Observable<BaseDto<Array<Object>>> {
    let dropDownTask = [];
    dropDownTask.push(this.prospectDetailsContainerService.dropdownprospectType())

    return forkJoin(...dropDownTask)
  }

  private markFormAsTouched() {
    for (const key in this.prospectDetailsForm.controls) {
      if (this.prospectDetailsForm.controls.hasOwnProperty(key)) {
        this.prospectDetailsForm.controls[key].markAsTouched();
      }
    }
  }

  private convertDateToServerFormat(dt: string) {
    if (dt) {
      let date = new Date(dt)
      let formattedDate = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear()
      console.log(formattedDate)
      return formattedDate
    }
    return null
  }
  private convertToPatchFormat(dt: string) {
    if (dt) {
      return dt.split('-').reverse().join('-')
    }
    return null
  }

  private disableField() {
    this.prospectDetailsForm.controls.prospectCode.disable()
    this.prospectDetailsForm.controls.prospectType.disable()
    this.prospectDetailsForm.controls.companyName.disable()
    this.prospectDetailsForm.controls.firstName.disable()
    this.prospectDetailsForm.controls.middleName.disable()
    this.prospectDetailsForm.controls.lastName.disable()
    this.prospectDetailsForm.controls.fatherName.disable()
    this.prospectDetailsForm.controls.alternateMobileNumber.disable()
    this.prospectDetailsForm.controls.std.disable()
    this.prospectDetailsForm.controls.telephoneNumber.disable()
    this.prospectDetailsForm.controls.emailId.disable()
    this.prospectDetailsForm.controls.address1.disable()
    this.prospectDetailsForm.controls.address2.disable()
    this.prospectDetailsForm.controls.address3.disable()
    this.prospectDetailsForm.controls.pinCode.disable()
    //this.prospectDetailsForm.controls.postOffice.patchValue(list.result.postOffice)
    this.prospectDetailsForm.controls.postOffice.disable()
    this.prospectDetailsForm.controls.language.disable()
    this.prospectDetailsForm.controls.dob.disable()
    this.prospectDetailsForm.controls.anniversaryDate.disable()
    this.prospectDetailsForm.controls.gstNumber.disable()
    this.prospectDetailsForm.controls.panNumber.disable()
  }

  private resetFieldForMobileNo() {
    this.prospectDetailsForm.controls.prospectCode.reset()
    this.prospectDetailsForm.controls.prospectCode.enable()
    this.prospectDetailsForm.controls.prospectType.reset()
    this.prospectDetailsForm.controls.prospectType.enable()
    this.prospectDetailsForm.controls.companyName.reset()
    this.prospectDetailsForm.controls.companyName.enable()
    this.prospectDetailsForm.controls.firstName.reset()
    this.prospectDetailsForm.controls.firstName.enable()
    this.prospectDetailsForm.controls.middleName.reset()
    this.prospectDetailsForm.controls.middleName.enable()
    this.prospectDetailsForm.controls.lastName.reset()
    this.prospectDetailsForm.controls.lastName.enable()
    this.prospectDetailsForm.controls.fatherName.reset()
    this.prospectDetailsForm.controls.fatherName.enable()
    this.prospectDetailsForm.controls.whatsAppNumber.reset()
    this.prospectDetailsForm.controls.alternateMobileNumber.reset()
    this.prospectDetailsForm.controls.alternateMobileNumber.enable()
    this.prospectDetailsForm.controls.std.reset()
    this.prospectDetailsForm.controls.std.enable()
    this.prospectDetailsForm.controls.telephoneNumber.reset()
    this.prospectDetailsForm.controls.telephoneNumber.enable()
    this.prospectDetailsForm.controls.emailId.reset()
    this.prospectDetailsForm.controls.emailId.enable()
    this.prospectDetailsForm.controls.address1.reset()
    this.prospectDetailsForm.controls.address1.enable()
    this.prospectDetailsForm.controls.address2.reset()
    this.prospectDetailsForm.controls.address2.enable()
    this.prospectDetailsForm.controls.address3.reset()
    this.prospectDetailsForm.controls.address3.enable()
    this.prospectDetailsForm.controls.pinCode.reset()
    this.prospectDetailsForm.controls.pinCode.enable()
    this.prospectDetailsForm.controls.postOffice.reset()
    this.prospectDetailsForm.controls.postOffice.enable()
    this.prospectDetailsForm.controls.language.reset()
    this.prospectDetailsForm.controls.language.enable()
    this.prospectDetailsForm.controls.dob.reset()
    this.prospectDetailsForm.controls.dob.enable()
    this.prospectDetailsForm.controls.anniversaryDate.reset()
    this.prospectDetailsForm.controls.anniversaryDate.enable()
    this.prospectDetailsForm.controls.gstNumber.reset()
    this.prospectDetailsForm.controls.gstNumber.enable()
    this.prospectDetailsForm.controls.panNumber.reset()
    this.prospectDetailsForm.controls.panNumber.enable()
    this.prospectDetailsForm.controls.city.reset()
    this.prospectDetailsForm.controls.state.reset()
    this.prospectDetailsForm.controls.district.reset()
    this.prospectDetailsForm.controls.tehsil.reset()
    this.prospectDetailsForm.controls.country.reset()
  }

  private openConfirmDialog(): void | any {
    let message = `Enquiry already created  with model ${this.getmodelValue} `;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Ok') {
      }

    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Ok'];
    return confirmationData;
  }
}