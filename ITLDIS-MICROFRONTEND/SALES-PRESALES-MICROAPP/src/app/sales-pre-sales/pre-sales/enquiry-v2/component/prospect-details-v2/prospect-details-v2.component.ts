import {
  Component,
  OnInit,
  Input,
  OnChanges,
  Output,
  EventEmitter,
} from "@angular/core";
import { FormGroup } from "@angular/forms";
import { EnquiryPresenter } from "../../services/enquiry-presenter";
import { itldisPatterns } from "../../../../../utils/itldis-patterns";
import { Observable } from "rxjs";
import {
  ProspectType,
  MobileNo,
  AutoCompPinCode,
  PostOffice,
  ViewEnquiry,
  SaveEnquiry,
  Title,
  DealerInformation,
  AutoTehsil,
} from "../../domains/enquiry";
import { ProspectDetailsV2WebService } from "../../services/prospect-details-v2-web.service";
import { BaseDto } from "BaseDto";
import {
  ConfirmationBoxComponent,
  ConfirmDialogData,
  ButtonAction,
} from "../../../../../confirmation-box/confirmation-box.component";
import {
  MatDialog,
  MatAutocompleteSelectedEvent,
  MatDatepickerInputEvent,
  MatSelectChange,
} from "@angular/material";
import { Operation } from "../../../../../utils/operation-util";
import { DateService } from "../../../../../root-service/date.service";
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: "app-prospect-details-v2",
  templateUrl: "./prospect-details-v2.component.html",
  styleUrls: ["./prospect-details-v2.component.scss"],
  providers: [ProspectDetailsV2WebService],
})
export class ProspectDetailsV2Component implements OnInit, OnChanges {
  prospectDetailsForm: FormGroup;
  @Input()
  max: Date | null;
  tomorrow = new Date();
  // languages = [
  //   { language: "English" },
  //   { language: "Marathi" },
  //   { language: "Hindi" },
  // ];
  languages:any[]=[]
  recordType:string;
  prospectType: Array<ProspectType>;
  mobileNo$: Observable<Array<MobileNo>>;
  pinCode$: Observable<Array<AutoCompPinCode>>;
  postOffice$: Observable<Array<PostOffice>>;
  isShowByProspectType: boolean;
  isDateOfBirth: boolean;
  title: Array<Title>;
  dealerInformation: Array<DealerInformation>;
  districtId: number;
  @Output() dataAutoPopulateByMobileNo = new EventEmitter<SaveEnquiry>();
  @Input() enquiryByEnquiryNumber: ViewEnquiry;

  constructor(
    private enquiryPresenter: EnquiryPresenter,
    private itldisPatterns: itldisPatterns,
    private prospectDetailsV2WebService: ProspectDetailsV2WebService,
    private dialog: MatDialog,
    private dateService: DateService,
    private toastr: ToastrService,
  ) {}

  ngOnChanges() {
    if (this.enquiryByEnquiryNumber) {  
      if (this.enquiryByEnquiryNumber.mobileCountUpdate == 0) {
        //this.prospectDetailsForm.get("mobileNumber").enable();
        if(this.enquiryByEnquiryNumber.recordType!=null && this.enquiryByEnquiryNumber.recordType.toLowerCase()==='prospect'){
            this.enquiryPresenter.enableProspectDetailsForMobileNumber();
            this.valueChangesForAutoComplate();
        }  
      }
      this.prospectDetailsForm.patchValue(this.enquiryByEnquiryNumber);
      this.prospectDetailsForm
        .get("mobileNumber")
        .patchValue({ mobileNumber: this.enquiryByEnquiryNumber.mobileNumber });
      if( this.prospectDetailsForm.get("whatsAppNumber").value === undefined || this.prospectDetailsForm.get("whatsAppNumber").value === null ||  this.prospectDetailsForm.get("whatsAppNumber").value === ''){
          this.prospectDetailsForm.get("whatsAppNumber").patchValue(this.enquiryByEnquiryNumber.mobileNumber);
      }
      this.prospectDetailsForm
        .get("pinCode")
        .patchValue({ pinCode: this.enquiryByEnquiryNumber.pinCode });
      this.prospectDetailsForm
        .get("district")
        .patchValue({ district: this.enquiryByEnquiryNumber.district });
      if (this.enquiryByEnquiryNumber.customerMaster) {
        this.prospectDetailsForm
          .get("prospectCode")
          .patchValue(this.enquiryByEnquiryNumber.customerMaster.customerCode);
      }
     /* this.prospectDetailsForm
        .get("prospectType")
        .patchValue({prospectType:this.enquiryByEnquiryNumber.customerMaster.customerType});*/
      this.prospectDetailsForm
      .get("prospectType")
      .patchValue(this.prospectType.find(p=>p.prospectType==this.enquiryByEnquiryNumber.customerMaster.customerType));
      
      this.prospectDetailsForm
        .get("dob")
        .patchValue(
          this.enquiryByEnquiryNumber.dob
            ? new Date(this.enquiryByEnquiryNumber.dob)
            : null
        );
     // this.prospectDetailsForm.get("recordType").patchValue(this.enquiryByEnquiryNumber.customerMaster.recordType)
      this.recordType = this.enquiryByEnquiryNumber.customerMaster.recordType;
      this.prospectDetailsForm.get("recordType").patchValue(this.recordType);
      this.prospectDetailsForm
        .get("anniversaryDate")
        .patchValue(
          this.enquiryByEnquiryNumber.anniversaryDate
            ? new Date(this.enquiryByEnquiryNumber.anniversaryDate)
            : null
        );
      let languagesObj = this.languages.findIndex(
        (res) => res.language === this.enquiryByEnquiryNumber.language
      );
      this.prospectDetailsForm
        .get("language")
        .patchValue(this.languages[languagesObj]);
      if (this.enquiryByEnquiryNumber.anniversaryDate) {
        this.isDateOfBirth = true;
      }
      if (this.enquiryByEnquiryNumber.customerMaster.customerType === "Institution") {
        this.isShowByProspectType = true;
      }
      if (this.enquiryPresenter.operation === Operation.VIEW_MOBILE) {
        this.patchValueForViewApp();
      }
    }
  }

  patchValueForViewApp() {
    this.prospectDetailsV2WebService
      .getDataByMobileNo(this.enquiryByEnquiryNumber.mobileNumber)
      .subscribe((response) => {
        if (response) {
          this.prospectDetailsForm.patchValue(response);
          this.prospectDetailsForm.get("prosId").patchValue(response.id);
          this.patchValueForMobileNoForViewApp(response);
          this.patchLanguagesByMobileNoForViewApp(response);
          if (response.prospectType === "Institution") {
            this.isShowByProspectType = true;
          }
          if (response.prospectCode || response.customerCode) {
            this.enquiryPresenter.disableProspectDetailsForMobileNumber();
          }
        }
      });
  }

  patchValueForMobileNoForViewApp(response: SaveEnquiry) {
    this.prospectDetailsForm
      .get("mobileNumber")
      .patchValue({ mobileNumber: response.mobileNumber });
    this.prospectDetailsForm
      .get("prospectType")
      .patchValue({ prospectType: response.prospectType });
    this.prospectDetailsForm
      .get("pinCode")
      .patchValue({ pinCode: response.pinCode });
    this.prospectDetailsForm
      .get("district")
      .patchValue({ district: response.district });
    if (response.dob) {
      const dob = this.dateService.getDateIntoYYYYMMDD(
        response.dob ? response.dob : null
      );
      this.prospectDetailsForm.get("dob").patchValue(dob);
    }
    this.prospectDetailsForm
      .get("anniversaryDate")
      .patchValue(
        response.anniversaryDate ? new Date(response.anniversaryDate) : null
      );
  }

  patchLanguagesByMobileNoForViewApp(response: SaveEnquiry) {
    let languagesObj = this.languages.findIndex(
      (res) => res.language === response.language
    );
    this.prospectDetailsForm
      .get("language")
      .patchValue(this.languages[languagesObj]);
  }

  ngOnInit() {
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.patchOrCreate();
    this.getDealerInfo();
    this.loadDropDownProspectType();
    this.valueChangesForAutoComplate();
    this.enquiryLanguageForProstect();
  }

  private patchOrCreate() {
    this.prospectDetailsForm = this.enquiryPresenter.enquiryForm.get(
      "prospectDetails"
    ) as FormGroup;
  }

  onKeyPressAllowNumbersOnly(event: KeyboardEvent) {
    this.itldisPatterns.allowNumbersOnly(event);
  }

  onKeyPressAllowCharsOnly(event: KeyboardEvent) {
    this.itldisPatterns.allowCharactersOnly(event);
  }
  onKeyPressAllowCharsWithSpace(event: KeyboardEvent) {
    this.itldisPatterns.allowCharactersWithSpace(event);
  }

  onKeyPressAlphaNumericsOnly(event: KeyboardEvent) {
    this.itldisPatterns.allowAlphaNumericsOnly(event);
  }

  blockCopyPaste(event: KeyboardEvent) {
    this.itldisPatterns.blockCopyPaste(event);
  }

  loadDropDownProspectType() {
    this.prospectDetailsV2WebService.getProspectType().subscribe((response) => {
      this.prospectType = response.result as Array<ProspectType>;
      console.log('Prospect Type : '+response.result)
      if(this.enquiryByEnquiryNumber){
          this.prospectDetailsForm
          .get("prospectType")
          .patchValue(this.prospectType.find(p=>p.prospectType==this.enquiryByEnquiryNumber.customerMaster.customerType));
      }
      
    });
    this.prospectDetailsV2WebService.dropDownTitle().subscribe((response) => {
      this.title = response.result as Array<Title>;
    });
  }

  getDealerInfo() {
    this.prospectDetailsV2WebService
      .getDealerRegionInfo()
      .subscribe((response) => {
        console.log("response ", response);
        this.dealerInformation = response;
        if (response) {
          this.prospectDetailsForm.get("state").patchValue(response[0].state);
          this.prospectDetailsForm.get("country").patchValue(response[0].country);
        }
      });
  }

  valueChangesForAutoComplate() {
    this.prospectDetailsForm
      .get("mobileNumber")
      .valueChanges.subscribe((value) => {
        if (value) {
          console.log("mobileNumber---->", value)
          let mobileNumber =
            typeof value == "object" ? value.mobileNumber : value;
          this.autoMobileNo(mobileNumber);
        }
      });
    this.prospectDetailsForm.get("pinCode").valueChanges.subscribe((value) => {
      if (value) {
        console.log("pinCode---->", value)
        let pinCode = typeof value == "object" ? value.pinCode : value;
        this.autoPincode(pinCode);
      }
      if (value !== null && typeof value !== "object") {
        this.prospectDetailsForm.get("pinCode").setErrors({
          selectFromList: "Please select from list",
        });
      }
    });
  }

  autoMobileNo(mobileNumber) {
    this.mobileNo$ = this.prospectDetailsV2WebService.getMobileNumber(
      mobileNumber
    );
  }

  displayFnForMObileNo(mobNum: MobileNo) {
    return mobNum ? mobNum.mobileNumber : undefined;
  }

  onKeyDownMobileNo(event: KeyboardEvent) {
    console.log("onKeyDownMobileNo---->", event)
    if (event.key === "Backspace" || event.key === "Delete") {
      this.enquiryPresenter.resetProspectDetailsForMobileNumber();
      this.enquiryPresenter.enableProspectDetailsForMobileNumber();
      this.enquiryPresenter.resetProspectBackgroundForMobileNo();
    }
    if (event.key === "Tab") {
        
        this.mobileTabOrBlurFunction();
    }
  }

  onBlurMethodForMobileNo(event) {
      this.mobileTabOrBlurFunction();
  }

  mobileTabOrBlurFunction(){

    let mobileNumber = '';
    if(typeof this.prospectDetailsForm.controls.mobileNumber.value === 'object'){
        mobileNumber = this.prospectDetailsForm.controls.mobileNumber.value.mobileNumber;
    }else{
        mobileNumber = this.prospectDetailsForm.controls.mobileNumber.value;
    }
    this.checkItemNoModelForEnquiry( mobileNumber );
    this.prospectDetailsV2WebService
      .getDataByMobileNo(mobileNumber)
      .subscribe((response) => {
        console.log(response);
        if(response==null){
            this.enquiryPresenter.resetProspectDetailsForMobileNumber();
            this.enquiryPresenter.enableProspectDetailsForMobileNumber();
            this.enquiryPresenter.resetProspectBackgroundForMobileNo();
            this.prospectDetailsForm.get("whatsAppNumber").patchValue(mobileNumber);
        }else{  
            let patchValue = this.dealerInformation.find(i => (i.district === response.district));
            if(patchValue == null){
                this.toastr.error("Customer belongs to another territory");
                this.prospectDetailsForm.controls.mobileNumber.reset();
                this.enquiryPresenter.resetProspectDetailsForMobileNumber();
                this.enquiryPresenter.enableProspectDetailsForMobileNumber();
                this.enquiryPresenter.resetProspectBackgroundForMobileNo();
                
            }else{
                this.prospectDetailsForm.get("whatsAppNumber").patchValue(mobileNumber);
                this.dataAutoPopulateByMobileNo.emit(response);
                this.prospectDetailsForm.patchValue(response);
                if(this.prospectDetailsForm.get("prosId"))
                    this.prospectDetailsForm.get("prosId").setValue(response.id);
                this.patchValueForMobileNo(response);
                this.patchLanguagesByMobileNo(response);
                if (response.prospectType === "Institution") {
                  this.isShowByProspectType = true;
                }
                if (response.recordType && !(response.recordType === 'PROSPECT')) {
                  this.enquiryPresenter.disableProspectDetailsForMobileNumber();
                }
            }
        }
      });
  
  }
  
  optionSelectedMobileNo(event) {
    let value = event.option.value;
    console.log(value);
    this.checkItemNoModelForEnquiry(value);
    console.log(event.option.value.mobileNumber);
    this.prospectDetailsV2WebService
      .getDataByMobileNo(event.option.value.mobileNumber)
      .subscribe((response) => {
        console.log(response);

        let patchValue = this.dealerInformation.find(i => (i.district === response.district));
        if(patchValue == null){
            this.toastr.error("Customer belongs to another territory");
            this.prospectDetailsForm.controls.mobileNumber.reset();
            this.enquiryPresenter.resetProspectDetailsForMobileNumber();
            this.enquiryPresenter.enableProspectDetailsForMobileNumber();
            this.enquiryPresenter.resetProspectBackgroundForMobileNo();
        }else{
            this.dataAutoPopulateByMobileNo.emit(response);
            this.prospectDetailsForm.patchValue(response);
            if(this.prospectDetailsForm.get("prosId"))
                this.prospectDetailsForm.get("prosId").setValue(response.id);
            this.patchValueForMobileNo(response);
            this.patchLanguagesByMobileNo(response);
            if (
              response.prospectType === "Institution" ||
              response.customerType === "Institution"
            ) {
              this.isShowByProspectType = true;
            }
            if (response.customerCode && response.recordType=='CUSTOMER') {
              
                this.enquiryPresenter.disableProspectDetailsForMobileNumber();
              
            }
        }
      });
  }

  checkItemNoModelForEnquiry(value) {
    let model = this.enquiryPresenter.enquiryForm.get("productIntrested").value
      .model;
    if (model) {
      this.prospectDetailsV2WebService
        .checkItemNumberModelInEnquiry(model.model, value.mobileNumber)
        .subscribe((response) => {
          console.log(response);
          let checkEnquiry = response as BaseDto<number>;
          if (checkEnquiry.result === 0) {
            this.openConfirmDialog();
          }
        });
    }
  }

  patchValueForMobileNo(response: SaveEnquiry) {
    this.recordType = response.recordType;  
    this.prospectDetailsForm.get("recordType").patchValue(this.recordType);
    this.prospectDetailsForm.get("prospectCode").patchValue(response.customerCode);
    this.prospectDetailsForm
      .get("mobileNumber")
      .patchValue({ mobileNumber: response.mobileNumber });
    this.prospectDetailsForm
      .get("prospectType")
      .patchValue(this.prospectType.find(p=>p.prospectType==response.customerType));
    this.prospectDetailsForm
      .get("pinCode")
      .patchValue({ pinCode: response.pinCode });
    this.prospectDetailsForm
      .get("district")
      .patchValue({ district: response.district });
    if (response.dob) {
      const dob = this.dateService.getDateIntoYYYYMMDD(
        response.dob ? response.dob : null
      );
      this.prospectDetailsForm.get("dob").patchValue(dob);
    }
    this.prospectDetailsForm
      .get("anniversaryDate")
      .patchValue(
        response.anniversaryDate ? new Date(response.anniversaryDate) : null
      );
    if (response.anniversaryDate) {
      this.isDateOfBirth = true;
    }
  }

  patchLanguagesByMobileNo(response: SaveEnquiry) {
    let languagesObj = this.languages.findIndex(
      (res) => res.language === response.language
    );
    this.prospectDetailsForm
      .get("language")
      .patchValue(this.languages[languagesObj]);
  }

  selectionDistrict(event: MatSelectChange) {
    console.log("event ", event);
    this.districtId = event.value.districtId;
  }

  autoPincode(autoPincode) {
    if (
      this.enquiryPresenter.operation === Operation.CREATE ||
      this.enquiryPresenter.operation === Operation.VIEW_MOBILE ||
      (this.enquiryByEnquiryNumber.mobileCountUpdate == 0 && Operation.VIEW)
    ) {       
      if (autoPincode && this.districtId) {
        this.pinCode$ = this.prospectDetailsV2WebService.autoCompleteTehsilCityPincode(
          this.districtId,
          autoPincode
        );
      }
    }
    // if (this.enquiryByEnquiryNumber.mobileCountUpdate == 0 && Operation.VIEW) {        
    //   if (autoPincode && this.districtId) {
    //     this.pinCode$ = this.prospectDetailsV2WebService.autoCompleteTehsilCityPincode(
    //       this.districtId,
    //       autoPincode
    //     );
    //   }
    //   }
  }

  displayFnForPinCode(code: AutoCompPinCode) {
    return code ? code.pinCode : undefined;
  }

  onKeyDownPinCode(event: KeyboardEvent) {
    if (event.key === "Backspace" || event.key === "Delete")
      this.enquiryPresenter.resetProspectDetailsForPinCode();
  }

  optionSelectedPinCode(event) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.prospectDetailsForm.get("pinCode").setErrors(null);
    }
    this.prospectDetailsV2WebService
      .getPincodeDetail(
        event.option.value.pinID,
        event.option.value.cityId,
      )
      .subscribe((response) => {
        console.log("response ", response);
        this.prospectDetailsForm.patchValue(response);
        this.prospectDetailsForm.get("pinId").patchValue(response.pinID);
        this.prospectDetailsForm.get("pinCode").patchValue({ pinCode: response.pinCode });
      });
  }

  selectionProspectType(value) {
    if (value.prospectType === "Institution") {
      this.isShowByProspectType = true;
      this.enquiryPresenter.mandatoryFieldForProspectType(true);
    } else {
      this.isShowByProspectType = false;
      this.enquiryPresenter.mandatoryFieldForProspectType(false);
    }
  }

  private openConfirmDialog(): void | any {
    let message = `Enquiry already created  with model Do you want to Continue? `;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: "500px",
      panelClass: "confirmation_modal",
      data: confirmationData,
    });
    dialogRef.afterClosed().subscribe((result) => {
      console.log("The dialog was closed", result);
      if (result === "Ok") {
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = "Confirmation";
    confirmationData.message = message;
    confirmationData.buttonName = ["Ok"];
    return confirmationData;
  }
  /*compareFnProspectType(c1: ProspectType, c2: ViewEnquiry): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === "object" && typeof c2 === "string")
        return c1.prospectType === c2;
      if (typeof c2 === "object" && typeof c1 === "string")
        return c1 === c2.customerMaster.customerType;
    }
    return c1 && c2 ? c1.prospectType === c2.customerMaster.customerType : c1 === c2.customerMaster;
  }*/

  compareFnTitle(c1: Title, c2: ViewEnquiry): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === "object" && typeof c2 === "string")
        return c1.title === c2;
      if (typeof c2 === "object" && typeof c1 === "string")
        return c1 === c2.title;
    }
    return c1 && c2 ? c1.title === c2.title : c1 === c2;
  }

  compareFnDistrict(c1: DealerInformation, c2: ViewEnquiry): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === "object" && typeof c2 === "string")
        return c1.district === c2;
      if (typeof c2 === "object" && typeof c1 === "string")
        return c1 === c2.district;
    }
    return c1 && c2 ? c1.district === c2.district : c1 === c2;
  }

  dobDateSelected(event: MatDatepickerInputEvent<Date>) {
    const yearsDiff = this.dateService.getYearsDiffrance(
      new Date(),
      event.value
    );
    console.log(`yearDiff : ${yearsDiff} years ago`);
    if (yearsDiff > 18) {
      console.log("18 years ago");
      this.isDateOfBirth = true;
    } else {
      console.log("Not 18 years ago");
      
      this.prospectDetailsForm.get("dob").setErrors({
        invalidBirthDate: "Age Should be more than 18 yrs.",
      });
      this.isDateOfBirth = false;
      //this.prospectDetailsForm.get("dob").reset();
    }
  }
  anniversayDateSelected(event: MatDatepickerInputEvent<Date>){
      console.log(this.prospectDetailsForm.controls['dob'].value);
      const yearsDiff = this.dateService.getYearsDiffrance(
              event.value,
              this.prospectDetailsForm.controls['dob'].value
            );
      console.log(yearsDiff);
      if (yearsDiff < 18) {
          console.log('error')
          this.prospectDetailsForm.get("anniversaryDate").setErrors({
              invalidDate: "Anniversary Date should be more that 18 yrs from date of Birth",
            });
          this.prospectDetailsForm.controls['anniversaryDate'].patchValue('');
      }else{
          this.prospectDetailsForm.get("anniversaryDate").setErrors(null);
      }
  }
  getPanNumber(event: FocusEvent) {
    let str = event.target["value"];
    let panNum = str.slice(2, 12);
    this.prospectDetailsForm.get("panNumber").patchValue(panNum);
  }

  checkAlternateMobileNo(event){
    let mobNo=this.prospectDetailsForm.get("mobileNumber").value
    if (event.target.value===mobNo) {
      this.prospectDetailsForm.get("alternateMobileNumber").setErrors({
        duplicateAlterMobNoError:'Alternate Mobile No is same as Mobile Number'
      })
    }
    
  }
  checkStdCode(){
    let teliphone = this.prospectDetailsForm.get("telephoneNumber").value
    if (teliphone) {
      this.prospectDetailsForm.get("std").enable()
    }
    else if (teliphone===undefined || teliphone=="" || teliphone=== null) {
      this.prospectDetailsForm.get("std").disable()
    }
  }

  enquiryLanguageForProstect(){
    this.prospectDetailsV2WebService.languagesForEnquary().subscribe(res=>{
      console.log('checkpoint1--',res);
      
      this.languages=res
    })
  }
}
