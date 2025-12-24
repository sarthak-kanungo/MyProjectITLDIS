import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface SpareInvoice {
  id?: number;
  invoiceNo: string;
  customerName?: string;
  customerCode?: string;
  referenceDocument?: string;
  documentType?: string;
  totalAmount?: number;
  status?: string;
  invoiceDate?: string;
}

export interface PurchaseOrder {
  id?: number;
  poNo: string;
  supplierName?: string;
  supplierCode?: string;
  totalAmount?: number;
  status?: string;
  poDate?: string;
}

@Injectable({
  providedIn: 'root'
})
export class SparesApiService {
  private readonly apiUrl = 'http://localhost:8083/api/spares';

  constructor(private http: HttpClient) {}

  // Invoices
  getAllInvoices(): Observable<SpareInvoice[]> {
    return this.http.get<SpareInvoice[]>(`${this.apiUrl}/invoices`);
  }

  getInvoiceById(id: number): Observable<SpareInvoice> {
    return this.http.get<SpareInvoice>(`${this.apiUrl}/invoices/${id}`);
  }

  getInvoiceByInvoiceNo(invoiceNo: string): Observable<SpareInvoice> {
    return this.http.get<SpareInvoice>(`${this.apiUrl}/invoices/invoiceNo/${invoiceNo}`);
  }

  getInvoicesByStatus(status: string): Observable<SpareInvoice[]> {
    return this.http.get<SpareInvoice[]>(`${this.apiUrl}/invoices/status/${status}`);
  }

  createInvoice(invoice: SpareInvoice): Observable<SpareInvoice> {
    return this.http.post<SpareInvoice>(`${this.apiUrl}/invoices`, invoice);
  }

  updateInvoice(id: number, invoice: SpareInvoice): Observable<SpareInvoice> {
    return this.http.put<SpareInvoice>(`${this.apiUrl}/invoices/${id}`, invoice);
  }

  cancelInvoice(id: number): Observable<SpareInvoice> {
    return this.http.put<SpareInvoice>(`${this.apiUrl}/invoices/${id}/cancel`, {});
  }

  deleteInvoice(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/invoices/${id}`);
  }

  // Purchase Orders
  getAllPurchaseOrders(): Observable<PurchaseOrder[]> {
    return this.http.get<PurchaseOrder[]>(`${this.apiUrl}/purchase-orders`);
  }

  getPurchaseOrderById(id: number): Observable<PurchaseOrder> {
    return this.http.get<PurchaseOrder>(`${this.apiUrl}/purchase-orders/${id}`);
  }

  getPurchaseOrderByPoNo(poNo: string): Observable<PurchaseOrder> {
    return this.http.get<PurchaseOrder>(`${this.apiUrl}/purchase-orders/poNo/${poNo}`);
  }

  getPurchaseOrdersByStatus(status: string): Observable<PurchaseOrder[]> {
    return this.http.get<PurchaseOrder[]>(`${this.apiUrl}/purchase-orders/status/${status}`);
  }

  createPurchaseOrder(po: PurchaseOrder): Observable<PurchaseOrder> {
    return this.http.post<PurchaseOrder>(`${this.apiUrl}/purchase-orders`, po);
  }

  updatePurchaseOrder(id: number, po: PurchaseOrder): Observable<PurchaseOrder> {
    return this.http.put<PurchaseOrder>(`${this.apiUrl}/purchase-orders/${id}`, po);
  }

  approvePurchaseOrder(id: number): Observable<PurchaseOrder> {
    return this.http.put<PurchaseOrder>(`${this.apiUrl}/purchase-orders/${id}/approve`, {});
  }

  deletePurchaseOrder(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/purchase-orders/${id}`);
  }
}

