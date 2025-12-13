import { Component, OnInit } from '@angular/core';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DealerMasterPagePresenter } from '../dealer-master-create/dealer-master-create.presenter';

@Component({
  selector: 'app-dealer-master-details',
  templateUrl: './dealer-master-details.component.html',
  styleUrls: ['./dealer-master-details.component.scss']
})
export class DealerMasterDetailsComponent implements OnInit {
  data: Object;
  status: string[] = [
    'ACTIVE', 'ONHOLD', 'INACTIVE', 
  ];
  firmtypes: string[] = [
    'Proprietorship', 'Partnership', 'Private Limited Company', 'Public Limited Company', 
  ];

  arealevels: string[] = [
    'District1', 'District2','Taluka1','Taluka2', 'State',  
  ];

  allocations: string[] = [
    'District', 'Taluka', 'State',  
  ];
  dealerMasterForm: FormGroup;
  dealerBankForm: FormGroup;
  constructor( 
    private dealerMasterPagePresenter: DealerMasterPagePresenter,
    ) { }

  ngOnInit() {
    this.dealerMasterForm = this.dealerMasterPagePresenter.dealerMaster;
  }
}
