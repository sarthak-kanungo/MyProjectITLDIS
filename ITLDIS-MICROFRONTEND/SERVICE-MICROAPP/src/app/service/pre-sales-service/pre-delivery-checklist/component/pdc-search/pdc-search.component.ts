import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDatepickerInput } from '@angular/material';
import { Observable } from 'rxjs';
import { MrcSearchWebService } from '../../../machine-receipt-checking/component/mrc-search/mrc-search-web.service';
import { ChassisNo, ChassisNoForSearch } from '../../domain/pdc-domain';
import { PdcSearchWebService } from './pdc-search-web.service';

@Component({
  selector: 'app-pdc-search',
  templateUrl: './pdc-search.component.html',
  styleUrls: ['./pdc-search.component.scss'],
  providers: [PdcSearchWebService, MrcSearchWebService]
})
export class PdcSearchComponent implements OnInit {

  @Input() pdcSearchForm: FormGroup
  chassisNoList$: Observable<Array<ChassisNoForSearch>>
  minDate: Date;
  newdate = new Date()
  maxDate: Date

  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr:any[] = []
  orgHierarchyId:number
  loginUser:any;

  constructor(
    private pdcSearchWebService: PdcSearchWebService,
    private mrcSearchService: MrcSearchWebService
  ) { }

  ngOnInit() {
    this.getChassisNoList()
    this.loginUser = localStorage.getItem('itldisUser');
    this.loginUser = JSON.parse(JSON.parse(JSON.stringify(this.loginUser)))
    if(this.loginUser.userType!='dealer'){
      this.getLevelByDeprtment();
    }
  }
  ngAfterViewInit() {
    
  }
  private getChassisNoList() {
    this.pdcSearchForm.get('chassisNo').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const chassisNo = typeof valueChange == 'object' ? valueChange.code : valueChange
        this.autoChassisNo(chassisNo)
      }
    })

  }

  autoChassisNo(chassisNo: string) {
    this.chassisNoList$ = this.pdcSearchWebService.pdcCreateAutoCompleteChassisNo(chassisNo)
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
      if (this.pdcSearchForm.get('pdcToDate').value > this.maxDate)
        this.pdcSearchForm.get('pdcToDate').patchValue(this.maxDate);
    }
  }

  getLevelByDeprtment() {
    if(this.controlsArr.length>0){
      this.controlsArr.forEach(controlName => {
        this.pdcSearchForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.mrcSearchService.getLevelByDepartment("SE").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.pdcSearchForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
          this.controlsArr.push(obj.LEVEL_CODE);
        })
        this.getLevelsForDept(this.orgHierLevelList[0].LEVEL_ID)
      }
    })
  }

  
  getLevelsForDept(levelId) {
    let orgHierId = 0
    this.mrcSearchService.getHierarchyByLevel(levelId, orgHierId).subscribe(res => {
      this.childArray = res['result']
      this.parentArray[0]=this.childArray;
    })
  }

  selectLevels(salesHoId, index) {
    let orgHierId = salesHoId.value.org_hierarchy_id;
    this.orgHierarchyId = orgHierId;
    this.pdcSearchForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
    this.mrcSearchService.getHierarchyByLevel(salesHoId.value.child_level, orgHierId).subscribe(res => {
      this.childArray = res['result'];
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
      }
    })
  }
}
