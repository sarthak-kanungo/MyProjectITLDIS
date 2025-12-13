declare module "ActivityProposalModule" {

    export interface MarketingActivitySearchDomain {

        activityNo: string,
        activityType: string,
        activityStatus: string,
        activityFromDate: string,
        activityToDate: string
    }
    export interface activityTypeSearchDomain {
        activityType: string,
        id : number
    }
    export interface activityStatusSearchDomain {
        statusName: string
    }

    export interface AutoActivityNoSearch {
        id: number,
        activityNumber: string,
        displayString: string
    }
    export interface AutoDealerCodeSearch {
        id: number,
        code: string,
        name: string,
        displayString: string
    }
    export interface ActivityProposalListSearchDomain {
        activityCreationDate: string;
        activityNumber: string;
        id: number;
        activityType: string;
        toDate: string;
        activityPurpose: string;
        proposedBudget: number;
        numberOfDays: number;
        activityStatus: string;
        maxAllowedBudget: number;
        location: string;
        fromDate: string;
        dealerCode?:string;
    dealerName ?: string;

    }

    export interface SearchFilterActivityProposalDomain {
        page: number,
        size: number,
        searchFlag?:boolean;
        activityNumber?: string,
        activityType?: number,
        activityStatus?: string,
        fromDate?: string,
        toDate?: string,
        dealerCode?: any,
        dealerId?:number,        
        hierId?:number,
        orgHierLevel1?: any,
        orgHierLevel2?: any,
        orgHierLevel3?: any,
        orgHierLevel4?: any,
        orgHierLevel5?: any        
    }
    
    export interface ZoneRegionAreaTerritory{
        id:number,
        code:string,
        name:string
    }
}