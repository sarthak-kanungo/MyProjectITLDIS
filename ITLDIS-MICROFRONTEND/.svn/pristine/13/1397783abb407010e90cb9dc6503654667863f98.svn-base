import { Component, OnInit, ChangeDetectionStrategy, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import { ServiceActivityReportCommonWebService } from '../../service/service-activity-report-common-web.service';
import { AutoActivityNo, DropDownActivityType, TargetedProductDropDown, ActivityEffectiveness } from '../../domain/service-activity-report.domain';
import { ServiceActivityReportSearchWebService } from './service-activity-report-search-web.service';
import { MatDatepickerInput } from '@angular/material';
import { JobCardSearchWebService } from 'src/app/service/customer-service/job-card/component/job-card-search/job-card-search-web.service';

@Component({
  selector: 'app-service-activity-report-search',
  templateUrl: './service-activity-report-search.component.html',
  styleUrls: ['./service-activity-report-search.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [ServiceActivityReportSearchWebService,JobCardSearchWebService]
})
export class ServiceActivityReportSearchComponent implements OnInit {

  @Input() public searchServiceActivityReportForm: FormGroup;
  @Input() isAdvanceSearch: boolean
  activityNoList$: Observable<Array<AutoActivityNo>>
  activityTypes: DropDownActivityType
  targetedProduct: TargetedProductDropDown
  Status: string[] = [
    'Generated', 'Approved', 'Rejected', 
  ];
  activityEffectiveness: ActivityEffectiveness
  minDate: Date;
  newdate= new Date()
  maxDate: Date
  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr:any[] = []
  orgHierarchyId:number;
  loginUser:any;
  kaiUser:boolean=false;
  constructor(
    private serviceActivityReportCommonWebService: ServiceActivityReportCommonWebService,
    private serviceActivityReportSearchWebService: ServiceActivityReportSearchWebService,
    private hierService:JobCardSearchWebService
  ) { }

  ngOnInit() {
    this.maxDate= this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth()-1);
    this.minDate = backDate;
    this.searchServiceActivityReportForm.get('activityDateFrom').patchValue(backDate);
    this.searchServiceActivityReportForm.get('activityDateTo').patchValue(new Date());
    this.getActivityNoList()
    this.loadAllDropDownForAcrtivityReport()
    this.getLevelByDeprtment();
  }
  ngAfterViewInit() {
    // this.maxDate= this.newdate
    // let backDate = new Date();
    // backDate.setMonth(this.newdate.getMonth()-1);
    // this.minDate = backDate;
    // console.log(this.minDate,'mindata')
    // this.searchServiceActivityReportForm.get('activityDateFrom').patchValue(backDate);
    // this.searchServiceActivityReportForm.get('activityDateTo').patchValue(new Date());

  }
  loadAllDropDownForAcrtivityReport() {
    this.serviceActivityReportSearchWebService.getAllActivityType().subscribe(response => {
      this.activityTypes = response
    })
    this.serviceActivityReportSearchWebService.getAllProduct().subscribe(response => {
      this.targetedProduct = response
    })
    this.serviceActivityReportCommonWebService.getActivityEffectiveness().subscribe(response => {
      this.activityEffectiveness = response
    })
  }

  private getActivityNoList() {
    this.searchServiceActivityReportForm.get('activityNumber').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const activityNo = typeof valueChange == 'object' ? valueChange.activityNumber : valueChange
        this.autoActivityNo(activityNo)
      }
    })
  }
  autoActivityNo(activityNo: string) {
    this.activityNoList$ = this.serviceActivityReportSearchWebService.getActivityNumberForSearch(activityNo)
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
      if (this.searchServiceActivityReportForm.get('activityDateTo').value > this.maxDate)
        this.searchServiceActivityReportForm.get('activityDateTo').patchValue(this.maxDate);
    }
  }
  getLevelByDeprtment() {
    if(this.controlsArr.length>0){
      this.controlsArr.forEach(controlName => {
        this.searchServiceActivityReportForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.hierService.getOrgLevelByHODept("SE").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.searchServiceActivityReportForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
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
    console.log(orgHierId,'orgHierId')
    this.orgHierarchyId = orgHierId;
     this.searchServiceActivityReportForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
    this.hierService.getOrgLevelHierForParent(salesHoId.value.child_level, orgHierId).subscribe(res => {
      this.childArray = res['result'];
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
      }
    })
  }
}
