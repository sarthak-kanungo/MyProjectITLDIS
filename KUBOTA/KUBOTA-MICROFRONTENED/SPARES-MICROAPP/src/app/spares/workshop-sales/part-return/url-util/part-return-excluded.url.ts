import { PartReturnUrl } from './part-return.url';

export abstract class PartIssueExcludedUrl {
    static urls: Array<string> = [
       PartReturnUrl.searchByRequisitionNoMobileNoEmpName,
       PartReturnUrl.searchByJobCardNoForPartReturn,
       PartReturnUrl.searchByReturnNo,
       PartReturnUrl.searchByRequisitionNo,
       PartReturnUrl.searchByJobCardNo,
       PartReturnUrl.searchPartReturn,
       PartReturnUrl.searchPartReturn,
    ]
}