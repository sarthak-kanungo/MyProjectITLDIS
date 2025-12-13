
export interface DealerMasterDropdown {
    id: number;
    value: string;
}

export interface SearchDealerMaster {
    zone: string;
    region: string;
    area: string;
    territoryLevel: string;
    dealerCode: string;
    dealerName: string;
    activeStatus: string;
    subsidyDealer: string;
    page: number;
    size: number;
}

export interface SearchResponse {
    id: number;
    activeStatus: string;
    creditLimit: string | number;
    dealerCode: string;
    dealerFirmType: string;
    dealerName: string;
    dealerType: string;
    emailId: string;
    gstNo: string;
    mobileNo: string;
    panNo: string;
    state: string;
    subsidyDealer: string;
}