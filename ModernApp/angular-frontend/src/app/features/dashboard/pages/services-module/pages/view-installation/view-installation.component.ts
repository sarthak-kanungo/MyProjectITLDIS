import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-view-installation',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatIconModule],
  template: `
    <div class="page-container">
      <mat-card>
        <mat-card-header>
          <mat-card-title>
            <mat-icon>install_mobile</mat-icon>
            View Installation
          </mat-card-title>
        </mat-card-header>
        <mat-card-content>
          <p>View Installation functionality will be implemented here.</p>
        </mat-card-content>
      </mat-card>
    </div>
  `,
  styles: [`
    .page-container { padding: 24px; }
    mat-card-title { display: flex; align-items: center; gap: 8px; }
  `]
})
export class ViewInstallationComponent {}

