import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { SapCommonWebService } from '../../service/sap-common-web.service';
import { ActivityType, TargetedProduct, ActivityNo, ActivityStatus } from '../../domain/sap.domain';
import { Observable } from 'rxjs';
import { SapSearchWebService } from './sap-search-web.service';
import { MatDatepickerInput } from '@angular/material';
import { JobCardSearchWebService } from 'src/app/service/customer-service/job-card/component/job-card-search/job-card-search-web.service';

@Component({
  selector: 'app-sap-search',
  templateUrl: './sap-search.component.html',
  styleUrls: ['./sap-search.component.scss'],
  providers: [SapSearchWebService,JobCardSearchWebService]
})
export class SapSearchComponent implements OnInit {

  @Input() searchSapForm : FormGroup
  @Input() isAdvanceSearch:boolean
  activityType: ActivityType
  targetedProduct: TargetedProduct
  ActivityStatus: ActivityStatus
  activityNoList$: Observable<Array<ActivityNo>>
  minDate: Date;
  newdate= new Date()
  maxDate: Date;
  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr:any[] = []
  orgHierarchyId:number;
  loginUser:any;
  kaiUser:boolean=false;
  constructor(
    private sapCommonWebService : SapCommonWebService,
    private sapSearchWebService : SapSearchWebService,
   private hierService:JobCardSearchWebService
  ) { }

  ngOnInit() {
    this.maxDate= this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth()-1);
    this.minDate = backDate;
    this.searchSapForm.get('activityProposalFromDate').patchValue(backDate);
    this.searchSapForm.get('activityProposalToDate').patchValue(new Date());
    this.loadAllDropDown()
    this.getActivityNoList();
    this.loginUser = localStorage.getItem('kubotaUser');
    this.loginUser = JSON.parse(JSON.parse(JSON.stringify(this.loginUser)));
    if(this.loginUser.userType!=="dealer"){
      this.getLevelByDeprtment();
      this.kaiUser=true;
    }
  }
  ngAfterViewInit() {
    // this.maxDate= this.newdate
    // let backDate = new Date();
    // backDate.setMonth(this.newdate.getMonth()-1);
    // this.minDate = backDate;
    // this.searchSapForm.get('activityProposalFromDate').patchValue(backDate);
    // this.searchSapForm.get('activityProposalToDate').patchValue(new Date());

  }
  loadAllDropDown() {
    this.sapCommonWebService.getAllActivityType().subscribe(response => {
      this.activityType = response
    })
    this.sapCommonWebService.getAllProduct().subscribe(response => {
      this.targetedProduct = response
    })
    this.sapSearchWebService.getServiceActivityProposalStatus().subscribe(response => {
      this.ActivityStatus = response
    })
  }

  private getActivityNoList() {
      this.searchSapForm.get('activityNumber').valueChanges.subscribe(valueChange => {
        if (valueChange) {
          const activityNo = typeof valueChange == 'object' ? valueChange.activityNumber : valueChange
          this.autoActivityNo(activityNo)
        }
      })
  }
  autoActivityNo(activityNo: string) {
    this.activityNoList$ = this.sapSearchWebService.autoCompleteActivityNo(activityNo)
  }
  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate)
        this.maxDate = this.newdate;
      else
        this.maxDate = maxDate;
      if (this.searchSapForm.get('activityProposalToDate').value > this.maxDate)
        this.searchSapForm.get('activityProposalToDate').patchValue(this.maxDate);
    }
  }
  getLevelByDeprtment() {
    if(this.controlsArr.length>0){
      this.controlsArr.forEach(controlName => {
        this.searchSapForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.hierService.getOrgLevelByHODept("SE").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.searchSapForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
          this.controlsArr.push(obj.LEVEL_CODE);
        })
        this.getLevelsForDept(this.orgHierLevelList[0].LEVEL_ID)
      }
    })
  }

  
  getLevelsForDept(levelId) {
    let orgHierId = 0
    this.hierService.getOrgLevelHierForParent(levelId, orgHierId+"").subscribe(res => {
      this.childArray = res['result']
      this.parentArray[0]=this.childArray;
    })
  }

  selectLevels(salesHoId, index) {
    let orgHierId = salesHoId.value.org_hierarchy_id;
    this.orgHierarchyId = orgHierId;
     this.searchSapForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
    this.hierService.getOrgLevelHierForParent(salesHoId.value.child_level, orgHierId).subscribe(res => {
      this.childArray = res['result'];
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
      }
    })
  }

}
