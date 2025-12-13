import { environment } from "src/environments/environment";

export abstract class employeeBankApproval {
    private static readonly module = ''
    private static readonly controller = 'employeeBankApproval'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${employeeBankApproval.controller}`
    //  static readonly validateMobileNo = `${bankDetailApi.apiController}/validateMobileNo`;
    //  static readonly getCodeMobile= `${bankDetailApi.apiController}/getDealerEmployeeByCodeOrMob`;
    //   static readonly updateEmployeeBankDetails= `${bankDetailApi.apiController}/updateEmployeeBankDetails`;
    //   static readonly searchEmployeeBankDetail= `${bankDetailApi.apiController}/searchEmployeeBankDetail`;
      static readonly searchEmployeeBankApproval= `${employeeBankApproval.apiController}/searchEmployeeBankApproval`;
      static readonly employeeBankGroupApproval= `${employeeBankApproval.apiController}/employeeBankGroupApproval`;

}