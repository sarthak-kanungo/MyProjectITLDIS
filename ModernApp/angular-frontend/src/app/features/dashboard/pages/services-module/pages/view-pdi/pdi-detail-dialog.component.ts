import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { PdiDetailView } from '../../services-api.service';

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
        <button mat-icon-button (click)="close()">
          <mat-icon>close</mat-icon>
        </button>
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

        <!-- Remarks Section -->
        <div class="info-section" *ngIf="data.remarks && data.remarks !== '-'">
          <div class="section-header">
            <h3>Remarks</h3>
          </div>
          <div class="remarks-content">
            {{ data.remarks }}
          </div>
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
    }
    .info-table {
      width: 100%;
      border-collapse: collapse;
      font-size: 12px;
    }
    .info-table td {
      padding: 8px 12px;
      border: 1px solid #ddd;
    }
    .info-table .label {
      background: #f9f9f9;
      font-weight: bold;
      width: 15%;
      text-align: right;
    }
    .remarks-content {
      padding: 12px;
      border: 1px solid #ddd;
      background: #fafafa;
      min-height: 60px;
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
    @Inject(MAT_DIALOG_DATA) public data: PdiDetailView
  ) {}

  close(): void {
    this.dialogRef.close();
  }
}

