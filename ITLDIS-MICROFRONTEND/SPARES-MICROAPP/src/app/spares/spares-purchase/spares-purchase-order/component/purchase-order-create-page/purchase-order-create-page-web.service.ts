import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SparePoApi } from '../../url-utils/spares-purchse-order-urls';
import { SaveSparesPurchaseOrder, SparesPOPartDetails, SpareLocalPartMaster, IdMaster } from '../../domain/spares-purchase-order.domain';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { typeofExpr } from '@angular/compiler/src/output/output_ast';
import { TypeOf } from 'src/app/utils/check-typeof';
@Injectable()
export class PurchaseOrderCreatePageWebService {

    private readonly printPOUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}${urlService.printpourl}`;

  constructor(
    private httpClient: HttpClient
  ) { }

  public saveAndEditPo(data: SaveSparesPurchaseOrder) {
    return this.httpClient.post(SparePoApi.saveSparePurchaseOrder, data)
  }
  public getPoDetailsFromId(poId: string, orderType: string) {
    return this.httpClient.get(SparePoApi.getSparePurchaseOrderById, {
      params: new HttpParams().set('id', poId).set('orderType', orderType)
    })
  }
  public approveSpareOrder(remark:string, poId: string, flag:string){
      return this.httpClient.get(SparePoApi.apiController+"/approveSparePurchaseOrder", {
          params: new HttpParams().set('poId', poId).set('remark', remark).set('flag', flag)
        })
        
  }
  public createSavePoObject(formValues:any, isDraft:boolean, editRecordId:number): SaveSparesPurchaseOrder {
  debugger
    let saveSparesPO: SaveSparesPurchaseOrder = {} as SaveSparesPurchaseOrder;
    saveSparesPO.orderType = {} as IdMaster;
    saveSparesPO.sparesMtSupplier = {} as IdMaster;
    let orderType = formValues.partsOrdering.orderType.orderType;
    saveSparesPO.sparePurchaseOrderPartDetail = [] as SparesPOPartDetails[];
    saveSparesPO.sparePurchaseOrderPartDetail = formValues.itemDetails as SparesPOPartDetails[];
    
    /*if ((orderType === 'Other Suppliers') || (orderType === 'Oil and Lubricant') || (orderType === 'Co-Dealer Oil Order')) {
      saveSparesPO.sparePurchaseOrderPartDetail.forEach((element: SparesPOPartDetails) => {
        let spareLocalPartMaster: SpareLocalPartMaster = {} as SpareLocalPartMaster;
        spareLocalPartMaster.id = element.sparePartMaster.id;
        spareLocalPartMaster.dmsItemNumber = element.sparePartMaster.itemNo;
        element.tcsPercent = formValues.poTotal.tcsPerc;
        if(element.tcsPercent!=null){
            element.tcsValue = parseFloat(((element.totalAmount * element.tcsPercent) / 100).toFixed(2));
            //element.totalAmount = parseFloat((element.totalAmount + ((element.totalAmount * element.tcsPercent) / 100 )).toFixed(2));
        }
        element.spareLocalPartMaster = spareLocalPartMaster;
        delete element.sparePartMaster;
      });
    }else{*/
        saveSparesPO.sparePurchaseOrderPartDetail.forEach((element: SparesPOPartDetails) => {
            element.tcsPercent = formValues.poTotal.tcsPerc;
            if(element.tcsPercent!=null){
                element.tcsValue = parseFloat(((element.totalAmount * element.tcsPercent) / 100 ).toFixed(2));
                //element.totalAmount = parseFloat((element.totalAmount + ((element.totalAmount * element.tcsPercent) / 100 )).toFixed(2));
            }
          });
    //}
    saveSparesPO.draftFlag = isDraft;
  
    saveSparesPO.freightBorneBy = formValues.partsOrdering.freightBorneBy;
    //  code for OpsId;
    if(formValues.partsOrdering.orderPlanningNo && (formValues.partsOrdering.orderPlanningNo.id !== null || formValues.partsOrdering.orderPlanningNo.id !== undefined)){
      saveSparesPO.opsId = formValues.partsOrdering.orderPlanningNo.id ? formValues.partsOrdering.orderPlanningNo.id : formValues.partsOrdering.opSheetNoId;
  }
  
       console.log(typeof(saveSparesPO.orderType))

      // check here draftflage for true and false
    if(saveSparesPO.draftFlag===true && typeof( saveSparesPO.orderType)==='object'){
      saveSparesPO.orderType = 
      {
        id :
        formValues.partsOrdering.orderTypeId?formValues.partsOrdering.orderTypeId:formValues.partsOrdering.orderType.id,
        // orderType:formValues.partsOrdering.orderType
       };
    }else{
    if(saveSparesPO.draftFlag===false && typeof( saveSparesPO.orderType)==='object' && formValues.partsOrdering.orderPlanningNo!=null){
      saveSparesPO.orderType = 
      {
         id :
       formValues.partsOrdering.orderTypeId?formValues.partsOrdering.orderTypeId:formValues.partsOrdering.orderType.id,
        orderType:formValues.partsOrdering.orderType
       };
       
    }
    
    else{
      saveSparesPO.orderType=formValues.partsOrdering.orderType
      
    }
  }

    saveSparesPO.supplierType = formValues.partsOrdering.supplierType;
    saveSparesPO.transportMode = formValues.partsOrdering.transportMode;
    saveSparesPO.transporter = formValues.partsOrdering.transporter;
    if(formValues.partsOrdering.supplierName!=null && formValues.partsOrdering.supplierName.id!=null)
        saveSparesPO.sparesMtSupplier = formValues.partsOrdering.supplierName;
    else
        saveSparesPO.sparesMtSupplier = null;
    if(orderType === 'Co-Dealer Order' || orderType === 'Co-Dealer Oil Order'){
        saveSparesPO.coDealerMaster = formValues.partsOrdering.coDealerMaster;
    }
    if(formValues.partsOrdering.serviceJobCard!=null && formValues.partsOrdering.serviceJobCard.id!=null)
        saveSparesPO.serviceJobCard = {id : formValues.partsOrdering.serviceJobCard.id};
    else
        saveSparesPO.serviceJobCard = null;
    saveSparesPO.priceType = formValues.partsOrdering.priceType;
    saveSparesPO.remarks = formValues.partsOrdering.remarks;
    if (editRecordId) {
      saveSparesPO.id = editRecordId as number;
    }
    return saveSparesPO;
  }
  
  printPO(ponumber:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printPOUrl, {
          params: new HttpParams().set('ponumber', ponumber)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
