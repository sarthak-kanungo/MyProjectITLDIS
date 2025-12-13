import { environment } from "src/environments/environment"

export abstract class bankDetailApi {
    private static readonly module = ''
    private static readonly controller = 'employeeBankDetails'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${bankDetailApi.controller}`
     static readonly validateMobileNo = `${bankDetailApi.apiController}/validateMobileNo`;
     static readonly getCodeMobile= `${bankDetailApi.apiController}/getDealerEmployeeByCodeOrMob`;
      static readonly updateEmployeeBankDetails= `${bankDetailApi.apiController}/updateEmployeeBankDetails`;
      static readonly searchEmployeeBankDetail= `${bankDetailApi.apiController}/searchEmployeeBankDetail`;
    //   static readonly searchEmployeeBankApproval= `${bankDetailApi.apiController}/searchEmployeeBankApproval`;

}