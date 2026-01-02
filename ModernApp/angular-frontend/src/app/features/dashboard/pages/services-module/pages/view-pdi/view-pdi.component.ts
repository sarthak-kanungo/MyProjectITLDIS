import { Component, OnInit, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';
import { ServicesApiService, PdiDetail, PdiDetailSearchParams, PdiDetailView } from '../../services-api.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { PdiDetailDialogComponent } from './pdi-detail-dialog.component';

@Component({
  selector: 'app-view-pdi',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatDialogModule
  ],
  template: `
    <div class="page-container">
      <mat-card>
        <mat-card-header>
          <mat-card-title>
            <mat-icon>visibility</mat-icon>
            View PDI Detail
          </mat-card-title>
        </mat-card-header>
        <mat-card-content>
          <!-- Search Form -->
          <form [formGroup]="searchForm" (ngSubmit)="onSearch()" class="search-form">
            <div class="search-row" [class.dates-active]="searchForm.get('useDateRange')?.value">
              <mat-form-field appearance="outline" class="chassis-field">
                <mat-label>Chassis No</mat-label>
                <input matInput formControlName="chassisNo" placeholder="Enter Chassis No">
              </mat-form-field>
              
              <div class="date-range-section">
                <mat-checkbox formControlName="useDateRange" (change)="onDateRangeToggle()">
                  PDI Done Date
                </mat-checkbox>
                
                <mat-form-field appearance="outline" class="date-field" *ngIf="searchForm.get('useDateRange')?.value">
                  <mat-label>From Date</mat-label>
                  <input matInput [matDatepicker]="fromPicker" formControlName="fromDate">
                  <mat-datepicker-toggle matSuffix [for]="fromPicker"></mat-datepicker-toggle>
                  <mat-datepicker #fromPicker></mat-datepicker>
                </mat-form-field>
                
                <span class="date-separator" *ngIf="searchForm.get('useDateRange')?.value">To</span>
                
                <mat-form-field appearance="outline" class="date-field" *ngIf="searchForm.get('useDateRange')?.value">
                  <mat-label>To Date</mat-label>
                  <input matInput [matDatepicker]="toPicker" formControlName="toDate">
                  <mat-datepicker-toggle matSuffix [for]="toPicker"></mat-datepicker-toggle>
                  <mat-datepicker #toPicker></mat-datepicker>
                </mat-form-field>
              </div>
              
              <mat-form-field appearance="outline" class="status-field">
                <mat-label>Status</mat-label>
                <mat-select formControlName="status">
                  <mat-option value="all">All</mat-option>
                  <mat-option value="ok">OK</mat-option>
                  <mat-option value="Atleast One NotOk">Atleast One NotOk</mat-option>
                </mat-select>
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
            
            <table mat-table [dataSource]="dataSource" class="pdi-table" *ngIf="!loading">
              <!-- S.No Column -->
              <ng-container matColumnDef="sno">
                <th mat-header-cell *matHeaderCellDef>S.No</th>
                <td mat-cell *matCellDef="let element; let i = index">{{ getSerialNumber(i) }}</td>
              </ng-container>

              <!-- Chassis No Column -->
              <ng-container matColumnDef="vinNo">
                <th mat-header-cell *matHeaderCellDef>Chassis No</th>
                <td mat-cell *matCellDef="let element">{{ element.vinNo }}</td>
              </ng-container>

              <!-- PDI No Column -->
              <ng-container matColumnDef="pdiNo">
                <th mat-header-cell *matHeaderCellDef>PDI No</th>
                <td mat-cell *matCellDef="let element">
                  <a href="javascript:void(0)" (click)="viewPdiData(element)" class="pdi-link">
                    {{ element.pdiNo }}
                  </a>
                </td>
              </ng-container>

              <!-- PDI Done Date Column -->
              <ng-container matColumnDef="pdiDate">
                <th mat-header-cell *matHeaderCellDef>PDI Done Date</th>
                <td mat-cell *matCellDef="let element">{{ element.pdiDate }}</td>
              </ng-container>

              <!-- Model Family Column -->
              <ng-container matColumnDef="modelFamily">
                <th mat-header-cell *matHeaderCellDef>Model Family</th>
                <td mat-cell *matCellDef="let element">{{ element.modelFamily }}</td>
              </ng-container>

              <!-- Dealer Name Column -->
              <ng-container matColumnDef="dealerName" *ngIf="showDealerDropdown">
                <th mat-header-cell *matHeaderCellDef>Dealer Name</th>
                <td mat-cell *matCellDef="let element">{{ element.dealerCode }} [{{ element.dealerName }}]</td>
              </ng-container>

              <!-- Location Column -->
              <ng-container matColumnDef="location" *ngIf="showDealerDropdown">
                <th mat-header-cell *matHeaderCellDef>Location</th>
                <td mat-cell *matCellDef="let element">{{ element.locationName }}</td>
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
      padding: 24px !important; 
      display: block !important;
      width: 100% !important;
      min-height: auto !important;
      background: #ffffff !important;
      box-sizing: border-box !important;
      margin: 0 !important;
      padding-top: 24px !important;
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
      margin-bottom: -30px !important; /* Compensate for empty space left by scale */
      margin-left: 0 !important;
      margin-right: 0 !important;
      box-sizing: border-box;
    }
    .search-row {
      display: flex;
      gap: 16px;
      align-items: center;
      flex-wrap: nowrap; /* Keep single line */
    }
    .chassis-field {
      flex: 1;
      min-width: 150px;
    }
    .date-range-section {
      display: flex;
      align-items: center;
      gap: 8px;
      flex-wrap: nowrap;
    }
    .date-field {
      width: 150px;
    }
    .date-separator {
      font-weight: bold;
      margin: 0 8px;
    }
    .status-field {
      width: 180px;
    }
    .dealer-field {
      flex: 1;
      min-width: 300px;
      transition: all 0.2s ease;
    }
    .search-row.dates-active .dealer-field {
      flex: 0.5;
      min-width: 150px;
    }
    /* Hide subscript wrapper to save vertical space even when scaled */
    ::ng-deep .search-form .mat-mdc-form-field-subscript-wrapper {
      display: none !important;
    }
    ::ng-deep .search-form .mat-mdc-form-field-bottom-align {
      display: none !important;
    }
    .button-group {
      display: flex;
      gap: 8px;
      align-items: center;
      margin-top: 0;
    }
    .table-container {
      margin-top: 16px;
      position: relative;
    }
    .loading-spinner {
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 40px;
    }
    .pdi-table {
      width: 100%;
      background: white;
    }
    .pdi-table th {
      background: #eeeeee;
      font-weight: bold;
      font-size: 11px;
      padding: 8px 4px;
    }
    .pdi-table td {
      font-size: 11px;
      padding: 8px 4px;
    }
    .pdi-link {
      color: #1976d2;
      text-decoration: none;
      cursor: pointer;
    }
    .pdi-link:hover {
      text-decoration: underline;
    }
    .no-results {
      text-align: center;
      padding: 40px;
      color: red;
      font-weight: bold;
    }
  `]
})
export class ViewPdiComponent implements OnInit, AfterViewInit {
  searchForm: FormGroup;
  dataSource: PdiDetail[] = [];
  displayedColumns: string[] = [];
  dealerList: any[] = [];
  showDealerDropdown: boolean = true; // Set based on user permissions
  loading: boolean = false;
  hasResults: boolean = false;
  searched: boolean = false;

  // Pagination
  totalElements: number = 0;
  pageSize: number = 15;
  currentPage: number = 0;

  constructor(
    private fb: FormBuilder,
    private apiService: ServicesApiService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {
    // Initialize form with default values (yesterday to today for date range)
    const yesterday = new Date();
    yesterday.setDate(yesterday.getDate() - 1);
    const today = new Date();

    this.searchForm = this.fb.group({
      chassisNo: [''],
      useDateRange: [false],
      fromDate: [yesterday],
      toDate: [today],
      status: ['all'],
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
  }

  private setupDisplayedColumns(): void {
    if (this.showDealerDropdown) {
      this.displayedColumns = ['sno', 'vinNo', 'pdiNo', 'pdiDate', 'modelFamily', 'dealerName', 'location'];
    } else {
      this.displayedColumns = ['sno', 'vinNo', 'pdiNo', 'pdiDate', 'modelFamily'];
    }
  }

  private loadDealerList(): void {
    // TODO: Load dealer list from API based on user permissions
    this.dealerList = [];
  }

  onDateRangeToggle(): void {
    // Date fields are shown/hidden via *ngIf in template
  }

  onSearch(): void {
    this.searched = true;
    this.loading = true;
    this.currentPage = 0;

    const formValue = this.searchForm.value;
    const searchParams: PdiDetailSearchParams = {
      chassisNo: formValue.chassisNo?.trim() || undefined,
      dealerCode: formValue.dealerCode === 'ALL' ? undefined : formValue.dealerCode,
      status: formValue.status === 'all' ? undefined : formValue.status,
      useDateRange: formValue.useDateRange,
      fromDate: formValue.useDateRange && formValue.fromDate ?
        this.formatDate(formValue.fromDate) : undefined,
      toDate: formValue.useDateRange && formValue.toDate ?
        this.formatDate(formValue.toDate) : undefined,
      page: this.currentPage,
      size: this.pageSize
    };

    this.apiService.getPdiDetailsList(searchParams).subscribe({
      next: (response) => {
        this.dataSource = response.content || [];
        this.totalElements = response.totalElements || 0;
        this.hasResults = this.dataSource.length > 0;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading PDI details list:', error);
        this.snackBar.open('Error loading data. Please try again.', 'Close', {
          duration: 3000
        });
        this.loading = false;
        this.hasResults = false;
      }
    });
  }

  onPageChange(event: PageEvent): void {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loading = true;

    const formValue = this.searchForm.value;
    const searchParams: PdiDetailSearchParams = {
      chassisNo: formValue.chassisNo?.trim() || undefined,
      dealerCode: formValue.dealerCode === 'ALL' ? undefined : formValue.dealerCode,
      status: formValue.status === 'all' ? undefined : formValue.status,
      useDateRange: formValue.useDateRange,
      fromDate: formValue.useDateRange && formValue.fromDate ?
        this.formatDate(formValue.fromDate) : undefined,
      toDate: formValue.useDateRange && formValue.toDate ?
        this.formatDate(formValue.toDate) : undefined,
      page: this.currentPage,
      size: this.pageSize
    };

    this.apiService.getPdiDetailsList(searchParams).subscribe({
      next: (response) => {
        this.dataSource = response.content || [];
        this.totalElements = response.totalElements || 0;
        this.hasResults = this.dataSource.length > 0;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading PDI details list:', error);
        this.snackBar.open('Error loading data. Please try again.', 'Close', {
          duration: 3000
        });
        this.loading = false;
      }
    });
  }

  onExport(): void {
    const formValue = this.searchForm.value;
    const searchParams: PdiDetailSearchParams = {
      chassisNo: formValue.chassisNo?.trim() || undefined,
      dealerCode: formValue.dealerCode === 'ALL' ? undefined : formValue.dealerCode,
      status: formValue.status === 'all' ? undefined : formValue.status,
      useDateRange: formValue.useDateRange,
      fromDate: formValue.useDateRange && formValue.fromDate ?
        this.formatDate(formValue.fromDate) : undefined,
      toDate: formValue.useDateRange && formValue.toDate ?
        this.formatDate(formValue.toDate) : undefined
    };

    this.apiService.exportPdiDetailsList(searchParams).subscribe({
      next: (blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `pdi_details_${new Date().getTime()}.xlsx`;
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

  viewPdiData(element: PdiDetail): void {
    this.loading = true;
    this.apiService.getPdiDetailView(element.vinNo, element.pdiNo).subscribe({
      next: (pdiDetailView) => {
        this.loading = false;
        // Open dialog/modal with PDI details
        this.openPdiDetailDialog(pdiDetailView);
      },
      error: (error) => {
        console.error('Error loading PDI detail view:', error);
        this.snackBar.open('Error loading PDI details. Please try again.', 'Close', {
          duration: 3000
        });
        this.loading = false;
      }
    });
  }

  private openPdiDetailDialog(pdiDetailView: PdiDetailView): void {
    const dialogRef = this.dialog.open(PdiDetailDialogComponent, {
      width: '90%',
      maxWidth: '1200px',
      maxHeight: '90vh',
      data: pdiDetailView
    });
  }

  getSerialNumber(index: number): number {
    return (this.currentPage * this.pageSize) + index + 1;
  }

  private formatDate(date: Date | null): string {
    if (!date) return '';
    const d = date instanceof Date ? date : new Date(date);
    if (isNaN(d.getTime())) return '';
    const day = String(d.getDate()).padStart(2, '0');
    const month = String(d.getMonth() + 1).padStart(2, '0');
    const year = d.getFullYear();
    return `${day}/${month}/${year}`;
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

