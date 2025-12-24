import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-create-new-order-vor',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatIconModule],
  template: `
    <div class="page-container">
      <mat-card>
        <mat-card-header>
          <mat-card-title>
            <mat-icon>add_business</mat-icon>
            Create New Order (VOR)
          </mat-card-title>
        </mat-card-header>
        <mat-card-content>
          <p>Create New Order (VOR) functionality will be implemented here.</p>
        </mat-card-content>
      </mat-card>
    </div>
  `,
  styles: [`
    .page-container { padding: 24px; }
    mat-card-title { display: flex; align-items: center; gap: 8px; }
  `]
})
export class CreateNewOrderVorComponent {}

