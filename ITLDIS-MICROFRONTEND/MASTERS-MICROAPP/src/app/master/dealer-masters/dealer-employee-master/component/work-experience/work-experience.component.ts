import { Component, OnInit, Input } from '@angular/core';
import { EmployeeMasterCreatePagePresenter } from '../employee-master-create-page/employee-master-page.presenter';
import { FormGroup, FormArray } from '@angular/forms';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-work-experience',
  templateUrl: './work-experience.component.html',
  styleUrls: ['./work-experience.component.scss']
})
export class WorkExperienceComponent implements OnInit {
  @Input() employeeWorkExperienceForm: FormGroup
  isView: boolean
  today = new Date();

  constructor(
    private employeeMasterCreatePagePresenter: EmployeeMasterCreatePagePresenter,
    private activityRoute: ActivatedRoute,

  ) { }

  ngOnInit() {
    this.employeeMasterCreatePagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.addRow()
    this.viewOrEditOrCreate()
  }
   private viewOrEditOrCreate() {
    if (this.employeeMasterCreatePagePresenter.operation === Operation.VIEW) {
      this.employeeMasterCreatePagePresenter.employeemasterForm.disable()
      this.isView=true
    } else if (this.employeeMasterCreatePagePresenter.operation === Operation.EDIT) {
      console.log("edit");
    }
    else if (this.employeeMasterCreatePagePresenter.operation === Operation.CREATE) {
      console.log("create");
      
    }
  }
  addRow() {
    this.employeeMasterCreatePagePresenter.addRows()
  }
  deleteRow() {
    this.employeeMasterCreatePagePresenter.deleteRows()
  }

}
