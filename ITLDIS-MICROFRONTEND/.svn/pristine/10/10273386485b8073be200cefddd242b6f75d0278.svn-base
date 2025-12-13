import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DealerMasterPagePresenter } from '../dealer-master-create/dealer-master-create.presenter';

@Component({
  selector: 'app-dealer-type',
  templateUrl: './dealer-type.component.html',
  styleUrls: ['./dealer-type.component.scss']
})
export class DealerTypeComponent implements OnInit {
  stars: string[] = [
    '5 Star', '4 Star', '3 Star', 'None'  
  ];
  dealerTypeForm: FormGroup;
  constructor( 
    private dealerMasterPagePresenter: DealerMasterPagePresenter,
  ) { }

  ngOnInit() {
    this.dealerTypeForm = this.dealerMasterPagePresenter.dealerType;
  }


}
