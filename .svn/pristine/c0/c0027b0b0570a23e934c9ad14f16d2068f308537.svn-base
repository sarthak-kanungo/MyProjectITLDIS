import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { branchTransferIssuePresenter } from '../create-branch-transfer-issue/branch-transfer-issue-presenter';

@Component({
  selector: 'app-branch-transfer-issue-item-details',
  templateUrl: './branch-transfer-issue-item-details.component.html',
  styleUrls: ['./branch-transfer-issue-item-details.component.scss']
})
export class BranchTransferIssueItemDetailsComponent implements OnInit {
   @Input() itemForm:FormArray
  items:FormArray

  orderForm1: FormArray;
  constructor(private fb:FormBuilder,private presenter:branchTransferIssuePresenter,private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    // this.itemForm=this.presenter.addItems;    
  }
  private checkFormOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation == Operation.CREATE) {
      this.addItem();
    } else if (this.presenter.operation == Operation.VIEW) {
      // this.isView = true;
    } 
  }
 

  addItem() {  
    this.presenter.addRowInImplement();   
  } 
  deleteRow(){
    
  }

}
