import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { NonMovmentService } from '../../non-movment-inventory/non-movment.service';

@Component({
  selector: 'app-all-dealer-autionlist',
  templateUrl: './all-dealer-autionlist.component.html',
  styleUrls: ['./all-dealer-autionlist.component.css'],
  providers:[NonMovmentService]
})
export class AllDealerAutionlistComponent implements OnInit {
  allDealerAuctionPart:FormGroup
  itemNo: FormControl;
  searchFlag:boolean=false;
  page:number=0;
  size:number=10;
  dataTable:DataTable;
  totalTableElements:number=0;
  constructor(
    private ngswSearchTableService:NgswSearchTableService,
    private nonMovmentService:NonMovmentService
  ) { 
   
  }


  searchData(){
  
    if(this.searchFlag==true)
    {
      this.page=0;
      this.size=10;
      // searchFormValues['page'] = this.page;
      // searchFormValues['size'] = this.size;
    }
    else{
      // searchFormValues['page'] = this.page;
      // searchFormValues['size'] = this.size;
    }
    const postData={
      "page":this.page,
      "size":this.size
    }

    this.nonMovmentService.getAcutionList(postData).subscribe(res => {
      res['result'].forEach(res=>{
        delete res.currStockId
        delete res.partId
        delete res.isAuction
      })
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      const a=res;
      this.totalTableElements = res['count'];
    })
    this.searchFlag=true; 
  }

  ngOnInit() {
    // let searchFormValues = {};
  this.searchData()
  }
  pageChange(event:any){
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false
    this.searchData();
  }

  tableAction(event:any){
    
  }
}
