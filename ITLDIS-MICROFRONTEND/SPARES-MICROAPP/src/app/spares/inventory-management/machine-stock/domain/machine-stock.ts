export interface ItemNumber {
    itemNo: string;
    value?: string;
    id?: number;
    itemNumber?: string;
}

export interface PopulateByItemNo {
    model?: string;
    subModel?: string;
    variant?: string;
    series?: string;
    product?: string;
    itemDescription?: string;
}

export interface Model {
    model: string;
}

export interface SubModel {
    subModel: string;
}

export interface Product {
    product: string
}

export interface Series {
    series: string;
}

export interface Variants {
    variant: string;
}
export interface AutoDealerCodeSearch {
    id: number,
    code: string,
    name: string,
    displayString: string
}
export interface SearchMachineStock{
    page:number;
    size:number;
    product:string;
    series:string;
    model:string;
    subModel:string;
    variant:string;
    itemNo:string;
    chassisNo:string;
    engineNo:string;
    status:string;
    grnDoneFlag:any;
    dealerCode:string;
    orgHierId:number;
}