import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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

@Injectable({
  providedIn: 'root'
})
export class ServicesApiService {
  private readonly apiUrl = 'http://localhost:8082/api/services';

  constructor(private http: HttpClient) {}

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
}

