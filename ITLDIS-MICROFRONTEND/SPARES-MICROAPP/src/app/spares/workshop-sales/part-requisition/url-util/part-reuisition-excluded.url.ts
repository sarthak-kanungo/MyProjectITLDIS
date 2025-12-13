import { PartRequisitionUrl } from './part-requisition.url';

export abstract class PartReuisitionExcludedUrl {
    static urls: Array<string> = [
       PartRequisitionUrl.searchPartRequisitionNo,
       PartRequisitionUrl.searchJobCardNo,
       PartRequisitionUrl.searchSparesPartItemNo
    ]
}
