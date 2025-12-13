export interface SaveBtBt {
    transferNumber?: string,
    itemDetails: ItemDetailRow[]
}
export interface SearchBtBt {

    fromDate: string,
    toDate: string,
    transferNumber:string,
    page:number,
    size:number
}
export interface ItemNoAuto {
    itemNo: string
    id: number,
    value?: string
}

export interface ItemDetailRow {
    fromBinStock: number,
    fromLocation: IdMaster,
    fromStore: IdMaster,
    id?: number,
    isSelected: boolean,
    itemNo: ItemNoAuto,
    rowId?: number,
    availableStock?:number,        
    toBinStock: number,
    toLocation: IdMaster,
    toStore: IdMaster,
    transferQty: number,
    isValidItem?:string
}
export interface ExcelItemDetail {
    fromStock: number,
    fromLocation: string,
    fromLocationId: number,
    fromStore: string,
    fromStoreId: number,
    id: number,
    itemNo: string,
    itemDescription: string,
    toStock: number,
    toLocation: string,
    toLocationId: number,
    toStore: string,
    toStoreId: number,
    transferQuantity: number,
    availableStock:number,
    isValidItem:string
}
export interface IdMaster {
    id: number,
    value: string
}
