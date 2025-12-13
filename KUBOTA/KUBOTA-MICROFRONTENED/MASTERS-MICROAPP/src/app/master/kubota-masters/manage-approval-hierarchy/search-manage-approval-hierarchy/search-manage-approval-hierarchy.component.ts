import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { ManageApprovalService } from '../manage-approval.service';
import { ActivatedRoute, Router } from '@angular/router';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { Toast } from 'ngx-toastr';

@Component({
  selector: 'app-search-manage-approval-hierarchy',
  templateUrl: './search-manage-approval-hierarchy.component.html',
  styleUrls: ['./search-manage-approval-hierarchy.component.css']
})
export class SearchManageApprovalHierarchyComponent implements OnInit {
  searchApprovalHierarchy: FormGroup;
  dataTable: DataTable
  totalSearchRecordCount: number = 0
  totalTableElements: number = 0
  searchValue: any
  clearSearchRow: any;
  finalApprovalList: any
  transList: any;
  finalApproval: string
  page: number = 0;
  size: number = 0;
  transactionNameNgModel: '';
  designationLevelNgModel: '';
  isFinalApprovalStatusNgModel: '';
  grpSeqNoNgModel: '';
  approverLevelSeqNgModel: '';


  actionButtons: any
  constructor(
    private fb: FormBuilder,
    private service: ManageApprovalService,
    private tableDataService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.searchApprovalHierarchy = this.fb.group({
      tranType: [{ value: null, disabled: false }],
      finalApproval: [{ value: null, disabled: false }],

    })
    this.getFinalApprovalList();
  }

  getFinalApprovalList() {
    this.service.getFinalApproval().subscribe(res => {
      if (res) {
        this.finalApprovalList = res['result']
      }
    })
  }




  autoSearchTransType() {
    let val = this.searchApprovalHierarchy.get('tranType').value;
    if (val && val.trim() !== '') {
      setTimeout(() => {
        this.service.getTransactionType(val).subscribe(
          res => {
            this.transList = res['result'];
            // Handle other logic after successful API call if needed
          },
          error => {
            console.error("An error occurred:", error);
            // Handle error, show user-friendly message, log, etc.
          }
        );
      }, 1000);
    } else {
      this.transList = [];
    }
  }

  searchApproval() {
    const value = this.searchApprovalHierarchy.getRawValue();
    if (value.finalApproval == 'Yes') {
      this.finalApproval = 'Y'
    } else {
      this.finalApproval = 'N'
    }
    const postData = {
      "transName": this.searchApprovalHierarchy.get('tranType').value ? this.searchApprovalHierarchy.get('tranType').value : null,
      "page": this.page,
      "size": this.size,
      // "finalApp":this.finalApproval?this.finalApproval:null
    }
    this.service.searchApprovalData(postData).subscribe(res => {
      if (res) {
        this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
        this.totalSearchRecordCount = res['count'];
      }
    })
  }
  clearApproval() {
    this.searchApprovalHierarchy.reset();
    this.dataTable = null
  }
  actionOnTableRecord(recordData: any) {
    const url = recordData.btnAction;
    if (url === "transactionName") {
      // this.router.navigate(['../update']);
      this.router.navigate(['../update', btoa(recordData['record']['transactionName'])], { relativeTo: this.activatedRoute });
    }
    // if(url.)
  }

  pageChangeOfSearchTable(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchApproval()

  }

}
