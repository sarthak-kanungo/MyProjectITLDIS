import { Component, OnInit, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { PendingInstallation, ServicesApiService } from '../../services-api.service';

@Component({
  selector: 'app-pending-installation',
  standalone: true,
  imports: [CommonModule, MatIconModule, FormsModule, RouterModule],
  template: `
    <div class="view-pdi-container">
      
      <!-- Breadcrumb -->
      <div class="breadcrumb_container">
        <span class="breadcrumb_link"><a href="#">SERVICE</a> > PENDING FOR INSTALLATION</span>
      </div>

      <!-- Main Content -->
      <div class="content-wrapper">
        <!-- Header Bar -->
        <div class="header-bar">
           <div class="blue-stripe"></div>
           <div class="header-title">PENDING FOR INSTALLATION</div>
        </div>

        <!-- Search Form -->
        <div class="search-section">
          <table class="search-table" width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td class="label-cell" width="80">Chassis No.</td>
              <td width="150">
                <input type="text" [(ngModel)]="searchChassisNo" class="txtField" style="width: 140px;">
              </td>
              <td align="left">
                <button type="button" class="btn" (click)="search()">SEARCH</button>
                &nbsp;
                <button type="button" class="btn" (click)="export()">EXPORT</button>
              </td>
            </tr>
          </table>
        </div>

        <!-- Data Grid -->
        <div class="grid-container">
          <table class="data-table" cellspacing="1" cellpadding="4" width="100%" bgcolor="#cccccc">
            <thead>
              <tr bgcolor="#eeeeee" class="grid-header">
                <th width="5%" align="center">S No.</th>
                
                <!-- Chassis No Sort & Filter -->
                <th width="20%" align="left" (click)="sort('vinNo')" class="sortable">
                   <div class="th-content">
                      <span class="col-title">Chassis No. <span *ngIf="sortColumn === 'vinNo'">{{ sortDirection === 'asc' ? '▲' : '▼' }}</span></span>
                      <mat-icon class="search-icon" (click)="toggleFilter('vinNo', $event)">search</mat-icon>
                   </div>
                   <div class="filter-popup" *ngIf="visibleFilters['vinNo']" (click)="$event.stopPropagation()">
                      <input type="text" class="filter-input" [value]="filterValues['vinNo']" (keyup)="applyFilter($event, 'vinNo')" placeholder="Search Chassis...">
                   </div>
                </th>

                <!-- Delivery Date Sort & Filter -->
                <th width="15%" align="left" (click)="sort('deliveryDate')" class="sortable">
                   <div class="th-content">
                      <span class="col-title">Delivery Date <span *ngIf="sortColumn === 'deliveryDate'">{{ sortDirection === 'asc' ? '▲' : '▼' }}</span></span>
                      <mat-icon class="search-icon" (click)="toggleFilter('deliveryDate', $event)">search</mat-icon>
                   </div>
                   <div class="filter-popup" *ngIf="visibleFilters['deliveryDate']" (click)="$event.stopPropagation()">
                      <input type="text" class="filter-input" [value]="filterValues['deliveryDate']" (keyup)="applyFilter($event, 'deliveryDate')" placeholder="Search Date...">
                   </div>
                </th>

                <!-- Model Family Sort & Filter -->
                <th width="15%" align="left" (click)="sort('modelFamily')" class="sortable">
                   <div class="th-content">
                      <span class="col-title">Model Family <span *ngIf="sortColumn === 'modelFamily'">{{ sortDirection === 'asc' ? '▲' : '▼' }}</span></span>
                      <mat-icon class="search-icon" (click)="toggleFilter('modelFamily', $event)">search</mat-icon>
                   </div>
                   <div class="filter-popup" *ngIf="visibleFilters['modelFamily']" (click)="$event.stopPropagation()">
                      <input type="text" class="filter-input" [value]="filterValues['modelFamily']" (keyup)="applyFilter($event, 'modelFamily')" placeholder="Search Model...">
                   </div>
                </th>

                <!-- Model Code Sort & Filter -->
                <th width="15%" align="left" (click)="sort('modelCode')" class="sortable">
                   <div class="th-content">
                      <span class="col-title">Model Code <span *ngIf="sortColumn === 'modelCode'">{{ sortDirection === 'asc' ? '▲' : '▼' }}</span></span>
                      <mat-icon class="search-icon" (click)="toggleFilter('modelCode', $event)">search</mat-icon>
                   </div>
                   <div class="filter-popup" *ngIf="visibleFilters['modelCode']" (click)="$event.stopPropagation()">
                      <input type="text" class="filter-input" [value]="filterValues['modelCode']" (keyup)="applyFilter($event, 'modelCode')" placeholder="Search Code...">
                   </div>
                </th>

                <!-- Customer Name Sort & Filter -->
                <th width="20%" align="left" (click)="sort('customerName')" class="sortable">
                   <div class="th-content">
                      <span class="col-title">Customer Name <span *ngIf="sortColumn === 'customerName'">{{ sortDirection === 'asc' ? '▲' : '▼' }}</span></span>
                      <mat-icon class="search-icon" (click)="toggleFilter('customerName', $event)">search</mat-icon>
                   </div>
                   <div class="filter-popup" *ngIf="visibleFilters['customerName']" (click)="$event.stopPropagation()">
                      <input type="text" class="filter-input" [value]="filterValues['customerName']" (keyup)="applyFilter($event, 'customerName')" placeholder="Search Customer...">
                   </div>
                </th>

                <!-- Mobile Phone Sort & Filter -->
                <th width="10%" align="left" (click)="sort('mobilePh')" class="sortable">
                   <div class="th-content">
                      <span class="col-title">Mobile Phone <span *ngIf="sortColumn === 'mobilePh'">{{ sortDirection === 'asc' ? '▲' : '▼' }}</span></span>
                      <mat-icon class="search-icon" (click)="toggleFilter('mobilePh', $event)">search</mat-icon>
                   </div>
                   <div class="filter-popup" *ngIf="visibleFilters['mobilePh']" (click)="$event.stopPropagation()">
                      <input type="text" class="filter-input" [value]="filterValues['mobilePh']" (keyup)="applyFilter($event, 'mobilePh')" placeholder="Search Mobile...">
                   </div>
                </th>
              </tr>
            </thead>
            <tbody>
              <tr *ngFor="let element of filteredDataSource; let i = index" bgcolor="#FFFFFF" class="grid-row">
                <td align="center">{{ getSerialNumber(i) }}</td>
                <td align="left">
                  <a [routerLink]="['../../install-info', element.vinNo]" class="link">{{element.vinNo}}</a>
                </td>
                <td align="left">{{ element.deliveryDate | date:'dd/MM/yyyy' }}</td>
                <td align="left">{{ element.modelFamily }}</td>
                <td align="left">{{ element.modelCode }}</td>
                <td align="left">{{ element.customerName }}</td>
                <td align="left">{{ element.mobilePh }}</td>
              </tr>
              <tr *ngIf="filteredDataSource.length === 0 && !loading" bgcolor="#FFFFFF">
                <td colspan="7" align="center" style="color:red; padding:10px;">No Record Found</td>
              </tr>
              <tr *ngIf="loading" bgcolor="#FFFFFF">
                <td colspan="7" align="center" style="padding:10px;">Loading...</td>
              </tr>
            </tbody>
          </table>

          <!-- Custom Paginator -->
          <div class="custom-paginator" *ngIf="totalElements > 0">
             <a href="javascript:void(0)" (click)="goToPage(0)" [class.disabled]="currentPage === 0" title="First">
                <mat-icon class="pager-icon">first_page</mat-icon>
             </a>
             <a href="javascript:void(0)" (click)="goToPage(currentPage - 1)" [class.disabled]="currentPage === 0" title="Previous" *ngIf="currentPage > 0">
                <mat-icon class="pager-icon">navigate_before</mat-icon>
             </a>
             
             <ng-container *ngFor="let p of getPageNumbers()">
                 <a href="javascript:void(0)" (click)="goToPage(p)" class="page-number" [class.active]="p === currentPage">{{ p + 1 }}</a>
             </ng-container>

             <a href="javascript:void(0)" (click)="goToPage(currentPage + 1)" [class.disabled]="currentPage >= getTotalPages() - 1" title="Next">
                <mat-icon class="pager-icon">navigate_next</mat-icon>
             </a>
             <a href="javascript:void(0)" (click)="goToPage(getTotalPages() - 1)" [class.disabled]="currentPage >= getTotalPages() - 1" title="Last">
                <mat-icon class="pager-icon">last_page</mat-icon>
             </a>
          </div>

        </div>
      </div>
    </div>
  `,
  styles: [`
    .view-pdi-container {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11px;
        background-color: #FFFFFF;
        min-height: 100vh;
        padding: 5px;
    }

    .breadcrumb_container {
        width: 100%;
        margin: 5px 0px 5px 0px;
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11px;
        font-weight: bold;
        color: #333333;
        text-transform: uppercase;
    }
    .breadcrumb_link a {
        text-decoration: none;
        color: #0000FF;
    }
    .breadcrumb_link a:hover { text-decoration: underline; }

    .content-wrapper {
        border-top: 1px solid #ffffff;
    }

    .header-bar {
        background: linear-gradient(to bottom, #f9f9f9 0%, #e9e9e9 100%);
        border: 1px solid #cccccc;
        height: 25px;
        display: flex;
        align-items: center;
        margin-bottom: 10px;
        position: relative;
    }
    .blue-stripe {
        background-color: #0052a5;
        width: 4px;
        height: 100%;
        position: absolute;
        left: 0;
        top: 0;
    }
    .header-title {
        width: 100%;
        text-align: right;
        padding-right: 10px;
        font-weight: bold;
        color: #000000;
        line-height: 25px;
    }

    .search-section {
        margin-bottom: 10px;
        padding: 5px;
    }
    
    .label-cell {
        font-weight: normal;
        color: #000000;
    }

    .txtField {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11px;
        color: #333333;
        border: 1px solid #7f9db9;
        height: 18px;
        padding-left: 2px;
    }

    .btn {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11px;
        font-weight: bold;
        color: #CC0000;
        background: linear-gradient(to bottom, #f0f0f0 0%, #d0d0d0 100%);
        border: 1px solid #999999;
        text-transform: uppercase;
        cursor: pointer;
        padding: 2px 8px;
        box-shadow: 0px 1px 1px rgba(0,0,0,0.1);
    }
    .btn:hover {
        border: 1px solid #666666;
        background: #e0e0e0;
    }

    .grid-container {
        width: 100%;
    }

    .data-table {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11px;
        border-collapse: separate; 
    }

    .grid-header th {
        font-size: 11px;
        font-weight: bold;
        color: #000000;
        padding: 4px;
        border: none;
        vertical-align: top;
    }
    
    .grid-header th.sortable {
        cursor: pointer;
    }
    .grid-header th.sortable:hover {
        background-color: #ddd;
    }
    
    .th-content {
        display: flex;
        align-items: center;
        width: 100%;
    }
    
    .col-title {
        flex-grow: 1;
    }
    
    .search-icon {
        font-size: 14px;
        height: 14px;
        width: 14px;
        color: #555;
        cursor: pointer;
        margin-left: 4px;
    }
    .search-icon:hover {
        color: #000;
    }
    
    .filter-popup {
        margin-top: 5px;
        width: 100%;
        display: block;
    }
    
    .filter-input {
        width: 95%;
        font-family: Arial, Helvetica, sans-serif;
        font-size: 10px;
        border: 1px solid #999;
        padding: 2px;
    }

    .grid-row td {
        font-size: 11px;
        color: #333333;
        padding: 4px;
        border: none;
    }
    
    .link {
      color: #000000;
      text-decoration: none;
    }
    .link:hover {
      text-decoration: underline;
    }

    .grid-row:hover td {
        background-color: #fcefa1;
    }

    .custom-paginator {
        text-align: center;
        margin-top: 5px;
        font-size: 12px;
        color: #0000FF;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .custom-paginator a {
        text-decoration: none;
        color: #0000FF;
        font-weight: bold;
        margin: 0 2px;
        display: inline-flex;
        align-items: center;
    }
    .pager-icon {
        font-size: 20px;
        height: 20px;
        width: 20px;
        color: #0000FF;
    }
    .page-number {
        padding: 0 4px;
        font-size: 11px;
    }
    .custom-paginator a.active {
        color: #000000;
        text-decoration: none;
    }
    .custom-paginator a.disabled .pager-icon {
        color: #999;
        cursor: default;
    }
  `]
})
export class PendingInstallationComponent implements OnInit, AfterViewInit {
  dataSource: PendingInstallation[] = [];
  filteredDataSource: PendingInstallation[] = [];
  searchChassisNo: string = '';
  loading: boolean = false;

  // Sorting & Filtering
  sortColumn: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';
  filterValues: any = {
    vinNo: '',
    deliveryDate: '',
    modelFamily: '',
    modelCode: '',
    customerName: '',
    mobilePh: ''
  };
  visibleFilters: { [key: string]: boolean } = {};


  // Pagination
  totalElements: number = 0;
  pageSize: number = 15;
  currentPage: number = 0;

  constructor(private servicesApiService: ServicesApiService) { }

  ngOnInit(): void {
    this.scrollToTop();
    this.search();
  }

  ngAfterViewInit(): void {
    setTimeout(() => this.scrollToTop(), 0);
  }

  search() {
    this.loading = true;
    this.currentPage = 0; // Reset to first page on search
    this.fetchData();
  }

  fetchData() {
    this.servicesApiService.getPendingInstallations(this.searchChassisNo).subscribe({
      next: (data) => {
        // We get full data, so we filter/sort locally for now (similar to ViewPdiComponent client-side logic on top of server data if it was paginated fully, here we get list so we strictly do client side pagination)
        // Note: Logic here handles the *entire* list from server then paginates locally.
        this.dataSource = data;
        this.processData();
        this.loading = false;
      },
      error: (err) => {
        console.error('Error fetching pending installations:', err);
        this.loading = false;
      }
    });
  }

  processData() {
    let data = [...this.dataSource];

    // Filter
    data = data.filter(item => {
      const vinMatch = !this.filterValues.vinNo || (item.vinNo && item.vinNo.toLowerCase().includes(this.filterValues.vinNo.toLowerCase()));
      const deliveryMatch = !this.filterValues.deliveryDate || (item.deliveryDate && item.deliveryDate.toString().toLowerCase().includes(this.filterValues.deliveryDate.toLowerCase()));
      const modelMatch = !this.filterValues.modelFamily || (item.modelFamily && item.modelFamily.toLowerCase().includes(this.filterValues.modelFamily.toLowerCase()));
      const codeMatch = !this.filterValues.modelCode || (item.modelCode && item.modelCode.toLowerCase().includes(this.filterValues.modelCode.toLowerCase()));
      const customerMatch = !this.filterValues.customerName || (item.customerName && item.customerName.toLowerCase().includes(this.filterValues.customerName.toLowerCase()));
      const mobileMatch = !this.filterValues.mobilePh || (item.mobilePh && item.mobilePh.toString().toLowerCase().includes(this.filterValues.mobilePh.toLowerCase()));

      return vinMatch && deliveryMatch && modelMatch && codeMatch && customerMatch && mobileMatch;
    });

    // Sort
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
    this.filteredDataSource = data.slice(startIndex, startIndex + this.pageSize);
  }

  applyFilter(event: Event, column: string): void {
    const value = (event.target as HTMLInputElement).value;
    this.filterValues[column] = value;
    this.currentPage = 0; // Reset to first page on filter change
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

  toggleFilter(column: string, event: Event): void {
    event.stopPropagation();
    this.visibleFilters[column] = !this.visibleFilters[column];
  }

  export() {
    // Generate Excel from currently filtered data
    const dataToExport = this.dataSource.filter(item => {
      const vinMatch = !this.filterValues.vinNo || (item.vinNo && item.vinNo.toLowerCase().includes(this.filterValues.vinNo.toLowerCase()));
      const deliveryMatch = !this.filterValues.deliveryDate || (item.deliveryDate && item.deliveryDate.toString().toLowerCase().includes(this.filterValues.deliveryDate.toLowerCase()));
      const modelMatch = !this.filterValues.modelFamily || (item.modelFamily && item.modelFamily.toLowerCase().includes(this.filterValues.modelFamily.toLowerCase()));
      const codeMatch = !this.filterValues.modelCode || (item.modelCode && item.modelCode.toLowerCase().includes(this.filterValues.modelCode.toLowerCase()));
      const customerMatch = !this.filterValues.customerName || (item.customerName && item.customerName.toLowerCase().includes(this.filterValues.customerName.toLowerCase()));
      const mobileMatch = !this.filterValues.mobilePh || (item.mobilePh && item.mobilePh.toString().toLowerCase().includes(this.filterValues.mobilePh.toLowerCase()));

      return vinMatch && deliveryMatch && modelMatch && codeMatch && customerMatch && mobileMatch;
    });

    this.generateLegacyExcel(dataToExport);
  }

  private generateLegacyExcel(data: PendingInstallation[]): void {
    const chassisFilter = this.searchChassisNo || '';

    let html = `
      <html xmlns:x="urn:schemas-microsoft-com:office:excel">
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  
        <style>
          body { font-family: Arial, sans-serif; font-size: 11px; }
          .textPaging { font-weight: bold; background-color: #eeeeee; }
          .headingSpas { font-weight: bold; font-size: 11px; background-color: #cccccc; }
          table { border-collapse: collapse; }
          td, th { border: 1px solid #cccccc; padding: 4px; font-size: 11px; }
        </style>
      </head>
      <body>
        <center>
           <h3>ALL PENDING FOR INSTALLATION LIST</h3>
           <table width="100%" border="1" cellspacing="1" cellpadding="4">
             <!-- Filter Row -->
             <tr bgcolor="#eeeeee" height="30">
                <td colspan="7" align="left" bgcolor="#eeeeee" class="textPaging">
                   <span>
                      <b>Filter On :-</b> &nbsp;&nbsp;
                      <b>Chassis No : [</b> ${chassisFilter} <b>]</b> &nbsp;&nbsp;
                   </span>
                </td>
             </tr>
             
             <!-- Header Row -->
             <tr bgcolor="#eeeeee" height="20">
                 <th align="center">S No.</th>
                 <th align="left">Chassis No.</th>
                 <th align="left">Delivery Date</th>
                 <th align="left">Model Family</th>
                 <th align="left">Model Code</th>
                 <th align="left">Customer Name</th>
                 <th align="left">Mobile Phone</th>
             </tr>
      `;

    if (data.length === 0) {
      html += `
           <tr>
             <td colspan="7" align="center" style="color:red; padding:10px;">No Record Found</td>
           </tr>`;
    } else {
      data.forEach((item, index) => {
        // Format date if needed, though item.deliveryDate might be string or Date object. 
        // Assuming string YYYY-MM-DD or similar from backend, standard display in table was piped.
        // We'll trust toString() or basic formatting here.
        html += `
            <tr bgcolor="#ffffff" height="30">
               <td align="center">${index + 1}</td>
               <td align="left">${item.vinNo}</td>
               <td align="left">${item.deliveryDate}</td>
               <td align="left">${item.modelFamily}</td>
               <td align="left">${item.modelCode}</td>
               <td align="left">${item.customerName}</td>
               <td align="left">${item.mobilePh}</td>
            </tr>`;
      });
    }

    html += `
        <tr height="30">
          <td colspan="7"></td>
        </tr>
      `;

    html += `
           </table>
        </center>
      </body>
      </html>
      `;

    const blob = new Blob([html], { type: 'application/vnd.ms-excel' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'Pending_Installation_List.xls';
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    window.URL.revokeObjectURL(url);
  }

  // Pagination Logic
  goToPage(page: number): void {
    if (page >= 0 && page < this.getTotalPages()) {
      this.currentPage = page;
      this.processData(); // Re-slice data
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

  private scrollToTop(): void {
    window.scrollTo({ top: 0, left: 0, behavior: 'instant' });
  }
}
