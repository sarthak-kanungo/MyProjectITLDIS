
declare module "channel-finance-indent" {

    export interface BankListDomain {
        bankName: string;
    }

    export interface CFIDomain {
        flexiLoanAccountNumber: string;
        category: string;
        numberOfDays: string;
        cfCreditLimit: string;
        availableLimit: string;
        utilisedLimit: string;
    }

    export interface SaveCFI {
        available: number;
        bankName: string;
        dealerCategory: string;
        dealerCode: string;
        dealerEmployeeMaster: DealerEmployeeMaster;
        flexiLoanAccountNumber: string;
        indentAmount: number;
        limit: number;
        numberOfDays: number;
        utilized: number;
        channelFinanceIntentDetail: Array<any>;
    }
    export interface DealerEmployeeMaster {
        id: number;
    }

    export interface ChannelFinanceIntentDetail {
        invoiceNumber: number;
        invoiceDate: string;
        invoiceAmount: number;
        ageing: string;
        status: string;
        utilisedAmount: number;
        channelFinanceAmount: number;
    }
}
