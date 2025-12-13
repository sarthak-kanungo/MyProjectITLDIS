import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { SapPagePresenter } from '../sap-page/sap-page-presenter';
import { HeadsWebService } from './heads-web.service';
import { Heads, ServiceActivityProposalHead, ViewServiceActivityProposal } from '../../domain/sap.domain';
import { Operation } from '../../../../../utils/operation-util';
import { element } from 'protractor';

@Component({
  selector: 'app-heads',
  templateUrl: './heads.component.html',
  styleUrls: ['./heads.component.scss'],
  providers: [HeadsWebService]
})
export class HeadsComponent implements OnInit, OnChanges {

  @Input() headTableForm: FormGroup
  @Input() headsTotalForm: FormGroup
  isActivityType: boolean
  heads: Heads
  total: number
  isEdit: boolean
  isCreate: boolean
  @Input() serviceActivityProposalDetails: ViewServiceActivityProposal
   
  constructor(
    private sapPagePresenter: SapPagePresenter,
    private headsWebService: HeadsWebService,

  ) { }

  ngOnChanges() {
    if (this.serviceActivityProposalDetails) {
      this.total = this.serviceActivityProposalDetails.proposedBudget
      this.totalAmount()
    }
  }

  ngOnInit() {
    this.valueChangesActivityType()
    this.viewOrEditOrCreate()
  }


  private viewOrEditOrCreate() {
    if (this.sapPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true
    } else if (this.sapPagePresenter.operation === Operation.CREATE) {
      this.isCreate = true
      this.addRow()
    }
  }

  valueChangesActivityType() {
    this.sapPagePresenter.activityTrainingProposal.get('activityType').valueChanges.subscribe(value => {
      if (value.activityType === 'FDFL' || value.activityType === 'USS') {
        this.isActivityType = false
      } else {
        this.isActivityType = true
        this.headsWebService.getAllHeadsByActivityTypeId(value.id).subscribe(response => {
          this.heads = response
        })
      }
      this.totalAmount()
    })
  }

  totalAmount() {
    const headsDetails = this.headTableForm.get('dataTable') as FormArray
    headsDetails.controls.forEach((ele: FormGroup) => {
      ele.get('amount').valueChanges.subscribe(value => {
        this.total = this.sapPagePresenter.calculationForTotalHeads()
        this.headsTotalForm.get('totalAmount').patchValue(this.total)
        this.sapPagePresenter.calculationForProposedBudget();
      })
    })
  }

  addRow(list?: ServiceActivityProposalHead) {
    this.sapPagePresenter.addRow(list)
    this.totalAmount()
  }

  deleteRow() {
    this.sapPagePresenter.deleteRow()
    this.total = this.sapPagePresenter.calculationForTotalHeads()
    this.headsTotalForm.get('totalAmount').patchValue(this.total)
    this.sapPagePresenter.calculationForProposedBudget();
  }

  compareFnHeads(c1: Heads, c2: ServiceActivityProposalHead): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.head === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.head;
    }
    return c1 && c2 ? c1.head === c2.head : c1 === c2;
  }

}
