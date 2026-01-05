import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { ServicesApiService, PdiDetailView } from '../../services-api.service';

@Component({
    selector: 'app-pdi-detail',
    standalone: true,
    imports: [CommonModule, MatButtonModule, MatIconModule, RouterModule],
    template: `
    <div class="view-pdi-container">
      <!-- Breadcrumb -->
      <div class="breadcrumb_container">
        <span class="breadcrumb_link"><a href="#">SERVICE</a> > <a routerLink="/dashboard/services/view-pdi">VIEW PDI</a> > PDI DETAIL</span>
      </div>

      <div class="content-wrapper">
        <!-- Header Bar -->
        <div class="header-bar">
           <div class="blue-stripe"></div>
           <div class="header-title">
              PDI For Chassis No: {{ data?.vinNo }}
           </div>
           <div class="header-actions">
               <a *ngIf="data?.createJobCard" 
                  href="javascript:void(0)" 
                  (click)="viewJobCard()" 
                  class="view-job-card-link">
                 View Job Card
               </a>
               <button mat-icon-button (click)="printPDI()" title="Print">
                 <mat-icon>print</mat-icon>
               </button>
               <button mat-icon-button (click)="goBack()" title="Back">
                 <mat-icon>arrow_back</mat-icon>
               </button>
           </div>
        </div>

        <div class="pdi-content" *ngIf="data">
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
                <td>{{ data?.modelFamily }}</td>
                <td class="label">Model Code:</td>
                <td>{{ data?.modelCode }}</td>
              </tr>
              <tr>
                <td class="label">Chassis No:</td>
                <td>{{ data?.vinNo }}</td>
                <td class="label">Model Family Desc:</td>
                <td>{{ data?.modelFamilyDesc || '-' }}</td>
                <td class="label">Model Code Desc:</td>
                <td>{{ data?.modelCodeDesc || '-' }}</td>
              </tr>
              <tr>
                <td class="label">Invoice Date:</td>
                <td>{{ data?.invoiceDate }}</td>
                <td class="label">Tractor Received Date:</td>
                <td>{{ data?.tractorReceivedDate || '-' }}</td>
                <td class="label">PDI No & Date:</td>
                <td>{{ data?.pdiNo }} [{{ data?.pdiDate }}]</td>
              </tr>
              <tr>
                <td class="label">Invoice No:</td>
                <td>{{ data?.invoiceNo }}</td>
                <td class="label">Engine No:</td>
                <td>{{ data?.engineNo }}</td>
                <td class="label">Dealer Name:</td>
                <td>{{ data?.dealerName }} [{{ data?.dealerCode }}]</td>
              </tr>
              <tr>
                <td class="label">PDI Done By:</td>
                <td>{{ data?.pdiDoneBy }}</td>
                <td colspan="4"></td>
              </tr>
            </table>
          </div>

          <!-- Tractor Information (Travel Card Parts) Section -->
          <div class="info-section" *ngIf="data?.travelCardParts && data!.travelCardParts!.length > 0">
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
                 <tr *ngFor="let part of data?.travelCardParts; let i = index">
                   <td class="text-center">{{ i + 1 }}</td>
                   <td>{{ part.contentDesc }}</td>
                   <td>{{ part.observation }}</td>
                   <td>{{ part.serialNo }}</td>
                 </tr>
               </tbody>
             </table>
          </div>

          <!-- PDI Checklist Section -->
          <div class="info-section" *ngIf="data?.checklist && data!.checklist!.length > 0">
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
                <ng-container *ngFor="let group of data?.checklist; let i = index">
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
                 <td>{{ data?.remarks }}</td>
               </tr>
            </table>
          </div>

          <div class="bottom-actions">
             <button class="btn" (click)="goBack()">Close</button>
          </div>
        </div>

        <div *ngIf="loading" class="loading-state">
           Loading PDI Details...
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
        height: 30px;
        display: flex;
        align-items: center;
        margin-bottom: 10px;
        position: relative;
        justify-content: space-between;
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
        padding-left: 15px;
        font-weight: bold;
        color: #000000;
        line-height: 25px;
    }
    .header-actions {
        display: flex;
        align-items: center;
        gap: 10px;
        padding-right: 10px;
    }

    .view-job-card-link {
       color: #0056b3;
       text-decoration: underline;
       font-weight: bold;
       cursor: pointer;
       font-size: 11px;
    }

    .pdi-content {
        padding: 5px;
    }

    .info-section {
      margin-bottom: 15px;
    }
    .section-header {
      background: #eeeeee;
      padding: 5px 10px;
      border: 1px solid #ddd;
      margin-bottom: 5px;
    }
    .section-header h3 {
      margin: 0;
      font-size: 12px;
      font-weight: bold;
      color: #333;
    }
    .info-table {
      width: 100%;
      border-collapse: collapse;
      font-size: 11px;
    }
    .info-table td {
      padding: 4px 6px;
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
      font-size: 11px;
      border: 1px solid #ccc;
    }
    .data-table th {
      background: #eeeeee;
      padding: 4px;
      border: 1px solid #ccc;
      font-weight: bold;
      text-align: left;
    }
    .data-table td {
      padding: 4px 6px;
      border: 1px solid #ccc;
    }
    .data-table .text-center {
      text-align: center;
    }
    .group-header {
       background-color: #f9f9f9;
       padding-left: 10px;
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

    .bottom-actions {
        display: flex;
        justify-content: center;
        margin-top: 15px;
        padding-top: 10px;
        border-top: 1px solid #eee;
    }

    .loading-state {
        text-align: center;
        padding: 20px;
        font-style: italic;
        color: #666;
    }
  `]
})
export class PdiDetailComponent implements OnInit {
    data: PdiDetailView | null = null;
    loading: boolean = false;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private apiService: ServicesApiService
    ) { }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            const vinNoEnc = params['vinNo'];
            const pdiNoEnc = params['pdiNo'];
            if (vinNoEnc && pdiNoEnc) {
                try {
                    const vinNo = atob(vinNoEnc);
                    const pdiNo = atob(pdiNoEnc);
                    this.fetchData(vinNo, pdiNo);
                } catch (e) {
                    console.error('Error decoding params', e);
                }
            }
        });

        // Scroll to top
        window.scrollTo(0, 0);
    }

    fetchData(vinNo: string, pdiNo: string): void {
        this.loading = true;
        this.apiService.getPdiDetailView(vinNo, pdiNo).subscribe({
            next: (view) => {
                this.data = view;
                this.loading = false;
            },
            error: (err) => {
                console.error('Error fetching PDI details', err);
                this.loading = false;
            }
        });
    }

    printPDI(): void {
        const vinNoEnc = this.route.snapshot.params['vinNo'];
        const pdiNoEnc = this.route.snapshot.params['pdiNo'];
        if (vinNoEnc && pdiNoEnc) {
            const url = this.router.serializeUrl(
                this.router.createUrlTree(['/print/services/pdi', vinNoEnc, pdiNoEnc])
            );
            window.open(url, '_blank', 'width=1000,height=800,scrollbars=yes,resizable=yes');
        } else {
            window.print(); // Fallback if params missing
        }
    }

    viewJobCard(): void {
        if (this.data) {
            this.router.navigate(['/dashboard/services/view-job-card'], {
                queryParams: {
                    jobCardNo: this.data.jobCardNo,
                    vinNo: this.data.vinNo,
                    flag: 'PDI'
                }
            });
        }
    }

    goBack(): void {
        this.router.navigate(['/dashboard/services/view-pdi']);
    }
}
