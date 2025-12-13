import { FormGroup } from '@angular/forms';
import { Component, OnInit, Input, EventEmitter, Output, OnChanges, SimpleChanges, OnDestroy } from '@angular/core';

import { PurchaseOrderService } from '../../purchase-order.service';
import { SalesPurchaseOrderCreateService } from './sales-purchase-order-create.service';
import { SavePurchaseOrder } from '../../pages/purchase-order-create/purchase-order-create';
import { PurchaseOrderCreateService } from '../../pages/purchase-order-create/purchase-order-create.service';
import { DatePipe } from '@angular/common';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-sales-purchase-order-create',
  templateUrl: './sales-purchase-order-create.component.html',
  styleUrls: ['./sales-purchase-order-create.component.scss'],
  providers:[DatePipe]
})
export class SalesPurchaseOrderCreateComponent implements OnInit, OnChanges, OnDestroy {

  machineTypeList=[{typeName:"Tractor"},{typeName:"Others"}]
  @Output() getFormStatusAndData = new EventEmitter<object>();
  @Input() poTypeList = [];
  @Input() depoList = [];
  @Input() kaiStatusList = [];
  @Input() isApproval: boolean;
  @Input() poDetailsFromId: SavePurchaseOrder = {} as SavePurchaseOrder;
  public isApproveOrReject: boolean;
  public isView: boolean;
  public isEdit: boolean;
  public kaiStatusHeading = [];
  public createPoForm: FormGroup;
  kaiStatusListLast = [];
//  isCredit:boolean=false
 newDate=new Date();
 @Output() isCredit= new EventEmitter<boolean>();
 @Output() sendShowCreditFieldEditOrView=new EventEmitter<boolean>();
 @Output() sendTypeValue=new EventEmitter<string>();
  showCreditfield: boolean;
  date: string;
  operation: any;
  isCreate: boolean;
  showCreditFieldEditOrView: boolean;

  @Input() public set getTotalAndAvailableCredit(availableData: object) {
    if (availableData !== undefined) {
      this.createPoForm.controls.totalCreditLimit.patchValue(availableData['creditLimit']);
      this.createPoForm.controls.availableLimit.patchValue(availableData['availableLimit']);
    }
  }
  private _dealearCode: string;
  @Input()
  public set dealerCode(v: string) {
    this._dealearCode = v;
    this.patchValueToDepotForDealer();
    this.enableFormControlNameDepot();
  }
  public get isDealerCode(): boolean {
    return typeof this._dealearCode === 'string' && !!this._dealearCode;
  }

  constructor(
    private purchaseOrderService: PurchaseOrderService,
    private purchaseOrderCreateService: PurchaseOrderCreateService,
    private salesPurchaseOrderCreateService: SalesPurchaseOrderCreateService,
    private datepipe:DatePipe,
    private activatedRoute:ActivatedRoute
  ) { }

  ngOnInit() {
    this.checkFormOperation()

    this.kaiStatusHeading = ['Total O/s', 'Current O/s', '0-30 Days', '31-60 Days',
      '61-90 Days', '90 Days', 'Payment Pending', 'NET O/s', 'Pending Orders', 'Order Under Process', 'Channel Finance Available', 'Exposure Amount'];
    this.createPoForm = this.salesPurchaseOrderCreateService.createForm();
    this.purchaseOrderService.submitOrResetFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        let formData = { isPurchaseFormValid: this.createPoForm.valid, ...this.createPoForm.getRawValue() }
        this.getFormStatusAndData.emit({ formData, kaiStatus: this.kaiStatusList });
        this.createPoForm.markAllAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.createPoForm.reset();
      }
    });
    this.purchaseOrderCreateService.getTotalMachineDetailsQuantity$.subscribe(totalQuantity => {
      if (totalQuantity) {
        this.createPoForm.controls.totalQuantity.patchValue(totalQuantity)
      }
    });
    this.getTotalPoAmountForExposureCalculation();
    if (this.depoList && this.depoList.length > 0) {
        const selected = this.depoList.find(depo => depo.defaultDepot === 'Y')
        this.createPoForm.controls.depot.patchValue(selected);
    }
    this.createPoForm.controls.depot.valueChanges.subscribe(depot => {
      // Add new By Ankit rai
      this.purchaseOrderCreateService.depoChangeSubject.next(depot);
      //   code commented By Ankit rai
      //  this.purchaseOrderCreateService.depoChangeSubject.next(depot['branchName']);
    })
    
    this.purchaseOrderCreateService.itemSelectionSubject.subscribe(isDisabled=>{
        if(isDisabled)
            this.createPoForm.controls.depot.disable();
    })

    console.log(this.poDetailsFromId,'poDetailsFromId')
  }
 

 checkFormOperation() {
    this.operation = OperationsUtil.operation(this.activatedRoute);
    console.log(this.operation,'opration')
    if (this.operation == Operation.VIEW) {
      this.isView = true;
    }
    else if (this.operation == Operation.CREATE) {
      this.isCreate = true

    }

  }
  
  ngOnChanges(changes: SimpleChanges) {
    if(this.poDetailsFromId.poType=='CREDIT'){
      
      this.showCreditFieldEditOrView=true
      this.sendShowCreditFieldEditOrView.emit(true)
     }else{
       this.showCreditFieldEditOrView=false
       this.sendShowCreditFieldEditOrView.emit(false)
     }
    if( this.isEdit){
      console.log(this.createPoForm.controls['chequeDate'].value,'form')
      this.date=this.datepipe.transform(this.createPoForm.controls['chequeDate'].value,'dd-MM-yyyy')
    //   this.createPoForm.controls['chequeDate'].patchValue(date)
    //  console.log(date,'datee')
    }
    if (this.poDetailsFromId && this.poDetailsFromId.id) {
      this.isView = this.poDetailsFromId.isView;
      this.isEdit = this.poDetailsFromId.isEdit;

      this.isApproveOrReject = this.poDetailsFromId.isApproveOrReject;
      this.createPoForm.patchValue(this.poDetailsFromId);
      this.createPoForm.controls.poDate.patchValue(new Date(this.poDetailsFromId.poDate))
      this.createPoForm.controls.chequeDate.patchValue(new Date(this.poDetailsFromId.chequeDate))
      this.createPoForm.controls.dealerCode.patchValue(this.poDetailsFromId.dealerMaster.dealerCode);
      this.createPoForm.controls.dealerName.patchValue(this.poDetailsFromId.dealerMaster.dealerName);
      this.createPoForm.controls.billingState.patchValue(this.poDetailsFromId.dealerMaster.billingState);
      let kaiObject = {
        totalOs: this.poDetailsFromId.totalOs,
        currentOs: this.poDetailsFromId.currentOs,
        os0To30Days: this.poDetailsFromId.os0To30Days,
        os31To60Days: this.poDetailsFromId.os31To60Days,
        os61To90Days: this.poDetailsFromId.os61To90Days,
        os90Days: this.poDetailsFromId.os90Days,
        paymentPending: this.poDetailsFromId.paymentPending,
        netOs: this.poDetailsFromId.netOs,
        pendingOrder: this.poDetailsFromId.pendingOrder,
        orderUnderProcess: this.poDetailsFromId.orderUnderProcess,
        channelFinanceAvailable: this.poDetailsFromId.channelFinanceAvailable,
        exposureAmount: this.poDetailsFromId.exposureAmount,
        tcsPercent: this.poDetailsFromId.tcsPercent,
        tcsValue: this.poDetailsFromId.tcsValue,
      }
      let kaiArray = [];
      kaiArray.push(kaiObject);
      this.kaiStatusListLast = kaiArray;
      if (this.depoList && this.depoList.length > 0) {
        const selected = this.depoList.find(depo => depo.branchName.toLowerCase() === this.poDetailsFromId.depot.toLowerCase())
        
        this.createPoForm.controls.depot.patchValue(selected);
      }
      this.createPoForm.disable();
      if (this.isEdit) {
        this.createPoForm.controls.poType.enable();
        this.createPoForm.controls.depot.disable();
      }
      if(this.isView){
          this.createPoForm.controls.depot.disable();
      }
      if (this.isApproveOrReject) {
          this.createPoForm.controls.depot.enable();
      }
    }else{
        this.kaiStatusListLast = this.kaiStatusList;
    }
    this.depoListChanges(changes)
    this.getTotalPoAmountForExposureCalculation();
  }

  poTypeChange(event: any){
    
    if(event.value === 'CREDIT'){
    this.showCreditfield=true
      this.isCredit.emit(true);
    }
    else{
    //  this.isCredit.emit(false);
    this.showCreditfield=false
    this.isCredit.emit(false)
    }  
  }
  private getTotalPoAmountForExposureCalculation() {
    // console.log('exposue amount calculation');
    this.purchaseOrderCreateService.getPoTotalAmountForExposure$.subscribe((poTotal: number) => {
      this.kaiStatusList && this.kaiStatusList.length > 0 ? this.kaiStatusList[0]['exposureAmount'] = Number(this.kaiStatusList[0]['netOs'] + Number(this.kaiStatusList[0]['pendingOrder'] )+Number(this.kaiStatusList[0]['orderUnderProcess'] )+ Number(poTotal )) : 0;
      // console.log("poTotal ", poTotal, typeof poTotal, this.kaiStatusList);
    })

  }
  private depoListChanges(changes: SimpleChanges) {
    if (changes.depoList && changes.depoList.currentValue) {
      this.patchValueToDepotForDealer();
    }
  }
  private enableFormControlNameDepot() {
    if (
      !this.createPoForm
      || (this.createPoForm
        && !this.createPoForm.get('depot'))
    ) {
      return;
    }
    if (!this.isDealerCode) {
      this.createPoForm.controls.depot.enable();
    }
  }
  private patchValueToDepotForDealer() {
    if (
      !this.createPoForm
      || (this.createPoForm
        && !this.createPoForm.get('depot'))
    ) {
      return;
    }
    if (this.depoList && this.depoList.length > 0 && !!this.isDealerCode && !this.isEdit && !this.isView) {
      const depot = this.depoList.find(depot => {
        return depot.defaultDepot === 'Y'
      })
      this.createPoForm.controls.depot.setValue(depot);
    }
  }
  ngOnDestroy() {
    this.purchaseOrderCreateService.getTotalMachineDetailsQuantity$.unsubscribe();
    this.purchaseOrderCreateService.getPoTotalAmountForExposure$.unsubscribe();
  }
  
  selectMachineType(event:any){
       this.createPoForm.controls.machineType.disable();
      this.sendTypeValue.emit(event.value);

  }
}
