import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { SubmitSearchDto } from '../customer-master/domain/customer-master-domain';
import { CustomerMasterSearchPageService } from '../customer-master/component/customer-master-search-page/customer-master-search-page.service';
import { DataTable, ColumnSearch, NgswSearchTableService, InfoForGetPagination } from 'ngsw-search-table';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-customer-master-change-request',
  templateUrl: './customer-master-change-request.component.html',
  styleUrls: ['./customer-master-change-request.component.css'],
  providers: [CustomerMasterSearchPageService]
})
export class CustomerMasterChangeRequestComponent implements OnInit {

    
    private page = 0;
    private size = 10;
    searchForm: FormGroup
    customerSearchDto = {} as SubmitSearchDto
    searchFlag:boolean=true
    dataTable: DataTable;
    actionButtons = [];
    searchValue: ColumnSearch;
    totalTableElements: number;
    
    constructor(
      private customerMasterSearchPageService: CustomerMasterSearchPageService,
      private tableDataService: NgswSearchTableService,
      private router : Router,
      private activatedRoute: ActivatedRoute
    ) { }

    ngOnInit() {
      
    }

    ngAfterViewInit() {
      
    }

    search() {
        if(this.dataTable){
            if(this.searchFlag==true)
            {
              this.page=0;
              this.size=10;
              this.dataTable['PageIndex']=this.page    
              this.dataTable['PageSize']=this.size
            }
            else{
              this.dataTable['PageIndex']=this.page    
              this.dataTable['PageSize']=this.size
            }
        }    
        this.searchFlag==true;
        this.customerSearchDto.page = this.page;
        this.customerSearchDto.size = this.size;
        

        this.customerMasterSearchPageService.submitCustomerApprovalSearchForm(this.customerSearchDto).subscribe(response => {
            this.dataTable = this.tableDataService.convertIntoDataTable(response.result);
            this.totalTableElements = response.count;
        });
        
     }
    
    tableAction(event: object) {
        
       if (event && event['btnAction'] && event['btnAction'].toLowerCase() === 'edit') {
         this.router.navigate(['../approve'], { relativeTo: this.activatedRoute, queryParams: { customerCode: event['record']['customerCode'] } });
       }
    }

    pageSizeChange(event: InfoForGetPagination) {
        this.page = event['page'];
        this.size = event['size'];
        this.searchFlag=false;
        this.search();
    }
   
}
