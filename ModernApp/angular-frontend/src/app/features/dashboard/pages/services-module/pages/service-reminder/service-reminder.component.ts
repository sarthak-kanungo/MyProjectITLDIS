import { Component, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { ServicesApiService, ServiceReminder } from '../../services-api.service';

@Component({
  selector: 'app-service-reminder',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, MatIconModule],
  providers: [DatePipe],
  templateUrl: './service-reminder.component.html',
  styleUrls: ['./service-reminder.component.scss']
})
export class ServiceReminderComponent implements OnInit {

  searchParams = {
    range: true,
    fromDate: '',
    toDate: '',
    jobType: '',
    dealerCode: 'ALL'
  };

  jobTypes = [
    { label: 'ALL', value: '' },
    { label: 'Paid Service / Repair', value: '9' },
    { label: 'Free Service', value: '1' }
  ];

  dealers = [{ label: 'ALL', value: 'ALL' }];

  reminders: ServiceReminder[] = [];
  displayedReminders: ServiceReminder[] = [];

  // Column Filters
  filters: { [key: string]: string } = {};
  visibleFilters: { [key: string]: boolean } = {};

  sortColumn: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';

  // Pagination
  currentPage: number = 0;
  pageSize: number = 15;

  constructor(
    private serviceApi: ServicesApiService,
    private router: Router,
    private datePipe: DatePipe
  ) { }

  ngOnInit() {
    this.initDates();
    this.loadData();
  }

  initDates() {
    const today = new Date();
    const lastMonth = new Date();
    lastMonth.setMonth(today.getMonth() - 1);
    lastMonth.setDate(1);

    const future = new Date();
    future.setDate(today.getDate() + 15);

    // Native date input uses yyyy-MM-dd
    this.searchParams.fromDate = this.datePipe.transform(lastMonth, 'yyyy-MM-dd') || '';
    this.searchParams.toDate = this.datePipe.transform(future, 'yyyy-MM-dd') || '';
  }

  loadData() {
    // Mocked Backend Call
    const fromDate = this.datePipe.transform(this.searchParams.fromDate, 'dd/MM/yyyy') || '';
    const toDate = this.datePipe.transform(this.searchParams.toDate, 'dd/MM/yyyy') || '';

    this.serviceApi.getServiceReminders(
      this.searchParams.range ? fromDate : '',
      this.searchParams.range ? toDate : '',
      this.searchParams.jobType,
      this.searchParams.dealerCode
    ).subscribe(res => {
      this.reminders = res;
      this.applyClientFilters();
    });
  }

  // Client Side Filtering & Sorting
  toggleFilter(column: string) {
    this.visibleFilters[column] = !this.visibleFilters[column];
    if (!this.visibleFilters[column]) {
      this.filters[column] = '';
      this.applyClientFilters();
    }
  }

  applyClientFilters() {
    let data = [...this.reminders];

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
        const valB = (b as any)[this.sortColumn];
        if (valA < valB) return this.sortDirection === 'asc' ? -1 : 1;
        if (valA > valB) return this.sortDirection === 'asc' ? 1 : -1;
        return 0;
      });
    }

    this.displayedReminders = data;
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

  viewHistory(vinNo: string) {
    this.router.navigate(['/dashboard/services/search-history'], { queryParams: { vinNo } });
  }

  openFollowUp(id: string) {
    // Placeholder
    alert('Opening Follow Up for Schedule ID: ' + id);
  }

  onRangeChange() {
    // Logic if needed when checkbox changes
  }

  get paginatedReminders(): ServiceReminder[] {
    const start = this.currentPage * this.pageSize;
    return this.displayedReminders.slice(start, start + this.pageSize);
  }

  get totalPages(): number {
    return Math.ceil(this.displayedReminders.length / this.pageSize);
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
