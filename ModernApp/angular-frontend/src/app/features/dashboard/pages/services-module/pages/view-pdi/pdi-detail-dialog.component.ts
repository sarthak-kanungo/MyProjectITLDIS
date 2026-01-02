import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { PdiDetailView } from '../../services-api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pdi-detail-dialog',
  standalone: true,
  imports: [CommonModule, MatDialogModule, MatButtonModule, MatIconModule],
  template: `
    <div class="pdi-detail-dialog">
      <div class="dialog-header">
        <h2>
          <mat-icon>visibility</mat-icon>
          PDI For Chassis No: {{ data.vinNo }}
        </h2>
        <div class="header-actions">
           <a *ngIf="data.createJobCard" 
              href="javascript:void(0)" 
              (click)="viewJobCard()" 
              class="view-job-card-link">
             View Job Card
           </a>
           <button mat-icon-button (click)="printPDI()" title="Print">
             <mat-icon>print</mat-icon>
           </button>
           <button mat-icon-button (click)="close()">
             <mat-icon>close</mat-icon>
           </button>
        </div>
      </div>

      <div class="dialog-content">
        <!-- Vehicle Information Section -->
        <div class="info-section">
          <div class="section-header">
            <h3>Vehicle Information</h3>
          </div>
          <table class="info-table">
            <tr>
              <td class="label">Product Category:</td>
              <td>Tractor</td>
              <td class="label">Model Family:</td>
              <td>{{ data.modelFamily }}</td>
              <td class="label">Model Code:</td>
              <td>{{ data.modelCode }}</td>
            </tr>
            <tr>
              <td class="label">Chassis No:</td>
              <td>{{ data.vinNo }}</td>
              <td class="label">Model Family Desc:</td>
              <td>{{ data.modelFamilyDesc || '-' }}</td>
              <td class="label">Model Code Desc:</td>
              <td>{{ data.modelCodeDesc || '-' }}</td>
            </tr>
            <tr>
              <td class="label">Invoice Date:</td>
              <td>{{ data.invoiceDate }}</td>
              <td class="label">Tractor Received Date:</td>
              <td>{{ data.tractorReceivedDate || '-' }}</td>
              <td class="label">PDI No & Date:</td>
              <td>{{ data.pdiNo }} [{{ data.pdiDate }}]</td>
            </tr>
            <tr>
              <td class="label">Invoice No:</td>
              <td>{{ data.invoiceNo }}</td>
              <td class="label">Engine No:</td>
              <td>{{ data.engineNo }}</td>
              <td class="label">Dealer Name:</td>
              <td>{{ data.dealerName }} [{{ data.dealerCode }}]</td>
            </tr>
            <tr>
              <td class="label">PDI Done By:</td>
              <td>{{ data.pdiDoneBy }}</td>
              <td colspan="4"></td>
            </tr>
          </table>
        </div>

        <!-- Tractor Information (Travel Card Parts) Section -->
        <div class="info-section" *ngIf="data.travelCardParts && data.travelCardParts.length > 0">
           <table class="data-table">
             <thead>
               <tr>
                 <th style="width: 10%">S.No</th>
                 <th style="width: 30%">Part Name</th>
                 <th style="width: 30%">Part Vendor Name</th>
                 <th style="width: 30%">Part Serial No</th>
               </tr>
             </thead>
             <tbody>
               <tr *ngFor="let part of data.travelCardParts; let i = index">
                 <td class="text-center">{{ i + 1 }}</td>
                 <td>{{ part.contentDesc }}</td>
                 <td>{{ part.observation }}</td>
                 <td>{{ part.serialNo }}</td>
               </tr>
             </tbody>
           </table>
        </div>

        <!-- PDI Checklist Section -->
        <div class="info-section" *ngIf="data.checklist && data.checklist.length > 0">
          <div class="section-header">
            <h3>PDI Checklist</h3>
          </div>
          <table class="data-table">
            <thead>
              <tr>
                <th style="width: 7%">S.No</th>
                <th style="width: 33%">Activity</th>
                <th style="width: 10%">OK/Not OK</th>
                <th style="width: 25%">Action Taken</th>
                <th style="width: 25%">Remarks</th>
              </tr>
            </thead>
            <tbody>
              <!-- Loop through groups -->
              <ng-container *ngFor="let group of data.checklist; let i = index">
                <!-- If group has items -->
                <ng-container *ngIf="group.items && group.items.length > 0; else singleGroupRow">
                  <tr>
                    <td colspan="5" class="group-header">
                      <strong>{{ group.contentDesc }}</strong>
                    </td>
                  </tr>
                  <tr *ngFor="let item of group.items; let j = index">
                    <td class="text-center">{{ j + 1 }}</td>
                    <td>{{ item.subContentDesc }}</td>
                    <td class="text-center">{{ item.textBoxOption }}</td>
                    <td>{{ item.actionTaken }}</td>
                    <td>{{ item.observations }}</td>
                  </tr>
                </ng-container>
                
                <!-- Else if group is single item -->
                <ng-template #singleGroupRow>
                  <tr>
                     <td class="text-center">&nbsp;</td>
                     <td><strong>{{ group.contentDesc }}</strong></td>
                     <td class="text-center">{{ group.status }}</td>
                     <td>{{ group.actionTaken }}</td>
                     <td>{{ group.observations }}</td>
                  </tr>
                </ng-template>
              </ng-container>
            </tbody>
          </table>
        </div>

        <!-- Any Observation / Remarks -->
        <div class="info-section">
          <table class="info-table">
             <tr>
               <td class="label" style="width: 20%">Any Observation:</td>
               <td>{{ data.remarks }}</td>
             </tr>
          </table>
        </div>

      </div>

      <div class="dialog-actions">
        <button mat-raised-button (click)="close()">Close</button>
      </div>
    </div>
  `,
  styles: [`
    .pdi-detail-dialog {
      display: flex;
      flex-direction: column;
      height: 100%;
    }
    .dialog-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 16px 24px;
      border-bottom: 1px solid #e0e0e0;
      background: #f5f5f5;
    }
    .dialog-header h2 {
      margin: 0;
      display: flex;
      align-items: center;
      gap: 8px;
      font-size: 20px;
      font-weight: 500;
    }
    .header-actions {
      display: flex;
      align-items: center;
      gap: 16px;
    }
    .view-job-card-link {
       color: #0056b3;
       text-decoration: underline;
       font-weight: bold;
       cursor: pointer;
    }
    .dialog-content {
      flex: 1;
      overflow-y: auto;
      padding: 24px;
    }
    .info-section {
      margin-bottom: 24px;
    }
    .section-header {
      background: #eeeeee;
      padding: 8px 12px;
      border: 1px solid #ddd;
      margin-bottom: 12px;
    }
    .section-header h3 {
      margin: 0;
      font-size: 14px;
      font-weight: bold;
      color: #333;
    }
    .info-table {
      width: 100%;
      border-collapse: collapse;
      font-size: 12px;
    }
    .info-table td {
      padding: 6px 10px;
      border: 1px solid #ddd;
    }
    .info-table .label {
      background: #f9f9f9;
      font-weight: bold;
      width: 15%;
      text-align: right;
      color: #555;
    }
    
    .data-table {
      width: 100%;
      border-collapse: collapse;
      font-size: 12px;
      border: 1px solid #ccc;
    }
    .data-table th {
      background: #eeeeee;
      padding: 8px;
      border: 1px solid #ccc;
      font-weight: bold;
      text-align: left;
    }
    .data-table td {
      padding: 6px 8px;
      border: 1px solid #ccc;
    }
    .data-table .text-center {
      text-align: center;
    }
    .group-header {
       background-color: #f9f9f9;
       padding-left: 10px;
    }

    .dialog-actions {
      padding: 16px 24px;
      border-top: 1px solid #e0e0e0;
      display: flex;
      justify-content: flex-end;
      gap: 8px;
    }
  `]
})
export class PdiDetailDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<PdiDetailDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: PdiDetailView,
    private router: Router
  ) { }

  close(): void {
    this.dialogRef.close();
  }

  printPDI(): void {
    window.print();
  }

  viewJobCard(): void {
    // Navigate to Job Card details using query params as per legacy workflow logic
    // legacy: serviceProcess.do?option=viewJobcard&jobCardNo=...&vinNo=...&flag=PDI
    this.close();
    this.router.navigate(['/dashboard/services/view-job-card'], {
      queryParams: {
        jobCardNo: this.data.jobCardNo,
        vinNo: this.data.vinNo,
        flag: 'PDI'
      }
    });
  }
}
