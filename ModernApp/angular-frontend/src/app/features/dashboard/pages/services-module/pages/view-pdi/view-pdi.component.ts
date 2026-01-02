import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ServicesApiService, PdiDetail, PdiDetailSearchParams, PdiDetailView } from '../../services-api.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { PdiDetailDialogComponent } from './pdi-detail-dialog.component';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-pdi',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    MatIconModule,
    MatPaginatorModule, // Keep for logic if needed, but we'll use custom UI
    MatDialogModule,
    RouterModule
  ],
  template: `
    <div class="view-pdi-container">
      
      <!-- Breadcrumb -->
      <div class="breadcrumb_container">
        <span class="breadcrumb_link"><a href="#">SERVICE</a> > VIEW PDI DETAIL</span>
      </div>

      <!-- Main Content -->
      <div class="content-wrapper">
        <!-- Header Bar -->
        <div class="header-bar">
           <div class="blue-stripe"></div>
           <div class="header-title">VIEW PDI DETAIL</div>
        </div>

        <!-- Search Form -->
        <div class="search-section">
          <form [formGroup]="searchForm" (ngSubmit)="onSearch()">
            <table class="search-table" width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td class="label-cell" width="80">Chassis No.</td>
                <td width="150">
                  <input type="text" formControlName="chassisNo" class="txtField" style="width: 140px;">
                </td>
                
                <td width="20">
                  <input type="checkbox" formControlName="useDateRange">
                </td>
                <td class="label-cell" width="60">PDI Date</td>
                <td width="100">
                  <input type="date" formControlName="fromDate" class="txtField" style="width: 90px;" [disabled]="!searchForm.get('useDateRange')?.value">
                </td>
                <td class="label-cell" width="30" align="center">To</td>
                <td width="100">
                  <input type="date" formControlName="toDate" class="txtField" style="width: 90px;" [disabled]="!searchForm.get('useDateRange')?.value">
                </td>

                <td class="label-cell" width="50" align="right">Status&nbsp;</td>
                <td width="120">
                   <select formControlName="status" class="txtField" style="width: 110px;">
                      <option value="all">All</option>
                      <option value="ok">OK</option>
                      <option value="not_ok">Atleast One NotOk</option>
                   </select>
                </td>

                <td align="left">
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
                
                <!-- PDI No Sort & Filter -->
                <th width="30%" align="left" (click)="sort('pdiNo')" class="sortable">
                   <div class="th-content">
                      <span class="col-title">PDI No. <span *ngIf="sortColumn === 'pdiNo'">{{ sortDirection === 'asc' ? '▲' : '▼' }}</span></span>
                      <mat-icon class="search-icon" (click)="toggleFilter('pdiNo', $event)">search</mat-icon>
                   </div>
                   <div class="filter-popup" *ngIf="visibleFilters['pdiNo']" (click)="$event.stopPropagation()">
                      <input type="text" class="filter-input" [value]="filterValues['pdiNo']" (keyup)="applyFilter($event, 'pdiNo')" placeholder="Search PDI No...">
                   </div>
                </th>

                <!-- PDI Date Sort & Filter -->
                <th width="15%" align="left" (click)="sort('pdiDate')" class="sortable">
                   <div class="th-content">
                      <span class="col-title">PDI Date <span *ngIf="sortColumn === 'pdiDate'">{{ sortDirection === 'asc' ? '▲' : '▼' }}</span></span>
                      <mat-icon class="search-icon" (click)="toggleFilter('pdiDate', $event)">search</mat-icon>
                   </div>
                   <div class="filter-popup" *ngIf="visibleFilters['pdiDate']" (click)="$event.stopPropagation()">
                      <input type="text" class="filter-input" [value]="filterValues['pdiDate']" (keyup)="applyFilter($event, 'pdiDate')" placeholder="Search Date...">
                   </div>
                </th>

                <!-- Model Family Sort & Filter -->
                <th width="30%" align="left" (click)="sort('modelFamily')" class="sortable">
                   <div class="th-content">
                      <span class="col-title">Model Family <span *ngIf="sortColumn === 'modelFamily'">{{ sortDirection === 'asc' ? '▲' : '▼' }}</span></span>
                      <mat-icon class="search-icon" (click)="toggleFilter('modelFamily', $event)">search</mat-icon>
                   </div>
                   <div class="filter-popup" *ngIf="visibleFilters['modelFamily']" (click)="$event.stopPropagation()">
                      <input type="text" class="filter-input" [value]="filterValues['modelFamily']" (keyup)="applyFilter($event, 'modelFamily')" placeholder="Search Model...">
                   </div>
                </th>
              </tr>
            </thead>
            <tbody>
              <tr *ngFor="let item of filteredDataSource; let i = index" bgcolor="#FFFFFF" class="grid-row">
                <td align="center">{{ getSerialNumber(i) }}</td>
                <td align="left">{{ item.vinNo }}</td>
                <td align="left" class="pdi-cell">
                   <!-- Special Condition (e.g., Installation): Text + Eye Icon -->
                   <ng-container *ngIf="isSpecialPdi(item.pdiNo); else regularPdi">
                       {{ item.pdiNo }} 
                       <a href="javascript:void(0)" (click)="viewPdiData(item)" class="eye-icon" title="View Detail">
                         <mat-icon style="font-size: 14px; height: 14px; width: 14px; vertical-align: middle;">visibility</mat-icon>
                       </a>
                   </ng-container>
                   
                   <!-- Regular Condition: Link Only -->
                   <ng-template #regularPdi>
                       <a href="javascript:void(0)" (click)="viewPdiData(item)" class="pdi-link" title="View Detail">
                           {{ item.pdiNo }}
                       </a>
                   </ng-template>
                </td>
                <td align="left">{{ item.pdiDate }}</td>
                <td align="left">{{ item.modelFamily }}</td>
              </tr>
              <tr *ngIf="filteredDataSource.length === 0 && !loading" bgcolor="#FFFFFF">
                <td colspan="5" align="center" style="color:red; padding:10px;">No Records Found</td>
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
             <!-- Previous Arrow (Hidden on first page often, or just disabled. Screenshot doesn't show it explicitly but implied standard) -->
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
        /* Using cellspacing="1" bgcolor="#cccccc" in HTML for borders */
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
    .grid-row a {
        text-decoration: none;
    }
    .grid-row:hover td {
        background-color: #fcefa1; /* Subtle highlight often used in legacy apps, or just stick to fdfdfd */
    }

    .pdi-cell {
        display: flex; /* Flex to align icon */
        align-items: center;
        justify-content: space-between;
    }
    .eye-icon {
        color: #0052a5;
        margin-left: 5px;
    }
    
    .custom-paginator {
        text-align: center; /* Centered as per screenshot bottom area, or use 'right' if preferred */
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
export class ViewPdiComponent implements OnInit, AfterViewInit {
  searchForm: FormGroup;
  dataSource: PdiDetail[] = [];
  filteredDataSource: PdiDetail[] = [];

  loading: boolean = false;
  isEyeIconEnabled: boolean = false;

  // Sorting & Filtering
  sortColumn: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';
  filterValues: any = {
    vinNo: '',
    pdiNo: '',
    pdiDate: '',
    modelFamily: ''
  };
  visibleFilters: { [key: string]: boolean } = {};

  // Pagination
  totalElements: number = 0;
  pageSize: number = 15;
  currentPage: number = 0;

  constructor(
    private fb: FormBuilder,
    private apiService: ServicesApiService,
    private dialog: MatDialog,
    private route: ActivatedRoute
  ) {
    const yesterday = new Date();
    yesterday.setDate(yesterday.getDate() - 1);
    const today = new Date();

    this.searchForm = this.fb.group({
      chassisNo: [''],
      useDateRange: [false],
      fromDate: [yesterday.toISOString().split('T')[0]],
      toDate: [today.toISOString().split('T')[0]],
      status: ['all']
    });
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const jobType = params['jobType'];
      const flag = params['flag'];
      // Check for strictly "PDI" and "check" as per requirements
      this.isEyeIconEnabled = (jobType === 'PDI' && flag === 'check');
    });

    this.scrollToTop();
    this.onSearch();
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

    const searchParams: PdiDetailSearchParams = {
      chassisNo: formValue.chassisNo?.trim() || undefined,
      status: formValue.status === 'all' ? undefined : formValue.status,
      useDateRange: formValue.useDateRange,
      fromDate: formValue.useDateRange ? formValue.fromDate : undefined,
      toDate: formValue.useDateRange ? formValue.toDate : undefined,
      page: this.currentPage,
      size: this.pageSize
    };

    this.apiService.getPdiDetailsList(searchParams).subscribe({
      next: (response) => {
        this.dataSource = response.content || [];
        this.totalElements = response.totalElements || 0;
        this.loading = false;
        this.processData();
      },
      error: (error) => {
        console.error('Error loading data', error);
        this.loading = false;
        this.dataSource = [];
        this.filteredDataSource = [];
      }
    });
  }

  // Local Processing (Filtering & Sorting)
  processData(): void {
    let data = [...this.dataSource];

    // Filter
    data = data.filter(item => {
      const vinMatch = !this.filterValues.vinNo || (item.vinNo && item.vinNo.toLowerCase().includes(this.filterValues.vinNo.toLowerCase()));
      const pdiMatch = !this.filterValues.pdiNo || (item.pdiNo && item.pdiNo.toLowerCase().includes(this.filterValues.pdiNo.toLowerCase()));
      const dateMatch = !this.filterValues.pdiDate || (item.pdiDate && item.pdiDate.toLowerCase().includes(this.filterValues.pdiDate.toLowerCase()));
      const modelMatch = !this.filterValues.modelFamily || (item.modelFamily && item.modelFamily.toLowerCase().includes(this.filterValues.modelFamily.toLowerCase()));

      return vinMatch && pdiMatch && dateMatch && modelMatch;
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
    // Implement format-specific export if needed, logic remains similar
    console.log('Exporting...');
  }

  viewPdiData(item: PdiDetail): void {
    this.apiService.getPdiDetailView(item.vinNo, item.pdiNo).subscribe(view => {
      this.dialog.open(PdiDetailDialogComponent, {
        width: '900px',
        data: view
      });
    });
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
    const contentWrapper = document.querySelector('.dashboard-content-wrapper') as HTMLElement;
    if (contentWrapper) {
      contentWrapper.scrollTop = 0;
      contentWrapper.scrollTo({ top: 0, behavior: 'instant' });
    }
    window.scrollTo({ top: 0, left: 0, behavior: 'instant' });
    document.documentElement.scrollTop = 0;
    document.body.scrollTop = 0;
  }

  isSpecialPdi(pdiNo: string | undefined): boolean {
    return this.isEyeIconEnabled;
  }
}

