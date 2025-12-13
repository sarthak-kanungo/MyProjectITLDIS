import { Component, EventEmitter, HostListener, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';

import { OPSService } from '../service/ops.service';
import { SparesPurchaseOrderWebService } from '../../spares-purchase-order/component/purchase-order/spares-purchase-order-web.service';
import { opsPresenter } from '../order-planning-sheet-page/order-planning-sheet-presenter';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { OpsStore } from '../order-planning-sheet-page/order-planning-sheet-store';


@Component({
  selector: 'app-create-order-planning-sheet',
  templateUrl: './create-order-planning-sheet.component.html',
  styleUrls: ['./create-order-planning-sheet.component.css'],
  providers:[SparesPurchaseOrderWebService]
})
export class CreateOrderPlanningSheetComponent implements OnInit {
@Input() orderPlanningForm:FormGroup
  @Input() partDetails:FormArray;
  reOrderList: any;
  suggestionList: any;
  logicData: any;
  orderTypeList: any;
  isView: boolean;
  isEdit: boolean;
  isCreate: boolean;
  itemDetailsList:any;
  currentPage = 1;
  pageSize = 500; 
  @Output() orderTypeId=new EventEmitter();
  @Output() logicId=new EventEmitter();
  @Output() count=new EventEmitter();

  orderTypeIdForPayload:number;
  logicTypeIdForPayload:number;
 
  constructor(
    private service:OPSService,
    private sparesPurchaseOrderWebService:SparesPurchaseOrderWebService,
    private presenter:opsPresenter,
    private activedRouter:ActivatedRoute,
    private store:OpsStore
    ) { }

  ngOnInit() {
    this.getReOrderQtyMonths()
    this.orderPlanningForm.get('suggestionOrderQty').disable();
    this.orderPlanningForm.get('orderType').disable();
     this.orderPlanningForm.get('logic').disable();
    this.checkFormOperation()
  //  this.getlogicData();
  }
  private checkFormOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activedRouter);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
      
      
    }
    else if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true
      
    }
    else if (this.presenter.operation == Operation.CREATE) {
      this.isCreate = true
      
    }

  }
  private getReOrderQtyMonths(){
    this.service.getReorderQty().subscribe(res=>{
      if(res){
        
        this.reOrderList=res['result'];
        
      }
     
    })
  }
  selectedOrderQty(event:any){
   this.orderPlanningForm.get('suggestionOrderQty').enable();
   this.getSuggestionQty()
  }

  private getSuggestionQty(){
    this.service.getSuggestedQty().subscribe(res=>{
      this.suggestionList=res['result']
    })
  }
  selectedSuggestionQty(event:any){
    this.orderPlanningForm.get('orderType').enable();
   
    this.getOrderTypesFromSupplierType();
  
  }
  private getlogicData(){
     this.service.getLogic().subscribe(res=>{
      if(res){
        this.logicData=res['result'];
      }
     
     })
  }
  private getOrderTypesFromSupplierType() {
    this.service.getOrderType().subscribe(res => {
      if (res)
        this.orderTypeList = res['result'];
      //  this.orderTypeList.shift();
    })
  }

  selectedOrderType(event){
     this.orderTypeId.emit(event.value.id);
     this.orderTypeIdForPayload=event.value.id;

    this.orderPlanningForm.get('logic').enable();
    this.getlogicData()
  }
  selectedLogic(event){
    this.logicId.emit(event.value.id);
    this.logicTypeIdForPayload=event.value.id;
  //  this.logicId=event.value.id
  const  postData={
    
    logicId:this.logicTypeIdForPayload,
    reOrderMonth:this.orderPlanningForm.get('orderQtyMonth').value.value ,
    suggestedOrderMonth: this.orderPlanningForm.get('suggestionOrderQty').value.value,
    orderTypeId:this.orderTypeIdForPayload,
    }
    this.partDetails.clear();
    this.service.getItemDetails(postData).subscribe(res => {
      const partsArray = res['result'].map((element: any) => {
        const part = this.store.buildmaterialDetailsForm();
        part.patchValue({
          itemNo: element.itemNo,
          itemDesc:element.itemDescription,
          reOrderQty:element.reorderQty,
          dealerStock:element.dealerStock,
         
          kaiBackOrder:element.kaiBackOrder,
          transitFromKai: element.transitFromKai,
          l12mSales:element.l12mSales,
          suggestedOrderQty:element.suggestedOrderQty,
          actualOrderQty:element.actualOrderQty,
          unitPrice:element.unitPrice,
          gstPercent:element.gstPercent,
          id:element.id,
          sparepartId:element.itemId
          // editsparepartId:element
          // totalOrderValue:element.totalOrderValue,
          // unitPrice:element.unitPrice,
          // actualOrderQty:element.actualOrderQty
    
        });
        return part;
      });
    
      this.partDetails.controls.push(...partsArray);
    });
  }    
  
}
