declare module "IncentiveSchemeMasterModule" {
    export interface DropDownSchemeTypeDomain {
        schemeType: string;
        id: number;
    }
    export interface DropDownZoneDomain {
        name: string;
        id: number;
    }
    export interface DropDownRegionDomain {
        id: number
        zone: number
        region: string;
    }
    export interface AutoComItemNoDomain {
        id?: number;
        itemNumber: string;
        value?:string;
        model?:string;
		sub_model?:string;
		variant?:string;
		series?:string;
		product?:string; 
        selected?:boolean,
        itemNo?:string
    }
    export interface DropDownProductDomain {
        product: string,
    }

    export interface AutoComActivityNoDomain {
        id: number
        activityNumber: string
    }

    export interface AutoPopolateDataByItemNo {
        subModel: string;
        series: string;
        variant: string;
        model: string;
        product: string;
        itemDescription?: string
        colour?: string
        igst?: number
        unitCost?: number
    }
    export interface DropDownSeriesDomain {
        series: string;
        product?: string;
    }
    export interface DropDownModelDomain {
        series?: string;
        product?: string;
        model: string;
    }

    export interface DropDownSubModelDomain {
        subModel: string;
        series?: string;
        product?: string;
        model?: string;
    }

    export interface DropDownVariantDomain {
        subModel?: string;
        series?: string;
        variant: string;
        product?: string;
        model?: string;
    }

    export interface IncentiveScheme {
        id?: number;
        schemeType: string
        schemeNo: string
        schemeDate: string
        status: string;
        referenceSchemeNo: string
        activityProposal: any
        validFrom: string
        validTo: string
        maxQty: boolean
        claimAttachReq:boolean
        product: any[]
        series: any[]
        model: any[]
        submodels: any[]
        variant: any[]
        itemNo: any[]
        zones: number[]
        regions: number[]
        incentiveSchemeDetails: IncentiveSchemeDetails[]
        activityNo?:AutoComActivityNoDomain

        sproduct?:string;
        sseries?:string;
        smodel?:string;
        ssubModel?:string;
        svariant?:string;
        sitem?:string;
        szone?:string;
        sregion?:string;
         multipartFile:multipartFile[]
    }

    export interface multipartFile{

    }
    
    export interface IncentiveSchemeDetails {
        id?: number;
        t1Quantity: number;
        t1IncentivePerQuantity: number;
        t2Quantity: number;
        t2IncentivePerQuantity: number;
        t3Quantity: number;
        t3IncentivePerQuantity: number;
        t4Quantity: number;
        t4IncentivePerQuantity: number;
        t5Quantity: number;
        t5IncentivePerQuantity: number;
        maximumQuantity?: any;
        dealerCode: string;
        dealerEmployeeCode: string;  
        errorMsg?: string; 
        dealerName: string; 
        dealerEmployeeName: string;
        dealer?: any;
        dealerEmployee?: any;
    }
    
}