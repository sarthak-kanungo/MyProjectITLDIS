import { Component, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ServicesApiService, ServiceDoneLapse } from '../../services-api.service';

@Component({
  selector: 'app-service-due-date',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
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

  sortColumn: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';

  currentPage: number = 0;
  pageSize: number = 15;

  constructor(
    private serviceApi: ServicesApiService,
    private datePipe: DatePipe
  ) { }

  ngOnInit() {
    this.initDates();
    this.loadData();
  }

  initDates() {
    const today = new Date();
    const tomorrow = new Date(today);
    tomorrow.setDate(today.getDate() + 1);

    this.searchParams.fromDate = this.datePipe.transform(today, 'yyyy-MM-dd') || '';
    this.searchParams.toDate = this.datePipe.transform(tomorrow, 'yyyy-MM-dd') || '';
  }

  loadData() {
    const fromDate = this.datePipe.transform(this.searchParams.fromDate, 'dd/MM/yyyy') || '';
    const toDate = this.datePipe.transform(this.searchParams.toDate, 'dd/MM/yyyy') || '';

    this.serviceApi.getServiceDoneLapse(
      this.searchParams.range ? fromDate : '',
      this.searchParams.range ? toDate : '',
      this.searchParams.status,
      this.searchParams.dealerCode
    ).subscribe(res => {
      this.reportData = res;
      this.displayedData = res;
      this.currentPage = 0;
    });
  }

  sort(column: string) {
    if (this.sortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortColumn = column;
      this.sortDirection = 'asc';
    }

    this.displayedData.sort((a, b) => {
      const valA = (a as any)[column];
      const valB = (b as any)[column];
      if (valA < valB) return this.sortDirection === 'asc' ? -1 : 1;
      if (valA > valB) return this.sortDirection === 'asc' ? 1 : -1;
      return 0;
    });
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

  goToPage(page: number) {
    if (page >= 0 && page < this.totalPages) {
      this.currentPage = page;
    }
  }
}
