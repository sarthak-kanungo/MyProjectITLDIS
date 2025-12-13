import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { MatDialog } from '@angular/material/dialog';
import { ActionOnTableRecord, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { element } from 'protractor';
import { BehaviorSubject, Observable } from 'rxjs';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { DateService } from 'src/app/root-service/date.service';
import { AutoCompleteResult, blockList,SearchAutocomplete } from '../../model/model';
import { BlockPartServiceService } from '../../service/block-part-service.service';

@Component({
  selector: 'app-block-part-sale-search',
  templateUrl: './block-part-sale-search.component.html',
  styleUrls: ['./block-part-sale-search.component.css']
})
export class BlockPartSaleSearchComponent implements OnInit {
  searchBlockParts:FormGroup
  searchValue: ColumnSearch;
  actionButtons
  totalTableElements
  dataTable
  page: number = 0
  size: number = 10
  // public blockList:blockList[]
  public filteredItemNumberList:[];
  public blockList: Observable<(string | object)[]>;
  searchFlag:boolean=false
  list:FormGroup
  clearSearchRow = new BehaviorSubject<string>("");
  partNoNgModel:any=''
  partDescriptionNgModel:any=''
  selectedForm: FormGroup;
   selectedCode: string;
  @Output() actionOnTableCell = new EventEmitter<Object>();
  bankData: any;
  constructor(private fb:FormBuilder, public dialog: MatDialog,private service:BlockPartServiceService,private tableDataService: NgswSearchTableService,private toastr: ToastrService,private dateService:DateService) { }
  statusList = [
    // { id: 1, name: "Active",value:"Y" },
    { id: 2, name: "Inactive",value:"N" },
  ];
  
  ngOnInit() {
    this.searchBlockParts = this.fb.group({
      partNo: [{value:null,disabled:false}],
      itemDescription:[{value:null,disabled:true}],
      status:[{value:'',disabled:false}]
     
    });
    
   this.partNo()
    // this.BankDetails()
  }
  partNo(){
    this.BankDetails(event)
    this.searchBlockParts.get('partNo').valueChanges.subscribe(val => {
      if(val && typeof val != 'object'){
          
          this.service.getPartsNoByPartNo(val).subscribe(response => {
            if(response){
              this.filteredItemNumberList = response['result'];    
            }
          })
      }
  })
  }
 
  itemNumberSelection(event:any){
    let enquiryData = event.option.value
    console.log(enquiryData)
   
    this.BankDetails(event.option.value)
 }
   
  BankDetails(event:any){
    this.  searchBlockParts
    .get('partNo').valueChanges.subscribe(val => {
      if(val){
      
      this.service.getPartDetailsByPartNo(val).subscribe(res=>{
        this.bankData=res
        console.log(this.bankData,'data')
        this.searchBlockParts.patchValue({
          itemDescription: res['result'].partDescription,
        });
        })
      }
    })
 }
 
 

  // }
  search(){
   
   
    let searchobj = this.searchBlockParts.getRawValue();
    console.log(searchobj,'ss')
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
    }else if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
    }
    searchobj = this.removeNullFromObjectAndFormatDate(searchobj);
    delete searchobj.page;
    delete searchobj.size;  
    this.searchFlag = true;
    if (Object.keys(searchobj).length>0) {
      if (!this.dateService.checkValidDatesInput(searchobj.fromDate, searchobj.toDate)) {
        this.toastr.error("Please Select Due Date Range.");
        return false;
      }
    
      searchobj.page = this.page
      searchobj.size = this.size
      this.service.getBlockPartForSaleSearch(searchobj).subscribe(res => {
        this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
        this.totalTableElements = res['count'];
      }, err => {
         this.toastr.error("Search Failed.");
        this.dataTable = null;
        this.totalTableElements = 0;
      });
    }else{
      this.toastr.error("Please Select Atleast One Input Fields");
    }
    
     

  }
  public removeNullFromObjectAndFormatDate(searchObject: object) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
      });
      return searchObject;
    }
  }
 
  
  clearForm(){
    this.searchBlockParts.reset()
    this.dataTable=null
  }
  pageChange(event:any){
    this.page = event.page;
    this.size = event.size;
    this.searchFlag=false;
    this.search();
  }
  
  onUrlChange(event:any){
    
  }
  actionOnTableRecord(recordData) {
    console.log("recordData ", recordData);
    this.actionOnTableCell.emit(recordData)
    if (!!recordData && recordData.btnAction.toLowerCase() === 'activestatus') {
      this.openConfirmDialog(recordData);
    }
  }
  private openConfirmDialog(emitedId: ActionOnTableRecord): void | any {
    let message = 'Are you sure you want to change status?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm') {
        this.changeActiveStatus(emitedId);
      }
    });
  }
  private changeActiveStatus(emitedId: ActionOnTableRecord) {
    this.service.changeActiveStatus(emitedId.record['id']).subscribe(res => {
      console.log('res', res);
      // const isError =  this.toastr.error('Changing active status of record is failed!', 'Error');
      // if (isError) {
      //   return;
      // }
      this.dataTable.tableBody.content[emitedId.recordIndex]['activeStatus'] = res['result']['activeStatus'];
      this.toastr.success('Active status changed successfully!', 'Success', {
        timeOut: 1500
      });
      console.log('this.dataTable.tableBody.content', this.dataTable.tableBody.content);
    }, err => {
      this.toastr.error('Changing active status of record is failed!');
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }
  // displayCodeFns(obj: AutoCompleteResult): string | number | undefined {
  //   return obj ? obj.partNumber : undefined;
  // }
  displayCodeFns(value:AutoCompleteResult){
    return value?value.value:undefined
  }
}
