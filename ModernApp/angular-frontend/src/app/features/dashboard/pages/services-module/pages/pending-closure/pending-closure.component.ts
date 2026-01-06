import { Component, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { ServicesApiService, ViewJobCardDTO } from '../../services-api.service';

@Component({
  selector: 'app-pending-closure',
  standalone: true,
  imports: [CommonModule, FormsModule, MatIconModule],
  templateUrl: './pending-closure.component.html',
  styleUrls: ['./pending-closure.component.scss'],
  providers: [DatePipe]
})
export class PendingClosureComponent implements OnInit {

  jobCards: ViewJobCardDTO[] = [];
  filteredJobCards: ViewJobCardDTO[] = [];

  // Sorting & Filtering
  sortColumn: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';
  filterValues: any = {
    jobCardNo: '',
    vinNo: '',
    jobCardDate: '',
    payeeName: '',
    mobilePhone: '',
    status: ''
  };
  visibleFilters: { [key: string]: boolean } = {};

  // Pagination
  totalElements: number = 0;
  pageSize: number = 15;
  currentPage: number = 0;

  constructor(private servicesApiService: ServicesApiService) { }

  ngOnInit(): void {
    this.fetchPendingClosureJobCards();
  }

  fetchPendingClosureJobCards(): void {
    this.servicesApiService.getViewJobCards(
      '',
      '',
      'OPEN',
      undefined,
      '',
      ''
    ).subscribe({
      next: (data) => {
        this.jobCards = data;
        this.currentPage = 0;
        this.processData();
      },
      error: (err) => {
        console.error('Error fetching pending closure job cards', err);
      }
    });
  }

  processData(): void {
    let data = [...this.jobCards];

    // Client-side Filtering
    data = data.filter(item => {
      const jcMatch = !this.filterValues.jobCardNo || (item.jobCardNo && item.jobCardNo.toLowerCase().includes(this.filterValues.jobCardNo.toLowerCase()));
      const vinMatch = !this.filterValues.vinNo || (item.vinNo && item.vinNo.toLowerCase().includes(this.filterValues.vinNo.toLowerCase()));
      const dateMatch = !this.filterValues.jobCardDate || (item.jobCardDate && item.jobCardDate.toLowerCase().includes(this.filterValues.jobCardDate.toLowerCase()));
      const payeeMatch = !this.filterValues.payeeName || (item.payeeName && item.payeeName.toLowerCase().includes(this.filterValues.payeeName.toLowerCase()));
      const mobileMatch = !this.filterValues.mobilePhone || (item.mobilePhone && item.mobilePhone.toLowerCase().includes(this.filterValues.mobilePhone.toLowerCase()));
      const statusMatch = !this.filterValues.status || (item.status && item.status.toLowerCase().includes(this.filterValues.status.toLowerCase()));

      return jcMatch && vinMatch && dateMatch && payeeMatch && mobileMatch && statusMatch;
    });

    // Sorting
    if (this.sortColumn) {
      data.sort((a, b) => {
        const valueA = (a as any)[this.sortColumn] ? (a as any)[this.sortColumn].toString().toLowerCase() : '';
        const valueB = (b as any)[this.sortColumn] ? (b as any)[this.sortColumn].toString().toLowerCase() : '';

        if (valueA < valueB) {
          return this.sortDirection === 'asc' ? -1 : 1;
        }
        if (valueA > valueB) {
          return this.sortDirection === 'asc' ? 1 : -1;
        }
        return 0;
      });
    }

    this.totalElements = data.length;

    // Pagination
    const startIndex = this.currentPage * this.pageSize;
    this.filteredJobCards = data.slice(startIndex, startIndex + this.pageSize);
  }

  toggleFilter(column: string, event: Event): void {
    event.stopPropagation();
    this.visibleFilters[column] = !this.visibleFilters[column];
  }

  applyFilter(event: Event, column: string): void {
    const value = (event.target as HTMLInputElement).value;
    this.filterValues[column] = value;
    this.currentPage = 0;
    this.processData();
  }

  sort(column: string): void {
    if (this.sortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortColumn = column;
      this.sortDirection = 'asc';
    }
    this.processData();
  }

  goToPage(page: number): void {
    if (page >= 0 && page < this.getTotalPages()) {
      this.currentPage = page;
      this.processData();
    }
  }

  getTotalPages(): number {
    return Math.ceil(this.totalElements / this.pageSize);
  }

  getPageNumbers(): number[] {
    const total = this.getTotalPages();
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

  getSerialNumber(index: number): number {
    return (this.currentPage * this.pageSize) + index + 1;
  }

  viewJobCard(jobCardNo: string): void {
    console.log('View Job Card:', jobCardNo);
  }
}
