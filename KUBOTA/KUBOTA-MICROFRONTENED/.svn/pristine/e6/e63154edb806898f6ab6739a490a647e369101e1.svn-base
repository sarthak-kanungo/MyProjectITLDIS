import { SelectList } from '../../../../core/model/select-list.model';

export interface PartRequisition {
    requisitionPurpose: string | SelectList;
    partRequisitionStatus: string;
    sparePartRequisitionItem: SparePartRequisitionItem[];
    type: string; // APR
}
export interface SparePartRequisitionItem {
    amount?: 0;
    priceUnit?: 0;
    reqQuantity?: 0;
    sparePartMaster: {
        id: number;
        itemNo: string
    };
    uom: string;
}
export interface PartRequisitionForm {
    partRequisition: PartRequisitionSubForm;
    sparePartRequisitionItem: SparePartRequisitionItemSubForm[];
}
export interface PartRequisitionSubForm {
    requisitionPurpose: string | SelectList;
    requestedBy: string;
    requisitionNo: number;
    requisitionDate: string;
    serviceJobCard: string;
    jobCardDate: string;
}
export interface SparePartRequisitionItemSubForm {
    itemNo: SelectList | string;
    itemDescription: string;
    uom: string;
    requisitionQty: number;
    isSelected: boolean;
    sparePartMaster: {
        id: number;
        itemNo: string
    }
}
export interface SparePartRequisitionSearchFilter {
    page: number;
    size: number;
    fromDate?: string;
    jobCardNo?: string;
    requisitionNo?: string;
    requisitionPurpose?: string;
    toDate?: string;
}
export interface SparePartRequisitionSearchResult {
    id: number;
    requisitionNo: string;
    requisitionPurpose: string;
    requestedBy: string;
    requisitionDate: string;
    jobCardNo?: string;
    jobCardDate?: string;
}
export interface PartRequisitionById {
    sparePartRequisition: SparePartRequisitionById;
    sparePartRequisitionItem: SparePartRequisitionItemById[];
}
export class SparePartRequisitionById {
    requisitionPurpose: string;
    requestedBy: String;
    jobCardNo: String;
    jobCardId: String;
    id: number;
    jobCardDate: string;
    requisitionNo: string;
}
export class SparePartRequisitionItemById {
    itemNo: string | SelectList;
    uom: string;
    requisitionQty: number;
    itemDescription: string;
    sparePartId: number;
    id: number;
}