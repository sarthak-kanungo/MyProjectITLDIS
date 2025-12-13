
declare module "ActualReportModule" {
    import { UploadableFile } from "itldis-file-upload";

    export interface ActivityReportCreateDomain {
        activityNo: string;
        activityCreationDate: string;
        activityType: string;
        fromDate: string;
        toDate: string;
        activityStatus: string;
        actualFromDate: string;
        actualToDate: string;
        totalEnquiries: string;
        enquiriesPending: string;
        totalRetails: string;
        totalBookings: string
    }

    export interface AutoActivityNo {
        activityNumber: string
        id: number
    }
    export interface AutoPopulateActivityNo {
        fromDate: string;
        toDate: string;
        activityType: string;
        activityNumber: string;
        activityStatus: string;
        activityCreationDate: string;
    }
    export interface ActivityReportEnquiryDetails {
        model: string;
        mobileNumber: string;
        status: string;
        totalBookings: number;
        totalRetails: number;
        tentativePurchaseDate?: any;
        customerName: string;
        firstName?: string
        enquiryDate?: any;
        enquiryStatus? : string
        id: number;
        variant: string;
        totalEnquiries: number;
        enquiryNumber: string;
        enquiryType: string;
        isContacted: boolean
    }

    export interface MarketingActivityReportCreateDomain {
        marketingActivityReport: MarketingActivityReport;
        marketingActivityReportImages: MarketingActivityReportImage[];
    }

    // export interface MarketingActivityReportImage {
    //     photoPath: any;
    // }

    export interface MarketingActivityReport {
        actualFromDate: string;
        marketingActivityProposal: MarketingActivityProposal;
        actualToDate: string;
        totalBookings: number;
        totalEnquiries: number;
        totalRetails: number;
    }

    export interface MarketingActivityProposal {
        activityProposalId: number;
    }

    export interface ViewActualReport {
        activityType: string
        marketingActivityProposal: MarketingActivityProposalView;
        actualFromDate: string;
        reportId: number;
        actualToDate: string;
        totalEnquiries: number;
        totalRetails: number;
        marketingActivityReportImages: MarketingActivityReportImageView[];
        activityReportEnquiryDetails: ActivityReportEnquiryDetailsView[]
    }

    export interface MarketingActivityReportImageView {
        photoPath: any
        id: number;
    }

    export interface MarketingActivityProposalView {
        activityNumber: string;
        activityStatus: string;
        fromDate: string;
        toDate: string;
        activityType: string;
        activityTypeName: string;
        activityCreationDate: string;
    }

    export interface ActivityReportEnquiryDetailsView {
        enquiry: Enquiries;
        isContacted: boolean;
        activityReportEnquiryId: number;
        tentativeDateOfPurchase: string;
    }

    export interface Enquiries {
        model: string;
        firstName: string;
        lastName: string;
        mobileNumber: string;
        enquiryNumber: string;
        enquiryType: string;
        enquiryStatus: string;
        enquiryDate: string;
        variant: string;
    }
    export interface ActivityReportSearchTableDomain {
        activityNo: string;
        activityType: string;
        activityStatus: string;
        fromDate: string;
        toDate: string
    }
    export interface activityStatusSearchDomain {

        statusName: string
    }
    export interface ActivityTypeDomain {
        sourceName: string
    }
    export interface AutoActivityNoSearch {
        id: number
        activityNumber: string
        displayString: string
    }

    export interface FilterSearchReport {
        activityNumber?: string;
        //activityStatus?: string;
        activityType?: string;
        fromDate?: string;
        page: number;
        size: number;
        toDate?: string;
        hierId?:number
    }

    export interface SearchActivityReport {
        actualToDate: string;
        activityNumber: string;
        activityCreationDate: string;
        activityType: string;
        totalEnquiries: number;
        totalBookings: number;
        fromDate: string;
        totalRetails: number;
        activityStatus: string;
        actualFromDate: string;
        toDate: string;
    }

    export interface SubmitActivityReport {
        marketingActivityReport: ActivityReport,
        multipartFileList: UploadableFile[]
      }

   export interface ActivityReport {
        actualFromDate: string;
        actualToDate: string;
        totalEnquiries: number;
        totalRetails: number;
        totalBookings: number;
        activityReportEnquiryDetails: ActivityReportEnquiryDetail[];
        marketingActivityProposal: MarketingActivityProposal;
        marketingActivityReportImages: File[];
      }
      
     export interface MarketingActivityReportImage {
        photoPath: string;
      }
      
     export interface MarketingActivityProposal {
        activityProposalId: number;
      }
      
     export interface ActivityReportEnquiryDetail {
        tentativeDateOfPurchase: string;
        isContacted: boolean;
        enquiry: Enquiry;
      }
      
     export interface Enquiry {
        id: number;
      }

}

