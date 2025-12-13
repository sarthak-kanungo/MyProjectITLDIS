import { Component, OnInit } from '@angular/core';
import { DealerMasterPagePresenter } from '../dealer-master-create/dealer-master-create.presenter';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-dealer-address',
  templateUrl: './dealer-address.component.html',
  styleUrls: ['./dealer-address.component.scss']
})
export class DealerAddressComponent implements OnInit {
  dealeraddressForm: FormGroup;

  constructor(
    private dealerMasterPagePresenter: DealerMasterPagePresenter,
  ) { }

  ngOnInit() {
    this.dealeraddressForm = this.dealerMasterPagePresenter.dealerAddress;
    console.log('this.dealeraddressForm_Parent', this.dealeraddressForm);
  }
}
