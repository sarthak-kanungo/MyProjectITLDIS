import { FormControl, FormGroup } from '@angular/forms';
import { PdiSearchWebService } from './pdi-search-web.service';
import { SearchAutocomplete } from '../../domain/pdi-domain';
import { Component, OnInit, Input } from '@angular/core';
import { MatDatepickerInput } from '@angular/material';
import { MrcSearchWebService } from '../../../machine-receipt-checking/component/mrc-search/mrc-search-web.service';

@Component({
  selector: 'app-search-pdi',
  templateUrl: './pdi-search.component.html',
  styleUrls: ['./pdi-search.component.scss'],
  providers: [PdiSearchWebService, MrcSearchWebService]
})
export class PdiSearchComponent implements OnInit {
  @Input() pdiDetailsSearchForm: FormGroup;
  kaiChassisNoList: Array<SearchAutocomplete>
  invoiceNoList: Array<SearchAutocomplete>
  dmsgrnNoList: Array<SearchAutocomplete>
  searchform: boolean = false
  newdate = new Date()
  maxDate: Date
  minDate: Date
  public totalTableElements: number;
  
  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr:any[] = []
  orgHierarchyId:number
  loginUser:any;

  constructor(
    private pdiSearchWebService: PdiSearchWebService,
    private mrcSearchService: MrcSearchWebService
  ) { }
  ngOnInit() {
    this.chassisNoChanges()
    this.invoiceNoChanges()
    this.grnNoChanges();
    this.loginUser = localStorage.getItem('kubotaUser');
    this.loginUser = JSON.parse(JSON.parse(JSON.stringify(this.loginUser)))
    if(this.loginUser.userType!='dealer'){
      this.getLevelByDeprtment();
    }
  }
  ngAfterViewInit() {

  }
  private chassisNoChanges() {
    this.pdiDetailsSearchForm.get('chassisNo').valueChanges.subscribe(changedValue => {
      if (changedValue) {
        this.pdiSearchWebService.searchByChassisNumber(changedValue).subscribe(res => {
          this.kaiChassisNoList = res;
        })
      }
    })

  }
  private invoiceNoChanges() {
    this.pdiDetailsSearchForm.get('kaiInvoiceNo').valueChanges.subscribe(changedValue => {
      if (changedValue) {
        this.pdiSearchWebService.searchByInvoiceNumber(changedValue).subscribe(res => {
          this.invoiceNoList = res;
        })
      }
    })

  }
  private grnNoChanges() {
    this.pdiDetailsSearchForm.get('dmsgrnNo').valueChanges.subscribe(changedValue => {
      if (changedValue) {
        this.pdiSearchWebService.searchByGrnNumber(changedValue).subscribe(res => {
          this.dmsgrnNoList = res;
        })
      }
    })

  }
  public displayFnChassisNo(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }
  public displayFnInvoiceNo(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['value'] : undefined;
  }
  public displayFnGrnNo(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['value'] : undefined;
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
      if (this.pdiDetailsSearchForm.get('dmsgrnToDate').value > this.maxDate)
        this.pdiDetailsSearchForm.get('dmsgrnToDate').patchValue(this.maxDate);
    }
  }
  setToDate1(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate)
        this.maxDate = this.newdate;
      else
        this.maxDate = maxDate;
      if (this.pdiDetailsSearchForm.get('pdiDateTo').value > this.maxDate)
        this.pdiDetailsSearchForm.get('pdiDateTo').patchValue(this.maxDate);
    }
  }

  getLevelByDeprtment() {
    if(this.controlsArr.length>0){
      this.controlsArr.forEach(controlName => {
        this.pdiDetailsSearchForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.mrcSearchService.getLevelByDepartment("SE").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.pdiDetailsSearchForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
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
    this.pdiDetailsSearchForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
    this.mrcSearchService.getHierarchyByLevel(salesHoId.value.child_level, orgHierId).subscribe(res => {
      this.childArray = res['result'];
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
      }
    })
  }
}
