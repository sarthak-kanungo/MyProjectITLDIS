import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { ServicesApiService, PdiDetailView } from '../../services-api.service';

@Component({
    selector: 'app-pdi-print',
    standalone: true,
    imports: [CommonModule, MatButtonModule, MatIconModule],
    template: `
    <div class="print-page" *ngIf="data; else loading">
      <!-- Header -->
      <div class="print-header">
         <div class="logo-container">
             <img src="assets/logo.svg" alt="ITL Logo" class="logo-img">
         </div>
         <div class="header-text">
             <h1 class="company-name">INTERNATIONAL TRACTORS LIMITED</h1>
             <p class="address">Vill. Chak Gujran, P.O. Piplanwala, Jalandhar Road, Hoshiarpur.</p>
             <p class="doc-title">PDI Certificate</p>
         </div>
         <div class="print-action-container no-print">
            <button mat-icon-button (click)="printPage()" title="Print">
               <img src="assets/images/print-icon.png" alt="Print" *ngIf="false"> <!-- If asset missing, use icon -->
               <!-- Using Mat Icon as fallback or primary if desired, but user mentioned 'print icon' -->
               <mat-icon>print</mat-icon>
            </button>
         </div>
      </div>

      <!-- Vehicle Info -->
      <div class="section-container">
         <div class="section-title">Vehicle Information</div>
         <table class="info-table">
            <tr>
               <td class="label">Product Category:</td>
               <td class="value">Tractor</td>
               <td class="label">Model Family:</td>
               <td class="value">{{ data.modelFamily }}</td>
               <td class="label">Model Code:</td>
               <td class="value">{{ data.modelCode }}</td>
            </tr>
            <tr>
               <td class="label">Chassis No:</td>
               <td class="value">{{ data.vinNo }}</td>
               <td class="label">Model Family Desc:</td>
               <td class="value">{{ data.modelFamilyDesc || '-' }}</td>
               <td class="label">Model Code Desc:</td>
               <td class="value">{{ data.modelCodeDesc || '-' }}</td>
            </tr>
            <tr>
               <td class="label">Invoice Date:</td>
               <td class="value">{{ data.invoiceDate }}</td>
               <td class="label">Tractor Received Date:</td>
               <td class="value">{{ data.tractorReceivedDate || '-' }}</td>
               <td class="label">PDI No & Date:</td>
               <td class="value">{{ data.pdiNo }} [{{ data.pdiDate }}]</td>
            </tr>
            <tr>
               <td class="label">Invoice No:</td>
               <td class="value">{{ data.invoiceNo }}</td>
               <td class="label">Engine No:</td>
               <td class="value">{{ data.engineNo }}</td>
               <td class="label">PDI Done Date:</td>
               <td class="value">{{ data.pdiDate }}</td>
            </tr>
            <tr>
               <td class="label">Dealership Name:</td>
               <td class="value" colspan="3">
                  {{ data.dealerCode }} [ {{ data.dealerName }} ]
               </td>
               <td class="label">PDI Done By:</td>
               <td class="value">{{ data.pdiDoneBy }}</td>
            </tr>
         </table>
      </div>

      <!-- Travel Card Parts -->
      <div class="section-container" *ngIf="data.travelCardParts && data.travelCardParts.length > 0">
         <table class="grid-table">
            <thead>
               <tr>
                  <th width="10%">S.No</th>
                  <th width="30%">Part Name</th>
                  <th width="30%">Part Vendor Name</th>
                  <th width="30%">Part Serial No</th>
               </tr>
            </thead>
            <tbody>
               <tr *ngFor="let part of data.travelCardParts; let i = index">
                  <td class="center">{{ i + 1 }}</td>
                  <td>{{ part.contentDesc }}</td>
                  <td>{{ part.observation }}</td>
                  <td>{{ part.serialNo }}</td>
               </tr>
            </tbody>
         </table>
      </div>

      <!-- PDI Checklist -->
      <div class="section-container" *ngIf="data.checklist && data.checklist.length > 0">
         <div class="section-title">PDI Checklist</div>
         <table class="grid-table">
            <thead>
               <tr>
                  <th width="7%">S.No</th>
                  <th width="33%">Activity</th>
                  <th width="10%">OK/Not OK</th>
                  <th width="25%">Action Taken</th>
                  <th width="25%">Remarks</th>
               </tr>
            </thead>
            <tbody>
               <ng-container *ngFor="let group of data.checklist; let i = index">
                  <ng-container *ngIf="group.items && group.items.length > 0; else singleGroup">
                     <tr>
                        <td colspan="5" class="group-header">
                           <strong>{{ group.contentDesc }}</strong>
                        </td>
                     </tr>
                     <tr *ngFor="let item of group.items; let j = index">
                        <td class="center">{{ j + 1 }}</td>
                        <td>{{ item.subContentDesc }}</td>
                        <td class="center">{{ item.textBoxOption }}</td>
                        <td>{{ item.actionTaken }}</td>
                        <td>{{ item.observations }}</td>
                     </tr>
                  </ng-container>
                  <ng-template #singleGroup>
                     <tr>
                        <td class="center">&nbsp;</td>
                        <td><strong>{{ group.contentDesc }}</strong></td>
                        <td class="center">{{ group.status }}</td>
                        <td>{{ group.actionTaken }}</td>
                        <td>{{ group.observations }}</td>
                     </tr>
                  </ng-template>
               </ng-container>
               <!-- Any Observation -->
               <tr height="40">
                  <td><strong>Any Observation</strong></td>
                  <td colspan="4">{{ data.remarks }}</td>
               </tr>
            </tbody>
         </table>
      </div>

      <!-- Footer -->
      <div class="print-footer">
         <p class="disclaimer">
            If some defect is observed during PDI, the same should be communicated to area office within seven days. 
            Warranty claim of such tractor would not be considered if the PDI form has not been sent to the area office within the prescribed time.
         </p>
         <div class="signatures">
            <div class="sig-block">
               <b>Checked By Name & Signature</b><br>
               {{ data.pdiDoneBy }}
            </div>
            <div class="sig-block">
               <b>Dealer representative Name & <br>Signature with Stamp</b>
            </div>
            <div class="sig-block date-block">
               <b>Date:</b>
            </div>
         </div>
      </div>
    </div>
    <ng-template #loading>
       <div style="text-align:center; padding: 20px;">Loading Print View...</div>
    </ng-template>
  `,
    styles: [`
    @media print {
        .no-print { display: none !important; }
        @page { margin: 0.5cm; }
    }
    .print-page {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11px;
        color: #000;
        background: #fff;
        width: 100%;
        max-width: 1000px;
        margin: 0 auto;
    }
    .print-header {
        text-align: center;
        margin-bottom: 20px;
        position: relative;
    }
    .logo-container {
        float: left;
        width: 100px;
    }
    .logo-img {
        width: 80px; 
    }
    .header-text {
        text-align: center;
    }
    .company-name {
        font-size: 18px;
        margin: 0;
        font-weight: bold;
    }
    .address {
        margin: 2px 0;
    }
    .doc-title {
        font-size: 16px; 
        font-weight: bold;
        margin-top: 10px;
        text-transform: uppercase;
    }
    .print-action-container {
        position: absolute;
        right: 0;
        top: 0;
    }
    
    .section-container {
        margin-bottom: 20px;
    }
    .section-title {
        font-weight: bold;
        margin-bottom: 5px;
        border-bottom: 1px solid #ccc;
        padding-bottom: 2px;
    }

    .info-table {
        width: 100%;
        border-collapse: collapse;
        font-size: 11px;
    }
    .info-table td {
        padding: 4px;
        border: 1px solid #ccc;
    }
    .info-table .label {
        background-color: #f9f9f9;
        font-weight: bold;
        text-align: right;
        width: 15%;
    }
    .info-table .value {
        width: 18%;
        text-align: left;
    }

    .grid-table {
        width: 100%;
        border-collapse: collapse;
        font-size: 11px;
        border: 1px solid #000;
    }
    .grid-table th {
        background-color: #eee;
        border: 1px solid #000;
        padding: 5px;
        text-align: left;
    }
    .grid-table td {
        border: 1px solid #000;
        padding: 4px;
    }
    .center { text-align: center; }
    .group-header {
        background-color: #f9f9f9;
        padding-left: 10px;
    }

    .print-footer {
        margin-top: 30px;
        border-top: 1px solid #000;
        padding-top: 10px;
    }
    .disclaimer {
        font-size: 10px;
        margin-bottom: 30px;
    }
    .signatures {
        display: flex;
        justify-content: space-between;
        margin-top: 40px;
    }
    .sig-block {
        text-align: left;
        width: 30%;
    }
  `]
})
export class PdiPrintComponent implements OnInit {
    data: PdiDetailView | null = null;
    loading: boolean = false;

    constructor(
        private route: ActivatedRoute,
        private apiService: ServicesApiService
    ) { }

    ngOnInit(): void {
        const vinNoEnc = this.route.snapshot.paramMap.get('vinNo');
        const pdiNoEnc = this.route.snapshot.paramMap.get('pdiNo');

        if (vinNoEnc && pdiNoEnc) {
            try {
                const vinNo = atob(vinNoEnc);
                const pdiNo = atob(pdiNoEnc);
                this.fetchData(vinNo, pdiNo);
            } catch (e) {
                console.error("Error decoding params", e);
            }
        }
    }

    fetchData(vinNo: string, pdiNo: string): void {
        this.loading = true;
        this.apiService.getPdiDetailView(vinNo, pdiNo).subscribe({
            next: (res) => {
                this.data = res;
                this.loading = false;
            },
            error: (err) => {
                console.error("Error loading print data", err);
                this.loading = false;
            }
        });
    }

    printPage(): void {
        window.print();
    }
}
