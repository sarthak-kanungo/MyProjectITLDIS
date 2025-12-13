import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { SpareDescripancyClaimPresenter } from '../store-presenter/spare-descripancy-claim-presenter';
import { SpareDescripancyClaimStore } from '../store-presenter/spare-descripancy-claim-store';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { SpareClaimService } from '../service/spare-claim.service';
import { ToastrService } from 'ngx-toastr';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';

@Component({
  selector: 'app-page-spare-descripancy-claim',
  templateUrl: './page-spare-descripancy-claim.component.html',
  styleUrls: ['./page-spare-descripancy-claim.component.css']
})
export class PageSpareDescripancyClaimComponent implements OnInit {
  @Input() createSpareDescripancyForm: FormGroup;
  
  @Output() public sendGrnId = new EventEmitter();
  @Output() public sendClaimType=new EventEmitter();
  @Output() public grnDealerId=new EventEmitter();
  @Output() public sendInvoiceId=new EventEmitter();
  isView: boolean = false;
  isEdit: boolean = false;
  isCreate: boolean = false;
  grnList: any
  grnId: number;
  headerData: any;
  claimTypeList: any;
  claimTypeFinalLIst: any
  userType: any;
  userName: string;
  dealerMobileNumber: number;
  loginUserId: number;
  showMrrItemDetails:boolean=false;
  claimTypeValue:string;
  isApprove:boolean=false
  constructor(
    private presenter: SpareDescripancyClaimPresenter,
    private store: SpareDescripancyClaimStore,
    private activatedRoute: ActivatedRoute,
    private service: SpareClaimService,
    private toaster: ToastrService,
    private localStorage: LocalStorageService
  ) { }

  ngOnInit() {
    this.claimType();
    this.userType = this.localStorage.getLoginUser();
    this.userName = this.userType.username;
    this.dealerMobileNumber = this.userType.mobileNo;
    this.loginUserId = this.userType.loginUserId;
    this.checkForOperation()
    // this.createSpareDescripancyForm.get('claimDate').patchValue(date.format('dd st-MMM-yyyy'))
  }
  claimType() {
    this.service.getClaimType().subscribe(type => {
      if (type) {
        this.claimTypeList = type['result'];
      }
    })
  }

  getSystemDate(){
    
  }

  

  selectClaimType(event:any){
     this.claimTypeValue=event.value;
     
    if(event.value==="MRR"){
      this.sendClaimType.emit("MRR")
      // this.showMrrItemDetails=true;
      this.createSpareDescripancyForm.get('grnNo').reset();
      this.createSpareDescripancyForm.get('grnDate').reset();
      // this.createSpareDescripancyForm.get('dealerCode').reset();
      this.createSpareDescripancyForm.get('claimRaisedBy').reset();
      this.createSpareDescripancyForm.get('noOfBoxSent').reset();
      this.createSpareDescripancyForm.get('kaiInvoiceNo').reset();
      this.createSpareDescripancyForm.get('invoiceDate').reset();
      // this.createSpareDescripancyForm.get('mobileNo').reset();
      this.createSpareDescripancyForm.get('noofBoxesRecieved').reset();
      this.createSpareDescripancyForm.get('mode').reset();
      this.createSpareDescripancyForm.get('transporter').reset();
      this.createSpareDescripancyForm.get('lrNo').reset();
      this.presenter.partDetailForm.reset();
    }else{
      this.sendClaimType.emit("Discrepancy Claim")
      this.presenter.partDetailForm.reset();
      this.createSpareDescripancyForm.get('grnNo').reset();
      this.createSpareDescripancyForm.get('grnDate').reset();
      // this.createSpareDescripancyForm.get('dealerCode').reset();
      this.createSpareDescripancyForm.get('claimRaisedBy').reset();
      this.createSpareDescripancyForm.get('noOfBoxSent').reset();
      this.createSpareDescripancyForm.get('kaiInvoiceNo').reset();
      this.createSpareDescripancyForm.get('invoiceDate').reset();
      // this.createSpareDescripancyForm.get('mobileNo').reset();
      this.createSpareDescripancyForm.get('noofBoxesRecieved').reset();
      this.createSpareDescripancyForm.get('mode').reset();
      this.createSpareDescripancyForm.get('transporter').reset();
      this.createSpareDescripancyForm.get('lrNo').reset();
    }
  }

  checkForOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
    }
    else if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true
    } else if(this.presenter.operation==Operation.APPROVE){
      this.isApprove=true
    }
    else {
      this.isCreate = true
    }

  }


  searchGrnNumber(event:any) {
    if(this.presenter.createSpareDesClaimForm.controls.claimType.value==null){
     this.toaster.warning("Please Select Claim Type First !");
     return false;
    }
    this.createSpareDescripancyForm.get('grnNo').valueChanges.subscribe(val => {
      if (val && typeof val != 'object') {
        this.service.autoSearchGrn(val,this.claimTypeValue).subscribe(response => {
          if (response) {
            this.grnList = response['result']
          }
        })
      }

    })
  }

  grnNumberSelected(event: any) {
    this.grnId = event.option.value.grnId;
    this.sendGrnId.emit(this.grnId);

    this.service.getGrnDetails(this.grnId).subscribe(res => {
      if (res) {
        this.headerData = res['result'];
        this.grnDealerId.emit(res['result'].grnDealerId);
        this.createSpareDescripancyForm.get('grnDate').patchValue(this.headerData.grnDate)
        this.createSpareDescripancyForm.get('dealerCode').patchValue(this.headerData.dealerCode)
        this.createSpareDescripancyForm.get('claimRaisedBy').patchValue(this.userName)
        this.createSpareDescripancyForm.get('noOfBoxSent').patchValue(this.headerData.noOfBoxSent);
        this.createSpareDescripancyForm.get('kaiInvoiceNo').patchValue(this.headerData.accpacInvoiceNo)
        this.createSpareDescripancyForm.get('invoiceDate').patchValue(this.headerData.accpacInvoiceDate)
        this.createSpareDescripancyForm.get('mobileNo').patchValue(this.dealerMobileNumber)
        this.createSpareDescripancyForm.get('noofBoxesRecieved').patchValue(this.headerData.noOfBoxReceived)
        this.createSpareDescripancyForm.get('mode').patchValue(this.headerData.mode)
        this.createSpareDescripancyForm.get('transporter').patchValue(this.headerData.transporter)
        this.createSpareDescripancyForm.get('lrNo').patchValue(this.headerData.lrNo);
        this.sendInvoiceId.emit(this.headerData.accpacInvoiceId);
      } else {
        this.toaster.warning("GRN Details Are Not Available")
      }
    })

  }

  keyUp(event: KeyboardEvent) {
    if (event.key == "Backspace" || event.key == 'Delete') {
      this.createSpareDescripancyForm.get('grnDate').reset();
      this.createSpareDescripancyForm.get('dealerCode').reset();
      this.createSpareDescripancyForm.get('claimRaisedBy').reset();
      this.createSpareDescripancyForm.get('noOfBoxSent').reset();
      this.createSpareDescripancyForm.get('kaiInvoiceNo').reset();
      this.createSpareDescripancyForm.get('invoiceDate').reset();
      this.createSpareDescripancyForm.get('mobileNo').reset();
      this.createSpareDescripancyForm.get('noofBoxesRecieved').reset();
      this.createSpareDescripancyForm.get('mode').reset();
      this.createSpareDescripancyForm.get('transporter').reset();
      this.createSpareDescripancyForm.get('lrNo').reset();
    }

  }

  displayGrnNo(grnNo) {
    // console.log(typeof(grnNo),'spareGrnNumber')
    // if(grnNo)
    if(typeof(grnNo)=="string"){
      return grnNo ? grnNo :undefined
    }else{
    return grnNo ? grnNo.grnNo : undefined
    }
  }


}
