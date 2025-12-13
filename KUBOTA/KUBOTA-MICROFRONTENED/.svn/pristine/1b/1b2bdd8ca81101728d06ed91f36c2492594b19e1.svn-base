import { Injectable } from '@angular/core';
import { LineItem, PartReturnItemSubForm } from '../../domain/part-return.domain';

@Injectable()
export class PartReturnService {
    constructor() { }
    convertLineItemIntoPartReturnItemSubForm(lineItemList: LineItem[]): PartReturnItemSubForm[]
    convertLineItemIntoPartReturnItemSubForm(lineItem: LineItem): PartReturnItemSubForm
    convertLineItemIntoPartReturnItemSubForm(lineItem: LineItem | LineItem[]): PartReturnItemSubForm | PartReturnItemSubForm[] {

        if (lineItem && Array.isArray(lineItem)) {
            return lineItem.map((lineItem, index) => {
                return this.convertLineItemIntoPartReturnItemSubForm(lineItem);
            });
        }
        if (lineItem && !Array.isArray(lineItem)) {
            
            const {
                itemNo = null,
                itemDescription = null,
                uom = null,
                reqQuantity = null,
                issuedQuantity = null,
                mrp = null,
                remark = null,
                returnQuantity = null,
                id = null,
                pendingQuantity = null,
                store = null,
                binLocation = null,
                sparePartId = null,
                binId = null,
                storeId=null,
                partIssueItemId = null,
                partsReceivedBy = null
            } = { ...lineItem }
            return {
                itemNo,
                itemDescription,
                uom,
                reqQuantity,
                mrp,
                remark,
                returnQuantity,
                id,
                store,
                binLocation,
                issuedQuantity,
                pendingQuantity: reqQuantity-issuedQuantity,
                sparePartMaster: { id: sparePartId, itemNo: itemNo },
                binLocationMaster: { id: binId },
                storeMaster: { id: storeId },
                sparePartIssue: { id: partIssueItemId },
                partsReceivedBy
            };
        }
    }
}