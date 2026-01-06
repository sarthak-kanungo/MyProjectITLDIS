import { Component, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { ActivatedRoute } from '@angular/router';
import { ServicesApiService, JobCard } from '../../services-api.service';

@Component({
  selector: 'app-search-history',
  standalone: true,
  imports: [CommonModule, FormsModule, MatIconModule],
  templateUrl: './search-history.component.html',
  styleUrls: ['./search-history.component.scss'],
  providers: [DatePipe]
})
export class SearchHistoryComponent implements OnInit {

  vinNo: string = '';
  historyList: JobCard[] = [];       // Raw data from API
  processedList: JobCard[] = [];     // Data after filtering and sorting
  searched: boolean = false;
  loading: boolean = false;

  // Pagination
  currentPage: number = 0;
  pageSize: number = 10;

  // Sorting
  sortColumn: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';

  // Filtering
  visibleFilters: { [key: string]: boolean } = {};
  filterValues: { [key: string]: string } = {};

  constructor(
    private servicesApiService: ServicesApiService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['vinNo']) {
        this.vinNo = params['vinNo'];
        this.submitForm();
      }
    });
  }

  submitForm(): void {
    if (this.vinNo.trim().length < 4) {
      alert("Chassis No. should be at least 4 characters!");
      return;
    }

    this.searched = true;
    this.loading = true;
    this.servicesApiService.getJobCardsByVinNo(this.vinNo.trim()).subscribe({
      next: (data: JobCard[]) => {
        this.historyList = data;
        this.processData(); // Initial process
        this.loading = false;
        this.currentPage = 0;
      },
      error: (err: any) => {
        console.error('Error fetching history', err);
        this.historyList = [];
        this.processedList = [];
        this.loading = false;
      }
    });
  }

  // Core Processing Method
  processData(): void {
    let data = [...this.historyList];

    // 1. Filter
    for (const key in this.filterValues) {
      if (this.filterValues[key]) {
        const filterVal = this.filterValues[key].toLowerCase();
        data = data.filter(item => {
          let val: any = '';
          if (key === 'jobCardNo') val = item.jobCardNo;
          else if (key === 'jobType') val = item.jobType;
          else if (key === 'jobCardDate') val = item.jobCardDate ? new DatePipe('en-US').transform(item.jobCardDate, 'dd/MM/yyyy') : ''; // Filter by formatted date
          else if (key === 'customerName') val = item.customerName;
          else if (key === 'mobilePhone') val = item.mobilePhone;
          else if (key === 'status') val = item.status;

          return val ? String(val).toLowerCase().includes(filterVal) : false;
        });
      }
    }

    // 2. Sort
    if (this.sortColumn) {
      data.sort((a, b) => {
        let valA: any = '';
        let valB: any = '';

        if (this.sortColumn === 'jobCardNo') { valA = a.jobCardNo; valB = b.jobCardNo; }
        else if (this.sortColumn === 'jobType') { valA = a.jobType; valB = b.jobType; }
        else if (this.sortColumn === 'jobCardDate') { valA = a.jobCardDate; valB = b.jobCardDate; }
        else if (this.sortColumn === 'customerName') { valA = a.customerName; valB = b.customerName; }
        else if (this.sortColumn === 'mobilePhone') { valA = a.mobilePhone; valB = b.mobilePhone; }
        else if (this.sortColumn === 'status') { valA = a.status; valB = b.status; }

        if (valA < valB) return this.sortDirection === 'asc' ? -1 : 1;
        if (valA > valB) return this.sortDirection === 'asc' ? 1 : -1;
        return 0;
      });
    }

    this.processedList = data;
  }

  // Sorting Handler
  sortData(column: string): void {
    if (this.sortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortColumn = column;
      this.sortDirection = 'asc';
    }
    this.processData();
  }

  // Filter Handlers
  toggleFilter(column: string): void {
    this.visibleFilters[column] = !this.visibleFilters[column];
  }

  applyFilter(column: string, event: Event): void {
    const input = event.target as HTMLInputElement;
    this.filterValues[column] = input.value;
    this.processData();
    this.currentPage = 0;
  }

  get paginatedList(): JobCard[] {
    const start = this.currentPage * this.pageSize;
    return this.processedList.slice(start, start + this.pageSize);
  }

  goToPage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.currentPage = page;
    }
  }

  get totalPages(): number {
    return Math.ceil(this.processedList.length / this.pageSize);
  }

  getPageNumbers(): number[] {
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

  printInvoice(jobCardNo: string): void {
    const url = `/dashboard/services/view-job-card?jobCardNo=${jobCardNo}&flag=print`;
    window.open(url, '_blank', 'width=1000,height=900,resizable=yes,scrollbars=yes');
  }

  getSerialNumber(index: number): number {
    return (this.currentPage * this.pageSize) + index + 1;
  }
}
