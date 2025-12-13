import { Component, OnInit, Input, OnChanges, SimpleChanges, AfterViewInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { KubotaUserMasterDomain } from '../../domain/kubota-user-domain';
import { KubotaUserMasterCreatePagePresenter } from '../kubota-user-create-page/kubota-user-create-page.presenter';
import { ActivatedRoute } from '@angular/router';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { CreateNewUserService } from '../create-new-user/create-new-user.service';

@Component({
  selector: 'app-assign-function',
  templateUrl: './assign-function.component.html',
  styleUrls: ['./assign-function.component.scss'],
  providers: [CreateNewUserService]
})
export class AssignFunctionComponent implements OnInit, OnChanges {

  @Input() functionTableForm: FormGroup;
  @Input() getFunctionData:boolean;
  @Input() viewId
  isView: boolean
  

  constructor(
    private kubotaUserMasterCreatePagePresenter:KubotaUserMasterCreatePagePresenter,
    private activityRoute: ActivatedRoute,
    private createNewUserService: CreateNewUserService,
    private formBuilder: FormBuilder

  ) { }

  ngOnChanges() {    
    this.viewOrEditOrCreate();
  }

  ngOnInit() {
    this.kubotaUserMasterCreatePagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.viewOrEditOrCreate()
  }

  private viewOrEditOrCreate() {
    if (this.kubotaUserMasterCreatePagePresenter.operation === Operation.VIEW) {      
      this.kubotaUserMasterCreatePagePresenter.createUserMasterMainForm.disable()
      this.patchValue(this.viewId);
      
    } else if (this.kubotaUserMasterCreatePagePresenter.operation === Operation.EDIT) {
      this.patchValue(this.viewId);
    }
    else if (this.kubotaUserMasterCreatePagePresenter.operation === Operation.CREATE) {
      if (this.getFunctionData) {            
        this.createNewUserService.getAssignRoleDropDown().subscribe(role => {
          role['result'].forEach((element: KubotaUserMasterDomain)=> {
            this.kubotaUserMasterCreatePagePresenter.addRow(element);
          });
        })
      }      
    }
  }

  patchValue(viewId){
    if (viewId) {
      this.createNewUserService.getAssignFunctions(viewId).subscribe(functions => {
        functions['result'].forEach((element: KubotaUserMasterDomain)=> {
          this.kubotaUserMasterCreatePagePresenter.addRow(element);
        });
      })
    }
  }

  assignFunction(check: boolean, value: FormGroup){
    value.controls.assignFunction.patchValue(check['checked']);
    console.log('value----', value.controls.assignFunction);
    
  }

}
