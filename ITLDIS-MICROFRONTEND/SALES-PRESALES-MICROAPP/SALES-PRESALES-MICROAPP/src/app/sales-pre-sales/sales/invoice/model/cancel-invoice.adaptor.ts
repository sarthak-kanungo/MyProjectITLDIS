import { Adapter } from '../../../../core/adapter';

export class CancelInvoice {
    //brand: string;
    cancellationType: string;
    invoiceCancellationReason: string;
    invoiceId: number;
    //model: string;
    reason: string;
    remarks: string;
}

export class CancelInvoiceAdaptor implements Adapter<CancelInvoice> {
    constructor() { }
    public adapt<R>(item: any, keyMap?: any): CancelInvoice | R {
        throw new Error("Method not implemented.");
    }
    public saveAdapt?<R>(record: any): CancelInvoice | R {
        return {
            //brand: record.brand || null,
            cancellationType:record.cancellationType || null,
            invoiceCancellationReason: record.invoiceCancellationReason || null,
            invoiceId: record.invoiceId || null,
            //model: record.model || null,
            reason: record.reason || null,
            remarks: record.remarks || null
        } as CancelInvoice
    }
}