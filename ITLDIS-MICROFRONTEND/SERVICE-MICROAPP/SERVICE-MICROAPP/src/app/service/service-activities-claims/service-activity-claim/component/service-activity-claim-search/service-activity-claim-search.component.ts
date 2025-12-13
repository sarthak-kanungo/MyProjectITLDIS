import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDatepickerInput } from '@angular/material';
import { Observable } from 'rxjs';
import { JobCardSearchWebService } from 'src/app/service/customer-service/job-card/component/job-card-search/job-card-search-web.service';
import { AutoCompActivityNo, DropdownActivityType, ActivityEffectivenessDropDown, ActivityClaimNo } from '../../domain/service-activity-claim.domain';
import { ServiceActivityClaimSearchWebService } from './service-activity-claim-search-web.service';

@Component({
  selector: 'app-service-activity-claim-search',
  templateUrl: './service-activity-claim-search.component.html',
  styleUrls: ['./service-activity-claim-search.component.scss'],
  providers:[ServiceActivityClaimSearchWebService, JobCardSearchWebService]
})
export class ServiceActivityClaimSearchComponent implements OnInit {

  @Input() searchServiceActivityClaimForm : FormGroup
  @Input() isAdvanceSearch : boolean
  activityNoList$: Observable<Array<AutoCompActivityNo>>
  activityClaimNoList$: Observable<Array<ActivityClaimNo>>
  activityTypes: DropdownActivityType
  Claimstatus: any[] = [];
  activityEffectiveness: ActivityEffectivenessDropDown
  minDate: Date;
  newdate= new Date()
  maxDate: Date;
  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr:any[] = []
  orgHierarchyId:number
  loginUser;
  dealercodeList;
  constructor(
    private serviceActivityClaimSearchWebService : ServiceActivityClaimSearchWebService,
    private jobCardSearchWebService : JobCardSearchWebService
  ) { }

  ngOnInit() {
    
  this.loginUser = localStorage.getItem('itldisUser');
  this.loginUser = JSON.parse(JSON.parse(JSON.stringify(this.loginUser)));

    this.getActivityNoList()
    this.loadActivityTypeDropDown()
    this.getActivityClaimNoList()
    if(this.loginUser.userType!='dealer'){
      this.getLevelByDeprtment();
    }
  }
  ngAfterViewInit() {
    this.maxDate= this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth()-1);
    this.minDate = backDate;
    this.searchServiceActivityClaimForm.get('fromDate').patchValue(backDate);
    this.searchServiceActivityClaimForm.get('toDate').patchValue(new Date());

    this.searchServiceActivityClaimForm.controls.dealerCode.valueChanges.subscribe((res) => {
      this.searchServiceActivityClaimForm.controls.dealerCode.setErrors(null);
      if (res && typeof res != 'object') {
        this.searchServiceActivityClaimForm.controls.dealerCode.setErrors({'selectFromList':'Select From List'});
        this.jobCardSearchWebService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response;
        })
      }
    });

  }
  loadActivityTypeDropDown() {
    this.serviceActivityClaimSearchWebService.getAllActivityType().subscribe(response => {
      this.activityTypes = response
    })
    this.serviceActivityClaimSearchWebService.getActivityEffectiveness().subscribe(response => {
      this.activityEffectiveness = response
    })
    this.serviceActivityClaimSearchWebService.getLookup("CLAIM_STATUS").subscribe(response => {
      this.Claimstatus = response['result'];
    });
  }

  private getActivityNoList() {
    this.searchServiceActivityClaimForm.get('activityNumber').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const activityNo = typeof valueChange == 'object' ? valueChange.activityNumber : valueChange
        this.autoActivityNo(activityNo)
      }
    })
  }
  autoActivityNo(activityNo: string) {
    this.activityNoList$ = this.serviceActivityClaimSearchWebService.getActivityNumberForSearch(activityNo)
  }

  private getActivityClaimNoList() {
    this.searchServiceActivityClaimForm.get('activityClaimNumber').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const claimNo = typeof valueChange == 'object' ? valueChange.claimNumber : valueChange
        this.autoActivityClaimNo(claimNo)
      }
    })
  }
  autoActivityClaimNo(claimNo: string) {
    this.activityClaimNoList$ = this.serviceActivityClaimSearchWebService.getActivityClaimNumber(claimNo)
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
      if (this.searchServiceActivityClaimForm.get('toDate').value > this.maxDate)
        this.searchServiceActivityClaimForm.get('toDate').patchValue(this.maxDate);
    }
  }

  
  getLevelByDeprtment() {
    if(this.controlsArr.length>0){
      this.controlsArr.forEach(controlName => {
        this.searchServiceActivityClaimForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.jobCardSearchWebService.getOrgLevelByHODept("SE").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.searchServiceActivityClaimForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
          this.controlsArr.push(obj.LEVEL_CODE);
        })
        this.getLevelsForDept(this.orgHierLevelList[0].LEVEL_ID)
      }
    })
  }

  
  getLevelsForDept(levelId) {
    let orgHierId = 0
    this.jobCardSearchWebService.getOrgLevelHierForParent(levelId, orgHierId+"").subscribe(res => {
      this.childArray = res['result']
      this.parentArray[0]=this.childArray;
    })
  }

  selectLevels(salesHoId, index) {
    let orgHierId = salesHoId.value.org_hierarchy_id;
    this.orgHierarchyId = orgHierId;
    this.searchServiceActivityClaimForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
    this.jobCardSearchWebService.getOrgLevelHierForParent(salesHoId.value.child_level, orgHierId).subscribe(res => {
      this.childArray = res['result'];
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
      }
    })
  }

  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }

}
