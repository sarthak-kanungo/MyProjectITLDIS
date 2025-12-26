import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-create-job-card',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatIconModule],
  template: `
    <div class="page-container">
      <mat-card>
        <mat-card-header>
          <mat-card-title>
            <mat-icon>add_task</mat-icon>
            Create Job Card
          </mat-card-title>
        </mat-card-header>
        <mat-card-content>
          <p>Create Job Card functionality will be implemented here.</p>
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
      min-height: calc(100vh - 60px) !important;
      background: #ffffff !important;
      box-sizing: border-box !important;
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
export class CreateJobCardComponent implements OnInit {
  ngOnInit(): void {
    console.log('CreateJobCardComponent loaded and initialized');
  }
}

