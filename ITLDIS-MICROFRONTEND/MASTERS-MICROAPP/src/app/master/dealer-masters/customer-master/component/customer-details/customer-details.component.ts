import { Component, OnInit, Input } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomerMasterCreatePagePresenter } from '../customer-master-create-page/customerMasterCreatePage.presenter';
import { CustomerDetailsService } from './customer-details.service';
import { CustomerTypeDropdown, TitleDropdown, QualificationDropdown, PreferrdeLanguageDropdown } from '../../domain/customer-master-domain';
import { Observable } from 'rxjs'
import { ToastrService } from 'ngx-toastr';
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
  mobileNo$:Array<any>;
  
  constructor(private fb: FormBuilder, public dialog: MatDialog,
    private customerMasterCreatePagePresenter: CustomerMasterCreatePagePresenter,
    private customerDetailsService: CustomerDetailsService,
    private toasterService : ToastrService
  ) { }

  ngOnInit() {
    this.customerMasterDetailsForm = this.customerMasterCreatePagePresenter.customerCreateDetailsForm;
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
    this.customerMasterDetailsForm.get('mobileNumber').valueChanges.subscribe(value => {
        if(value==undefined || value.toString().length<10){  
          this.customerMasterDetailsForm.get('mobileNumber').setErrors({"invalidMobile":"Invalid Mobile Number"})
        }else{
          this.customerMasterDetailsForm.get('mobileNumber').setErrors(null);
        }
        this.customerDetailsService.validateMobileNumber(value, this.customerMasterDetailsForm.get('customerCode').value).subscribe(response => {
            if(response!=null && response['result']!='OK'){
                this.toasterService.error(response['result']);
                this.customerMasterDetailsForm.get('mobileNumber').reset();
            }
        })
    });
    
  }
  
  displayFnForMObileNo(mobNum:any) {
      return mobNum ? mobNum['mobileNumber'] : undefined;
  }
  
  getPanNumber(event: FocusEvent) {
      let str = event.target["value"];
      let panNum = str.slice(2, 12);
      this.customerMasterDetailsForm.get("panNumber").patchValue(panNum);
    }
}
