import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class ProspectDetailsService {
  private prospectDetailsForm: FormGroup;

  constructor(
    private fb: FormBuilder
  ) { }

  createprospectDetailsForm() {
    this.prospectDetailsForm = this.fb.group({
      prospectCode: [{ value: null, disabled: true }],
      companyName: [null],
      prospectType: [null, Validators.compose([Validators.required])],
      firstName: [null, Validators.compose([Validators.required, Validators.pattern('^[a-zA-Z \-\']+')])],
      middleName: [null, Validators.compose([Validators.pattern('^[a-zA-Z \-\']+')])],
      lastName: [null, Validators.compose([Validators.required, Validators.pattern('^[a-zA-Z \-\']+')])],
      fatherName: [null, Validators.compose([Validators.required, Validators.pattern('^[a-zA-Z \-\']+')])],
      mobileNumber: [null, Validators.compose([Validators.required])],
      whatsAppNumber: [null, Validators.compose([Validators.required, Validators.pattern('[1-9]\\d{9}')])],
      alternateMobileNumber: [null, Validators.compose([Validators.pattern('[1-9]\\d{9}')])],
      std: [null, Validators.compose([])],
      telephoneNumber: [null],
      emailId: [null, Validators.compose([Validators.email, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')])],
      address1: [null, Validators.compose([Validators.required])],
      address2: [null, Validators.compose([])],
      address3: [null, Validators.compose([])],
      pinCode: [null, Validators.compose([Validators.required,])],
      postOffice: [null, Validators.compose([Validators.required])],
      city: [{ value: null, disabled: true }],
      tehsil: [{ value: null, disabled: true }],
      district: [{ value: null, disabled: true }],
      state: [{ value: null, disabled: true }],
      country: [{ value: null, disabled: true }],
      language: [null],
      dob: [null],
      anniversaryDate: [null],
      gstNumber: [null, Validators.compose([Validators.pattern(/^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/)])],
      panNumber: [null, Validators.compose([Validators.pattern('^[a-zA-Z0-9]+')])]
    });
    return this.prospectDetailsForm
  }

  keyPress(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keyPresswhatsapp(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keyPressalt(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }
  keyStd(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }
  keytel(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keyPressFirstName(event: any) {
    const pattern = /[a-zA-Z]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keyPressMiddleName(event: any) {
    const pattern = /[a-zA-Z]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keyPressLastName(event: any) {
    const pattern = /[a-zA-Z]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keyPressFatherName(event: any) {
    const pattern = /[a-zA-Z ]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keyPressPanNumber(event: any) {
    const pattern = /[a-zA-Z0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }


  viewprospectDetailsForm() {
    this.prospectDetailsForm = this.fb.group({
      prospectCode: this.fb.control({ value: null, disabled: true }),
      companyName: this.fb.control(null),
      prospectType: this.fb.control({ value: null, disabled: true }),
      firstName: this.fb.control({ value: null, disabled: true }),
      middleName: this.fb.control(null),
      lastName: this.fb.control({ value: null, disabled: true }),
      fatherName: this.fb.control({ value: null, disabled: true }),
      mobileNumber: this.fb.control({ value: null, disabled: true }),
      whatsAppNumber: this.fb.control({ value: null, disabled: true }),
      alternateMobileNumber: this.fb.control(null),
      std: this.fb.control(null),
      telephoneNumber: this.fb.control(null),
      emailId: this.fb.control({ value: null, disabled: true }),
      address1: this.fb.control({ value: null, disabled: true }),
      address2: this.fb.control(null),
      address3: this.fb.control(null),
      pinCode: this.fb.control({ value: null, disabled: true }),
      postOffice: this.fb.control({ value: null, disabled: true }),
      city: this.fb.control({ value: null, disabled: true }),
      tehsil: this.fb.control({ value: null, disabled: true }),
      district: this.fb.control({ value: null, disabled: true }),
      state: this.fb.control({ value: null, disabled: true }),
      country: this.fb.control({ value: null, disabled: true }),
      language: this.fb.control(null),
      dob: this.fb.control(null),
      anniversaryDate: this.fb.control(null),
      gstNumber: this.fb.control(null),
      panNumber: this.fb.control(null)
    });
    return this.prospectDetailsForm
  }

  viewMobileprospectDetailsForm() {
    this.prospectDetailsForm = this.fb.group({
      prospectCode: this.fb.control({ value: null, disabled: true }),
      companyName: this.fb.control({ value: null, disabled: false }),
      prospectType: this.fb.control({ value: null, disabled: false }),
      firstName: this.fb.control({ value: null, disabled: true }),
      middleName: this.fb.control({ value: null, disabled: false }),
      lastName: this.fb.control({ value: null, disabled: false }),
      fatherName: this.fb.control({ value: null, disabled: false }),
      mobileNumber: this.fb.control({ value: null, disabled: true }),
      whatsAppNumber: this.fb.control({ value: null, disabled: false }),
      alternateMobileNumber: this.fb.control({ value: null, disabled: false }),
      std: this.fb.control({ value: null, disabled: false }),
      telephoneNumber: this.fb.control({ value: null, disabled: false }),
      emailId: this.fb.control({ value: null, disabled: false }),
      address1: this.fb.control({ value: null, disabled: false }),
      address2: this.fb.control({ value: null, disabled: false }),
      address3: this.fb.control({ value: null, disabled: false }),
      pinCode: this.fb.control({ value: null, disabled: false }),
      postOffice: this.fb.control({ value: null, disabled: false }),
      city: this.fb.control({ value: null, disabled: true }),
      tehsil: this.fb.control({ value: null, disabled: true }),
      district: this.fb.control({ value: null, disabled: true }),
      state: this.fb.control({ value: null, disabled: true }),
      country: this.fb.control({ value: null, disabled: true }),
      language: this.fb.control({ value: null, disabled: false }),
      dob: this.fb.control({ value: null, disabled: false }),
      anniversaryDate: this.fb.control({ value: null, disabled: false }),
      gstNumber: this.fb.control({ value: null, disabled: false }),
      panNumber: this.fb.control({ value: null, disabled: false })
    });
    return this.prospectDetailsForm
  }
}
