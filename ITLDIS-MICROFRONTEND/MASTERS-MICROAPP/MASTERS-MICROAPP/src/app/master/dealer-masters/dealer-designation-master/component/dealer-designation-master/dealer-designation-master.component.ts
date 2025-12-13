import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { OperationsUtil, Operation } from 'src/app/utils/operation-util';
import { DeptDropdownForDesignation } from '../../domain/dealer-designation-master-domain';
import { DealerDesignationMasterPagePresenter } from '../dealer-designation-master-page/dealer-designation-master-page.presenter';
import { DealerDesignationMasterService } from './dealer-designation-master.service';

@Component({
  selector: 'app-dealer-designation-master',
  templateUrl: './dealer-designation-master.component.html',
  styleUrls: ['./dealer-designation-master.component.scss'],
  providers: [DealerDesignationMasterService]

})
export class DealerDesignationMasterComponent implements OnInit {

  dealerDetails: FormGroup
  isView: boolean;
  isEdit: boolean;
  isCreate: boolean;

  isShowPcrButton: boolean

  // departmentsforDealer:Array<DeptDropdownForDesignation>
  departmentsforDealer:DeptDropdownForDesignation

  constructor(
    private dealerDesignationMasterPagePresenter: DealerDesignationMasterPagePresenter,
    private dealerDesignationMasterService: DealerDesignationMasterService,
    private activityRoute: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.dealerDesignationMasterPagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.dealerDetails = this.dealerDesignationMasterPagePresenter.dealerMasterTableForm
    this.viewOrEditOrCreate()
    this.getDepartmentsforDealer()
    this.routerDesignationParams()
  }

  private viewOrEditOrCreate() {
    if (this.dealerDesignationMasterPagePresenter.operation === Operation.VIEW) {

      this.dealerDesignationMasterPagePresenter.createDealerDesignationMasterForm.disable()
      this.isView = true
    }
    else if (this.dealerDesignationMasterPagePresenter.operation === Operation.EDIT) {
      this.isEdit=true
    } else {
      this.dealerDesignationMasterPagePresenter.operation === Operation.CREATE
    }
  }

  routerDesignationParams() {
    this.activityRoute.queryParamMap.subscribe(param => {
      console.log('param--',param);
      console.log('param_has--',param.get('id'));
      if (param.has('id')) {
				if (this.isEdit== true) {
					this.viewDesignationDetails(param.get('id'))
					this.isShowPcrButton = true
				}
				if (this.isView) {
					this.viewDesignationDetails(param.get('id'))
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



  checkDuplicateDesignationCode(event){
    let value=event.toUpperCase()
    this.dealerDetails.get('designationCode').setValue(event.toUpperCase());
    this.dealerDesignationMasterService.checkDesignationCode(value).subscribe(res=>{
     if (res===undefined || res===null) {}
     else if (res.toUpperCase()===value.toUpperCase()) {
        this.dealerDetails.get('designationCode').setErrors({
          designationCodeErroe:'designationCodeErroe'
        })
      }
    })
  }

  checkDuplicateDesignationName(event:string){
    let value=event.toUpperCase()
    //this.dealerDetails.get('designationName').setValue(event.toUpperCase());
    this.dealerDesignationMasterService.checkDesignationName(value).subscribe(res=>{
     if (res===undefined || res===null) {}
     else if (res.toUpperCase()===value.toUpperCase()) {
        this.dealerDetails.get('designationName').setErrors({
          designationNameErroe:'designationNameErroe'
        })
      }
    })
  }

  getDepartmentsforDealer() {
    this.dealerDesignationMasterService.getDepartment().subscribe(res=>{
      this.departmentsforDealer=res['result']
    })
  }

  viewDesignationDetails(id:any){
    this.dealerDesignationMasterService.viewDesignation(id).subscribe(res=>{
      console.log('viewDesignationDetails--',res[1].departmentName);
      this.dealerDetails.get('designationCode').patchValue(res[0].designationCode)
      this.dealerDetails.get('designationName').patchValue(res[0].designation)
     // this.dealerDetails.get('department').setValue(res[1].departmentName)
      this.dealerDetails.get('department').patchValue
    })
  }

}
