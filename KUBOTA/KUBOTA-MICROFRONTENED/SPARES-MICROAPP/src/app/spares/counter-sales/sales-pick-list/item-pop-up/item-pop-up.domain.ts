export interface PartDetails {
    id: number,
    unitPrice: number
    availableQuantity: number;
    binLocation: string;
    binId: number;
    storeId: number;
    store: string;
    uniqueKey: string;
    issueQuantity?: number;
    spmgst?:number;
    spegst?:number;
    spmrp?:number;
}