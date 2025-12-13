import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { PartyMasterPageStore } from './partyMasterPage.store';

@Injectable()
export class PartyMasterPagePresenter {

  readonly createPartyMasterForm: FormGroup

  private _operations: string

  set operation(type: string) {
    this._operations = type
  }
  get operation() {
    return this._operations
  }

  constructor(
    private partyMasterPageStore: PartyMasterPageStore,
    private toastr: ToastrService,

  ) {
    this.createPartyMasterForm = this.partyMasterPageStore.partyMasterCreateForm
  }

  get partyCreateDetailsForm() {
    return this.createPartyMasterForm.get('partyCreateDetailsForm') as FormGroup
  }

  get partyCreateAddressDetailsForm() {
    return this.createPartyMasterForm.get('partyCreateAddressDetailsForm') as FormGroup
  }

  markFormAsTouched() {
    this.createPartyMasterForm.markAllAsTouched();
  }
  stateValidation() {
    this.partyCreateAddressDetailsForm.get('state').setErrors({
      selectFromList: 'Please select from list',
    });
  }

  districtValidation() {
    this.partyCreateAddressDetailsForm.get('district').setErrors({
      selectFromList: 'Please select from list',
    });
  }

  tehsilValidation() {
    this.partyCreateAddressDetailsForm.get('tehsil').setErrors({
      selectFromList: 'Please select from list',
    });
  }

  villageValidation() {
    this.partyCreateAddressDetailsForm.get('city').setErrors({
      selectFromList: 'Please select from list',
    });
  }

  pinCodeValidation() {
    this.partyCreateAddressDetailsForm.get('pinCode').setErrors({
      selectFromList: 'Please select from list',
    });
  }

  postOfficeValidation() {
    this.partyCreateAddressDetailsForm.get('postOffice').setErrors({
      selectFromList: 'Please select from list',
    });
  }
  localityValidation() {
    this.partyCreateAddressDetailsForm.get('locality').setErrors({
      selectFromList: 'Please select from list',
    });
  }
  branchCodeValidation() {
    this.partyCreateDetailsForm.get('branchCode').setErrors({
      selectFromList: 'Please select from list',
    });
  }
  patchDataFromView(res: any) {

    if (res) {
      this.partyCreateAddressDetailsForm.patchValue(res.result)
      this.partyCreateDetailsForm.patchValue(res.result)
      this.createPartyMasterForm.patchValue(res.result)
      let firstNameValue = "";
      let middleNameValue = "";
      let lastNameValue = "";
      let stdValue = "";
      let telephoneValue = "";
      var contactNameValue = res.result.contactName;
      if(contactNameValue){
        var arry = contactNameValue.split(" ");
        firstNameValue = arry[0];
        middleNameValue = arry[1];
        lastNameValue = arry[2];
      }
      this.partyCreateDetailsForm.controls.firstName.patchValue(firstNameValue);
      this.partyCreateDetailsForm.controls.middleName.patchValue(middleNameValue);
      this.partyCreateDetailsForm.controls.lastName.patchValue(lastNameValue);
      var telephone = res.result.telephone;
      if (telephone != null && telephone != undefined && telephone != '') {
        var length = telephone.length
        if (length == 12) {
          stdValue = telephone.substr(0, 2);
          telephoneValue = telephone.substr(2, telephone.length - 1);
        }
        else if (length > 12) {
          var prefix = length - 10;
          stdValue = telephone.substr(0, prefix);
          telephoneValue = telephone.substr(prefix, telephone.length - 1);
        }

        this.partyCreateAddressDetailsForm.controls.telephone.patchValue(telephoneValue);
        this.partyCreateAddressDetailsForm.controls.std.patchValue(stdValue);
      }
      // for(let i=0;i<this.categories.length;i++)
      // {
      //   if(res.result.partyCategoryId==this.categories[i].id)
      //   {
      //     this.partyCreateDetailsForm.controls.category.patchValue(this.categories[i]);
      //   }
      // } 
      this.partyCreateDetailsForm.
        controls.category.patchValue({ id: res.result.partyCategoryId });
      //     for(let i=0;i<branchCodeList.length;i++)
      //     {
      //       if(branchCodeList[i].id===res.result.branchId)
      //       {
      this.partyCreateDetailsForm.
        controls.branchCode.patchValue({ id: res.result.branchId });
      //       }
      //     }

    }
    this.partyCreateAddressDetailsForm.patchValue({
      address1: res.result.address1,
      address2: res.result.address2,
      address3: res.result.address3,
      pinCode: { pinCode: res.result.pinCode },
      // locality: res.result.locality,
      tehsil: { tehsil: res.result.tehsil },
      postOffice: { postOffice: res.result.postOffice },
      district: { district: res.result.district },
      city: { city: res.result.city },
      state: { state: res.result.state },
    })
  }
  submitData(submitDataFinal: any) {
    submitDataFinal.pinCode = submitDataFinal.pinCode.pinCode
    submitDataFinal.city = submitDataFinal.city.city
    submitDataFinal.district = submitDataFinal.district.district
    submitDataFinal.postOffice = submitDataFinal.postOffice.postOffice
    submitDataFinal.tehsil = submitDataFinal.tehsil.tehsil
    submitDataFinal.state = submitDataFinal.state.state
    submitDataFinal.mobileNumber = submitDataFinal.mobileNumber.toString()
    submitDataFinal.category = submitDataFinal.category.CategoryCode
    submitDataFinal.branchId = submitDataFinal.branchCode.id
    if (submitDataFinal.std != null && submitDataFinal.std != undefined && submitDataFinal.std != '') {
      if (submitDataFinal.telephone != null && submitDataFinal.telephone != undefined && submitDataFinal.telephone != '') {
        submitDataFinal.telephone = submitDataFinal.std + submitDataFinal.telephone
      }
      else {
        this.toastr.error("please fill telephone number")
        return;
      }
    }
    else {
      if (submitDataFinal.telephone != null && submitDataFinal.telephone != undefined && submitDataFinal.telephone != '') {
        this.toastr.error("please fill std code")
        return;
      }
    }
    if (submitDataFinal.middleName != null && submitDataFinal.middleName != undefined && submitDataFinal.middleName != ''
      && submitDataFinal.lastName != null && submitDataFinal.lastName != undefined && submitDataFinal.lastName != '') {
      submitDataFinal.contactName = submitDataFinal.firstName + " "
        + submitDataFinal.middleName + " " + submitDataFinal.lastName;
    }
    else if ((submitDataFinal.middleName == null || submitDataFinal.middleName == undefined || submitDataFinal.middleName == '') &&
      (submitDataFinal.lastName != null && submitDataFinal.lastName != undefined && submitDataFinal.lastName != '')) {
      submitDataFinal.contactName = submitDataFinal.firstName + " " + submitDataFinal.lastName;
    }
    else if ((submitDataFinal.lastName == null || submitDataFinal.lastName == undefined || submitDataFinal.lastName == '') &&
      (submitDataFinal.middleName != null && submitDataFinal.middleName != undefined && submitDataFinal.middleName != '')) {
      submitDataFinal.contactName = submitDataFinal.firstName + " " + submitDataFinal.middleName;
    }
    else {
      submitDataFinal.contactName = submitDataFinal.firstName;
    }
    console.log("PartyMasterFinal ", submitDataFinal);

    return submitDataFinal;
  }
  updateData(submitDataFinal: any, id: any) {
    submitDataFinal.pinCode = submitDataFinal.pinCode.pinCode
    submitDataFinal.city = submitDataFinal.city.city
    submitDataFinal.district = submitDataFinal.district.district
    submitDataFinal.postOffice = submitDataFinal.postOffice.postOffice
    submitDataFinal.tehsil = submitDataFinal.tehsil.tehsil
    submitDataFinal.state = submitDataFinal.state.state
    submitDataFinal.mobileNumber = submitDataFinal.mobileNumber.toString()
    submitDataFinal.category = submitDataFinal.category.CategoryCode
    submitDataFinal.id = id;

    if (submitDataFinal.std != null && submitDataFinal.std != undefined && submitDataFinal.std != '') {
      if (submitDataFinal.telephone != null && submitDataFinal.telephone != undefined && submitDataFinal.telephone != '') {
        submitDataFinal.telephone = submitDataFinal.std + submitDataFinal.telephone
      }
      else {
        this.toastr.error("please fill telephone number")
        return;
      }
    }
    else {
      if (submitDataFinal.telephone != null && submitDataFinal.telephone != undefined && submitDataFinal.telephone != '') {
        this.toastr.error("please fill std code")
        return;
      }
    }
    if (submitDataFinal.middleName != null && submitDataFinal.middleName != undefined && submitDataFinal.middleName != ''
      && submitDataFinal.lastName != null && submitDataFinal.lastName != undefined && submitDataFinal.lastName != '') {
      submitDataFinal.contactName = submitDataFinal.firstName + " "
        + submitDataFinal.middleName + " " + submitDataFinal.lastName;
    }
    else if ((submitDataFinal.middleName == null || submitDataFinal.middleName == undefined || submitDataFinal.middleName == '') &&
      (submitDataFinal.lastName != null && submitDataFinal.lastName != undefined && submitDataFinal.lastName != '')) {
      submitDataFinal.contactName = submitDataFinal.firstName + " " + submitDataFinal.lastName;
    }
    else if ((submitDataFinal.lastName == null || submitDataFinal.lastName == undefined || submitDataFinal.lastName == '') &&
      (submitDataFinal.middleName != null && submitDataFinal.middleName != undefined && submitDataFinal.middleName != '')) {
      submitDataFinal.contactName = submitDataFinal.firstName + " " + submitDataFinal.middleName;
    }
    else {
      submitDataFinal.contactName = submitDataFinal.firstName;
    }
    console.log("PartyMasterFinal ", submitDataFinal);
    return submitDataFinal;

  }
}