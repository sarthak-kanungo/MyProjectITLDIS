import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';
import { MrcPageService } from '../machine-receipt-checking/component/mrc-details-page/mrc-page-web.service';
import { MachineFormfService } from './machine-formf.service';

@Component({
  selector: 'app-machine-formf',
  templateUrl: './machine-formf.component.html',
  styleUrls: ['./machine-formf.component.css'],
  providers: [MachineFormfService, MrcPageService]
})
export class MachineFormfComponent implements OnInit {

  machineSearchForm:FormGroup
  totalSearchRecordCount:number
  dataTable : DataTable
  actionButtons = []
  searchValue : ColumnSearch
  page:number = 0
  size:number = 10
  clearSearchRow = new BehaviorSubject<string>("");
  searchFlag:boolean=true;
  dealerNgModel;
  machineNoNgModel;
  engineNgModel;
  productNgModel;
  seriesNgModel;
  modelNgModel;
  subModelNgModel;
  variantNgModel;
  kaiChassisNoList;
  pageLoadCount:number=0

  constructor(private formBuilder: FormBuilder,
    private ngswSearchTableService: NgswSearchTableService,
    private mrcPageService: MrcPageService,
    private searchService: MachineFormfService) {
    
   }

  ngOnInit() {
    this.machineSearchForm = this.formBuilder.group({
      machineNo:[null]
    });
    this.chassisNumberChanges();
    // this.searchData();
  }


  searchData(){
    let searchObject = this.machineSearchForm.getRawValue();
    if (this.dataTable != undefined) {
      if (this.searchFlag == true) {
        this.page = 0;
        this.size = 10;
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
      else {
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
    }
    this.searchFlag = true;
    searchObject['page']=this.page;
    searchObject['size']=this.size;
    this.searchService.machineFormFSearch(searchObject).subscribe(resp => {
      if(resp){
        this.totalSearchRecordCount = resp['count'];
        this.dataTable = this.ngswSearchTableService.convertIntoDataTable(resp['result']);
      }
    })
  }
  clearForm(){
    this.dealerNgModel = "";
    this.machineNoNgModel = "";
    this.engineNgModel = "";
    this.productNgModel = "";
    this.seriesNgModel = "";
    this.modelNgModel = "";
    this.subModelNgModel = "";
    this.variantNgModel = "";
    this.machineSearchForm.reset();
    this.dataTable = null
    this.clearSearchRow.next("");
  }

  pageChange(event){
    this.searchFlag = false;
    this.page = event.page;
    this.size = event.size;
    if(this.pageLoadCount > 0){
      this.searchData();
    }
    this.pageLoadCount = 1;
    
  }

  actionOnTableRecord(recordData: any) {

    if (recordData.btnAction.toLowerCase() === 'formf') {
      this.printForm(recordData.record.machineNo, 'FormF');
    }
    if (recordData.btnAction.toLowerCase() === 'form 22') {
      this.printForm(recordData.record.machineNo, 'Form22');
    }
  }

  private chassisNumberChanges() {
    this.machineSearchForm.get('machineNo').valueChanges.subscribe(changedValue => {
      if(changedValue){
          this.searchService.getChassisNo(changedValue).subscribe(res => {
              this.kaiChassisNoList = res['result'];
          })
       }
    })
  }

  printForm(machineNo, formType){
    this.mrcPageService.printForm(machineNo,formType,'machine', "true").subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        const blobUrl = window.URL.createObjectURL(resp.body);
        const iframe = document.createElement('iframe');
        iframe.style.display = 'none';
        iframe.src = blobUrl;
        document.body.appendChild(iframe);
        iframe.contentWindow.print();
        this.searchData();
      }
    })
  }

}
