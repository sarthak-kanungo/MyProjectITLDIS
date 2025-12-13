declare module "EnquirySearchCriteria" {
    export interface EnquiryNoDomain {
        enquiryNo: string;
    }

    export interface DropDownModel {
        model: string;
    }

    export interface DropDownSubModel {
        subModel: string;
    }

    export interface DropDownProduct {
        product: string
    }

    export interface DropDownitemAllData {
        itemDescription: string;
        colour: string;
        subModel: string;
        igst: number;
        variant: string;
        series: string;
        model: string;
        product: string;
        unitCost: number;
    }

    export interface DropDownVariant {
        variant: string;
    }

    export interface DropDownSeries {
        series: string;
    }

    interface SearchEnqiryListDomain {
        enquiryNumber: string;
        enquiryDate?: string;
        nextFollowUpDate?: string;
        remarks: string;
        source: string;
        enquiryStatus?: string
        variant: string;
        currentFollowUpDate?: string;
        followUpType?: string;
        tentativePurchaseDate?: string;
        purposeOfPurchase?: any;
        validationDate?: any;
        model: string;
        mobileNumber: string;
        firstName: string;
        action?: string;
    }

    export interface SearchEnquiryFilterDomain {
        page: number
        size: number
        userId: number
        enquiryNumber?: string
        enquiryType?: string
        model?: string
        salesPerson?: string
        fromDate?: string
        toDate?: string,
        source?: string
        enquiryStatus?: string
        retailConversionActivity?: string
        product?: string
        series?: string
        variant?: string
        itemNo?: string
        finance?: string
        autoClose?: string
        subSidy?: string
        exchange?: string
        nextFollowUpFromDate?: string
        nextFollowUpToDate?: string
        tentativePurchaseFromDate?: string
        tentativePurchaseToDate?: string
    }
}