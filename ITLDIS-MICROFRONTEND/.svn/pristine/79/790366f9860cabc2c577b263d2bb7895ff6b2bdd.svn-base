import { Component, OnInit } from '@angular/core';
import { DealerMasterPagePresenter } from '../dealer-master-create/dealer-master-create.presenter';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-dealer-bank',
  templateUrl: './dealer-bank.component.html',
  styleUrls: ['./dealer-bank.component.css']
})
export class DealerBankComponent implements OnInit {
  dealerBankForm: FormGroup;
  constructor(
    private dealerMasterPagePresenter: DealerMasterPagePresenter,
  ) { }

  ngOnInit() {
    this.dealerBankForm = this.dealerMasterPagePresenter.dealerBank;
  }

}
