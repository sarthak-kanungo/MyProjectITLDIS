import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTableModule } from '@angular/material/table';
import { MatChipsModule } from '@angular/material/chips';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';
import { ServicesApiService, JobCard, ServiceInvoice } from './services/services-api.service';

@Component({
  selector: 'app-services-module',
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
  templateUrl: './services-module.component.html',
  styleUrls: ['./services-module.component.scss']
})
export class ServicesModuleComponent implements OnInit {
  selectedTab = 0;
  isLoading = signal(false);
  
  jobCardStats = signal({
    total: 0,
    open: 0,
    pending: 0,
    closed: 0
  });

  invoiceStats = signal({
    total: 0,
    pending: 0,
    generated: 0
  });

  serviceScheduleStats = signal({
    due: 0,
    overdue: 0,
    completed: 0
  });

  extendedWarrantyStats = signal({
    active: 0,
    expired: 0,
    pending: 0
  });

  jobCardsDataSource = signal<JobCard[]>([]);
  invoicesDataSource = signal<ServiceInvoice[]>([]);
  displayedColumns = ['jobCardNo', 'vinNo', 'customerName', 'status', 'date', 'actions'];

  constructor(
    private servicesApi: ServicesApiService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.isLoading.set(true);
    
    // Load job cards
    this.servicesApi.getAllJobCards().subscribe({
      next: (jobCards) => {
        this.jobCardsDataSource.set(jobCards);
        this.updateJobCardStats(jobCards);
        this.isLoading.set(false);
      },
      error: (error) => {
        console.error('Error loading job cards:', error);
        this.snackBar.open('Failed to load job cards. Using sample data.', 'Close', {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top'
        });
        this.loadSampleData();
        this.isLoading.set(false);
      }
    });

    // Load invoices
    this.servicesApi.getAllInvoices().subscribe({
      next: (invoices) => {
        this.invoicesDataSource.set(invoices);
        this.updateInvoiceStats(invoices);
      },
      error: (error) => {
        console.error('Error loading invoices:', error);
      }
    });
  }

  updateJobCardStats(jobCards: JobCard[]): void {
    const total = jobCards.length;
    const open = jobCards.filter(jc => jc.status === 'OPEN').length;
    const pending = jobCards.filter(jc => jc.status === 'PENDING').length;
    const closed = jobCards.filter(jc => jc.status === 'CLOSED').length;
    
    this.jobCardStats.set({ total, open, pending, closed });
  }

  updateInvoiceStats(invoices: ServiceInvoice[]): void {
    const total = invoices.length;
    const pending = invoices.filter(inv => inv.status === 'PENDING').length;
    const generated = invoices.filter(inv => inv.status === 'GENERATED').length;
    
    this.invoiceStats.set({ total, pending, generated });
  }

  loadSampleData(): void {
    // Fallback sample data if API fails
    const sampleJobCards: JobCard[] = [
      { jobCardNo: 'JC-2024-001', vinNo: 'VIN123456', customerName: 'John Doe', status: 'OPEN', date: '2024-12-24' },
      { jobCardNo: 'JC-2024-002', vinNo: 'VIN123457', customerName: 'Jane Smith', status: 'PENDING', date: '2024-12-23' },
      { jobCardNo: 'JC-2024-003', vinNo: 'VIN123458', customerName: 'Bob Johnson', status: 'CLOSED', date: '2024-12-22' }
    ];
    this.jobCardsDataSource.set(sampleJobCards);
    this.updateJobCardStats(sampleJobCards);
  }

  getStatusColor(status: string): string {
    switch (status?.toUpperCase()) {
      case 'OPEN': return 'primary';
      case 'PENDING': return 'warn';
      case 'CLOSED': 
      case 'APPROVED': return 'accent';
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
