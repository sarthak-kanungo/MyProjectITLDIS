declare module "TransferSearchCriteria" {
  export interface SearchEnquiryCode {
    enquiryNumber: string
    id: number
    value: string
  }
  export interface DropDownTehsil {
    tehsil: string,
    id :number,
    displayValue : string
  }
  export interface DropDownSalePerson {
    firstName: string,
    id: number,
    salesPerson: string
  }

  export interface TransferEnquiryDomain {
    autoClose?: any;
    enquiryNumber?: string;
    enquiryType?: any;
    salesPerson?: any;
    tehsil?: any;
    userId: number
  }

  export interface SearchTransferEnquiryByValue {
    tehsil: string;
    district: string;
    salesPerson: string;
    enquiryNumber: string;
    enquiryType: string;
    enquiryStatus: string;
    enquiryDate?: any;
    city: string;
    customerName: string;
    state: string;
    variant: string;
    employeeId: number;
    id: number;
    salesPersonId: number;
    isSelected? : boolean
  }

  export interface SendTransferEnquiry {
    enquiryId: number[];
    transferByEmployeeId: TransferByEmployeeId;
    transferToEmployeeId: TransferByEmployeeId;
  }

  export interface TransferByEmployeeId {
    id: number;
  }

  export interface TransfertoSalesPerson {
    firstName: string;
    id: number
  }
}