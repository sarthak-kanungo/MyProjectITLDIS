import { Component, OnInit, Input } from '@angular/core';
import { FormArray } from '@angular/forms';
import { WpdcPagePresenter } from '../wpdc-page/wpdc-page.presenter';

@Component({
  selector: 'app-wpdc-material-details',
  templateUrl: './wpdc-material-details.component.html',
  styleUrls: ['./wpdc-material-details.component.scss']
})
export class WpdcMaterialDetailsComponent implements OnInit {
  @Input() materialDetailForm: FormArray;
  materialDetailHeading = ['Select', 'Warranty Claim No', 'Item No', 'Item Description', 'HSN Code', 'Approx. Rate', 'Quantity', 'Value', 'Total Amount']
  // , 'GST%', 'GST Amount'
  totalValue: number;
  totalAmount: number;
  totalGstAmount: number;
  constructor(
    private wpdcPagePresenter: WpdcPagePresenter,
  ) { }

  ngOnInit() {
    this.totalAmount = this.wpdcPagePresenter.totalAmount;
    this.totalValue = this.wpdcPagePresenter.totalValue;
    this.totalGstAmount = this.wpdcPagePresenter.totalGstAmount;
  }

}
