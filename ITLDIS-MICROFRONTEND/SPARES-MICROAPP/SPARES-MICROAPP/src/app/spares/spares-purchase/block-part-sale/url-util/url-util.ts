import { environment } from "src/environments/environment";


export abstract class blockPartsForSale {
    private static readonly module = ''
    private static readonly controller = 'blockPartsForSale'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${blockPartsForSale.controller}`
    
    //   static readonly searchEmployeeBankApproval= `${employeeBankApproval.apiController}/searchEmployeeBankApproval`;
      static readonly getPartsNoByPartNo= `${blockPartsForSale.apiController}/getPartsNoByPartNo`;
      static readonly getPartDetailsByPartNo= `${blockPartsForSale.apiController}/getPartDetailsByPartNo`;
      static readonly getBlockPartForSaleSearch= `${blockPartsForSale.apiController}/getBlockPartForSaleSearch`;
      static readonly changeActiveStatus= `${blockPartsForSale.apiController}/changeActiveStatus`;
}