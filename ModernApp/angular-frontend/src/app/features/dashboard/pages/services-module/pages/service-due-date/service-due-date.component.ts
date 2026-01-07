import { Component, OnInit, HostListener } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { ServicesApiService, ServiceDoneLapse } from '../../services-api.service';

@Component({
  selector: 'app-service-due-date',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, MatIconModule],
  providers: [DatePipe],
  templateUrl: './service-due-date.component.html',
  styleUrls: ['./service-due-date.component.scss']
})
export class ServiceDueDateComponent implements OnInit {

  searchParams = {
    range: true,
    fromDate: '',
    toDate: '',
    status: '',
    dealerCode: 'ALL'
  };

  statusOptions = [
    { label: 'ALL', value: '' },
    { label: 'DONE', value: 'DONE' },
    { label: 'NOT DONE', value: 'NOT DONE' },
    { label: 'PENDING', value: 'PENDING' }
  ];

  dealers = [{ label: 'ALL', value: 'ALL' }];

  reportData: ServiceDoneLapse[] = [];
  displayedData: ServiceDoneLapse[] = [];

  // Column Filters
  filters: { [key: string]: string } = {};
  visibleFilters: { [key: string]: boolean } = {};

  sortColumn: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';

  currentPage: number = 0;
  pageSize: number = 15;
  pageSizeOptions: number[] = [10, 15, 25, 50, 100];
  
  isLoading: boolean = false;

  // Column Visibility Management
  showColumnMenu: boolean = false;
  columns = [
    { key: 'vinNo', label: 'Chassis No.', visible: true },
    { key: 'modelCode', label: 'Model Code', visible: true },
    { key: 'modelCodeDesc', label: 'Model Code Desc.', visible: true },
    { key: 'jobTypeDesc', label: 'Job Type', visible: true },
    { key: 'jobCardNo', label: 'Job Card No.', visible: true },
    { key: 'hmr', label: 'HMR', visible: true }
  ];

  constructor(
    private serviceApi: ServicesApiService,
    private datePipe: DatePipe
  ) { }

  ngOnInit() {
    this.initDates();
    this.loadColumnVisibility();
    this.loadData();
  }

  loadColumnVisibility() {
    // Load from localStorage if available
    const saved = localStorage.getItem('serviceDueDateColumns');
    if (saved) {
      try {
        const savedColumns = JSON.parse(saved);
        this.columns.forEach(col => {
          const savedCol = savedColumns.find((sc: any) => sc.key === col.key);
          if (savedCol) {
            col.visible = savedCol.visible;
          }
        });
      } catch (e) {
        console.error('Error loading column visibility:', e);
      }
    }
  }

  saveColumnVisibility() {
    localStorage.setItem('serviceDueDateColumns', JSON.stringify(this.columns));
  }

  toggleColumnVisibility(columnKey: string) {
    const column = this.columns.find(col => col.key === columnKey);
    if (column) {
      column.visible = !column.visible;
      this.saveColumnVisibility();
    }
  }

  getVisibleColumnsCount(): number {
    return this.columns.filter(col => col.visible).length + 1; // +1 for S No.
  }

  isColumnVisible(columnKey: string): boolean {
    const column = this.columns.find(col => col.key === columnKey);
    return column ? column.visible : true;
  }

  toggleColumnMenu() {
    this.showColumnMenu = !this.showColumnMenu;
  }

  closeColumnMenu() {
    this.showColumnMenu = false;
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
    if (!this.showColumnMenu) return;
    
    const target = event.target as HTMLElement;
    // Close menu if click is outside the column visibility area
    if (!target.closest('.column-visibility') && !target.closest('.column-menu')) {
      this.closeColumnMenu();
    }
  }

  initDates() {
    const today = new Date();
    // Start with today for both
    this.searchParams.fromDate = this.datePipe.transform(today, 'yyyy-MM-dd') || '';
    this.searchParams.toDate = this.datePipe.transform(today, 'yyyy-MM-dd') || '';
  }

  loadData() {
    this.isLoading = true;
    const fromDate = this.datePipe.transform(this.searchParams.fromDate, 'dd/MM/yyyy') || '';
    const toDate = this.datePipe.transform(this.searchParams.toDate, 'dd/MM/yyyy') || '';

    this.serviceApi.getServiceDoneLapse(
      this.searchParams.range ? fromDate : '',
      this.searchParams.range ? toDate : '',
      this.searchParams.status,
      this.searchParams.dealerCode
    ).subscribe({
      next: (res) => {
        if (this.searchParams.range && this.searchParams.fromDate && this.searchParams.toDate) {
          const start = new Date(this.searchParams.fromDate);
          start.setHours(0, 0, 0, 0);
          const end = new Date(this.searchParams.toDate);
          end.setHours(0, 0, 0, 0);

          this.reportData = res.filter(item => {
            if (!item.jobCardDate) return false;
            // Assume dd/MM/yyyy format
            const parts = item.jobCardDate.split('/');
            if (parts.length === 3) {
              const d = new Date(parseInt(parts[2], 10), parseInt(parts[1], 10) - 1, parseInt(parts[0], 10));
              d.setHours(0, 0, 0, 0);
              return d.getTime() >= start.getTime() && d.getTime() <= end.getTime();
            }
            return false;
          });
        } else {
          this.reportData = res;
        }
        this.applyClientFilters();
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading data:', error);
        this.isLoading = false;
        alert('Error loading data. Please try again.');
      }
    });
  }

  // Client Side Filtering & Sorting
  toggleFilter(column: string) {
    this.visibleFilters[column] = !this.visibleFilters[column];
    // If closing, clear filter? The requirement doesn't say, but usually we keep it or clear it. 
    // The previous exmaple cleared it:
    if (!this.visibleFilters[column]) {
      // If we want to persist filter even when closed, remove this line. 
      // User requirement implies "searching functionality should be present", usually persistent until cleared manually. 
      // But looking at the reference ServiceReminderComponent code I read earlier:
      // if (!this.visibleFilters[column]) { this.filters[column] = ''; this.applyClientFilters(); }
      // I will follow the precedent set by the service-reminder component to keep behaviour consistent.
      this.filters[column] = '';
      this.applyClientFilters();
    }
  }

  applyClientFilters() {
    let data = [...this.reportData];

    // Filter
    Object.keys(this.filters).forEach(key => {
      const value = this.filters[key].toLowerCase();
      if (value) {
        data = data.filter(item => {
          const itemValue = String((item as any)[key] || '').toLowerCase();
          return itemValue.includes(value);
        });
      }
    });

    // Sort
    if (this.sortColumn) {
      data.sort((a, b) => {
        const valA = (a as any)[this.sortColumn];
        const valB = (b as any)[this.sortColumn]; // Fixed: was using sortColumn for both but accessing prop via string index is correct

        // Handle strings case-insensitively if needed, but standard comparison usually fine for this level 
        if (valA < valB) return this.sortDirection === 'asc' ? -1 : 1;
        if (valA > valB) return this.sortDirection === 'asc' ? 1 : -1;
        return 0;
      });
    }

    this.displayedData = data;
    // Reset to first page if current page is out of bounds
    const totalPages = Math.ceil(data.length / this.pageSize);
    if (this.currentPage >= totalPages && totalPages > 0) {
      this.currentPage = 0;
    } else if (totalPages === 0) {
      this.currentPage = 0;
    }
  }
  
  onPageSizeChange() {
    this.currentPage = 0;
  }

  sort(column: string) {
    if (this.sortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortColumn = column;
      this.sortDirection = 'asc';
    }
    this.applyClientFilters();
  }

  get paginatedData(): ServiceDoneLapse[] {
    const start = this.currentPage * this.pageSize;
    return this.displayedData.slice(start, start + this.pageSize);
  }

  get totalPages(): number {
    return Math.ceil(this.displayedData.length / this.pageSize);
  }

  get pageNumbers(): number[] {
    const total = this.totalPages;
    const current = this.currentPage;
    const visible = 5;
    let start = Math.max(0, current - Math.floor(visible / 2));
    let end = Math.min(total, start + visible);

    if (end - start < visible) {
      start = Math.max(0, end - visible);
    }

    const pages = [];
    for (let i = start; i < end; i++) {
      pages.push(i);
    }
    return pages;
  }
  
  get totalRecords(): number {
    return this.reportData.length;
  }
  
  get filteredRecords(): number {
    return this.displayedData.length;
  }
  
  get currentPageStart(): number {
    return this.displayedData.length > 0 ? (this.currentPage * this.pageSize) + 1 : 0;
  }
  
  get currentPageEnd(): number {
    const end = (this.currentPage + 1) * this.pageSize;
    return Math.min(end, this.displayedData.length);
  }
  
  refreshData() {
    this.loadData();
  }

  goToPage(page: number) {
    if (page >= 0 && page < this.totalPages) {
      this.currentPage = page;
    }
  }

  exportData() {
    // Legacy Validation: Status must be selected
    if (!this.searchParams.status) {
      alert('Please select Status to export data.');
      return;
    }

    const fromDate = this.datePipe.transform(this.searchParams.fromDate, 'dd/MM/yyyy') || '';
    const toDate = this.datePipe.transform(this.searchParams.toDate, 'dd/MM/yyyy') || '';

    this.serviceApi.exportServiceDoneLapse(
      this.searchParams.range ? fromDate : '',
      this.searchParams.range ? toDate : '',
      this.searchParams.status,
      this.searchParams.dealerCode
    ).subscribe((blob: Blob) => {
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.download = 'ServiceDoneLapseReport.xls'; // Matching legacy filename
      link.click();
      window.URL.revokeObjectURL(url);
    }, error => {
      console.error('Export failed', error);
      alert('Export failed. Please try again.');
    });
  }
}
