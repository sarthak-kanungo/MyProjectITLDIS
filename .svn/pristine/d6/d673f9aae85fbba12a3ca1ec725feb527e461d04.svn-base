declare module "AddEnquiryFollowup" {

  export interface EnquiryFollowUpDomain {
    brand: string;
    createdBy: string;
    customerName: string;
    enquiryNumber: string;
    enquiryDate: string
    enquiryStatus: string;
    id: number;
    lastModifiedBy: string;
    lostDropReason: string;
    mobileNumber: string;
    model: string;
    nextFollowType: string;
    product: string;
    reason: string;
    remarks: string;
    alternateRemarks: string;
    result: string;
    series: string;
    subModel: string;
    variant: string;
    currentFollowUpDate: string
  }
  export interface AddEnquiryFollowUp {
    createdBy: number;
    enquiry: Enquiry;
    followUpType: string;
    //nextFollowType: string;
    remarks: string;
    currentFollowUpDate: string;
    nextFollowUpDate: string;
    tentativePurchaseDate: string;
  }

  export interface AddEnquiryLostDropDomain {
    brand: string;
    enquiry: Enquiry;
    lostDrop: string;
    model: string;
    reason: string;
    remarks: string;
    result: string;
  }

  export interface Enquiry {
    id: number
  }
  export interface CreatedBy {
    id: number
  }
}