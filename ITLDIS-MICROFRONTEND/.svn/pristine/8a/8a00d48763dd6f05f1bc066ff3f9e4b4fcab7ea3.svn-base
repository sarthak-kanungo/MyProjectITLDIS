import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomerMasterCreatePagePresenter } from '../customer-master-create-page/customerMasterCreatePage.presenter';
import { ActivatedRoute } from '@angular/router';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { LandDetailsService } from './land-details.service';
import { SoilTypeDropdown, CropsGrownDropdown } from '../../domain/customer-master-domain';

@Component({
  selector: 'app-land-details',
  templateUrl: './land-details.component.html',
  styleUrls: ['./land-details.component.scss'],
  providers: [LandDetailsService]
})
export class LandDetailsComponent implements OnInit {

 @Input() soiltypes: Array<SoilTypeDropdown> = [];

 @Input() crops: Array<CropsGrownDropdown> = [];

  customerMasterLandDetailsForm: FormGroup;
  isView: boolean;
  isEdit: boolean;
  isCreate: boolean;
  constructor(
    private customerMasterCreatePagePresenter: CustomerMasterCreatePagePresenter,
    private activityRoute: ActivatedRoute,
    private landDetailsService: LandDetailsService
  ) { }

  ngOnInit() {
    this.customerMasterLandDetailsForm = this.customerMasterCreatePagePresenter.createCustomerLandDetails
    this.customerMasterCreatePagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    
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
    }
  }

}
