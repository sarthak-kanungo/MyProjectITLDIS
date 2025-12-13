export interface SaveGRN {
    accPacInvoice: { id: number };
    createdBy: number;
    driverMobile: string;
    driverName: string;
    grnBy: string;
    lrNo:string;
    grnDate: string;
    grnType: string;
    grnStatus: string;
    grnMachineDetails: Array<MachineInventory>;
    transporter: { id: number };
    transporterVehicleNumber: string;
    grossTotalValue: number;
    id?: number;
    lastModifiedBy?: { id: number };
}

export interface MachineInventory {
    chassisNo: string;
    engineNo: string;
    id?: number;
    invoiceQuantity: number;
    itemDescription: string;
    itemNo: string;
    receiptQuantity: number;
    assessableAmount: number;
    gstAmount:number;
    remarks: string;
    totalValue: number;
    unitPrice: number;
}