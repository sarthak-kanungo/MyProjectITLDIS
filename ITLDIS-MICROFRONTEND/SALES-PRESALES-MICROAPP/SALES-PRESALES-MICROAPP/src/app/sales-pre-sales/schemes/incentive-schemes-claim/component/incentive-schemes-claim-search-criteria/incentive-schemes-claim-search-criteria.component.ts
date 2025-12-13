import { HttpResponse } from '@angular/common/http';
import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { IncentiveSchemeClaimService } from '../../pages/incentive-schemes-claim-create/incentive-scheme-claim.service';
import { saveAs } from 'file-saver';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { ModalFileUploadComponent } from 'src/app/sales-pre-sales/modal-file-upload/modal-file-upload.component';
import { ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';

@Component({
  selector: 'app-incentive-schemes-claim-search-criteria',
  templateUrl: './incentive-schemes-claim-search-criteria.component.html',
  styleUrls: ['./incentive-schemes-claim-search-criteria.component.scss'],
  providers: [IncentiveSchemeClaimService]
})
export class IncentiveSchemesClaimSearchCriteriaComponent implements OnInit {
  @Input()
  max: Date | null
  @Input()
  min: Date | null
  today = new Date();
  minDate: Date;
  maxDate: Date
  page: number = 0;
  size: number = 10;
  searchFlag:boolean = false;
  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  SearchIncentiveClaim:FormGroup
  userType:string
  key:string = "IncentiveSchemeClaim";
  clearSearchRow = new BehaviorSubject<string>("");

  dealerCodeNgModel:any = '';
  dealerNameNgModel:any = '';
  claimNoNgModel:any = '';
  claimDateNgModel:any = '';
  statusNgModel:any = '';
  claimAmountNgModel:any = '';
  monthNgModel:any = '';

  dealercodeList:any[];
  autoComClaims:any[];

  months: any[] = [
    {id:1, value:'January'}, {id:2, value:'February'}, {id:3, value:'March'}, 
    {id:4, value:'April'}, {id:5, value:'May'}, {id:6, value:'June'}, 
    {id:7, value:'July'}, {id:8, value:'August'}, {id:9, value:'September'}, 
    {id:10, value:'October'}, {id:11, value:'November'}, {id:12, value:'December'}
  ];

  constructor(private fb: FormBuilder,
    private router : Router,
    private toastr: ToastrService,
    public dialog: MatDialog,
    private dateService : DateService,
    private loginService : LoginFormService,
    private claimService : IncentiveSchemeClaimService,
    private ngswSearchTableService : NgswSearchTableService,
    private activatedRoute : ActivatedRoute) { }

  ngOnInit() {
    this.createSearchIncentiveClaim()
    this.userType = this.loginService.getLoginUserType();
  }

  createIncentiveClaim(){
   this.router.navigate(['/sales-pre-sales/schemes/incentive-schemes-claim/create'])
  }
  createSearchIncentiveClaim() {
    this.SearchIncentiveClaim = this.fb.group({
      claimno: ['', Validators.compose([])],
      dealercode: ['', Validators.compose([])],
      claimfromdate: ['', Validators.compose([])],
      claimtodate: ['', Validators.compose([])],
    })
  }
  actionOnTableRecord(event) {
    if(event['btnAction'].toLowerCase() == 'claimno'){
      this.router.navigate(['../view'], { queryParams: {id:btoa(event['record']['id'])},relativeTo: this.activatedRoute })
    }
    if (event['btnAction'].toLowerCase() === 'action') {
      if(event['record']['action']=='Approve') 
        this.router.navigate(['../approve'], { queryParams: {id:btoa(event['record']['id'])},relativeTo: this.activatedRoute })
      if(event['record']['action']=='Generate Invoice')
        this.generateInvoice(btoa(event['record']['id']));
      if(event['record']['action']=='Upload Invoice')
        this.uploadInvoice(btoa(event['record']['id']));
      if(event['record']['action']=='Verify Invoice')
        this.openConfirmDialogVerification(btoa(event['record']['id']));
    }
    if (event['btnAction'].toLowerCase() === 'invoice') {
      this.downloadInvoice(btoa(event['record']['id']));
    }
  }
  downloadInvoice(id){
    this.claimService.downloadInvoice(id).subscribe( (response: HttpResponse<Blob>) => {
      if(response){
        let headerContentDispostion = response.headers.get('Content-Disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([response.body], `${fileName}`, { type: 'application/pdf' });
        saveAs(file);
      }
    })
  }

  generateInvoice(id){
    this.claimService.generateInvoice(id).subscribe(res=>{
      if (res && res['status'] == 200) {
        this.toastr.success(res['message']);
        this.searchIncentive();
      }else {
        this.toastr.error(res['message']);
      }
    }, error => {
      this.toastr.error('Error in invoice generation','Transaction Failed');
    });
  }

  uploadInvoice(id){
    const dialogConfig = new MatDialogConfig();
    let updatedata ={'claimId':id}
    dialogConfig.disableClose = true;
    dialogConfig.id = "modal-component";
    dialogConfig.width = "500px";
    dialogConfig.data = {}
    const modalDialog = this.dialog.open(ModalFileUploadComponent, dialogConfig);
    modalDialog.afterClosed().subscribe(result => {
      if(result.event === 'upload'){
        let file:File = result.data;
        this.claimService.uploadFile(updatedata,file).subscribe(res=>{
          if (res.status == 200) {
            this.toastr.success(res['message']);
            this.searchIncentive();
          }else {
            this.toastr.error(res.message)
          }
        })
      }
    })
  }
  private openConfirmDialogVerification(id) {
    let message = `Do you want to save deatils as Verified?`;

    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: "500px",
      panelClass: "confirmation_modal",
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == "Confirm") {
        this.claimService.updateWcrAsVerified(id).subscribe(response =>{
          this.toastr.success(response['message']);
          this.searchIncentive();
        });
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.title = "Confirmation";
    confirmationData.message = message;
    confirmationData.buttonName = ["Confirm", "Cancel"];
    return confirmationData;
  }
  pageChange(event) {
    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag = false;
    this.searchIncentive();
  }

  clearSearchForm(){
    this.claimNoNgModel = '';
    this.claimDateNgModel = '';
    this.claimAmountNgModel = '';
    this.dealerCodeNgModel = '';
    this.dealerNameNgModel = '';
    this.statusNgModel = '';
    this.monthNgModel = '';
    this.SearchIncentiveClaim.reset();
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }

  searchIncentive() {
    var searchFormValues = this.SearchIncentiveClaim.getRawValue();
    localStorage.setItem(this.key, JSON.stringify(searchFormValues))

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

    searchFormValues['page'] = this.page;
    searchFormValues['size'] = this.size;
    if(searchFormValues['claimno']){
      searchFormValues['claimno'] = searchFormValues['claimno']['claimNo']
    }
    if(searchFormValues['dealercode']){
      searchFormValues['dealerId'] = searchFormValues['dealercode']['id']
    }
    if(searchFormValues['claimfromdate']){
      searchFormValues['claimfromdate'] = this.dateService.getDateIntoYYYYMMDD(searchFormValues['claimfromdate']);
    }
    if(searchFormValues['claimtodate']){
      searchFormValues['claimtodate'] = this.dateService.getDateIntoYYYYMMDD(searchFormValues['claimtodate']);
    }
    this.claimService.searchClaimList(searchFormValues).subscribe(res => {
      var result = res['result'];
      if(result){
        result.forEach(r => {
          const m = this.months.find(m => m.id = r.month);
          r.month = m.value
          if(this.userType != 'itldis'){
            delete r.dealerCode;
            delete r.dealerName;
          }
        })
      }
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(result);
      this.totalTableElements = res['count'];
    })
  }

  ngAfterViewInit() {
    this.maxDate = this.today
    let backDate = new Date();
    backDate.setMonth(backDate.getMonth() - 1);
    this.minDate = backDate;
    this.SearchIncentiveClaim.get('claimfromdate').patchValue(backDate);
    this.SearchIncentiveClaim.get('claimtodate').patchValue(this.today);

    this.SearchIncentiveClaim.get('claimno').valueChanges.subscribe(value => {
      if(value && typeof value == 'string'){
        this.SearchIncentiveClaim.get('claimno').setErrors({selectFromList:'selectFromList'});
        this.claimService.getIncentiveSchemesClaimNo(value).subscribe(response => {
          this.autoComClaims = response['result'];
        });
      }else{
        this.SearchIncentiveClaim.get('claimno').setErrors(null);
      }
    });

    this.SearchIncentiveClaim.controls.dealercode.valueChanges.subscribe((res) => {
      if(res && typeof res == 'string'){
        this.SearchIncentiveClaim.get('dealercode').setErrors({selectFromList:'selectFromList'});
        this.claimService.getDealerList(res).subscribe(response => {
          this.dealercodeList = response['result']
        })
      }else{
        this.SearchIncentiveClaim.get('claimno').setErrors(null);
      }
    });
  
  }
  displayDealerCodeFn(itm){
    return itm?itm.code:"";
  }

  displayClaimNoFn(itm){
    return itm?itm.claimNo:"";
  }

  fromDateChange(event) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      this.maxDate = new Date(event['value']);
      this.maxDate.setMonth(this.maxDate.getMonth() + 1);
      if (this.maxDate > this.today)
        this.maxDate = this.today;
      if (this.SearchIncentiveClaim.get('claimtodate').value > this.maxDate)
        this.SearchIncentiveClaim.get('claimtodate').patchValue(this.maxDate);
    }
  }

}
