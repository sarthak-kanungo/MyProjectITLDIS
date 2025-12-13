import { RoleValues, RoleMaster, MenuNode } from './../../domains/role-master';
import { RoleMasterWebService } from './../../services/role-master-web-service';
import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { RoleMasterPresenter } from '../../services/role-master-presenter';
import { MatSelectChange } from '@angular/material';
import { Role } from '../../domains/role-master';
import { map, single } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';

@Component({
  selector: 'app-create-new-role',
  templateUrl: './create-new-role.component.html',
  styleUrls: ['./create-new-role.component.scss'],
  providers: [RoleMasterPresenter, RoleMasterWebService],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CreateNewRoleComponent implements OnInit {
  applicables: object[] = [
    { id: 1, applicable: 'itldis' }, { id: 1, applicable: 'Dealer' }
  ];
  labelPosition = 'before';
  isEdit: boolean = false;
  isView: boolean = false;
  form: FormGroup
  tabs: Array<MenuNode>
  role$: BaseDto<Array<RoleValues>>;
  paramId: any;
  private _operation: string
  isShowPcrButton: boolean

  constructor(
    private presenter: RoleMasterPresenter,
    private api: RoleMasterWebService,
    private toastr: ToastrService,
    private activityRoute: ActivatedRoute,
    private router: Router,

  ) {
    this.activityRoute.queryParamMap.subscribe(param => {
      if (param.has('id')) {
        this.paramId = param.get('id');
      }
    });
  }

  onSelectApplicableTo(event: MatSelectChange) {
    console.log(event)
    this.lessOptimizedMutation(event.value['applicable'])
  }

  private lessOptimizedMutation(applicable: string) {
    this.api.getAssignedFunctionalityToRole(
      applicable,
      this.presenter.getRoleName()
    ).subscribe(response => {
       this.tabs = response.result;
       this.presenter.menuSubject.next({menuList:this.tabs, selectedMenu:[]});
     }
    )
  }

  // private optimizedImmutation(applicable: string) {
  //   this.api.getAssignedFunctionalityToRole(
  //     applicable,
  //     this.presenter.getRoleName()
  //   ).pipe(
  //     map(dto => {
  //       return dto.result.map(
  //         tabs => tabs.functionalityMasters.map(master => {
  //           master.isSpoiled = master.accessibleFlag
  //           return master
  //         })
  //       )
  //     })
  //   ).subscribe()
  // }
  ngOnInit() {
    this.operation = OperationsUtil.operation(this.activityRoute)
    this.form = this.presenter.buildForm()
    //this.formValueChanges();
    this.viewOrEditOrCreate()
    this.checkRouterParamsForDealer()
    // this.form.get('roleName').valueChanges.subscribe(value => {
    //   this.api.getRoleCode(value).subscribe(response => {
    //     this.role$ = response as BaseDto<Array<RoleValues>>
    //     if(this.role$.result && this.role$.result.length>0){
    //       this.form.get('roleName').setErrors({'RoleNameError':'Same Role already exist'})
    //     }else{
    //       this.form.get('roleName').setErrors(null);
    //     }
    //   });
    // })
  }
  set operation(type: string) {
    this._operation = type
  }
  get operation() {
    return this._operation
  }

  onBlurRoleName(event){
    if(event.target.value){
      this.api.getRoleCode(event.target.value).subscribe(response => {
          this.role$ = response as BaseDto<Array<RoleValues>>
          if(this.role$.result && this.role$.result.length>0){
            this.form.get('roleName').setErrors({'RoleNameError':'Same Role already exist'})
          }else{
            this.form.get('roleName').setErrors(null);
          }
      })
    }
  }
  // formValueChanges() {
  //   this.form.get('roleCode').valueChanges.subscribe(value => {
  //     if (value) {
  //       console.log("roleCode---->", value)
  //       let roleCode = typeof value == "object" ? value.roleCode : value;
  //       this.autoRoleCode(roleCode);
  //     }
  //     if (value !== null && typeof value !== "string") {
  //       this.form.get("roleCode").setErrors({
  //         selectFromList: "Please select from list",
  //       });
  //     }
  //   });

  //   this.form.get('roleName').valueChanges.subscribe(value => {
  //     console.log("value ", value);
  //     if (value) {
  //       console.log("roleName---->", value)
  //       let roleName = typeof value == "object" ? value.roleName : value;
  //       this.autoRoleName(roleName);
  //     }
  //     if (value !== null && typeof value !== "string") {
  //       this.form.get("roleName").setErrors({
  //         selectFromList: "Please select from list",
  //       });
  //     }
  //   });
  // }

  private viewOrEditOrCreate() {
    if (this.operation === Operation.VIEW) {
      this.isView = true
      this.form.disable();
    } else if (this.operation === Operation.EDIT) {
      this.isEdit = true
    }
    else if (this.operation === Operation.CREATE) {
      this.isEdit = false
      this.isView = false
    }
  }

  checkRouterParamsForDealer() {
    this.activityRoute.queryParamMap.subscribe(param => {
      if (param.has('id')) {
        if (this.isEdit == true) {  
          this.viewdealerDetails(param.get('id'))
          this.isShowPcrButton = true
        }
        if (this.isView) {
          this.viewdealerDetails(param.get('id'))
          if (param.get('hasButton') == 'false') {
            this.isShowPcrButton = false
          }
          else if (param.get('hasButton') == 'true') {
            this.isShowPcrButton = true
          }
        }
      }

    })
  }
  viewdealerDetails(id: any) {
    this.api.searchRoleMasterById(id).pipe(
      map(dto => {
        return dto.result
      })
    ).subscribe(tabs => {
      this.tabs = tabs.functionality
      this.form.patchValue(tabs.roleMaster);
      if (tabs.roleMaster.activeStatus == "N") {
        this.form.controls.activeStatus.patchValue(false)
      }else {
        this.form.controls.activeStatus.patchValue(true)
      }
      this.form.controls.applicableTo.patchValue(tabs.roleMaster.applicableTo);
      if (this.isView) {
        this.isView = true;
      }else if (this.isEdit) {
        this.isEdit = true;
        this.form.controls.applicableTo.disable();
        this.form.controls.roleCode.disable();
      }
      let menuSelected:Array<number> = new Array<number>();
      tabs.role && tabs.role.forEach(element => {
        menuSelected.push(element.functionalityMaster.id);
      });
      this.presenter.menuSubject.next({menuList:this.tabs, selectedMenu:menuSelected});
    })
  }

  exitForm() {
    if (this.isEdit || this.isView) {
      this.router.navigate(['../../'], { relativeTo: this.activityRoute });
    } else {
      this.router.navigate(['../'], { relativeTo: this.activityRoute });
    }
  }
  resetForm() {
    this.form.reset();
    this.role$ = null;
    this.tabs = null;
    this.ngOnInit();
  }
  // autoRoleCode(autoRoleCode) {
  //   if (autoRoleCode) {
  //     this.api.getRoleCode(autoRoleCode).subscribe(response => {
  //       this.role$ = response as BaseDto<Array<RoleValues>>
  //     })
  //   }
  // }
  // displayFnForRoleCode(selectedOption: RoleValues) {
  //   if (!!selectedOption && typeof selectedOption === 'string') {
  //     return selectedOption;
  //   }
  //   return selectedOption ? selectedOption['roleCode'] : undefined;
  // }

  // autoRoleName(autoRoleName) {
  //   if (autoRoleName) {
  //     this.api.getRoleCode(autoRoleName).subscribe(response => {
  //       this.role$ = response as BaseDto<Array<RoleValues>>
  //     })
  //   }
  // }
  // displayFnForRoleName(selectedOption: RoleValues) {
  //   if (!!selectedOption && typeof selectedOption === 'string') {
  //     return selectedOption;
  //   }
  //   return selectedOption ? selectedOption['roleName'] : undefined;
  // }
  compareFnApplicableTo(c1: any, c2: any): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.applicable === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.applicable;
    }
  }
  validateForm() {
    this.presenter.fetchSelectedMenuSubject.next([]);
    this.form.markAllAsTouched();
    if(this.form.valid){
      this.submitData();
    }else{
      this.toastr.error("Mandatory filed are required","Mandatory Fields")
    }
  }

  submitData() {
    let formData=this.form.getRawValue();
    let menuData = this.presenter.menuForm.getRawValue();
    
    let role = {} as Role
    role.roleMaster = {} as RoleMaster
    if(formData['applicableTo']['applicable']!=undefined && 
      formData['applicableTo']['applicable']!=null){
        role.roleMaster.applicableTo = formData['applicableTo']['applicable']
    }
    else {
      role.roleMaster.applicableTo = formData['applicableTo']
    }
    role.roleMaster.activeStatus = formData['activeStatus'] ? 'Y' : 'N'
    role.roleMaster.roleName = formData['roleName']
    role.roleMaster.roleCode = formData['roleCode']
    role.roleMaster.id = formData['id']
    role.functionalityMasters = menuData.menuNode;

    // let spreadedFunctionalities = []
    // this.tabs.forEach(tab => spreadedFunctionalities.push(...tab.functionalityMasters.filter(master => master.accessibleFlag && !master.isSpoiled)))
    // role.functionalityMasters = spreadedFunctionalities
    // role.roleMaster.id=this.paramId;
    if(!this.isEdit){
      this.api.submitRoleMaster(role).subscribe(res => {
        if (res) {
          this.toastr.success(res['message'], 'Success');
          if (res['message'] == 'Role assigned to functionality') {
            if (this.isEdit)
              this.router.navigate(['../../'], { relativeTo: this.activityRoute })
            else
              this.router.navigate(['../'], { relativeTo: this.activityRoute })
          }
        }
      }, (error) => {
        this.toastr.error(`Role assigned to functionality Failed`, error)
      })
    } else{
      
      this.api.updateRoleMaster(role).subscribe(res => {
        if (res) {
          this.toastr.success(res['message'], 'Success');
          if (res['message'] == 'Role assigned to functionality') {
            if (this.isEdit)
              this.router.navigate(['../../'], { relativeTo: this.activityRoute })
            else
              this.router.navigate(['../'], { relativeTo: this.activityRoute })
          }
        }
      }, (error) => {
        this.toastr.error(`Role assigned to functionality Failed`, error)
      })
    }

  }
}
