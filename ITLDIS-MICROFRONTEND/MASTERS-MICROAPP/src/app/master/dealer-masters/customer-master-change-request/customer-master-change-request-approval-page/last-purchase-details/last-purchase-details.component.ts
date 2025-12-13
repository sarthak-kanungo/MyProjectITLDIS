import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { CustomerMasterCreatePagePresenter } from '../../../customer-master/component/customer-master-create-page/customerMasterCreatePage.presenter';
import { ActivatedRoute } from '@angular/router';
import { OperationsUtil, Operation } from 'src/app/utils/operation-util';
import { LastPurchaseDetailsService } from '../../../customer-master/component/last-purchase-details/last-purchase-details.service';
import { ManufacturerDropdown, ModelDropDown } from '../../../customer-master/domain/customer-master-domain';
import { CustomerMasterChangeRequestPresenter } from '../customerMasterChangeRequest.presenter';

@Component({
  selector: 'app-last-purchase-details',
  templateUrl: './last-purchase-details.component.html',
  styleUrls: ['./last-purchase-details.component.scss'],
  providers: [LastPurchaseDetailsService]
})
export class LastPurchaseDetailsComponent implements OnInit {
  purchaseDetails: FormGroup;
  purchaseDetailsReq: FormGroup;
  isView: boolean;
  isEdit: boolean;
  isCreate: boolean;
  manufacturers: Array<ManufacturerDropdown> = [];
  models: Array<ModelDropDown> = [];
@Input() brandColor;
@Input() modelColor
@Input() yearColor
@Input() serialColor
  constructor(
    private customerMasterCreatePagePresenter: CustomerMasterCreatePagePresenter,
    private customerMasterChangeRequestPresenter: CustomerMasterChangeRequestPresenter,
    private activityRoute: ActivatedRoute,
    private lastPurchaseDetailsService: LastPurchaseDetailsService

  ) { }

  ngOnInit() {
    this.purchaseDetails = this.customerMasterCreatePagePresenter.purchaseDetails;
    this.purchaseDetailsReq = this.customerMasterChangeRequestPresenter.purchaseDetails;
    this.customerMasterCreatePagePresenter.operation = OperationsUtil.operation(this.activityRoute);
    // this.lastPurchaseDetailsService.getManufacturerDropdown().subscribe(response => {
    //     this.manufacturers = response['result'];
    // })
    // this.lastPurchaseDetailsService.getModelAuto().subscribe(response => {
    //     this.models = response['result'];
    // })
  }

}
