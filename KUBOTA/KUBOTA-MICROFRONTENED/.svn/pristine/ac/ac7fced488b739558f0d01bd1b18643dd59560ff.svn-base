import { Component, OnInit, Input } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomerMasterCreatePagePresenter } from '../../../customer-master/component/customer-master-create-page/customerMasterCreatePage.presenter';
import { CustomerDetailsService } from '../../../customer-master/component/customer-details/customer-details.service';
import { CustomerTypeDropdown, TitleDropdown, QualificationDropdown, PreferrdeLanguageDropdown } from '../../../customer-master/domain/customer-master-domain';
import { CustomerMasterChangeRequestPresenter } from '../customerMasterChangeRequest.presenter'
@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    CustomerDetailsService
  ]
})
export class CustomerDetailsComponent implements OnInit {

  isEdit: boolean;
  isView: boolean;
  data: Object;
  customerTypes: Array<CustomerTypeDropdown> = [];

  titles: Array<TitleDropdown> = [];

  qualifications: Array<QualificationDropdown> = [];


  languages: Array<PreferrdeLanguageDropdown> = [];

  customerMasterDetailsForm: FormGroup;
  customerMasterDetailsFormReq: FormGroup;

  constructor(private fb: FormBuilder, public dialog: MatDialog,
    private customerMasterCreatePagePresenter: CustomerMasterCreatePagePresenter,
    private customerMasterChangeRequestPresenter: CustomerMasterChangeRequestPresenter,
    private customerDetailsService: CustomerDetailsService
  ) { }

  ngOnInit() {
    this.customerMasterDetailsForm = this.customerMasterCreatePagePresenter.customerCreateDetailsForm;
    this.customerMasterDetailsFormReq = this.customerMasterChangeRequestPresenter.customerCreateDetailsForm;
    
    this.customerDetailsService.getCustomerTypeDropdown().subscribe(response => {
        this.customerTypes = response['result'];
    })
    this.customerDetailsService.getTitleDropdown().subscribe(response => {
        this.titles = response['result'];
    })
    this.customerDetailsService.getQualificationDropdown().subscribe(response => {
        this.qualifications = response['result'];
    })
    this.customerDetailsService.getPreferrdeLanguageDropdown().subscribe(response => {
        this.languages = response['result'];
    })
  }
}
