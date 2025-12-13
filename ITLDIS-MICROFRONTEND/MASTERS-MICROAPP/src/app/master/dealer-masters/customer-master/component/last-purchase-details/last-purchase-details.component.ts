import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { CustomerMasterCreatePagePresenter } from '../customer-master-create-page/customerMasterCreatePage.presenter';
import { ActivatedRoute } from '@angular/router';
import { OperationsUtil, Operation } from 'src/app/utils/operation-util';
import { LastPurchaseDetailsService } from './last-purchase-details.service';
import { ManufacturerDropdown, ModelDropDown } from '../../domain/customer-master-domain';
import { BranchSubDealerAddressComponent } from '../../../branch-sub-dealer-master/component/branch-sub-dealer-address/branch-sub-dealer-address.component';

@Component({
  selector: 'app-last-purchase-details',
  templateUrl: './last-purchase-details.component.html',
  styleUrls: ['./last-purchase-details.component.scss'],
  providers: [LastPurchaseDetailsService]
})
export class LastPurchaseDetailsComponent implements OnInit {
  purchaseDetails: FormGroup;
  isView: boolean;
  isEdit: boolean;
  isCreate: boolean;
  manufacturers: Array<ManufacturerDropdown> = [];
  models: Array<ModelDropDown> = [];

  constructor(
    private customerMasterCreatePagePresenter: CustomerMasterCreatePagePresenter,
    private activityRoute: ActivatedRoute,
    private lastPurchaseDetailsService: LastPurchaseDetailsService

  ) { }

  ngOnInit() {
    this.purchaseDetails = this.customerMasterCreatePagePresenter.purchaseDetails
    this.customerMasterCreatePagePresenter.operation = OperationsUtil.operation(this.activityRoute);
    this.lastPurchaseDetailsService.getManufacturerDropdown().subscribe(response => {
        this.manufacturers = response['result'];
    })
    this.lastPurchaseDetailsService.getModelAuto().subscribe(response => {
        this.models = response['result'];
    })
    this.viewOrEditOrCreate()
  }

  private viewOrEditOrCreate() {
    if (this.customerMasterCreatePagePresenter.operation === Operation.VIEW) {
      //this.addRow()
      //this.customerMasterCreatePagePresenter.createCustomerMasterForm.disable()
      this.isView = true
    }
    else if (this.customerMasterCreatePagePresenter.operation === Operation.EDIT) {

    } else {
      this.customerMasterCreatePagePresenter.operation === Operation.CREATE
      this.addRow()
    }
  }

  addRow() {
    let fg:FormGroup = this.customerMasterCreatePagePresenter.addRow()
    this.onchangeManufacture(fg);

  }
  deleteRow() {
    this.customerMasterCreatePagePresenter.dltRow()

  }
  onchangeManufacture(fg:FormGroup){
    fg.controls.brand.valueChanges.subscribe(brand => {
      fg.controls.model.reset();
      if(brand == 'itldis'){
        fg.controls.modelEditable.patchValue("N");
      }else{
        fg.controls.modelEditable.patchValue("Y");
      }
    })
  }

}
