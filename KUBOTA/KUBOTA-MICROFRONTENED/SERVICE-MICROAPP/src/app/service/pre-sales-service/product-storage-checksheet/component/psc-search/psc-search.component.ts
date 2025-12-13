import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AutoChasisNumber, PscNumber } from '../../domain/psc-domain';
import { PscSearchWebService } from './psc-search-web.service';
import { Observable } from 'rxjs';
import { MatDatepickerInput } from '@angular/material';
import { MrcSearchWebService } from '../../../machine-receipt-checking/component/mrc-search/mrc-search-web.service';

@Component({
  selector: 'app-psc-search',
  templateUrl: './psc-search.component.html',
  styleUrls: ['./psc-search.component.scss'],
  providers: [PscSearchWebService, MrcSearchWebService]
})
export class PscSearchComponent implements OnInit {

  @Input() searchPscForm: FormGroup
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  chassisNoList$: Observable<Array<AutoChasisNumber>>
  pscNoList$: Observable<Array<PscNumber>>

  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr:any[] = []
  orgHierarchyId:number
  loginUser:any;

  constructor(
    private pscSearchWebService: PscSearchWebService,
    private mrcSearchService: MrcSearchWebService
  ) { }

  ngOnInit() {
    this.getChassisNoList()
    this.getPscNoList()
    this.loginUser = localStorage.getItem('kubotaUser');
    this.loginUser = JSON.parse(JSON.parse(JSON.stringify(this.loginUser)))
    if(this.loginUser.userType!='dealer'){
      this.getLevelByDeprtment();
    }
  }
  ngAfterViewInit() {
    

  }
  private getChassisNoList() {
    this.searchPscForm.get('chassisNo').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const chassisNo = typeof valueChange == 'object' ? valueChange.code : valueChange
        this.autoChassisNo(chassisNo)
      }
    })
  }

  autoChassisNo(chassisNo: string) {
    this.chassisNoList$ = this.pscSearchWebService.getChassisNo(chassisNo)
  }

  private getPscNoList() {
    this.searchPscForm.get('pscNo').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const pscNo = typeof valueChange == 'object' ? valueChange.pscNo : valueChange
        this.autoPscNo(pscNo)
      }
    })
  }
  autoPscNo(pscNo: string) {
    this.pscNoList$ = this.pscSearchWebService.autoCompletePscNo(pscNo)
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
      if (this.searchPscForm.get('toDate').value > this.maxDate)
        this.searchPscForm.get('toDate').patchValue(this.maxDate);
    }
  }getLevelByDeprtment() {
    if(this.controlsArr.length>0){
      this.controlsArr.forEach(controlName => {
        this.searchPscForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.mrcSearchService.getLevelByDepartment("SE").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.searchPscForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
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
    this.searchPscForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
    this.mrcSearchService.getHierarchyByLevel(salesHoId.value.child_level, orgHierId).subscribe(res => {
      this.childArray = res['result'];
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
      }
    })
  }
}