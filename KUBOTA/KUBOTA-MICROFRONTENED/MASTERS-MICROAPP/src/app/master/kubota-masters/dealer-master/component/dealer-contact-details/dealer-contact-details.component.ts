import { Component, OnInit } from '@angular/core';
import { DealerMasterPagePresenter } from '../dealer-master-create/dealer-master-create.presenter';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-dealer-contact-details',
  templateUrl: './dealer-contact-details.component.html',
  styleUrls: ['./dealer-contact-details.component.scss']
})
export class DealerContactDetailsComponent implements OnInit {

  dealerContactDetailForm: FormGroup;
  codes: string[] = [
    'EMPDEALER01', 'EMPDEALER02', 'EMPDEALER03'  
   ];
  constructor(
    private dealerMasterPagePresenter: DealerMasterPagePresenter,
  ) { }

  ngOnInit() {
    this.dealerContactDetailForm = this.dealerMasterPagePresenter.dealerContactDetail;
    console.log('this.dealerContactDetailForm', this.dealerContactDetailForm)
  }

}
