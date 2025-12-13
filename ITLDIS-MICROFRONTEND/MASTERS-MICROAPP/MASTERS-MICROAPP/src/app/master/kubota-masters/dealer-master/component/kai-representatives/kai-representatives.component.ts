import { Component, OnInit } from '@angular/core';
import { DealerMasterPagePresenter } from '../dealer-master-create/dealer-master-create.presenter';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-kai-representatives',
  templateUrl: './kai-representatives.component.html',
  styleUrls: ['./kai-representatives.component.scss']
})
export class KaiRepresentativesComponent implements OnInit {

  kaiRepresentativeForm: FormGroup;
  employeecodes: string[] = [
    '9166', '9167', '9168'  
  ];

  depots: string[] = [
    'Chennai', 'Pune', 'Odisha'  
  ];
  constructor(
    private dealerMasterPagePresenter: DealerMasterPagePresenter,
  ) { }

  ngOnInit() {
  this.kaiRepresentativeForm = this.dealerMasterPagePresenter.kaiRepresentative;
    
  }

}
