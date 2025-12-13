import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Injectable()
export class DealerMasterPageStore {
  private readonly dealerMasterForm: FormGroup;

  constructor(private fb: FormBuilder) {
    console.log('---------Dealer Master Form Created---------------');
    this.dealerMasterForm = this.fb.group({
      dealerMaster: this.dealerMaster(),
      dealerBank: this.dealerBank(),
      dealerAddress: this.dealerAddress(),
      dealerType: this.dealerType(),
      dealerContactDetail:  this.dealerContactDetail(),
      kaiRepresentative: this.kaiRepresentative(),
      
    });
  }

  private dealerMaster() {
    return this.fb.group({
      dealerCode: [{value: null, disabled: false}, Validators.compose([])],
      dealerName: [{value: null, disabled: false}, Validators.compose([])],
      dealerFirmType: [{ value: null, disabled: true }, Validators.compose([])],
      gstNo: [{ value: null, disabled: true }, Validators.compose([])],
      panNo: [{ value: null, disabled: true }, CustomValidators.validatePanNo],
      status: [{ value: null, disabled: true }, Validators.compose([])],
      state: [{ value: null, disabled: true }, Validators.compose([])],
      dealerEmailId: [{value: null, disabled: false}, Validators.compose([Validators.required, CustomValidators.validateEmail])],
      areaLevel: [{value: null, disabled: false}, Validators.required],
      allocationTerritory: [{value: null, disabled: false}, Validators.compose([])]
    });
  }

  private dealerBank() {
    return this.fb.group({
      bankName: [null, Validators.compose([Validators.required])],
      BranchName: [null, Validators.compose([Validators.required])],
      currentAccountNo: [null, Validators.compose([Validators.required])],
      ifscCode: [null, Validators.compose([Validators.required])],
      micrCode: [null, Validators.compose([Validators.required])]
    });
  }
  private dealerAddress() {
    return this.fb.group({
      billingAddress: this.address(),
      showRoomAddress: this.address(),
      workShopAddress: this.address()
    }) 
  }

  private address() {
    return this.fb.group({
      address1: [{value: null, disabled: false}, Validators.compose([])],
      address2: [{value: null, disabled: false}, Validators.compose([])],
      address3: [{value: null, disabled: false}, Validators.compose([])],
      address4: [{value: null, disabled: false}, Validators.compose([])],
      pinCode: [{value: null, disabled: false}, Validators.compose([CustomValidators.numberOnly])],
      tehsil: [{value: null, disabled: false}, Validators.compose([])],
      city: [{value: null, disabled: false}, Validators.compose([])],
      state: [{value: null, disabled: false}, Validators.compose([])],
      country: [{value: null, disabled: false}, Validators.compose([])]
    });
  }
  private dealerType() {
    return this.fb.group({
      dealerType: [null, Validators.compose([])],
      dealerStarRating: [null, Validators.compose([])],
  
    })
  }

  private dealerContactDetail() {
      return this.fb.group({
        partners: this.fb.group({
            partner: [null, Validators.compose([])],
            detail: this.contactDetailForm()
        }),
        sales: this.fb.group({
            sale: [null, Validators.compose([])],
            detail: this.contactDetailForm()
        }),
        services: this.fb.group({
            service: [null, Validators.compose([])],
            detail: this.contactDetailForm()
        }),
        spareParts: this.fb.group({
            sparePart: [null, Validators.compose([])],
            detail: this.contactDetailForm()
        }),
      })
  }

  private contactDetailForm() {
      return this.fb.group({
          name: [null, Validators.compose([])],
          designation: [null, Validators.compose([])],
          email: [null, Validators.compose([])],
          mobileNo: [null, Validators.compose([])],
      })
  }

  private kaiRepresentative() {
    return this.fb.group({
        depot: [null, Validators.compose([])],
        sales: this.fb.group({
            sale: [null, Validators.compose([])],
            detail: this.representativeForm()
        }),
        marketing: this.fb.group({
            marketing: [null, Validators.compose([])],
            detail: this.representativeForm()
        }),
        services: this.fb.group({
            service: [null, Validators.compose([])],
            detail: this.representativeForm()
        }),
        warranty: this.fb.group({
            warranty: [null, Validators.compose([])],
            detail: this.representativeForm()
        }),
        spareParts: this.fb.group({
            sparePart: [null, Validators.compose([])],
            detail: this.representativeForm()
        })
    })
  }

  private representativeForm() {
    return this.fb.group({
        name: [null, Validators.compose([])],
        designation: [null, Validators.compose([])],
        dealerCode: [null, Validators.compose([])],
        dealerCreditLimits: [null, Validators.compose([])],
    })
  }

  get allForm() {
    if(this.dealerMasterForm) {
     return this.dealerMasterForm as FormGroup;
    }
  }
}
