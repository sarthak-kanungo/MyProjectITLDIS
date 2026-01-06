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

export interface PendingInstallation {
  vinNo: string;
  modelFamily: string;
  modelCode: string;
  customerName: string;
  mobilePh: number;
  dealerName: string;
  dealerCode: string;
  location: string;
  locationCode: string;
  deliveryDate: string;
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

  getViewJobCards(
    fromDate?: string,
    toDate?: string,
    status?: string,
    dealerCode?: string,
    searchColumn?: string,
    searchValue?: string
  ): Observable<ViewJobCardDTO[]> {
    let httpParams = new HttpParams();
    if (fromDate) httpParams = httpParams.set('fromDate', fromDate);
    if (toDate) httpParams = httpParams.set('toDate', toDate);
    if (status) httpParams = httpParams.set('status', status);
    if (dealerCode) httpParams = httpParams.set('dealerCode', dealerCode);
    if (searchColumn) httpParams = httpParams.set('searchColumn', searchColumn);
    if (searchValue) httpParams = httpParams.set('searchValue', searchValue);

    return this.http.get<ViewJobCardDTO[]>(`${this.apiUrl}/job-cards/view`, {
      params: httpParams
    });
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

  // Pending Installation
  getPendingInstallations(chassisNo?: string, dealerCode?: string): Observable<PendingInstallation[]> {
    let httpParams = new HttpParams();
    if (chassisNo) httpParams = httpParams.set('chassisNo', chassisNo);
    if (dealerCode) httpParams = httpParams.set('dealerCode', dealerCode);

    return this.http.get<PendingInstallation[]>(`${this.apiUrl}/installation/pending`, {
      params: httpParams
    });
  }

  getCompletedInstallations(chassisNo?: string, dealerCode?: string): Observable<ViewInstallation[]> {
    let httpParams = new HttpParams();
    if (chassisNo) httpParams = httpParams.set('chassisNo', chassisNo);
    if (dealerCode) httpParams = httpParams.set('dealerCode', dealerCode);

    return this.http.get<ViewInstallation[]>(`${this.apiUrl}/installation/view`, {
      params: httpParams
    });
  }

  // Job Card Creation Support
  getVehicleInfoInitData(): Observable<VehicleInfoInitData> {
    return this.http.get<VehicleInfoInitData>(`${this.apiUrl}/job-cards/init-vehicle-info`);
  }

  getVehicleDetails(vinNo: string): Observable<VehicleDetails> {
    return this.http.get<VehicleDetails>(`${this.apiUrl}/job-cards/vehicle-details/${vinNo}`);
  }
}

export interface ViewInstallation {
  insNo: string;
  insDate: string;
  vinNo: string;
  modelFamily: string;
  customerName: string;
  mobilePh: string;
  dealerName: string;
  dealerCode: string;
  location: string;
  deliveryDate: string;
}

export interface DropdownOption {
  label: string;
  value: string;
}

export interface VehicleInfoInitData {
  jobTypes: DropdownOption[];
  locations: DropdownOption[];
  serviceTypes: DropdownOption[];
  productCategories: DropdownOption[];
}

export interface VehicleDetails {
  vinNo: string;
  engineNo: string;
  saleDate: string;
  modelFamily: string;
  sellingDealerCode: string;
  sellingDealerName: string;
  plateNo: string;
  modelCode: string;
  modelDescription: string;
  customerName: string;
  customerId: string;
}

export interface ViewJobCardDTO {
  jobCardNo: string;
  jobCardDate: string;
  vinNo: string;
  payeeName: string;
  dealerCode: string;
  dealerName: string;
  location: string;
  locationCode: string;
  status: string;
  mobilePhone: string;
}

