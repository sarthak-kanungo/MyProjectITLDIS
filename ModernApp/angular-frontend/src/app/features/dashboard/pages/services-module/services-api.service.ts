import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface JobCard {
  id?: number;
  jobCardNo: string;
  vinNo: string;
  customerName: string;
  modelFamily?: string;
  engineNumber?: string;
  registrationNo?: string;
  jobType?: string;
  jobLocation?: string;
  serviceType?: string;
  status: string;
  jobCardDate?: string;
  date?: string;
}

export interface ServiceInvoice {
  id?: number;
  invoiceNo: string;
  jobCardNo?: string;
  customerName?: string;
  vinNo?: string;
  totalPartsValue?: number;
  totalLabourCharges?: number;
  totalAmount?: number;
  status?: string;
  invoiceDate?: string;
}

export interface PendingChassis {
  vinNo: string;
  modelFamily: string;
  modelCode: string;
  dealerCode: string;
  dealerName: string;
  locationName: string;
  locationCode: string;
  pdiPendingDays: number;
  pdiPendingDate?: string;
}

export interface PendingPdiSearchParams {
  chassisNo?: string;
  dealerCode?: string;
  page?: number;
  size?: number;
}

export interface PendingPdiResponse {
  content: PendingChassis[];
  totalElements: number;
  totalPages: number;
  currentPage: number;
  size: number;
}

export interface PdiDetail {
  vinNo: string;
  pdiNo: string;
  pdiDate: string;
  modelFamily: string;
  modelCode: string;
  dealerCode: string;
  dealerName: string;
  locationName: string;
  locationCode: string;
  refPDIno?: string;
}

export interface PdiDetailSearchParams {
  chassisNo?: string;
  dealerCode?: string;
  fromDate?: string;
  toDate?: string;
  status?: string;
  useDateRange?: boolean;
  page?: number;
  size?: number;
}

export interface PdiDetailResponse {
  content: PdiDetail[];
  totalElements: number;
  totalPages: number;
  currentPage: number;
  size: number;
}

export interface PdiDetailView {
  vinNo: string;
  engineNo: string;
  modelFamily: string;
  modelCode: string;
  modelFamilyDesc: string;
  modelCodeDesc: string;
  pdiNo: string;
  invoiceDate: string;
  tractorReceivedDate: string;
  pdiDate: string;
  invoiceNo: string;
  dealerName: string;
  dealerCode: string;
  locationName: string;
  locationCode: string;
  remarks: string;
  pdiDoneBy: string;
  jobCardNo: string;
  createJobCard: boolean;
  travelCardParts: TravelCardPart[];
  checklist: ChecklistGroup[];
}

export interface TravelCardPart {
  sno: number;
  contentDesc: string; // Part Name
  observation: string; // Part Vendor Name
  serialNo: string;    // Part Serial No
}

export interface ChecklistItem {
  subContentId: string;
  subContentDesc: string;
  textBoxOption: string; // "OK", "NOT OK" or value
  actionTaken: string;
  observations: string;
}

export interface ChecklistGroup {
  contentId: string;
  contentDesc: string;
  items: ChecklistItem[];
  // For groups that don't have sub-items but direct status
  status?: string;
  actionTaken?: string;
  observations?: string;
}

@Injectable({
  providedIn: 'root'
})
export class ServicesApiService {
  private readonly apiUrl = 'http://localhost:8082/api/services';

  constructor(private http: HttpClient) { }

  // Job Cards
  getAllJobCards(): Observable<JobCard[]> {
    return this.http.get<JobCard[]>(`${this.apiUrl}/job-cards`);
  }

  getJobCardById(id: number): Observable<JobCard> {
    return this.http.get<JobCard>(`${this.apiUrl}/job-cards/${id}`);
  }

  getJobCardByJobCardNo(jobCardNo: string): Observable<JobCard> {
    return this.http.get<JobCard>(`${this.apiUrl}/job-cards/jobCardNo/${jobCardNo}`);
  }

  getJobCardsByStatus(status: string): Observable<JobCard[]> {
    return this.http.get<JobCard[]>(`${this.apiUrl}/job-cards/status/${status}`);
  }

  createJobCard(jobCard: JobCard): Observable<JobCard> {
    return this.http.post<JobCard>(`${this.apiUrl}/job-cards`, jobCard);
  }

  updateJobCard(id: number, jobCard: JobCard): Observable<JobCard> {
    return this.http.put<JobCard>(`${this.apiUrl}/job-cards/${id}`, jobCard);
  }

  approveJobCard(id: number): Observable<JobCard> {
    return this.http.put<JobCard>(`${this.apiUrl}/job-cards/${id}/approve`, {});
  }

  closeJobCard(id: number): Observable<JobCard> {
    return this.http.put<JobCard>(`${this.apiUrl}/job-cards/${id}/close`, {});
  }

  deleteJobCard(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/job-cards/${id}`);
  }

  // Service Invoices
  getAllInvoices(): Observable<ServiceInvoice[]> {
    return this.http.get<ServiceInvoice[]>(`${this.apiUrl}/invoices`);
  }

  getInvoiceById(id: number): Observable<ServiceInvoice> {
    return this.http.get<ServiceInvoice>(`${this.apiUrl}/invoices/${id}`);
  }

  createInvoice(invoice: ServiceInvoice): Observable<ServiceInvoice> {
    return this.http.post<ServiceInvoice>(`${this.apiUrl}/invoices`, invoice);
  }

  generateInvoiceFromJobCard(jobCardNo: string, invoice: ServiceInvoice): Observable<ServiceInvoice> {
    return this.http.post<ServiceInvoice>(`${this.apiUrl}/invoices/generate/${jobCardNo}`, invoice);
  }

  // Pending PDI
  getPendingPdiChassisList(params: PendingPdiSearchParams): Observable<PendingPdiResponse> {
    let httpParams = new HttpParams();
    if (params.chassisNo) httpParams = httpParams.set('chassisNo', params.chassisNo);
    if (params.dealerCode) httpParams = httpParams.set('dealerCode', params.dealerCode);
    if (params.page !== undefined) httpParams = httpParams.set('page', params.page.toString());
    if (params.size) httpParams = httpParams.set('size', params.size.toString());

    return this.http.get<PendingPdiResponse>(`${this.apiUrl}/pdi/pending-chassis`, {
      params: httpParams
    });
  }

  exportPendingPdiChassisList(params: PendingPdiSearchParams): Observable<Blob> {
    let httpParams = new HttpParams();
    if (params.chassisNo) httpParams = httpParams.set('chassisNo', params.chassisNo);
    if (params.dealerCode) httpParams = httpParams.set('dealerCode', params.dealerCode);

    return this.http.get(`${this.apiUrl}/pdi/pending-chassis/export`, {
      params: httpParams,
      responseType: 'blob'
    });
  }

  // View PDI Details
  getPdiDetailsList(params: PdiDetailSearchParams): Observable<PdiDetailResponse> {
    let httpParams = new HttpParams();
    if (params.chassisNo) httpParams = httpParams.set('chassisNo', params.chassisNo);
    if (params.dealerCode) httpParams = httpParams.set('dealerCode', params.dealerCode);
    if (params.fromDate) httpParams = httpParams.set('fromDate', params.fromDate);
    if (params.toDate) httpParams = httpParams.set('toDate', params.toDate);
    if (params.status) httpParams = httpParams.set('status', params.status);
    if (params.useDateRange !== undefined) httpParams = httpParams.set('useDateRange', params.useDateRange.toString());
    if (params.page !== undefined) httpParams = httpParams.set('page', params.page.toString());
    if (params.size) httpParams = httpParams.set('size', params.size.toString());

    return this.http.get<PdiDetailResponse>(`${this.apiUrl}/pdi/details`, {
      params: httpParams
    });
  }

  exportPdiDetailsList(params: PdiDetailSearchParams): Observable<Blob> {
    let httpParams = new HttpParams();
    if (params.chassisNo) httpParams = httpParams.set('chassisNo', params.chassisNo);
    if (params.dealerCode) httpParams = httpParams.set('dealerCode', params.dealerCode);
    if (params.fromDate) httpParams = httpParams.set('fromDate', params.fromDate);
    if (params.toDate) httpParams = httpParams.set('toDate', params.toDate);
    if (params.status) httpParams = httpParams.set('status', params.status);
    if (params.useDateRange !== undefined) httpParams = httpParams.set('useDateRange', params.useDateRange.toString());

    return this.http.get(`${this.apiUrl}/pdi/details/export`, {
      params: httpParams,
      responseType: 'blob'
    });
  }

  // Get PDI Detail View
  getPdiDetailView(vinNo: string, pdiNo: string): Observable<PdiDetailView> {
    let httpParams = new HttpParams();
    httpParams = httpParams.set('vinNo', vinNo);
    httpParams = httpParams.set('pdiNo', pdiNo);

    return this.http.get<PdiDetailView>(`${this.apiUrl}/pdi/details/view`, {
      params: httpParams
    });
  }
}

