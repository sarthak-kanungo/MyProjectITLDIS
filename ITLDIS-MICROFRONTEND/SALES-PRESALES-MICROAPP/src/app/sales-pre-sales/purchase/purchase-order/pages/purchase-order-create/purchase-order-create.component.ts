import { MatDialog } from '@angular/material';
import { Component, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PurchaseOrderService } from '../../purchase-order.service';
import { Source, DialogResult } from '../../../../../confirmation-box/confirmation-data';
import { SavePurchaseOrder, MachineDetails, DealerEmployeeMaster, CancelPo } from './purchase-order-create';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { LoginFormService } from '../../../../../root-service/login-form.service'; import { PurchaseOrderCreateService } from './purchase-order-create.service';
import { ToastrService } from 'ngx-toastr';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { EventEmitter } from 'protractor';


@Component({
  selector: 'app-purchase-order-create',
  templateUrl: './purchase-order-create.component.html',
  styleUrls: ['./purchase-order-create.component.scss'],
  providers: [PurchaseOrderCreateService]
})
export class PurchaseOrderCreateComponent implements OnInit {
  // @Select(ZooState) animals$: Observable<string[]>;
  finalValueMachineType:any
  private savePoFormData: SavePurchaseOrder = {} as SavePurchaseOrder;
  public poDetailsFromId: SavePurchaseOrder = {} as SavePurchaseOrder;
  private isDraftOrIsApprove: boolean;
  public isApproveOrReject: boolean;
  public showAllButton:boolean;
  public allImagesUrl: object = {};
  private dialogMsg: string;
  public editPoId: any;
  public isView: boolean;
  public isEdit: boolean;
  public draftMode: boolean;
  isSubmitDisable:boolean = false;
  managementCheck:boolean;
    chequeLeaf:string
    coveringLetter:string
    creditApplication:string
    chequeCopy: string //added by mahesh.kumar
  sendTrueOrFalse: any;
  showCreditFieldEditOrView: boolean;
  showCheckPhoto: any;
  constructor(
    private router: Router,
    public dialog: MatDialog,
    private toasterService: ToastrService,
    private activatedRoute: ActivatedRoute,
    private loginFormService: LoginFormService,
    private purchaseOrderService: PurchaseOrderService,
    private purchaseOrderCreateService: PurchaseOrderCreateService,
  ) { }

  ngOnInit() {
    // console.log(this.getsendShowCreditFieldEdit,'getsendShowCreditFieldEdit')
    this.activatedRoute.paramMap.subscribe(params => {
      if (params && params['params']) {
        if (params['params']['approveId']) {
          this.editPoId = atob(params['params']['approveId']);
          this.isApproveOrReject = true;
          this.getPoDetailsForEdit();
        } else if (params['params']['viewId']) {
          this.editPoId = atob(params['params']['viewId']);
          this.isView = true;
          this.getPoDetailsForEdit();
        } else if (params['params']['editId']) {
          this.editPoId = atob(params['params']['editId']);
          this.isEdit = true;
          this.getPoDetailsForEdit();
        }
      }
    })
    // this.animals$.subscribe(res => {
    //   console.log("res ", res);
    // })
    
    this.purchaseOrderCreateService.fileDeleteSubject.subscribe(documentName => {
       if(documentName=='Cheque Leaf'){
           this.chequeLeaf = null;
       } 
       if(documentName=='Covering Letter'){
           this.coveringLetter = null;
       }
       if(documentName=='Credit Application'){
           this.creditApplication = null;
       }
       //added by mahesh.kumar
       if(documentName=='Cheque Copy'){
        this.chequeCopy = null;
    }
    });
    this.purchaseOrderCreateService.fileSelectionSubject.subscribe(obj => {
        if(obj['documentName']=='Cheque Leaf'){
            this.chequeLeaf = obj['title'];
        } 
        if(obj['documentName']=='Covering Letter'){
            this.coveringLetter = obj['title'];
        } 
        if(obj['documentName']=='Credit Application'){
            this.creditApplication = obj['title'];
        }
        //added by mahesh.kumar
        if(obj['documentName']=='Cheque Copy'){
          this.chequeCopy = obj['title'];
      } 
    });
    this.purchaseOrderCreateService.managementApprovalCheckSubject.subscribe(isCheck => {
        this.managementCheck = isCheck;
    })
  }
  private getPoDetailsForEdit() {
    this.purchaseOrderCreateService.getPoDetailsById(this.editPoId.toString()).subscribe(res => {
      
      if (res) {
         this.poDetailsFromId = res['result'] as SavePurchaseOrder;
        this.poDetailsFromId.isEdit = this.isEdit;
        this.poDetailsFromId.isView = this.isView;
        this.poDetailsFromId.isApproveOrReject = this.isApproveOrReject;
        this.showAllButton = this.poDetailsFromId.showAllButton;
        this.draftMode = this.poDetailsFromId.poStatus==='draft'?true:false;
        this.poDetailsFromId.draftMode = this.draftMode;
        this.chequeLeaf = res['result']['chequeLeaf'],
        this.coveringLetter = res['result']['coveringLetter'],
        this.creditApplication = res['result']['creditApplication'],
        this.chequeCopy = res['result']['chequeCopy'] //added by mahesh.kumar
        
        this.allImagesUrl = {
          chequeLeaf: res['result']['chequeLeaf'],
          coveringLetter: res['result']['coveringLetter'],
          creditApplication: res['result']['creditApplication'],
          chequeCopy: res['result']['chequeCopy'] //added by mahesh.kumar
        }
        
      }
      
    })
  }
  submitOrClearForm(functionalKeyForSaveClear: string, isDraft?: boolean,btnType?:string) {
    this.isDraftOrIsApprove = isDraft;
    this.dialogMsg = isDraft ? 'save' : 'submit';
    this.purchaseOrderService.submitOrResetForm(functionalKeyForSaveClear);

    this.savePoFormData.draftMode = this.isDraftOrIsApprove;
    let tcsPercent = this.savePoFormData.tcsPercent;
    this.savePoFormData.tcsPercent=Number(tcsPercent);
    console.log("Final DATA PO", this.savePoFormData);
    if ((this.isEdit) || !(this.editPoId)) {
      this.dialogMsg = this.isEdit ? 'update' : this.dialogMsg;
      if (this.savePoFormData.isPurchaseFormValid && this.savePoFormData.isMachineDetailsFormValid) {
        let chequeLeafImage: File = this.savePoFormData.chequeLeafImage;
        let coveringLetterImage: File = this.savePoFormData.coveringLetterImage;
        let creditApplicationImage: File = this.savePoFormData.creditApplicationImage;
        let chequeCopyImage: File = this.savePoFormData.chequeCopyImage;
        if(!isDraft && (this.savePoFormData.poType == 'CREDIT' || this.savePoFormData.poType == 'PARTIAL CREDIT')){
            if(chequeLeafImage == null || chequeLeafImage.name == undefined || 
                  coveringLetterImage == null || coveringLetterImage.name == undefined || 
                    creditApplicationImage == null || creditApplicationImage.name == undefined
                    || chequeCopyImage == null || chequeCopyImage.name == undefined){ //updated chequeCopyImage by mahesh.kumar
                if(this.isEdit){
                    if(this.chequeLeaf === null || this.coveringLetter === null || this.creditApplication === null || this.chequeCopy === null){
                        this.toasterService.error('Please upload supporting documents');
                        return;
                    }
                }else{
                    this.toasterService.error('Please upload supporting documents');
                    return;
                }
            }
        }
        this.openConfirmationModal(event);
      }
    } else if (this.isApproveOrReject) {
      if (this.savePoFormData.isMachineDetailsFormValid) {
        this.dialogMsg = btnType;//this.isDraftOrIsApprove ? 'approve' : 'reject';
        let machineDetails: MachineDetails[] = this.savePoFormData.machineDetails;
        let flag:boolean=false;
        machineDetails.forEach(detail => {
            if(detail.amount <= 0){
                this.toasterService.error("Amount should not be less than 0");
                flag = true;
                return false;
            }
        })
        if(flag){
            return false;
        }
        
        this.openApprovalConfirmationModal();
      }
    }
    
  }
  getFormData(event: object) {
    // this.checkFormIsValid(event);
    this.generatePoData(event);

  }
  generatePoData(event) {
    console.log("event ", event);
    if (event['form'] === 'createPo') {
      const poFormData = event['event'] ? event['event'].formData : {};
      // const depot = poFormData.depot ? poFormData.depot.branchName : null;
      // const branchCode = (poFormData.depot && poFormData.depot.) ? poFormData.depot.branchCode : null;
      const { depot = null, branchCode = null } = { depot: poFormData.depot.branchName, branchCode: poFormData.depot.branchCode };
      this.savePoFormData = { ...this.savePoFormData, ...event['event']['formData'], ...event['event']['kaiStatus'][0], depot, branchCode } as SavePurchaseOrder;
    }
    if (event['form'] === 'machineDetails') {
      this.savePoFormData.machineDetails = [] as MachineDetails[];
      this.savePoFormData.machineDetails = [...event['event']['formData']['machineDetails']] as MachineDetails[];
      this.savePoFormData = { ...this.savePoFormData, ...event['event']['formData'] }
    }
    if (event['form'] === 'documentSubmission') {
      if(typeof event['chequeLeaf'] === 'string')
          this.savePoFormData.chequeLeaf = event['chequeLeaf'];
      else    
          this.savePoFormData.chequeLeafImage = event['chequeLeaf'];
      if(typeof event['coveringLetter'] === 'string')
          this.savePoFormData.coveringLetter = event['coveringLetter'];
      else    
          this.savePoFormData.coveringLetterImage = event['coveringLetter'];
      if(typeof event['creditApplication'] === 'string')
          this.savePoFormData.creditApplication = event['creditApplication'];
      else    
          this.savePoFormData.creditApplicationImage = event['creditApplication'];

      //added by mahesh.kumar only if else condition
      if(typeof event['chequeCopy'] === 'string')
          this.savePoFormData.chequeCopy = event['chequeCopy'];
      else    
          this.savePoFormData.chequeCopyImage = event['chequeCopy'];
      
      console.log("this.savePoFormData >>>>>>", this.savePoFormData);
    }
    
  }
  private openApprovalConfirmationModal(){
      let message = `Are you sure you want to ${this.dialogMsg}?`
      const confirmationData = this.setConfirmationModalData(message);
      const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
        width: '500px',
        panelClass: 'confirmation_modal',
        data: confirmationData
      });
      dialogRef.afterClosed().subscribe((result:DialogResult) => {
         if (result.button === 'Confirm') {
            if (this.poDetailsFromId.machineDetails && this.poDetailsFromId.machineDetails.length > 0) {
              this.poDetailsFromId.machineDetails.forEach((element, index) => {
                this.savePoFormData.machineDetails[index].id = element.id
              })
            }
            this.savePoFormData.approverRemark = result.remarkText;
            this.savePoFormData.isApprovalRequired = this.managementCheck;
            this.isSubmitDisable = true;
            this.cancelOrApprovePo(this.savePoFormData);
         }
      })
  }
  
  private openConfirmationModal(event: object) {
    let message = `Are you sure you want to ${this.dialogMsg}?`
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm') {
          this.isSubmitDisable = true;
          this.saveEditPoForm(this.savePoFormData);
      }
    })
  }
  
  private setConfirmationModalData(message: string) {
      const confirmationData = {} as ConfirmDialogData;
      confirmationData.buttonAction = [] as Array<ButtonAction>;
      confirmationData.title = 'Confirmation';
      confirmationData.message = message;
      confirmationData.buttonName = ['Cancel', 'Confirm'];
      if(this.isApproveOrReject){
          confirmationData.remarkConfig = {
              source: Source.APPROVE
          }
      }
      return confirmationData;
  }
  
  private saveEditPoForm(savePoFormData: SavePurchaseOrder) {
    savePoFormData.draftMode = this.isDraftOrIsApprove;
    savePoFormData.dealerEmployeeMaster = {} as DealerEmployeeMaster;
    savePoFormData.dealerEmployeeMaster.id = this.loginFormService.getLoginUserId();
    if (this.isEdit) {
      savePoFormData.id = this.editPoId;
    }
    savePoFormData.machineDetails = [...savePoFormData.machineDetails, ...savePoFormData.deletedParts];
    console.log("savePoFormData ", savePoFormData);
    this.purchaseOrderCreateService.submitPo(savePoFormData).subscribe(res => {
      if (res) {
        this.toasterService.success(this.isEdit ? 'PO updated successfully!' : 'PO created successfully!', 'Success');
        this.router.navigate([this.isEdit ? '../../' : '../../purchase-order'], { relativeTo: this.activatedRoute })
      }else{
        this.isSubmitDisable = false;
      }
    },error=>{this.toasterService.error('Something went wrong !')
      this.isSubmitDisable = false;
    })
  }
  private cancelOrApprovePo(savePoFormData:SavePurchaseOrder) {
    let machineDetails: MachineDetails[] = savePoFormData.machineDetails;
    let cancelPoData = {} as CancelPo;
    cancelPoData.machineDetails = [] as MachineDetails[];
    cancelPoData.machineDetails = machineDetails;
    cancelPoData.purchaseOrderId = this.editPoId;
    cancelPoData.approvalFlag = this.dialogMsg; //contain approve flag
    cancelPoData.userId = this.loginFormService.getLoginUserId();
    cancelPoData.remark = savePoFormData.approverRemark;
    cancelPoData.totalOs = savePoFormData.totalOs;
    cancelPoData.currentOs = savePoFormData.currentOs;
    cancelPoData.os0To30Days = savePoFormData.os0To30Days;
    cancelPoData.os31To60Days = savePoFormData.os31To60Days;
    cancelPoData.os61To90Days = savePoFormData.os61To90Days;
    cancelPoData.os90Days = savePoFormData.os90Days;
    cancelPoData.paymentPending = savePoFormData.paymentPending;
    cancelPoData.netOs = savePoFormData.netOs;
    cancelPoData.pendingOrder = savePoFormData.pendingOrder;
    cancelPoData.orderUnderProcess = savePoFormData.orderUnderProcess;
    cancelPoData.channelFinanceAvailable = savePoFormData.channelFinanceAvailable;
    cancelPoData.exposureAmount = savePoFormData.exposureAmount;

    cancelPoData.totalAmount = savePoFormData.totalAmount;
    cancelPoData.totalGstAmount = savePoFormData.totalGstAmount;
    cancelPoData.basicAmount = savePoFormData.basicAmount;
    cancelPoData.tcsPercent = savePoFormData.tcsPercent;
    cancelPoData.availableLimit = savePoFormData.availableLimit;
    cancelPoData.totalCreditLimit = savePoFormData.totalCreditLimit;
    cancelPoData.isApprovalRequired = savePoFormData.isApprovalRequired;
    this.purchaseOrderCreateService.cancelOrApprovePO(cancelPoData).subscribe(res => {
      if (res) {
        this.toasterService.success(res['message']);
        this.router.navigate(['../../'], { relativeTo: this.activatedRoute })
      }else{
        this.isSubmitDisable = false;
      }
    },error => {
      this.toasterService.error('Transaction Failed','Transaction Failed');
      this.isSubmitDisable = false;
    })
  }
 
  // feed() {
  //   this.feedZebra({ name: 'zeb1', carrots: 10, hay: 13 })
  // }
  // feedZebra(name: ZebraFood) {
  //   this.store.dispatch(new FeedZebra(name));
  // }
  // getDetails() {
  //   const token = this.store.selectSnapshot<FeedZebra>((state) => state);
  //   console.log("token ", token);
  // }

  viewPrint(printStatus:string){
      this.purchaseOrderCreateService.printPO(this.poDetailsFromId.poNumber, printStatus).subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              let headerContentDispostion = resp.headers.get('content-disposition');
              let fileName = headerContentDispostion.split("=")[1].trim();
              const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
              saveAs(file);
            }
       })
   }
   sendTrueOrfalseForCredit(event){
    
    this.sendTrueOrFalse=event
   }

   getPhotos(event:any){
    
    this.showCheckPhoto=event

   }

   machineTypeValue(value:any){
    // console.log(value)
     this.finalValueMachineType=value;
    
   }
 
  
}
