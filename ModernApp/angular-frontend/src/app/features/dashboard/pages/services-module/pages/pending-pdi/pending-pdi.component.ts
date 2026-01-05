import { Component, OnInit, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator'; // Keep for module compatibility if needed
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';
import { ServicesApiService, PendingChassis, PendingPdiSearchParams } from '../../services-api.service';

@Component({
  selector: 'app-pending-pdi',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatSnackBarModule
  ],
  template: `
    <div class="page-container">
      
      <!-- Breadcrumb -->
      <div class="breadcrumb_container">
        <span class="breadcrumb_link"><a href="#">SERVICE</a> > PENDING FOR PDI</span>
      </div>

      <!-- Main Content -->
      <div class="content-wrapper">
        <!-- Header Bar -->
        <div class="header-bar">
           <div class="blue-stripe"></div>
           <div class="header-title">PENDING FOR PDI</div>
        </div>

        <!-- Search Form -->
        <div class="search-section">
          <form [formGroup]="searchForm" (ngSubmit)="onSearch()">
            <table class="search-table" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td class="label-cell">Chassis No. &nbsp;</td>
                <td>
                  <input type="text" formControlName="chassisNo" class="txtField">
                </td>
                
                <td style="padding-left: 20px;">
                  <button type="submit" class="btn">SEARCH</button>
                  &nbsp;
                  <button type="button" class="btn" (click)="onExport()">EXPORT</button>
                </td>
              </tr>
            </table>
          </form>
        </div>

        <!-- Data Grid -->
        <div class="grid-container">
          <table class="data-table" cellspacing="1" cellpadding="4" width="100%" bgcolor="#cccccc">
            <thead>
              <tr bgcolor="#eeeeee" class="grid-header">
                <th width="5%" align="center">S No.</th>
                
                <th width="25%" align="left" (click)="sort('vinNo')" class="sortable">
                   <div class="th-content">
                      <span class="col-title">Chassis No. <span *ngIf="sortColumn === 'vinNo'">{{ sortDirection === 'asc' ? '▲' : '▼' }}</span></span>
                      <mat-icon class="search-icon" (click)="toggleFilter('vinNo', $event)">search</mat-icon>
                   </div>
                   <div class="filter-popup" *ngIf="visibleFilters['vinNo']" (click)="$event.stopPropagation()">
                      <input type="text" class="filter-input" [value]="filterValues['vinNo']" (keyup)="applyFilter($event, 'vinNo')" placeholder="Search Chassis...">
                   </div>
                </th>

                <th width="30%" align="left" (click)="sort('modelFamily')" class="sortable">
                   <div class="th-content">
                      <span class="col-title">Model Family <span *ngIf="sortColumn === 'modelFamily'">{{ sortDirection === 'asc' ? '▲' : '▼' }}</span></span>
                      <mat-icon class="search-icon" (click)="toggleFilter('modelFamily', $event)">search</mat-icon>
                   </div>
                   <div class="filter-popup" *ngIf="visibleFilters['modelFamily']" (click)="$event.stopPropagation()">
                      <input type="text" class="filter-input" [value]="filterValues['modelFamily']" (keyup)="applyFilter($event, 'modelFamily')" placeholder="Search Model...">
                   </div>
                </th>

                <th width="25%" align="left" (click)="sort('modelCode')" class="sortable">
                   <div class="th-content">
                      <span class="col-title">Model Code <span *ngIf="sortColumn === 'modelCode'">{{ sortDirection === 'asc' ? '▲' : '▼' }}</span></span>
                      <mat-icon class="search-icon" (click)="toggleFilter('modelCode', $event)">search</mat-icon>
                   </div>
                   <div class="filter-popup" *ngIf="visibleFilters['modelCode']" (click)="$event.stopPropagation()">
                      <input type="text" class="filter-input" [value]="filterValues['modelCode']" (keyup)="applyFilter($event, 'modelCode')" placeholder="Search Code...">
                   </div>
                </th>

                <th width="15%" align="center" (click)="sort('pdiPendingDays')" class="sortable">
                   <div class="th-content">
                      <span class="col-title">Pending Days <span *ngIf="sortColumn === 'pdiPendingDays'">{{ sortDirection === 'asc' ? '▲' : '▼' }}</span></span>
                      <mat-icon class="search-icon" (click)="toggleFilter('pdiPendingDays', $event)">search</mat-icon>
                   </div>
                   <div class="filter-popup" *ngIf="visibleFilters['pdiPendingDays']" (click)="$event.stopPropagation()">
                      <input type="text" class="filter-input" [value]="filterValues['pdiPendingDays']" (keyup)="applyFilter($event, 'pdiPendingDays')" placeholder="Search Days...">
                   </div>
                </th>
              </tr>
            </thead>
            <tbody>
              <tr *ngFor="let item of filteredDataSource; let i = index" bgcolor="#FFFFFF" class="grid-row">
                <td align="center">{{ getSerialNumber(i) }}</td>
                <td align="left">{{ item.vinNo }}</td>
                <td align="left">{{ item.modelFamily }}</td>
                <td align="left">{{ item.modelCode }}</td>
                <td align="center">{{ item.pdiPendingDays }}</td>
              </tr>
              <tr *ngIf="filteredDataSource.length === 0 && !loading" bgcolor="#FFFFFF">
                <td colspan="5" align="center" style="color:red; padding:10px;">No records found</td>
              </tr>
              <tr *ngIf="loading" bgcolor="#FFFFFF">
                <td colspan="5" align="center" style="padding:10px;">Loading...</td>
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
    .page-container {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11px;
        background-color: #FFFFFF;
        min-height: 100vh;
        padding: 5px;
    }

    .breadcrumb_container {
        width: 100%;
        margin: 5px 0px 10px 0px;
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11px;
        font-weight: bold;
        color: #333333;
        text-transform: uppercase;
    }
    .breadcrumb_link a {
        text-decoration: none;
        color: #0052a5;
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
        margin-bottom: 15px;
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
        font-size: 11px;
        line-height: 25px;
        text-transform: uppercase;
    }

    .search-section {
        margin-bottom: 10px;
        padding-left: 20px;
    }
    
    .label-cell {
        font-weight: normal;
        color: #000000;
        font-size: 12px;
    }

    .txtField {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11px;
        color: #333333;
        border: 1px solid #cccccc;
        height: 18px;
        width: 200px;
        padding-left: 2px;
    }

    .btn {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11px;
        color: #CC0000;
        background: linear-gradient(to bottom, #f0f0f0 0%, #d0d0d0 100%);
        border: 1px solid #cccccc;
        text-transform: uppercase;
        cursor: pointer;
        padding: 2px 10px;
    }
    .btn:hover {
        background: #e0e0e0;
        border: 1px solid #999999;
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
        padding: 4px; /* Increased padding slightly for better spacing */
        border: none;
        white-space: normal; /* Allow wrapping if needed, though usually nowrap is fine, but with filters block it's better normal or nowrap with block inside */
        vertical-align: top;
        min-height: 30px;
    }

    .grid-row td {
        font-size: 11px;
        color: #333333;
        padding: 0 3px;
        border: none;
        height: 30px;
        white-space: nowrap;
    }
    
    .custom-paginator {
        text-align: center;
        margin-top: 10px;
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
        font-size: 18px;
        height: 18px;
        width: 18px;
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

    /* Sort & Filter Styles */
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
        height: 100%; /* Ensure full height */
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
  `]
})
export class PendingPdiComponent implements OnInit, AfterViewInit {
  searchForm: FormGroup;
  dataSource: PendingChassis[] = [];
  filteredDataSource: PendingChassis[] = [];

  loading: boolean = false;

  // Sorting & Filtering
  sortColumn: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';
  filterValues: any = {
    vinNo: '',
    modelFamily: '',
    modelCode: '',
    pdiPendingDays: ''
  };
  visibleFilters: { [key: string]: boolean } = {};

  // Pagination
  totalElements: number = 0;
  pageSize: number = 15;
  currentPage: number = 0;

  constructor(
    private fb: FormBuilder,
    private apiService: ServicesApiService,
    private snackBar: MatSnackBar
  ) {
    this.searchForm = this.fb.group({
      chassisNo: [''],
      dealerCode: ['ALL']
    });
  }

  ngOnInit(): void {
    this.scrollToTop();
  }

  ngAfterViewInit(): void {
    setTimeout(() => this.scrollToTop(), 0);
  }

  onSearch(): void {
    this.loading = true;
    this.currentPage = 0;
    this.fetchData();
  }

  fetchData(): void {
    this.loading = true;
    const formValue = this.searchForm.value;

    const searchParams: PendingPdiSearchParams = {
      chassisNo: formValue.chassisNo?.trim() || undefined,
      dealerCode: formValue.dealerCode === 'ALL' ? undefined : formValue.dealerCode,
      page: this.currentPage,
      size: this.pageSize
    };

    this.apiService.getPendingPdiChassisList(searchParams).subscribe({
      next: (response) => {
        this.dataSource = response.content || [];
        this.totalElements = response.totalElements || 0;
        this.loading = false;
        this.processData();
      },
      error: (error) => {
        console.error('Error loading pending PDI list:', error);
        this.snackBar.open('Error loading data', 'Close', { duration: 3000 });
        this.loading = false;
        this.dataSource = [];
        this.filteredDataSource = [];
      }
    });
  }

  processData(): void {
    let data = [...this.dataSource];

    // Filter
    data = data.filter(item => {
      const vinMatch = !this.filterValues.vinNo || (item.vinNo && item.vinNo.toLowerCase().includes(this.filterValues.vinNo.toLowerCase()));
      const familyMatch = !this.filterValues.modelFamily || (item.modelFamily && item.modelFamily.toLowerCase().includes(this.filterValues.modelFamily.toLowerCase()));
      const codeMatch = !this.filterValues.modelCode || (item.modelCode && item.modelCode.toLowerCase().includes(this.filterValues.modelCode.toLowerCase()));
      const daysMatch = !this.filterValues.pdiPendingDays || (item.pdiPendingDays != null && item.pdiPendingDays.toString().includes(this.filterValues.pdiPendingDays));

      return vinMatch && familyMatch && codeMatch && daysMatch;
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

    this.filteredDataSource = data;
  }

  applyFilter(event: Event, column: string): void {
    const value = (event.target as HTMLInputElement).value;
    this.filterValues[column] = value;
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

  onExport(): void {
    const formValue = this.searchForm.value;
    const searchParams: PendingPdiSearchParams = {
      chassisNo: formValue.chassisNo?.trim() || undefined,
      dealerCode: formValue.dealerCode === 'ALL' ? undefined : formValue.dealerCode,
      page: 0,
      size: 10000
    };

    this.apiService.getPendingPdiChassisList(searchParams).subscribe({
      next: (response) => {
        const data = response.content || [];
        this.generateLegacyExcel(data, formValue.chassisNo);
      },
      error: (error) => {
        console.error('Export failed', error);
        this.snackBar.open('Export failed', 'Close', { duration: 3000 });
      }
    });
  }

  private generateLegacyExcel(data: PendingChassis[], chassisFilter: string): void {
    let html = `
    <html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
      <style>
        body { font-family: 'Calibri', sans-serif; }
        .title { 
            font-weight: bold; 
            font-size: 18pt; 
            text-align: center; 
            vertical-align: middle;
            background-color: #FFFFFF;
            height: 45px;
        }
        .header { 
            font-weight: bold; 
            background-color: #f2f2f2; 
            border: 0.5pt solid #cccccc;
            font-size: 6pt;
            vertical-align: middle;
            height: 30px;
            white-space: nowrap;
            padding: 0 3px;
        }
        .data-cell {
            border: 0.5pt solid #cccccc;
            font-size: 6pt;
            padding: 0 3px;
            vertical-align: middle;
            height: 30px;
            white-space: nowrap;
        }
        table { border-collapse: collapse; table-layout: fixed; }
      </style>
    </head>
    <body>
      <center>
         <table width="100%" cellspacing="0" cellpadding="0" border="0">
           
           <col width="120">
           <col width="520">
           <col width="400">
           <col width="360">
           <col width="360">

           <!-- Title Row -->
           <tr>
              <td colspan="5" class="title">PENDING FOR PDI</td>
           </tr>

           <!-- Empty Row -->
           <tr>
              <td colspan="5" style="height: 30px;"></td>
           </tr>

           <!-- Header Row -->
           <tr>
               <th class="header" align="center">S No.</th>
               <th class="header" align="left">Chassis No.</th>
               <th class="header" align="left">Model Family</th>
               <th class="header" align="left">Model Code</th>
               <th class="header" align="center">Pending Days</th>
           </tr>
    `;

    if (data.length === 0) {
      html += `<tr><td colspan="5" align="center" style="color:red; border: 0.5pt solid #cccccc; font-size: 6pt;">No Records Found</td></tr>`;
    } else {
      data.forEach((item, index) => {
        html += `
          <tr bgcolor="#ffffff">
             <td class="data-cell" align="center">${index + 1}</td>
             <td class="data-cell" align="left">${item.vinNo}</td>
             <td class="data-cell" align="left">${item.modelFamily}</td>
             <td class="data-cell" align="left">${item.modelCode}</td>
             <td class="data-cell" align="center">${item.pdiPendingDays}</td>
          </tr>`;
      });
    }

    // Add empty row at bottom
    html += `
      <tr style="height: 30px;">
        <td colspan="5" style="border: 0.5pt solid #cccccc; background-color: #FFFFFF;"></td>
      </tr>
    `;

    html += `</table></center></body></html>`;

    const blob = new Blob([html], { type: 'application/vnd.ms-excel' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'PENDING_PDI_LIST.xls';
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    window.URL.revokeObjectURL(url);
  }

  // Pagination Logic
  goToPage(page: number): void {
    if (page >= 0 && page < this.getTotalPages()) {
      this.currentPage = page;
      this.fetchData();
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
    if (end - start < visible) start = Math.max(0, end - visible);
    const pages = [];
    for (let i = start; i < end; i++) pages.push(i);
    return pages;
  }

  getSerialNumber(index: number): number {
    return (this.currentPage * this.pageSize) + index + 1;
  }

  private scrollToTop(): void {
    const contentWrapper = document.querySelector('.dashboard-content-wrapper') as HTMLElement;
    if (contentWrapper) {
      contentWrapper.scrollTop = 0;
      contentWrapper.scrollTo({ top: 0, behavior: 'instant' });
    }
    window.scrollTo({ top: 0, left: 0, behavior: 'instant' });
  }
}

