import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { functionMasterDomain } from '../../domain/function-master-domain';
import { FunctionMasterPagePresenter } from '../function-master-page/function-master-page.presenter';

@Component({
  selector: 'app-function',
  templateUrl: './function.component.html',
  styleUrls: ['./function.component.scss']
})
export class FunctionComponent implements OnInit {
  @Input() functionTableForm: FormGroup
  functionDetails: Array<functionMasterDomain> =
    [{
      function: 'Dealer Sales PO',
      itldis: true,
      dealer: true,
    }]

  constructor(
    private functionMasterPagePresenter: FunctionMasterPagePresenter
  ) { }

  ngOnInit() {
    this.getFunctionDetails()
  }

  getFunctionDetails() {
    this.functionDetails.forEach(data => {
      this.functionMasterPagePresenter.addRow(data)
    })
   
 console.log(" this.functionTableForm.value ",  this.functionTableForm.value);
  }


}
