import { FormBuilder, FormGroup } from '@angular/forms';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { BlockMachineService } from '../service/block-machine.service';
import { statusModel } from './models/models';
import { ModelBySeries, Product, SeriesByProduct, Variants } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/domains/search-enquiry-v2';
import { AutoCompSubModel } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/domains/enquiry';
import { SearchEnquiryV2WebService } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/services/search-enquiry-v2-web.service';
import { SalesReportService } from 'src/app/sales-pre-sales/reports/sales-report-service/sales-report.service';
import { ProductInterestedV2WebService } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/services/product-interested-v2-web.service';
import { SearchDeliveryChallanService } from '../../../delivery-challan/component/search-delivery-challan/search-delivery-challan.service';
import { SearchAllotmentDeAllotmentService } from '../../../de-allotment/component/search-allotment-de-allotment/search-allotment-de-allotment.service';
import { SelectList } from 'src/app/core/model/select-list.model';
import { ActionOnTableRecord, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs-compat';
import { DateService } from 'src/app/root-service/date.service';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material/dialog';
import { AnimationQueryOptions } from '@angular/animations';

@Component({
  selector: 'app-block-machine-sale-search',
  templateUrl: './block-machine-sale-search.component.html',
  styleUrls: ['./block-machine-sale-search.component.css'],
  providers: [SearchAllotmentDeAllotmentService, SalesReportService, SearchEnquiryV2WebService]
})

export class BlockMachineSaleSearchComponent implements OnInit {

  searchBlockMachine: FormGroup
  productTypeList = [] as SelectList[];
  seriesTypeList = [] as SelectList[];
  modelTypeList = [] as Array<SelectList>
  variantList = [] as SelectList[];
  submodelList = [] as Array<SelectList>;
  dataTable
  totalTableElements
  searchValue: ColumnSearch;
  actionButtons
  page: number = 0
  size: number = 10
  blockList: any
  searchFlag: boolean = false
  productNgModel: any = ''
  seriesNgModel: any = ''
  modelNgModel: any = ''
  subModelNgModel: any = ''
  variantNgModel: any = ''
  clearSearchRow = new BehaviorSubject<string>("");
  public deleteIdArray: Array<number> = [];
  @Output() actionOnTableCell = new EventEmitter<Object>();
  forIndex: boolean;
  id: any;
  all: Array<number>=[];
  buttonShow:boolean=false
  pageLoadCount:number=0

  constructor(private fb: FormBuilder, public dialog: MatDialog, private sarvice: BlockMachineService, private dateService: DateService, private tableDataService: NgswSearchTableService, private toastr: ToastrService,
    private salesReportService: SalesReportService, private searchAllotmentDeAllotmentService: SearchAllotmentDeAllotmentService) {


  }

  statusList = [
    // { id: 1, name: "Active",value:"Y" },
    { id: 2, name: "Inactive", value: "N" },
  ];
  displayedColumns = [
    'name',
    'position',
    'weight',
    'symbol',
    'position',
    'weight',
    'symbol',
    'star',
  ];


  ngOnInit() {
    this.searchBlockMachine = this.fb.group({
      product: [''],
      series: [''],
      machineModel: [''],
      subModel: [''],
      variant: [''],
      model: [''],
      status: ['']
    })
    this.getAllProduct()
    // this.search()
  }
  getAllProduct() {
    this.searchAllotmentDeAllotmentService.dropdownGetAllProductType().subscribe(response => {
      this.productTypeList = response.result
    })
  }
  getAllSeries(event) {
    const product = this.searchBlockMachine.get('product').value;
    this.searchAllotmentDeAllotmentService.getSeriesByProduct(product).subscribe(response => {
      this.seriesTypeList = response.result
    })
  }
  getAllModel(event) {
    const series = this.searchBlockMachine.get('series').value;
    this.searchAllotmentDeAllotmentService.getModelBySeries(series).subscribe(response => {
      this.modelTypeList = response.result;
    })
  }
  getAllSubModel(event) {
    const model = this.searchBlockMachine.get('model').value;
    this.searchAllotmentDeAllotmentService.getSubModelByModel(model).subscribe(response => {
      this.submodelList = response.result;
    })
  }
  getAllVariant(event) {
    const subModel = this.searchBlockMachine.get('subModel').value;
    this.searchAllotmentDeAllotmentService.getAllVariant(subModel).subscribe(response => {
      this.variantList = response.result;
    })
  }


  search() {
    let searchobj = this.searchBlockMachine.value;
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
    } else if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
    }
    searchobj = this.removeNullFromObjectAndFormatDate(searchobj);
    delete searchobj.page;
    delete searchobj.size;

    this.searchFlag = true;
    if (Object.keys(searchobj).length > 0) {
      if (!this.dateService.checkValidDatesInput(searchobj.fromDate, searchobj.toDate)) {
        this.toastr.error("Please Select Due Date Range.");
        return false;
      }

      //if (Object.keys(searchobj).length>0) {

      searchobj.page = this.page
      searchobj.size = this.size
      this.sarvice.blockMachineForSaleSearch(searchobj).subscribe(res => {   
        this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
       this.totalTableElements = res['count'];
      }, err => {
        this.toastr.error("Search Failed.");
        this.dataTable = null;
        this.totalTableElements = 0;
      });

    }
    else{
      this.toastr.error("Please Select Atleast One Input Field");
    
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
  clearForm() {
    this.searchBlockMachine.reset()
    this.dataTable = null
    this.clearSearchRow.next("");
  }
  onUrlChange(event: any) {

  }
  actionOnTableRecord(recordData) {
    if(recordData.recordIndex>=1){
      this.buttonShow=true
    }else{
      this.buttonShow=false



    }

      console.log(recordData,'recordDatae')
    this.actionOnTableCell.emit(recordData)
    if (!!recordData && recordData.btnAction.toLowerCase() === 'activestatus') {
      this.openConfirmDialog(recordData);
      
    }
    if(recordData.btnAction === 'multiSelect') {
      if(this.deleteIdArray.indexOf(recordData.record['id'])==-1){
        this.deleteIdArray.push(recordData.record['id']);
         console.log('this.deleteIdArray', this.deleteIdArray);
      }else{
        this.deleteIdArray = this.deleteIdArray.filter(id => id!=recordData.record['id']);
         console.log('this.deleteIdArray', this.deleteIdArray);
      }
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
      // console.log('The dialog was closed', result);
      if (result === 'Confirm') {
        this.changeActiveStatus(emitedId);
      }
    });
  }
  private changeActiveStatus(emitedId: ActionOnTableRecord) {
    this.sarvice.changeActiveStatus(emitedId.record['id']).subscribe(res => {
      this.dataTable.tableBody.content[emitedId.recordIndex]['activeStatus'] = res['result']['activeStatus'];
      this.toastr.success('Active status changed successfully!', 'Success', {
        timeOut: 1500
      });
      // console.log('this.dataTable.tableBody.content', this.dataTable.tableBody.content);
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
  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    if(this.pageLoadCount > 0){
      this.search();
    }
    this.pageLoadCount = 1;
    
  }
  multiRecordSelect(id:any) {

    let jsonObj = {
      blockIds : this.deleteIdArray,
      
    }
    this.sarvice.changeActiveStatusByIds(jsonObj).subscribe(res => {
      if(res){
        if(res['status']=='200'){        
          this.toastr.success('Block Machine Status Change Successfully')
          this.forIndex=false;
          this.buttonShow=false
          this.dataTable=null
          this.deleteIdArray = [];
          this.deleteIdArray=null
          // this.search()
          // setTimeout(()=>{
          //   this.search()
          //      window.location.reload();
          // },1000)
          
        }
      }else {
        this.toastr.error('Block Machine Status Change Failed');
      }
    })
    
  } 
  actionOnHeaderRecord(event: any) {
     this.deleteIdArray=[]
    var name=event.content
    name.forEach(id=>{
     this.id=id.id
   this.deleteIdArray.push(id.id)
    })
     console.log(this.all,'delete')
    
  }
}