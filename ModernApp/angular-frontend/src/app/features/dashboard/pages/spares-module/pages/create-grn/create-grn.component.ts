import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-create-grn',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatIconModule],
  template: `
    <div class="page-container">
      <mat-card>
        <mat-card-header>
          <mat-card-title>
            <mat-icon>inventory_2</mat-icon>
            Create GRN
          </mat-card-title>
        </mat-card-header>
        <mat-card-content>
          <p>Create GRN functionality will be implemented here.</p>
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
  `]
})
export class CreateGrnComponent implements OnInit {
  ngOnInit(): void {
    // Scroll to top when component loads
    const contentWrapper = document.querySelector('.dashboard-content-wrapper') as HTMLElement;
    if (contentWrapper) {
      contentWrapper.scrollTop = 0;
      contentWrapper.scrollTo({ top: 0, behavior: 'instant' });
    }
    window.scrollTo({ top: 0, left: 0, behavior: 'instant' });
    
    // Also scroll after a brief delay to ensure DOM is ready
    setTimeout(() => {
      if (contentWrapper) {
        contentWrapper.scrollTop = 0;
        contentWrapper.scrollTo({ top: 0, behavior: 'instant' });
      }
      window.scrollTo({ top: 0, left: 0, behavior: 'instant' });
    }, 50);
  }
}

