// import { QuotationImplements } from "../../pages/quotation-create/create-quotation";

declare module "add-implements-dto" {

  export interface ItemNoDomain {
    //code: string;
    //value:string;
    itemNo:string;  
    uuid?: string;
  }
  export interface EditableTableAutocompleteList {
    config: object;
    list: Array<ItemNoDomain>;
    searchKey: string;
  }

  export interface ImplementsDomain {
    itemDescription:string  
    color: string,
    cgst: number,
    igst: number,
    sgst: number,
  }

  export interface ImplementsDisplayFormDomain {
    itemDescription: string;
    itemNo: string;
    color: string;
    qty: string;
    rate: string;
    discount: string;
    gst: string;
    gstamt: string;
    total: string;
  }
  export interface ChargesFormDomain {
    insurance: string;
    rto: string;
  }
  export interface CalcDisplayFormDomain {
    basicValue: string;
    discount: string;
    gstAmt: string;
    charges: string;
    totalAmt: string;
  }

  export interface SendValidAddImplementsFormDomain<T> {
    editableTableData: Array<T>;
    implementsDisplayForm: ImplementsDisplayFormDomain;
    chargesForm: ChargesFormDomain;
    calcDisplayForm: CalcDisplayFormDomain;
  }
}