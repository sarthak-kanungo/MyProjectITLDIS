import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, RouterOutlet } from '@angular/router';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatBadgeModule } from '@angular/material/badge';
import { MatDividerModule } from '@angular/material/divider';
import { AuthService } from '../../core/services/auth.service';

export interface MenuItem {
  id: string;
  label: string;
  icon: string;
  route?: string;
  badge?: number;
  children?: MenuItem[];
  expanded?: boolean;
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    RouterOutlet,
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatListModule,
    MatMenuModule,
    MatBadgeModule,
    MatDividerModule
  ],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  sidenavOpened = signal(true);
  currentUser = signal<any>(null);
  menuItems = signal<MenuItem[]>([
    {
      id: 'services',
      label: 'Services Module',
      icon: 'build',
      route: '/dashboard/services',
      expanded: false,
      children: [
        {
          id: 'services-pending-pdi',
          label: 'Pending For PDI',
          icon: 'pending_actions',
          route: '/dashboard/services/pending-pdi'
        },
        {
          id: 'services-view-pdi',
          label: 'View PDI Detail',
          icon: 'visibility',
          route: '/dashboard/services/view-pdi'
        },
        {
          id: 'services-pending-installation',
          label: 'Pending For Installation',
          icon: 'pending',
          route: '/dashboard/services/pending-installation'
        },
        {
          id: 'services-view-installation',
          label: 'View Installation',
          icon: 'install_mobile',
          route: '/dashboard/services/view-installation'
        },
        {
          id: 'services-create-job-card',
          label: 'Create Job Card',
          icon: 'add_task',
          route: '/dashboard/services/create-job-card'
        },
        {
          id: 'services-view-job-cards',
          label: 'View All Job Card',
          icon: 'assignment',
          route: '/dashboard/services/view-job-cards'
        },
        {
          id: 'services-pending-closure',
          label: 'Pending Job Card For Closure',
          icon: 'lock_clock',
          route: '/dashboard/services/pending-closure'
        },
        {
          id: 'services-chassis-validation',
          label: 'Pending For Job Card Chassis Validation',
          icon: 'verified_user',
          route: '/dashboard/services/chassis-validation'
        },
        {
          id: 'services-reopen-job-card',
          label: 'Re-Open/Reject Job Card',
          icon: 'refresh',
          route: '/dashboard/services/reopen-job-card'
        },
        {
          id: 'services-generate-invoice',
          label: 'Generate Invoice',
          icon: 'receipt_long',
          route: '/dashboard/services/generate-invoice'
        },
        {
          id: 'services-search-history',
          label: 'Search Tractor History',
          icon: 'search',
          route: '/dashboard/services/search-history'
        },
        {
          id: 'services-reminder',
          label: 'Service Reminder',
          icon: 'notifications_active',
          route: '/dashboard/services/reminder'
        },
        {
          id: 'services-due-date',
          label: 'Service Due Date / Lapse Report',
          icon: 'event_busy',
          route: '/dashboard/services/due-date'
        }
      ]
    },
    {
      id: 'spares',
      label: 'Spares Module',
      icon: 'inventory',
      route: '/dashboard/spares',
      expanded: false,
      children: [
        {
          id: 'spares-invoices',
          label: 'Invoices',
          icon: 'receipt',
          route: '/dashboard/spares/invoices'
        },
        {
          id: 'spares-sales-orders',
          label: 'Sales Orders',
          icon: 'shopping_cart',
          route: '/dashboard/spares/sales-orders'
        },
        {
          id: 'spares-purchase-orders',
          label: 'Purchase Orders',
          icon: 'shopping_bag',
          route: '/dashboard/spares/purchase-orders'
        },
        {
          id: 'spares-inventory',
          label: 'Inventory',
          icon: 'warehouse',
          route: '/dashboard/spares/inventory'
        },
        {
          id: 'spares-grn',
          label: 'GRN',
          icon: 'inventory_2',
          route: '/dashboard/spares/grn'
        }
      ]
    }
  ]);

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.currentUser.set(this.authService.currentUser());
  }

  toggleSidenav(): void {
    this.sidenavOpened.update(value => !value);
  }

  toggleMenu(item: MenuItem, event: Event): void {
    if (item.children) {
      event.preventDefault();
      event.stopPropagation();
      item.expanded = !item.expanded;
      // Update the signal to trigger change detection
      this.menuItems.set([...this.menuItems()]);
    }
  }

  isRouteActive(item: MenuItem): boolean {
    if (!item.route) return false;
    // Check if any child route is active
    if (item.children) {
      return item.children.some(child => {
        const currentPath = window.location.pathname;
        return child.route && currentPath.startsWith(child.route);
      });
    }
    return false;
  }

  logout(): void {
    this.authService.logout();
  }
}

