export interface ChannelFinanceLimitUpload { 

    dealerBankDetailsId: DealerBankDetailsId ;		
	flexiLoanAccountNumber: string ;	
	cfCreditLimit: number ;
	availableAmount: number ;
	utilisedLimit: number ;
}

export interface DealerBankDetailsId 
{    
    dealerCode: string ;
	bankName: string ;
}

export interface DealerCodeInvalid {
	notFoundCode: Array<string> ;
	repeatedCode: Array<string> ;
}