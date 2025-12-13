import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { CustomerMasterCreatePagePresenter } from '../../../customer-master/component/customer-master-create-page/customerMasterCreatePage.presenter';
import { Sales } from '../../../customer-master/domain/customer-master-domain'

@Component({
  selector: 'app-vehicle-details',
  templateUrl: './vehicle-details.component.html',
  styleUrls: ['./vehicle-details.component.scss']
})
export class VehicleDetailsComponent implements OnInit {

  vehicalDetail: FormGroup
      
  constructor(
    private customerMasterCreatePagePresenter: CustomerMasterCreatePagePresenter
  ) { }

  ngOnInit() {
    this.vehicalDetail = this.customerMasterCreatePagePresenter.vehicalDetailsForm
    
  }
  
}
