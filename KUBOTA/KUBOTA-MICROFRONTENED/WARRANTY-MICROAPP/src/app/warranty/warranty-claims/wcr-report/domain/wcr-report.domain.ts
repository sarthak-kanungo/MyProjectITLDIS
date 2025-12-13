export interface WcrReportSearch {
  size: number;
  page: number;
  creditNoteReqNo: string;
  dealerCode: string;
  dealerName: string;
  fromDate: string;
  machineNo: string;
  model: string;
  partNo: string;
  state: string;
  status: string;
  toDate: string;
  wcrType: string;
}

export interface WcrReport {
  wcrReportDto: WcrReportSearch;
}

export interface WcrDetail {
  id: string;
  model: string;
  jobCardNo: string;
  pcrNo: string;
  wcrNo: string;
  chassisNo: string;
  engineNo: string;
  wcrDate: string;
  typeOfClaim: string;
  noOfTime: string;
  hour: string;
  isSelect?: boolean;
  claimValue?: string;
  installationDate: string;
  failureDate?: string;
  invoiceNo?: string;
  creditNoteNo?: string;
  creditNotedate?: string;
  creditNoteReqNo?: string;
  status?: string;
  serviceDealerName: string;
  amount?: string;
}

export interface CreditDetails {
  installationDate: string;
  failureDate: string;
  invoiceNo: string;
  creditNoteNo: string;
  creditNotedate: string;
  creditNoteReqNo: string;
  status: string;
  serviceDealerName: string;
  amount: string;
}



export interface WcrReportAutoComplete {
  dealerName: string;
  value: string;
  id: number;
  code: string;
}
