import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-install-info',
    standalone: true,
    imports: [CommonModule],
    template: `
    <div style="padding: 20px;">
      <h2>Installation Information</h2>
      <p>Form to add installation details will be implemented here.</p>
    </div>
  `
})
export class InstallInfoComponent { }
