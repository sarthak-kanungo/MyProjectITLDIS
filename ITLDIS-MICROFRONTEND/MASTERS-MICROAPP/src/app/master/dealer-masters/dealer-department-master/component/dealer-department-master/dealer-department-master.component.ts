import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { OperationsUtil, Operation } from 'src/app/utils/operation-util';
import { DealerDepartmentMasterPagePresenter } from '../dealer-department-master-page/dealer-department-master-page.presenter';
import { DepartmentNameCreateDropdown } from '../../domain/dealer-department-master-domain';
import { DealerDepartmentMasterService } from './dealer-department-master.service';

@Component({
  selector: 'app-dealer-department-master',
  templateUrl: './dealer-department-master.component.html',
  styleUrls: ['./dealer-department-master.component.scss'],
  providers: [DealerDepartmentMasterService]
})
export class DealerDepartmentMasterComponent implements OnInit {

  dealerDetails: FormGroup
  isView: boolean;
  isEdit: boolean;
  isCreate: boolean;

  isShowPcrButton: boolean

  departments: DepartmentNameCreateDropdown

  constructor(
    private dealerDepartmentMasterPagePresenter: DealerDepartmentMasterPagePresenter,
    private activityRoute: ActivatedRoute,
    private dealerDepartmentMasterService: DealerDepartmentMasterService
  ) { }

  ngOnInit() {
    this.dealerDetails = this.dealerDepartmentMasterPagePresenter.dealerMasterCreateForm
    this.dealerDepartmentMasterPagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.viewOrEditOrCreate()
    //this.getDealerDropDown()
    this. routerDepartmentParams()
  }

  private viewOrEditOrCreate() {
    if (this.dealerDepartmentMasterPagePresenter.operation === Operation.VIEW) {

      this.dealerDepartmentMasterPagePresenter.createDealerDepartmentMasterForm.disable()
      this.isView = true
    }
    else if (this.dealerDepartmentMasterPagePresenter.operation === Operation.EDIT) {
      this.isEdit=true
    } else {
      this.dealerDepartmentMasterPagePresenter.operation === Operation.CREATE
    }
  }

  routerDepartmentParams() {
    this.activityRoute.queryParamMap.subscribe(param => {
      console.log('param--',param);
      console.log('param_has--',param.get('id'));
      if (param.has('id')) {
				if (this.isEdit== true) {
					this.viewDepartmentDetails(param.get('id'))
					this.isShowPcrButton = true
				}
				if (this.isView) {
					this.viewDepartmentDetails(param.get('id'))
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


  // getDealerDropDown() {
  //   this.dealerDepartmentMasterService.getDepartmentNameCreateDropdown().subscribe(res => {
  //     console.log("res ", res);
  //     this.departments = res['result']
  //   })
  // }

  checkDuplicateDepartmentCode(event){
    let value=event.toUpperCase()
    this.dealerDetails.get('departmentCode').setValue(event.toUpperCase());
    this.dealerDepartmentMasterService.checkDepartmentCode(value).subscribe(res=>{
      if (res===undefined || res===null) {}
      else if (res.toUpperCase()===value.toUpperCase()) {
          this.dealerDetails.get('departmentCode').setErrors({
            departmentCodeErroe:'departmentCodeErroe'
          })
        }
    })
  }

  checkDuplicateDepartmentName(event:string){
    this.dealerDepartmentMasterService.checkDepartmentName(event).subscribe(res=>{
      if (res===undefined || res===null) {}
      else if (res.toUpperCase()===event.toUpperCase()) {
          this.dealerDetails.get('departmentName').setErrors({
            departmentNameErroe:'departmentNameErroe'
          })
        }
    })
  }

  viewDepartmentDetails(id:any){
    this.dealerDepartmentMasterService.viewDepartment(id).subscribe(res=>{
      console.log('viewDepartmentDetails--',res);
      this.dealerDetails.get('departmentCode').patchValue(res.departmentCode)
      this.dealerDetails.get('departmentName').patchValue(res.departmentName)
    })
  }


}
