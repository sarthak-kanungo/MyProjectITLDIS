declare module "LostDropEnquiry" {
    export interface DropDownResult {
        result : string
    }
    
    export interface DropDownLostDrop{
        lostDropReason : string
    }
    
    export interface DropDownReason{
        reason : string
    }
    
    export interface LostDropEnquiryDomain {
        result : string;
        lostDrop : string;
        remarks : string;
        brand : string;
        model : string;
        reason : string
    }
}