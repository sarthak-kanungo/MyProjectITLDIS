import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';

@Injectable()
export class InvoiceProspectDetailsService {

  isEdit: boolean;
  isView: boolean;
  data: Object;
  show
  labelPosition = 'before';
  disable = true;

  customertypes: string[] = [
    'Institution', 'Individual'
  ];

  prospectDetailsFrom: FormGroup;

  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.createprospectDetailsFrom();

  }
  createprospectDetailsFrom() {
    this.prospectDetailsFrom = this.fb.group({
      customerType: [{ value: '', disabled: true }, Validators.compose([])],
      companyName: [{ value: '', disabled: true }, Validators.compose([])],
      customerCode: [{ value: '', disabled: true }, Validators.compose([])],
      customerName: [{ value: '', disabled: true }, Validators.compose([])],
      invoiceCustomerName: [{ value: '', disabled: true }, Validators.compose([])],
      address1: [{ value: '', disabled: true }, Validators.compose([])],
      address2: [{ value: '', disabled: true }, Validators.compose([])],
      address3: [{ value: '', disabled: true }, Validators.compose([])],
      pinCode: [{ value: '', disabled: true }, Validators.compose([])],
      postOffice: [{ value: '', disabled: true }, Validators.compose([])],
      tehsil: [{ value: '', disabled: true }, Validators.compose([])],
      district: [{ value: '', disabled: true }, Validators.compose([])],
      city: [{ value: '', disabled: true }, Validators.compose([])],
      state: [{ value: '', disabled: true }, Validators.compose([])],
      mobileNumber: [{ value: '', disabled: true }, Validators.compose([])],
      panNumber: [{ value: '', disabled: true }, Validators.compose([])],
      gstinNumber: [{ value: '', disabled: true }, Validators.compose([])],

    })
    return this.prospectDetailsFrom;
  }
}
