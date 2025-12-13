import { Component, HostListener, OnInit } from '@angular/core';
import { nonMovementStore } from '../store-presenter/non-movment-store';
import { nonMovmentPresenter } from '../store-presenter/non-movment-presenter';
import { FormArray, FormGroup } from '@angular/forms';
import { NonMovmentService } from '../non-movment.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { PageEvent } from '@angular/material/paginator';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
@Component({
  selector: 'app-create-non-movment',
  templateUrl: './create-non-movment.component.html',
  styleUrls: ['./create-non-movment.component.css'],
  providers: [nonMovmentPresenter, nonMovementStore]
})
export class CreateNonMovmentComponent implements OnInit {
  searchNonMoveMentForm: FormGroup;
  partDetailsForm: FormArray;
  resultList: any;
  pageSize = 10;
  pageIndex = 0;
  initialSize = 100;
  totalCount: number = 0;
  recordPerPageList: Array<number> = [1, 5, 10, 25, 50, 100];
  submitData: any;
  public loading: boolean;
  itemList: any
  itemNo: any
  aginList:any
  loginType:string
  count:any;
  serialNumber: number = 1;
  auction:boolean=false;
  auctionList = [
    { id: 1, name: "All" },
    { id: 2, name: "Auction" },
    {id:3,name:"Non-Auction"}
    // {id:3}
  
  ];
  constructor(
    private presenter: nonMovmentPresenter,
    private store: nonMovementStore,
    private service: NonMovmentService,
    private toaster: ToastrService,
   private router:Router,
   private localStorage:LocalStorageService,
  ) { 
    let userType=localStorage.getLoginUser();
    // console.log(userType,'userTypes')
    this.loginType=userType['userType'];
    // console.log(loginType)
  }

 
  ngOnInit() {
    this.searchNonMoveMentForm = this.presenter.searchSpareDesClaimForm;
    this.partDetailsForm = this.presenter.partDetailForm;
    if(this.loginType=='dealer'){
      this.getSearchData();
      this.service.dropdownAginType().subscribe(res=>{
        if(res){
          this.aginList=res['result']
        }
      })
    }else{
       this.searchAuctionListForKAi();
    }
    

   

  }

  searchAuctionListForKAi(){
    let postData={
    "page":this.pageIndex,
    "size":this.pageSize,
    "isAuction":this.auction,
    }
    this.service.getnonMovInvSearchForHo(postData).subscribe((res) => {
      this.count=res['count'];
      this.partDetailsForm.clear();
      // this.count=res.count
      if (res['result'].length > 0) {  
        res['result'].forEach((element: any) => {
          const part = this.store.partDetails();
          if (element.isAuction === true) {
            
            part.patchValue({
              isNonMoving: element.isAuction,
            });
          }
          Object.keys(part.controls).forEach((key) => {
            part.patchValue({
              id: element.currStockId ? element.currStockId : null,
              itemNo: element.itemNumber ? element.itemNumber : null,
              itemDesc: element.itemDescription ? element.itemDescription : null,
              aging: element.aging,
              isOemPart: element.isOEMPart,
              isoilSupplierPart: element.isOilSupplierPart,
              islocalPart: element.isLocalPart,
              spegst: element.spegst,
              spmgst: element.spmgst,
              spmrp: element.spmrp,
              localpurchaseprice: element.localPurchasePrice,
              alternatepartno: element.alternatePartNo,
              igstpercent: element.igstPercent,
              uom: element.uom,
              hsncode: element.hsCode8Digit,
            });
          });

          this.partDetailsForm.controls.push(part);
        
        });
        // this.page++;
        this.initialSize += 100;
      } else {
        this.toaster.error('Part Details Not Found!');
      }
    });
  }
 
  


  getSearchData() {
    if(!this.loading){
    const data = {
      page: this.pageSize,
      size: this.pageIndex,
      itemNo: this.itemNo ? this.itemNo : null,
      aging:this.searchNonMoveMentForm.get('aging').value
    };

    this.service.searchApprovalData(data).subscribe((res) => {
      this.partDetailsForm.clear();
      if (res['result'].length > 0) {
        res['result'].forEach((element: any) => {
          const part = this.store.partDetails();
          if (element.isAuction === true) {
            part.patchValue({
              isNonMoving: element.isAuction,
            });
          }
          Object.keys(part.controls).forEach((key) => {
            part.patchValue({
              id: element.currStockId ? element.currStockId : null,
              itemNo: element.itemNumber ? element.itemNumber : null,
              itemDesc: element.itemDescription ? element.itemDescription : null,
              aging: element.aging,
              isOemPart: element.isOEMPart,
              isoilSupplierPart: element.isOilSupplierPart,
              islocalPart: element.isLocalPart,
              spegst: element.spegst,
              spmgst: element.spmgst,
              spmrp: element.spmrp,
              localpurchaseprice: element.localPurchasePrice,
              alternatepartno: element.alternatePartNo,
              igstpercent: element.igstPercent,
              uom: element.uom,
              hsncode: element.hsCode8Digit,
            });
          });

          this.partDetailsForm.controls.push(part);
        
        });
        // this.page++;
        this.initialSize += 100;
      } else {
        this.toaster.error('Part Details Not Found!');
      }
    });
  }
  }




  submit() {
    const formData = this.partDetailsForm.getRawValue();
    const filteredData = formData.filter((element) => element);
    const filteredObject = { stockCurrent: filteredData };
    this.service.submitNonMov(filteredObject).subscribe(
      (res) => {
       this.toaster.success(res['message']);
       this.getSearchData();
      },
      (error) => {
      
        console.error('Error while Creating:', error);
      }
    );
    

  }

  exit() {
    // Implement your exit logic here
    this.router.navigate(['..'])
  }

  autoSearchPartNumber(event:KeyboardEvent) {
    //  console.log(event,'event')
     if(event.key==="Backspace" || event.key==="Delete"){
      this.itemNo=null;
     }
    const data = this.searchNonMoveMentForm.get('partNo').value;
    this.service.getautoSearchPartNumber(data).subscribe(res => {

      if (res) {
        this.itemList = res;
      }
    })
  }

  displayPartNo(partNumber) {
    return partNumber ? partNumber : null
  }

  search() {
   
    this.getSearchData();
  }

  selectedPartNo(event: any) {
    // console.log(event)
    if (event.key === "Backspace" || event.key === 'delete') {
      this.itemNo = null
     
    } else {
      let itemNo = this.searchNonMoveMentForm.get('partNo').value;
      const spaceIndex: number = itemNo.indexOf(' ');
      if (spaceIndex !== -1) {
        const extractedValue: string = itemNo.substring(0, spaceIndex);
        this.itemNo = extractedValue;
      } else {
        console.log("No space found in the string.");
      }
    }
  }

  clear(){
    this.searchNonMoveMentForm.reset();
    this.itemNo=null;
    this.auction=null;
  }

  handlePageChange(event:PageEvent){
    this.pageIndex=event.pageIndex;
    this.pageSize=event.pageSize;
    const a = this.pageIndex * this.pageSize + 1;
    // console.log(a,'a')

    this.searchAuctionListForKAi();
  }

  calculateSerialNumber(index: number): number {
    return this.serialNumber + index + (this.pageIndex * this.pageSize);
  }
  downloadPartNo(){
    const payloadForExcel={
     "page":this.pageIndex,
     "size":this.pageSize,
      "isAuction":this.auction?this.auction:null
    }
      // this.service.downloadExcelForHo(payloadForExcel).subscribe
      this.service.downloadExcelForHo(payloadForExcel).subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
          saveAs(file);
        }
      })
  }
  selectIsAuction(event:any){
    // console.log(event,'event')
    // this.auction=event.value.name=="Auction"?event.value.name=="Auction":false;
    if(event.value.name=="Auction"){
      this.auction=true
    } else if(event.value.name=="All"){
      this.auction=null;
    }
    else{
      this.auction=false;
    }
    // console.log(a,'console.log')
  }
}
