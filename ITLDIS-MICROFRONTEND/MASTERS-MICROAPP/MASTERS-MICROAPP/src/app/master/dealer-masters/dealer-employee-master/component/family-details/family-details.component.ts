import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { EmployeeMasterCreatePagePresenter } from '../employee-master-create-page/employee-master-page.presenter';
import { ActivatedRoute } from '@angular/router';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { FamilyDetailsService } from './family-details.service';
import { DealerMasterDropdown } from '../../domain/dealer-employee-domain';

@Component({
  selector: 'app-family-details',
  templateUrl: './family-details.component.html',
  styleUrls: ['./family-details.component.scss'],
  providers: [FamilyDetailsService]
})
export class FamilyDetailsComponent implements OnInit {
  isView: boolean;
  titles:string[]=[]
@Input() employeeFamilyDetailsForm:FormGroup


  relationship: DealerMasterDropdown
  constructor(
    private employeeMasterCreatePagePresenter:EmployeeMasterCreatePagePresenter,
    private activityRoute: ActivatedRoute,
    private familyDetailsService:FamilyDetailsService,

  ) { }

  ngOnInit() {
    this.employeeMasterCreatePagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.viewOrEditOrCreate()
    this.getReleationshipDropdown();
    
  }

  private viewOrEditOrCreate() {
    if (this.employeeMasterCreatePagePresenter.operation === Operation.VIEW) {
      this.addRow()
      this.isView=true
      this.employeeMasterCreatePagePresenter.employeemasterForm.disable()
    } else if (this.employeeMasterCreatePagePresenter.operation === Operation.EDIT) {
      console.log("edit");
    }
    else if (this.employeeMasterCreatePagePresenter.operation === Operation.CREATE) {
      console.log("create");
      this.addRow()
    }
  }

  addRow(){
    this.employeeMasterCreatePagePresenter.addRow()
  }
  deleteRow(){
    this.employeeMasterCreatePagePresenter.deleteRow()
  }

  private getReleationshipDropdown() {
    this.familyDetailsService.getReleationshipDropdown().subscribe(res => {
      this.relationship = res;
    })
  }

}
