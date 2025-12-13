import { Component, OnInit, Input, OnChanges, SimpleChanges, AfterViewInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { itldisUserMasterDomain } from '../../domain/itldis-user-domain';
import { itldisUserMasterCreatePagePresenter } from '../itldis-user-create-page/itldis-user-create-page.presenter';
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
    private itldisUserMasterCreatePagePresenter:itldisUserMasterCreatePagePresenter,
    private activityRoute: ActivatedRoute,
    private createNewUserService: CreateNewUserService,
    private formBuilder: FormBuilder

  ) { }

  ngOnChanges() {    
    this.viewOrEditOrCreate();
  }

  ngOnInit() {
    this.itldisUserMasterCreatePagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.viewOrEditOrCreate()
  }

  private viewOrEditOrCreate() {
    if (this.itldisUserMasterCreatePagePresenter.operation === Operation.VIEW) {      
      this.itldisUserMasterCreatePagePresenter.createUserMasterMainForm.disable()
      this.patchValue(this.viewId);
      
    } else if (this.itldisUserMasterCreatePagePresenter.operation === Operation.EDIT) {
      this.patchValue(this.viewId);
    }
    else if (this.itldisUserMasterCreatePagePresenter.operation === Operation.CREATE) {
      if (this.getFunctionData) {            
        this.createNewUserService.getAssignRoleDropDown().subscribe(role => {
          role['result'].forEach((element: itldisUserMasterDomain)=> {
            this.itldisUserMasterCreatePagePresenter.addRow(element);
          });
        })
      }      
    }
  }

  patchValue(viewId){
    if (viewId) {
      this.createNewUserService.getAssignFunctions(viewId).subscribe(functions => {
        functions['result'].forEach((element: itldisUserMasterDomain)=> {
          this.itldisUserMasterCreatePagePresenter.addRow(element);
        });
      })
    }
  }

  assignFunction(check: boolean, value: FormGroup){
    value.controls.assignFunction.patchValue(check['checked']);
    console.log('value----', value.controls.assignFunction);
    
  }

}
