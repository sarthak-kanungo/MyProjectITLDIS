import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTableModule } from '@angular/material/table';
import { MatChipsModule } from '@angular/material/chips';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';
import { SparesApiService, SpareInvoice, PurchaseOrder } from './services/spares-api.service';

@Component({
  selector: 'app-spares-module',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatTabsModule,
    MatTableModule,
    MatChipsModule,
    MatTooltipModule,
    MatProgressSpinnerModule,
    MatSnackBarModule
  ],
  templateUrl: './spares-module.component.html',
  styleUrls: ['./spares-module.component.scss']
})
export class SparesModuleComponent implements OnInit {
  selectedTab = 0;
  isLoading = signal(false);
  
  invoiceStats = signal({
    total: 0,
    pending: 0,
    completed: 0
  });

  salesOrderStats = signal({
    total: 0,
    pending: 0,
    completed: 0
  });

  purchaseOrderStats = signal({
    total: 0,
    pending: 0,
    approved: 0
  });

  stockStats = signal({
    totalItems: 0,
    lowStock: 0,
    outOfStock: 0,
    inStock: 0
  });

  invoicesDataSource = signal<SpareInvoice[]>([]);
  purchaseOrdersDataSource = signal<PurchaseOrder[]>([]);

  invoiceColumns = ['invoiceNo', 'customerName', 'amount', 'status', 'date', 'actions'];
  poColumns = ['poNo', 'supplierName', 'amount', 'status', 'date', 'actions'];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private sparesApi: SparesApiService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.setupRouteListener();
    this.loadData();
  }

  loadData(): void {
    console.log('Loading data from Spares API...');
    this.isLoading.set(true);
    
    // Load invoices
    this.sparesApi.getAllInvoices().subscribe({
      next: (invoices) => {
        console.log('Invoices loaded:', invoices);
        this.invoicesDataSource.set(invoices);
        this.updateInvoiceStats(invoices);
        this.isLoading.set(false);
      },
      error: (error) => {
        console.error('Error loading invoices:', error);
        this.snackBar.open('Failed to load invoices. Using sample data.', 'Close', {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top'
        });
        this.loadSampleData();
        this.isLoading.set(false);
      }
    });

    // Load purchase orders
    this.sparesApi.getAllPurchaseOrders().subscribe({
      next: (pos) => {
        console.log('Purchase orders loaded:', pos);
        this.purchaseOrdersDataSource.set(pos);
        this.updatePurchaseOrderStats(pos);
      },
      error: (error) => {
        console.error('Error loading purchase orders:', error);
      }
    });
  }

  updateInvoiceStats(invoices: SpareInvoice[]): void {
    const total = invoices.length;
    const pending = invoices.filter(inv => inv.status === 'PENDING').length;
    const completed = invoices.filter(inv => inv.status === 'COMPLETED').length;
    
    this.invoiceStats.set({ total, pending, completed });
  }

  updatePurchaseOrderStats(pos: PurchaseOrder[]): void {
    const total = pos.length;
    const pending = pos.filter(po => po.status === 'PENDING').length;
    const approved = pos.filter(po => po.status === 'APPROVED').length;
    
    this.purchaseOrderStats.set({ total, pending, approved });
  }

  loadSampleData(): void {
    // Fallback sample data if API fails
    const sampleInvoices: SpareInvoice[] = [
      { invoiceNo: 'INV-2024-001', customerName: 'ABC Motors', totalAmount: 12500, status: 'COMPLETED', invoiceDate: '2024-12-24' },
      { invoiceNo: 'INV-2024-002', customerName: 'XYZ Auto', totalAmount: 8900, status: 'PENDING', invoiceDate: '2024-12-23' },
      { invoiceNo: 'INV-2024-003', customerName: 'DEF Garage', totalAmount: 15600, status: 'COMPLETED', invoiceDate: '2024-12-22' }
    ];
    this.invoicesDataSource.set(sampleInvoices);
    this.updateInvoiceStats(sampleInvoices);
  }

  setupRouteListener(): void {
    // Listen to route changes to update selected tab
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
        const url = this.router.url;
        if (url.includes('/invoices')) {
          this.selectedTab = 0;
        } else if (url.includes('/sales-orders')) {
          this.selectedTab = 1;
        } else if (url.includes('/purchase-orders')) {
          this.selectedTab = 2;
        } else if (url.includes('/inventory')) {
          this.selectedTab = 3;
        } else if (url.includes('/grn')) {
          this.selectedTab = 4;
        } else {
          // Default to first tab if no specific route
          this.selectedTab = 0;
        }
      });

    // Set initial tab based on current route
    const url = this.router.url;
    if (url.includes('/invoices')) {
      this.selectedTab = 0;
    } else if (url.includes('/sales-orders')) {
      this.selectedTab = 1;
    } else if (url.includes('/purchase-orders')) {
      this.selectedTab = 2;
    } else if (url.includes('/inventory')) {
      this.selectedTab = 3;
    } else if (url.includes('/grn')) {
      this.selectedTab = 4;
    }
  }

  getStatusColor(status: string | undefined): string {
    switch (status?.toLowerCase()) {
      case 'completed':
      case 'approved': return 'primary';
      case 'pending': return 'warn';
      default: return '';
    }
  }

  formatDate(date: string | undefined): string {
    if (!date) return '';
    try {
      return new Date(date).toLocaleDateString();
    } catch {
      return date;
    }
  }
}

