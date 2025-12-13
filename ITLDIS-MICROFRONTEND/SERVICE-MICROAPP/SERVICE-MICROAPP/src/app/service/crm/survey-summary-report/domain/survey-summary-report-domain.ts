export interface AutoModel {
    model: string;
}

export interface SubModel {
    subModel: string;
}

export interface SearchPartyResponse {
    id: number;
    activeStatus: string;
    creditLimit: string | number;
    partyCode: string;
    dealerFirmType: string;
    partyName: string;
    partyType: string;
    emailId: string;
    gstNumber: string;
    mobileNumber: string;
    panNumber: string;
    state: string;
    subsidyDealer: string;
    action?:any;
    branchId?:any;
  }

  export interface FieldLevel {
    LEVEL_ID: number
    LEVEL_CODE?: string,
    LEVEL_DESC: string,
    SEQ_NO: string
}
export interface DealerDetails {
    id: number,
    code?: string,
    displayString: string
}
export interface LevelHierarchy {
    org_hierarchy_id: number
    level_id?: number,
    hierarchy_code: string,
    hierarchy_desc: string,
    parent_org_hierarchy_id?: number
}

export interface ChassisNoSelect {
    // id: number,
    // code?: string,
    // displayString: string
    chassisNo:any
}
