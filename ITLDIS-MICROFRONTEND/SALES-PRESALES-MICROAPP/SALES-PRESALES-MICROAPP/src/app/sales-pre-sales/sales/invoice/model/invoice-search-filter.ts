export class InvoiceSearchFilter {
    static readonly type = '[Invoice] InvoiceSearchFilter';
    chassisNo?: string;
    customerName?: string;
    engineNo?: string;
    enquiryNumber?: string;
    enquiryType?: string;
    fromDate?: string;
    invoiceNumber?: string;
    invoiceStatus?: string;
    invoiceType?: string;
    itemNo?: string;
    mobileNo?: string;
    model?: string;
    page: number;
    product?: string;
    series?: string;
    size: number;
    subModel?: string;
    toDate?: string;
    variant?: string
}

export class InvoiceSearchForm extends InvoiceSearchFilter {

}
