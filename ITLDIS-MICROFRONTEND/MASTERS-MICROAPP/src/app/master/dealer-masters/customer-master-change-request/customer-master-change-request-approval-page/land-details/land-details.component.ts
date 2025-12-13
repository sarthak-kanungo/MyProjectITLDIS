import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomerMasterCreatePagePresenter } from '../../../customer-master/component/customer-master-create-page/customerMasterCreatePage.presenter';
import { ActivatedRoute } from '@angular/router';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { LandDetailsService } from '../../../customer-master/component/land-details/land-details.service';
import { SoilTypeDropdown, CropsGrownDropdown } from '../../../customer-master/domain/customer-master-domain';
import { CustomerMasterChangeRequestPresenter } from '../customerMasterChangeRequest.presenter'

@Component({
  selector: 'app-land-details',
  templateUrl: './land-details.component.html',
  styleUrls: ['./land-details.component.scss'],
  providers: [LandDetailsService]
})
export class LandDetailsComponent implements OnInit {

 @Input() soiltypes: Array<SoilTypeDropdown> = [];

 @Input() crops: Array<CropsGrownDropdown> = [];

@Input() colorCheck;
@Input() colorCheckCrop
  customerMasterLandDetailsForm: FormGroup;
  customerMasterLandDetailsFormReq: FormGroup;
  isView: boolean;
  isEdit: boolean;
  isCreate: boolean;
  
  constructor(
    private customerMasterCreatePagePresenter: CustomerMasterCreatePagePresenter,
    private customerMasterChangeRequestPresenter: CustomerMasterChangeRequestPresenter,
    private activityRoute: ActivatedRoute,
    private landDetailsService: LandDetailsService
  ) { }

  ngOnInit() {
   
//     console.log('color check from master',this.colorCheck);
    this.customerMasterLandDetailsForm = this.customerMasterCreatePagePresenter.createCustomerLandDetails;
    this.customerMasterLandDetailsFormReq = this.customerMasterChangeRequestPresenter.createCustomerLandDetails;
    this.customerMasterCreatePagePresenter.operation = OperationsUtil.operation(this.activityRoute);
  }

}
