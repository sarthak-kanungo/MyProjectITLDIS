import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormGroup, } from '@angular/forms';
import { MatDialog, MatDatepickerInput } from '@angular/material';
import { MrcSearchPage } from '../mrc-search-page/mrc-search-page.presenter';
import { Observable } from 'rxjs';
import { MrcNumberAuto, KaiInvoiceNumber, ChassisNumber } from '../../domain/machine-receipt-checking.domain';
import { MrcSearchWebService } from './mrc-search-web.service';


@Component({
  selector: 'app-mrc-search',
  templateUrl: './mrc-search.component.html',
  styleUrls: ['./mrc-search.component.scss'],
  providers: [MrcSearchPage, MrcSearchWebService]
})
export class MrcSearchComponent implements OnInit {
  searchform: boolean = false
  mrcNoList$: Observable<Array<MrcNumberAuto>>
  kaiInvoiceNoList$: Observable<Array<KaiInvoiceNumber>>
  chassisNoList$: Observable<Array<ChassisNumber>>
  @Input() mrcSearchForm: FormGroup;
  @Input()
  max: Date | null
  serverDate: Date;
  minDate: Date;
  iminDate: Date;
  newdate= new Date()
  maxDate: Date
  imaxDate: Date

  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr:any[] = []
  orgHierarchyId:number
  loginUser:any;

  constructor(
    private presenter: MrcSearchPage,
    private mrcSearchService: MrcSearchWebService,

    public dialog: MatDialog,

  ) { }
  ngOnInit() {
    this.mrcSearchForm = this.presenter.mrcSearch;
    this.valueChangesForAutoComplete();
    this.getDateFromServer();
    this.loginUser = localStorage.getItem('itldisUser');
    this.loginUser = JSON.parse(JSON.parse(JSON.stringify(this.loginUser)))
    if(this.loginUser.userType!='dealer'){
      this.getLevelByDeprtment();
    }
  }
  ngAfterViewInit() {
    

  }
  private getDateFromServer() {
    this.mrcSearchService.getSystemGeneratedDate().subscribe(dateRes => {
      if (dateRes.result) {
        this.maxDate = new Date(dateRes.result)
        this.imaxDate = new Date(dateRes.result)
      }
    });
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
      if (this.mrcSearchForm.get('mrcDateTo').value > this.maxDate)
        this.mrcSearchForm.get('mrcDateTo').patchValue(this.maxDate);
    }
  }
  
  setInvoiceToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.iminDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate)
        this.imaxDate = this.newdate;
      else
        this.imaxDate = maxDate;
      if (this.mrcSearchForm.get('invoiceDateTo').value > this.imaxDate)
        this.mrcSearchForm.get('invoiceDateTo').patchValue(this.imaxDate);
    }
  }
  valueChangesForAutoComplete() {
    this.mrcSearchForm.get('mrcNo').valueChanges.subscribe(value => {
      if (value) {
      //  let mrcNumber = typeof value == 'object' ? value.mrcNumber : value
        this.autoKaiMrcNo(value)
      }
    })

    this.mrcSearchForm.get('kaiInvoiceNo').valueChanges.subscribe(value => {
      if(value){
      //let invoiceNumber = typeof value == 'object' ? value.invoiceNumber : value
      this.autoKaiInvoiceNo(value)
      }
    })
  }
  
  autoKaiMrcNo(mrcNumber: any) {
    this.mrcNoList$ = this.mrcSearchService.getMrcNumber(mrcNumber)
  }

  displayFnMrcNo(mrcNo: MrcNumberAuto) {
    return mrcNo ? mrcNo.code : undefined
  }

  autoKaiInvoiceNo(invoiceNumber: any) {
    this.kaiInvoiceNoList$ = this.mrcSearchService.getKaiInvoiceNumber(invoiceNumber)
  }

  displayFnKaiInvoiceNo(invoiceNum: KaiInvoiceNumber) {
    return invoiceNum ? invoiceNum.invoiceNumber : undefined
  }

  autoKaiChassisNo(chassisNumber: any) {
    this.chassisNoList$ = this.mrcSearchService.getChassisNumber(chassisNumber)
  }
  displayFnChassisNo(chassisNum: ChassisNumber) {
    return chassisNum ? chassisNum.chassisNumber : undefined
  }

  getLevelByDeprtment() {
    if(this.controlsArr.length>0){
      this.controlsArr.forEach(controlName => {
        this.mrcSearchForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.mrcSearchService.getLevelByDepartment("SE").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.mrcSearchForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
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
    this.mrcSearchForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
    this.mrcSearchService.getHierarchyByLevel(salesHoId.value.child_level, orgHierId).subscribe(res => {
      this.childArray = res['result'];
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
      }
    })
  }
}
