import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
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
          id: 'spares-view-inventory',
          label: 'View Inventory',
          icon: 'inventory',
          route: '/dashboard/spares/view-inventory'
        },
        {
          id: 'spares-counter-sale',
          label: 'Counter Sale',
          icon: 'point_of_sale',
          route: '/dashboard/spares/counter-sale'
        },
        {
          id: 'spares-add-inventory',
          label: 'Add Inventory From Other Dealers',
          icon: 'add_shopping_cart',
          route: '/dashboard/spares/add-inventory'
        },
        {
          id: 'spares-counter-sales-return',
          label: 'Counter Sales Return',
          icon: 'assignment_return',
          route: '/dashboard/spares/counter-sales-return'
        },
        {
          id: 'spares-transaction-details',
          label: 'Transaction Details',
          icon: 'receipt_long',
          route: '/dashboard/spares/transaction-details'
        },
        {
          id: 'spares-view-invoice',
          label: 'View Invoice',
          icon: 'receipt',
          route: '/dashboard/spares/view-invoice'
        },
        {
          id: 'spares-view-stock-ledger',
          label: 'View Stock Ledger',
          icon: 'book',
          route: '/dashboard/spares/view-stock-ledger'
        },
        {
          id: 'spares-manage-reorder-level',
          label: 'Manage Re-Order Level',
          icon: 'settings',
          route: '/dashboard/spares/manage-reorder-level'
        },
        {
          id: 'spares-view-reorder-level',
          label: 'View Re-Order Level',
          icon: 'visibility',
          route: '/dashboard/spares/view-reorder-level'
        },
        {
          id: 'spares-add-reorder-parts',
          label: 'Add Re-Order Parts To Cart',
          icon: 'add_shopping_cart',
          route: '/dashboard/spares/add-reorder-parts'
        },
        {
          id: 'spares-create-new-order',
          label: 'Create New Order',
          icon: 'add_business',
          route: '/dashboard/spares/create-new-order'
        },
        {
          id: 'spares-create-new-order-vor',
          label: 'Create New Order (VOR)',
          icon: 'add_business',
          route: '/dashboard/spares/create-new-order-vor'
        },
        {
          id: 'spares-view-order',
          label: 'View Order',
          icon: 'list_alt',
          route: '/dashboard/spares/view-order'
        },
        {
          id: 'spares-pending-cancelled-lines',
          label: 'Pending Cancelled Lines For Acceptance',
          icon: 'cancel',
          route: '/dashboard/spares/pending-cancelled-lines'
        },
        {
          id: 'spares-view-purchase-order-invoice',
          label: 'View Purchase Order Invoice',
          icon: 'description',
          route: '/dashboard/spares/view-purchase-order-invoice'
        },
        {
          id: 'spares-create-grn',
          label: 'Create GRN',
          icon: 'inventory_2',
          route: '/dashboard/spares/create-grn'
        },
        {
          id: 'spares-back-order-report',
          label: 'Back Order Report',
          icon: 'assessment',
          route: '/dashboard/spares/back-order-report'
        },
        {
          id: 'spares-order-details-report',
          label: 'Order Details Report',
          icon: 'summarize',
          route: '/dashboard/spares/order-details-report'
        },
        {
          id: 'spares-invoice-details-report',
          label: 'Invoice Details Report',
          icon: 'description',
          route: '/dashboard/spares/invoice-details-report'
        }
      ]
    }
  ]);

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.currentUser.set(this.authService.currentUser());
    this.expandActiveMenu();
  }

  expandActiveMenu(): void {
    const currentPath = window.location.pathname;
    this.menuItems().forEach(item => {
      if (item.children) {
        // Check if any child route is active
        const hasActiveChild = item.children.some(child => {
          return child.route && currentPath.startsWith(child.route);
        });
        // Also check if the parent route itself is active
        const isParentActive = item.route && currentPath === item.route;
        if (hasActiveChild || isParentActive) {
          item.expanded = true;
        }
      }
    });
    // Update signal to trigger change detection
    this.menuItems.set([...this.menuItems()]);
  }

  toggleSidenav(): void {
    this.sidenavOpened.update(value => !value);
  }

  toggleMenu(item: MenuItem, event: Event): void {
    if (item.children) {
      // If clicking the parent item, navigate to its dashboard and expand menu
      if (item.route) {
        this.router.navigate([item.route]);
      }
      // Toggle expansion
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

