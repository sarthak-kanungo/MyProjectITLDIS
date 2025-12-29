import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatSortModule, MatSort } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';
import { ServicesApiService, PendingChassis, PendingPdiSearchParams } from '../../services-api.service';

@Component({
  selector: 'app-pending-pdi',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSnackBarModule
  ],
  template: `
    <div class="page-container">
      <mat-card>
        <mat-card-header>
          <mat-card-title>
            <mat-icon>pending_actions</mat-icon>
            Pending For PDI
          </mat-card-title>
        </mat-card-header>
        <mat-card-content>
          <!-- Search Form -->
          <form [formGroup]="searchForm" (ngSubmit)="onSearch()" class="search-form">
            <div class="search-row">
              <mat-form-field appearance="outline" class="chassis-field">
                <mat-label>Chassis No</mat-label>
                <input matInput formControlName="chassisNo" placeholder="Enter Chassis No">
              </mat-form-field>
              
              <mat-form-field appearance="outline" class="dealer-field" *ngIf="showDealerDropdown">
                <mat-label>Dealer Name</mat-label>
                <mat-select formControlName="dealerCode">
                  <mat-option value="ALL">ALL</mat-option>
                  <mat-option *ngFor="let dealer of dealerList" [value]="dealer.code">
                    {{ dealer.name }} [{{ dealer.location }}] [{{ dealer.code }}]
                  </mat-option>
                </mat-select>
              </mat-form-field>
              
              <div class="button-group">
                <button mat-raised-button color="primary" type="submit" [disabled]="loading">
                  <mat-icon>search</mat-icon>
                  Search
                </button>
                <button mat-raised-button type="button" (click)="onExport()" [disabled]="loading || !hasResults">
                  <mat-icon>download</mat-icon>
                  Export
                </button>
              </div>
            </div>
          </form>

          <!-- Results Table -->
          <div class="table-container" *ngIf="hasResults || loading">
            <div *ngIf="loading" class="loading-spinner">
              <mat-spinner diameter="40"></mat-spinner>
            </div>
            
            <table mat-table [dataSource]="tableDataSource" matSort class="pending-table" *ngIf="!loading">
              <!-- S.No Column -->
              <ng-container matColumnDef="sno">
                <th mat-header-cell *matHeaderCellDef>S.No</th>
                <td mat-cell *matCellDef="let element; let i = index">{{ getSerialNumber(i) }}</td>
              </ng-container>

              <!-- Chassis No Column -->
              <ng-container matColumnDef="vinNo">
                <th mat-header-cell *matHeaderCellDef mat-sort-header="vinNo" (click)="toggleFilter('vinNo', $event)">
                  <div class="header-with-filter">
                    <span>Chassis No</span>
                    <input matInput class="column-filter" 
                           [class.visible]="visibleFilters['vinNo']"
                           placeholder="Filter..." 
                           (keyup)="applyColumnFilter($event, 'vinNo')" 
                           (click)="$event.stopPropagation()"
                           (blur)="onFilterBlur('vinNo')">
                  </div>
                </th>
                <td mat-cell *matCellDef="let element">{{ element.vinNo }}</td>
              </ng-container>

              <!-- Model Family Column -->
              <ng-container matColumnDef="modelFamily">
                <th mat-header-cell *matHeaderCellDef mat-sort-header="modelFamily" (click)="toggleFilter('modelFamily', $event)">
                  <div class="header-with-filter">
                    <span>Model Family</span>
                    <input matInput class="column-filter" 
                           [class.visible]="visibleFilters['modelFamily']"
                           placeholder="Filter..." 
                           (keyup)="applyColumnFilter($event, 'modelFamily')" 
                           (click)="$event.stopPropagation()"
                           (blur)="onFilterBlur('modelFamily')">
                  </div>
                </th>
                <td mat-cell *matCellDef="let element">{{ element.modelFamily }}</td>
              </ng-container>

              <!-- Model Code Column -->
              <ng-container matColumnDef="modelCode">
                <th mat-header-cell *matHeaderCellDef mat-sort-header="modelCode" (click)="toggleFilter('modelCode', $event)">
                  <div class="header-with-filter">
                    <span>Model Code</span>
                    <input matInput class="column-filter" 
                           [class.visible]="visibleFilters['modelCode']"
                           placeholder="Filter..." 
                           (keyup)="applyColumnFilter($event, 'modelCode')" 
                           (click)="$event.stopPropagation()"
                           (blur)="onFilterBlur('modelCode')">
                  </div>
                </th>
                <td mat-cell *matCellDef="let element">{{ element.modelCode }}</td>
              </ng-container>

              <!-- Dealer Name Column -->
              <ng-container matColumnDef="dealerName" *ngIf="showDealerDropdown">
                <th mat-header-cell *matHeaderCellDef mat-sort-header="dealerName" (click)="toggleFilter('dealerName', $event)">
                  <div class="header-with-filter">
                    <span>Dealer Name</span>
                    <input matInput class="column-filter" 
                           [class.visible]="visibleFilters['dealerName']"
                           placeholder="Filter..." 
                           (keyup)="applyColumnFilter($event, 'dealerName')" 
                           (click)="$event.stopPropagation()"
                           (blur)="onFilterBlur('dealerName')">
                  </div>
                </th>
                <td mat-cell *matCellDef="let element">{{ element.dealerCode }} [{{ element.dealerName }}]</td>
              </ng-container>

              <!-- Location Column -->
              <ng-container matColumnDef="location" *ngIf="showDealerDropdown">
                <th mat-header-cell *matHeaderCellDef mat-sort-header="location" (click)="toggleFilter('location', $event)">
                  <div class="header-with-filter">
                    <span>Location</span>
                    <input matInput class="column-filter" 
                           [class.visible]="visibleFilters['location']"
                           placeholder="Filter..." 
                           (keyup)="applyColumnFilter($event, 'location')" 
                           (click)="$event.stopPropagation()"
                           (blur)="onFilterBlur('location')">
                  </div>
                </th>
                <td mat-cell *matCellDef="let element">{{ element.locationName }}</td>
              </ng-container>

              <!-- Pending Days Column -->
              <ng-container matColumnDef="pendingDays">
                <th mat-header-cell *matHeaderCellDef mat-sort-header="pdiPendingDays" (click)="toggleFilter('pdiPendingDays', $event)">
                  <div class="header-with-filter">
                    <span>Pending Days</span>
                    <input matInput class="column-filter" 
                           [class.visible]="visibleFilters['pdiPendingDays']"
                           placeholder="Filter..." 
                           (keyup)="applyColumnFilter($event, 'pdiPendingDays')" 
                           (click)="$event.stopPropagation()"
                           (blur)="onFilterBlur('pdiPendingDays')">
                  </div>
                </th>
                <td mat-cell *matCellDef="let element">{{ element.pdiPendingDays }}</td>
              </ng-container>

              <tr mat-header-row *matHeaderRowDef="displayedColumns"></tr>
              <tr mat-row *matRowDef="let row; columns: displayedColumns"></tr>
            </table>

            <!-- Pagination -->
            <mat-paginator
              *ngIf="!loading && hasResults"
              [length]="totalElements"
              [pageSize]="pageSize"
              [pageIndex]="currentPage"
              [pageSizeOptions]="[10, 15, 25, 50, 100]"
              (page)="onPageChange($event)"
              showFirstLastButtons>
            </mat-paginator>
          </div>

          <!-- No Results Message -->
          <div class="no-results" *ngIf="!loading && !hasResults && searched">
            <p>No records found</p>
          </div>
        </mat-card-content>
      </mat-card>
    </div>
  `,
  styles: [`
    :host {
      display: block !important;
      width: 100% !important;
      min-height: calc(100vh - 60px) !important;
      height: auto !important;
      visibility: visible !important;
      opacity: 1 !important;
      position: relative !important;
      top: 0 !important;
      left: 0 !important;
      z-index: 10 !important;
      background: #ffffff !important;
      overflow: visible !important;
      margin: 0 !important;
      padding: 0 !important;
    }
    .page-container { 
      padding: 16px !important; 
      display: block !important;
      width: 100% !important;
      min-height: auto !important;
      background: #ffffff !important;
      box-sizing: border-box !important;
      margin: 0 !important;
    }
    mat-card {
      display: block !important;
      width: 100% !important;
      background: #ffffff !important;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1) !important;
    }
    mat-card-title { 
      display: flex !important; 
      align-items: center !important; 
      gap: 8px !important; 
    }
    .search-form {
      width: 149.25% !important;
      max-width: 149.25% !important;
      min-width: 149.25% !important;
      padding: 16px;
      background: #f5f5f5;
      border-radius: 4px;
      transform: scale(0.67);
      transform-origin: left top;
      margin-bottom: -10px !important;
      margin-left: 0 !important;
      margin-right: 0 !important;
      box-sizing: border-box;
    }
    .search-row {
      width: 100%;
      display: flex;
      gap: 16px;
      align-items: flex-start;
      flex-wrap: wrap;
      margin-bottom: 0 !important;
      padding-bottom: 0 !important;
    }
    .chassis-field {
      flex: 1;
      min-width: 200px;
      margin-bottom: 0 !important;
    }
    .chassis-field ::ng-deep .mat-mdc-form-field-subscript-wrapper {
      display: none !important;
      height: 0 !important;
      margin: 0 !important;
      padding: 0 !important;
    }
    .chassis-field ::ng-deep .mat-mdc-form-field-bottom-align {
      display: none !important;
    }
    .dealer-field {
      flex: 1;
      min-width: 300px;
      margin-bottom: 0 !important;
    }
    .dealer-field ::ng-deep .mat-mdc-form-field-subscript-wrapper {
      display: none !important;
      height: 0 !important;
      margin: 0 !important;
      padding: 0 !important;
    }
    .dealer-field ::ng-deep .mat-mdc-form-field-bottom-align {
      display: none !important;
    }
    .button-group {
      display: flex;
      gap: 8px;
      align-items: center;
      margin-top: 8px;
    }
    .table-container {
      margin-top: -10px !important;
      position: relative;
    }
    .loading-spinner {
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 40px;
    }
    .pending-table {
      width: 100%;
      background: white;
    }
    .pending-table th {
      background: #eeeeee;
      font-weight: bold;
      font-size: 11px;
      padding: 8px 4px;
      position: relative;
    }
    .header-with-filter {
      display: flex;
      flex-direction: column;
      gap: 4px;
      align-items: flex-start;
      position: relative;
      cursor: pointer;
    }
    .header-with-filter span {
      font-weight: bold;
      display: flex;
      align-items: center;
      gap: 4px;
    }
    .header-with-filter span::after {
      content: '🔍';
      font-size: 10px;
      opacity: 0.5;
    }
    .column-filter {
      width: 100%;
      font-size: 10px;
      padding: 2px 4px;
      border: 1px solid #ccc;
      border-radius: 2px;
      background: white;
      display: none;
      opacity: 0;
      transition: opacity 0.2s ease;
      margin-top: 4px;
    }
    .column-filter.visible {
      display: block;
      opacity: 1;
    }
    .column-filter:focus {
      outline: 1px solid #1976d2;
      border-color: #1976d2;
    }
    .pending-table td {
      font-size: 11px;
      padding: 8px 4px;
    }
    .no-results {
      text-align: center;
      padding: 40px;
      color: red;
      font-weight: bold;
    }
  `]
})
export class PendingPdiComponent implements OnInit, AfterViewInit {
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  
  searchForm: FormGroup;
  dataSource: PendingChassis[] = [];
  tableDataSource = new MatTableDataSource<PendingChassis>([]);
  displayedColumns: string[] = [];
  dealerList: any[] = [];
  showDealerDropdown: boolean = true; // Set based on user permissions
  loading: boolean = false;
  hasResults: boolean = false;
  searched: boolean = false;
  
  // Column filters
  columnFilters: { [key: string]: string } = {};
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
    this.setupDisplayedColumns();
    this.loadDealerList();
    // Load initial data
    this.onSearch();
  }

  ngAfterViewInit(): void {
    setTimeout(() => this.scrollToTop(), 0);
    setTimeout(() => this.scrollToTop(), 50);
    setTimeout(() => this.scrollToTop(), 100);
    
    // Set up custom sort accessor
    this.tableDataSource.sortingDataAccessor = (item: PendingChassis, property: string) => {
      switch (property) {
        case 'dealerName':
          return item.dealerName || '';
        case 'location':
          return item.locationName || '';
        case 'pdiPendingDays':
          return item.pdiPendingDays || 0;
        default:
          const value = (item as any)[property];
          return value != null ? value : '';
      }
    };
    
    // Connect sort to data source after view init
    setTimeout(() => {
      this.connectSort();
    }, 200);
  }

  private setupDisplayedColumns(): void {
    if (this.showDealerDropdown) {
      this.displayedColumns = ['sno', 'vinNo', 'modelFamily', 'modelCode', 'dealerName', 'location', 'pendingDays'];
    } else {
      this.displayedColumns = ['sno', 'vinNo', 'modelFamily', 'modelCode', 'pendingDays'];
    }
  }

  private loadDealerList(): void {
    // TODO: Load dealer list from API based on user permissions
    // For now, using empty array - will be populated from API
    this.dealerList = [];
  }

  onSearch(): void {
    this.searched = true;
    this.loading = true;
    this.currentPage = 0;
    // Clear column filters when performing new search
    this.columnFilters = {};
    
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
        this.tableDataSource.data = this.dataSource;
        this.totalElements = response.totalElements || 0;
        this.hasResults = this.dataSource.length > 0;
        this.loading = false;
        
        // Reconnect sort after data load
        if (this.sort) {
          this.tableDataSource.sort = this.sort;
        }
        // Reapply column filters if any exist
        this.reapplyColumnFilters();
      },
      error: (error) => {
        console.error('Error loading pending PDI chassis list:', error);
        this.snackBar.open('Error loading data. Please try again.', 'Close', {
          duration: 3000
        });
        this.loading = false;
        this.hasResults = false;
        this.tableDataSource.data = [];
      }
    });
  }

  onPageChange(event: PageEvent): void {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
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
        this.tableDataSource.data = this.dataSource;
        this.totalElements = response.totalElements || 0;
        this.hasResults = this.dataSource.length > 0;
        this.loading = false;
        
        // Reconnect sort after data load and table renders
        setTimeout(() => {
          this.connectSort();
        }, 100);
        // Reapply column filters if any exist
        this.reapplyColumnFilters();
      },
      error: (error) => {
        console.error('Error loading pending PDI chassis list:', error);
        this.snackBar.open('Error loading data. Please try again.', 'Close', {
          duration: 3000
        });
        this.loading = false;
      }
    });
  }

  private connectSort(): void {
    // Try multiple times to ensure sort is connected
    const tryConnect = () => {
      if (this.sort && this.tableDataSource) {
        this.tableDataSource.sort = this.sort;
        return true;
      }
      return false;
    };
    
    // Try immediately
    if (!tryConnect()) {
      // Try after a short delay
      setTimeout(() => {
        if (!tryConnect()) {
          // Try one more time after longer delay
          setTimeout(() => tryConnect(), 300);
        }
      }, 100);
    }
  }

  toggleFilter(column: string, event: Event): void {
    // Don't toggle if clicking on the input itself or sort icon
    const target = event.target as HTMLElement;
    if (target.tagName === 'INPUT' || target.closest('.mat-sort-header-arrow')) {
      return;
    }
    
    // Check if clicking on the sort button area
    if (target.closest('button') || target.closest('.mat-sort-header')) {
      // Only toggle if not clicking directly on sort arrow
      const sortArrow = target.querySelector('.mat-sort-header-arrow');
      if (sortArrow && sortArrow.contains(target)) {
        return;
      }
    }
    
    // Toggle filter visibility
    this.visibleFilters[column] = !this.visibleFilters[column];
    
    // Focus the input if it's being shown
    if (this.visibleFilters[column]) {
      setTimeout(() => {
        const headerCell = (event.currentTarget as HTMLElement);
        const input = headerCell.querySelector('.column-filter') as HTMLInputElement;
        if (input) {
          input.focus();
        }
      }, 0);
    }
  }

  onFilterBlur(column: string): void {
    // Hide filter if it's empty when losing focus
    // Keep it visible if it has a value
    if (!this.columnFilters[column] || this.columnFilters[column].trim() === '') {
      setTimeout(() => {
        // Double-check it's still empty (user might have typed something)
        if (!this.columnFilters[column] || this.columnFilters[column].trim() === '') {
          this.visibleFilters[column] = false;
        }
      }, 200); // Small delay to allow click events to process
    }
  }

  applyColumnFilter(event: Event, column: string): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.columnFilters[column] = filterValue.trim().toLowerCase();
    
    // Keep filter visible if it has a value
    if (this.columnFilters[column] && this.columnFilters[column].length > 0) {
      this.visibleFilters[column] = true;
    }
    
    this.reapplyColumnFilters();
  }

  private reapplyColumnFilters(): void {
    // Set up custom filter predicate for column filters
    this.tableDataSource.filterPredicate = (data: PendingChassis, filter: string) => {
      // Check each column filter
      for (const [col, filterVal] of Object.entries(this.columnFilters)) {
        if (filterVal && filterVal.length > 0) {
          let cellValue = '';
          
          switch (col) {
            case 'vinNo':
              cellValue = (data.vinNo || '').toLowerCase();
              break;
            case 'modelFamily':
              cellValue = (data.modelFamily || '').toLowerCase();
              break;
            case 'modelCode':
              cellValue = (data.modelCode || '').toLowerCase();
              break;
            case 'dealerName':
              cellValue = ((data.dealerCode || '') + ' ' + (data.dealerName || '')).toLowerCase();
              break;
            case 'location':
              cellValue = (data.locationName || '').toLowerCase();
              break;
            case 'pdiPendingDays':
              cellValue = (data.pdiPendingDays || 0).toString();
              break;
            default:
              cellValue = '';
          }
          
          if (!cellValue.includes(filterVal)) {
            return false;
          }
        }
      }
      return true;
    };
    
    // Trigger filter by setting a dummy filter value
    this.tableDataSource.filter = Math.random().toString();
  }

  onExport(): void {
    const formValue = this.searchForm.value;
    const searchParams: PendingPdiSearchParams = {
      chassisNo: formValue.chassisNo?.trim() || undefined,
      dealerCode: formValue.dealerCode === 'ALL' ? undefined : formValue.dealerCode
    };

    this.apiService.exportPendingPdiChassisList(searchParams).subscribe({
      next: (blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `pending_pdi_chassis_${new Date().getTime()}.xlsx`;
        document.body.appendChild(a);
        a.click();
        window.URL.revokeObjectURL(url);
        document.body.removeChild(a);
        this.snackBar.open('Export started successfully', 'Close', {
          duration: 2000
        });
      },
      error: (error) => {
        console.error('Error exporting data:', error);
        this.snackBar.open('Error exporting data. Please try again.', 'Close', {
          duration: 3000
        });
      }
    });
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
}

