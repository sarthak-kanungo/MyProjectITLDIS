import { Component, OnInit, EventEmitter, Output, Input, OnChanges, SimpleChanges } from '@angular/core';
import { SalesPurchaseOrderContainerService } from './sales-purchase-order-container.service';
import { SavePurchaseOrder } from '../../pages/purchase-order-create/purchase-order-create'; 
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { SalesPurchaseOrderCreateService } from '../sales-purchase-order-create/sales-purchase-order-create.service';
@Component({
  selector: 'app-sales-purchase-order-create-container',
  templateUrl: './sales-purchase-order-create-container.component.html',
  styleUrls: ['./sales-purchase-order-create-container.component.scss'],
  providers: [SalesPurchaseOrderContainerService, SalesPurchaseOrderCreateService]
})
export class SalesPurchaseOrderCreateContainerComponent implements OnInit, OnChanges {
  public poTypeDropdown = [];
  public depoDropdown = [];
  public kaiStatus = [];
  @Input() poApprovalDetails: SavePurchaseOrder = {} as SavePurchaseOrder;
  @Output() sendFormData = new EventEmitter<object>();
  public totalAndAvailableCredit: object;
  public dealerCode: string;
  @Output() sendTrueOrfalseForCredit=new EventEmitter<boolean>();
  @Output() getsendShowCreditFieldEdit=new EventEmitter<boolean>();
  @Output() machineTypeValue=new EventEmitter<boolean>();
  constructor(
    private loginService: LoginFormService,
    private purchaseOrderConainerService: SalesPurchaseOrderContainerService
  ) {
    this.dealerCode = this.loginService.getLoginUserDealerCode();
  }

  ngOnInit() {
    this.getPoTypes();
    //this.getTotalCreditsFromDealerCode();
  }
  ngOnChanges(changes: SimpleChanges) {
   // if (changes.poApprovalDetails.currentValue && !changes.poApprovalDetails.currentValue.id) {
      let poid = null;
      //alert("id: "+this.poApprovalDetails.id)
      if(this.poApprovalDetails && this.poApprovalDetails.id){
          poid= this.poApprovalDetails.id;
      }
      this.getDepos(poid);
      this.getKaiStatus(poid);
    //  this.getKaiStatusChannelFinance();
    /*} else {
      this.getDepos(changes.poApprovalDetails.currentValue.id);
    }*/
  }
 /* private getTotalCreditsFromDealerCode() {
    this.purchaseOrderConainerService.getTotalCreditsFromDealerCode(this.dealerCode).subscribe(res => {
      if (res) {
        this.totalAndAvailableCredit = res['result'];
      }
    })
  }
*/
  private getPoTypes() {
    this.purchaseOrderConainerService.getPoTypes().subscribe(res => {
      this.poTypeDropdown = res['result'];
      
    }, error => {
      console.log("error ", error);
    })
  }
  private getDepos(poId?: string) {
    this.purchaseOrderConainerService.getDepos(poId).subscribe(res => {
      this.depoDropdown = res['result'];
      
    }, error => {
      console.log("error ", error);
    })
  }
  private getKaiStatus(poId?: string) {
    this.purchaseOrderConainerService.getKaiOutstandingStatus(this.dealerCode,poId).subscribe(res => {
      this.kaiStatus = [res['result']];
      console.log("this.kaiStatus : "+this.kaiStatus)
    }, error => {
      console.log("error ", error);
    })
  }
  /*private getKaiStatusChannelFinance() {
    this.purchaseOrderConainerService.getKaiStatusChannelFinance(this.dealerCode).subscribe(res => {
      console.log("error ", this.kaiStatus, res);
      if (res && this.kaiStatus.length > 0) {
        this.kaiStatus['0']['channelFinanceAvailable'] = res['result']['channelFinanceAvailable'];
      }
    }, error => {
      console.log("error ", error);
    })
  }
*/
  getFormStatusAndDataWithFormName(event) {
    this.sendFormData.emit({ form: 'createPo', event: event })
  }
  getIsCreditValue(event){
   
    this.sendTrueOrfalseForCredit.emit(event)

  }
  getsendShowCreditFieldEditOrView(event){

    this.getsendShowCreditFieldEdit.emit(event);
    
    // this.getsendShowCreditFieldEdit=event
    // console.log('image',this.getsendShowCreditFieldEdit)
  }

  sendTypeValue(value:any){
    this.machineTypeValue.emit(value);
    // console.log(value,'value')
  }
  
}
