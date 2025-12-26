import { Component, OnInit, OnDestroy, signal, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule, ViewportScroller } from '@angular/common';
import { Router, RouterModule, RouterOutlet, NavigationEnd } from '@angular/router';
import { filter, Subscription } from 'rxjs';
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
export class DashboardComponent implements OnInit, OnDestroy, AfterViewInit {
  sidenavOpened = signal(true);
  currentUser = signal<any>(null);
  private routerSubscription?: Subscription;
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
    private router: Router,
    private viewportScroller: ViewportScroller,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.currentUser.set(this.authService.currentUser());
    this.expandActiveMenu();
    
    // Subscribe to router events to scroll to top on navigation
    this.routerSubscription = this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
        // CRITICAL: Reset scroll IMMEDIATELY and aggressively on navigation end
        const contentWrapper = document.querySelector('.dashboard-content-wrapper') as HTMLElement;
        
        const forceScrollReset = () => {
          if (contentWrapper) {
            contentWrapper.scrollTop = 0;
            (contentWrapper as any).scrollTop = 0;
            contentWrapper.scrollTo(0, 0);
            contentWrapper.scrollTo({ top: 0, left: 0, behavior: 'auto' });
          }
          window.scrollTo(0, 0);
          document.documentElement.scrollTop = 0;
          document.body.scrollTop = 0;
        };
        
        // Reset immediately
        forceScrollReset();
        
        // Use requestAnimationFrame for immediate reset
        requestAnimationFrame(() => {
          forceScrollReset();
        });
        
        // Use MutationObserver to watch for component insertion
        if (contentWrapper) {
          const observer = new MutationObserver(() => {
            forceScrollReset();
          });
          
          observer.observe(contentWrapper, { 
            childList: true, 
            subtree: false 
          });
          
          setTimeout(() => {
            observer.disconnect();
            forceScrollReset();
          }, 200);
        }
        
        // Aggressive scroll reset at multiple intervals
        [0, 1, 5, 10, 20, 50, 100, 200, 300, 500].forEach(delay => {
          setTimeout(() => forceScrollReset(), delay);
        });
      });
  }

  ngOnDestroy(): void {
    if (this.routerSubscription) {
      this.routerSubscription.unsubscribe();
    }
  }

  ngAfterViewInit(): void {
    // Initial scroll to top
    this.scrollToTop();
  }

  scrollToTop(): void {
    // Get the scrollable wrapper (this is the actual scrollable element now)
    const contentWrapper = document.querySelector('.dashboard-content-wrapper') as HTMLElement;
    
    // CRITICAL: Use multiple methods to force scroll to top
    if (contentWrapper) {
      // Method 1: Direct property assignment
      contentWrapper.scrollTop = 0;
      
      // Method 2: scrollTo method
      contentWrapper.scrollTo({ top: 0, left: 0, behavior: 'instant' });
      
      // Method 3: scrollIntoView on the wrapper itself
      contentWrapper.scrollIntoView({ behavior: 'instant', block: 'start', inline: 'nearest' });
      
      // Method 4: Force via style
      contentWrapper.style.scrollBehavior = 'auto';
    }
    
    // Also scroll window/document to top
    window.scrollTo({ top: 0, left: 0, behavior: 'instant' });
    document.documentElement.scrollTop = 0;
    document.body.scrollTop = 0;
    
    // Use ViewportScroller
    try {
      this.viewportScroller.scrollToPosition([0, 0]);
    } catch (e) {
      // Fallback if ViewportScroller fails
    }
    
    // Force multiple scroll attempts using requestAnimationFrame
    requestAnimationFrame(() => {
      if (contentWrapper) {
        contentWrapper.scrollTop = 0;
        contentWrapper.scrollTo({ top: 0, behavior: 'instant' });
      }
    });
    
    // Multiple timeouts to catch different render phases
    const scrollAttempts = [0, 1, 5, 10, 20, 50, 100, 200, 300];
    scrollAttempts.forEach(delay => {
      setTimeout(() => {
        if (contentWrapper) {
          contentWrapper.scrollTop = 0;
          contentWrapper.scrollTo({ top: 0, behavior: 'instant' });
          // Also try scrollIntoView on first child if it exists
          const firstChild = contentWrapper.firstElementChild as HTMLElement;
          if (firstChild) {
            firstChild.scrollIntoView({ behavior: 'instant', block: 'start', inline: 'nearest' });
          }
        }
      }, delay);
    });
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
      // Prevent navigation when toggling menu
      event.preventDefault();
      event.stopPropagation();
      // Toggle expansion
      const currentExpanded = item.expanded;
      item.expanded = !currentExpanded;
      // Update the signal to trigger change detection
      this.menuItems.set([...this.menuItems()]);
    } else if (item.route) {
      // Navigate to route if no children
      this.router.navigate([item.route]);
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

  navigateToRoute(route: string | undefined, event?: Event): void {
    if (route) {
      // Prevent default link behavior if event is provided
      if (event) {
        event.preventDefault();
        event.stopPropagation();
      }
      
      // Navigate to the route
      this.router.navigate([route]).then(() => {
        // Navigation successful - close sidebar on mobile if needed
        if (window.innerWidth < 768) {
          this.sidenavOpened.set(false);
        }
      }).catch(err => {
        console.error('Navigation error:', err);
      });
    }
  }

  navigateToChildRoute(route: string | undefined, event: Event): void {
    if (route) {
      event.preventDefault();
      event.stopPropagation();
      
      const contentWrapper = document.querySelector('.dashboard-content-wrapper') as HTMLElement;
      
      // Function to force scroll to top
      const forceScrollTop = () => {
        if (contentWrapper) {
          contentWrapper.scrollTop = 0;
          contentWrapper.scrollTo(0, 0);
        }
        window.scrollTo(0, 0);
        document.documentElement.scrollTop = 0;
        document.body.scrollTop = 0;
      };
      
      // Reset scroll BEFORE navigation
      forceScrollTop();
      
      // Use requestAnimationFrame to ensure scroll happens before navigation
      requestAnimationFrame(() => {
        forceScrollTop();
        
        // Navigate
        this.router.navigateByUrl(route).then(() => {
          // Continuous scroll reset using requestAnimationFrame loop
          let frameCount = 0;
          const maxFrames = 60; // ~1 second at 60fps
          
          const scrollLoop = () => {
            forceScrollTop();
            frameCount++;
            if (frameCount < maxFrames) {
              requestAnimationFrame(scrollLoop);
            }
          };
          
          // Start the loop
          requestAnimationFrame(scrollLoop);
          
          // Also use MutationObserver
          if (contentWrapper) {
            const observer = new MutationObserver(() => {
              forceScrollTop();
            });
            observer.observe(contentWrapper, { childList: true, subtree: false });
            setTimeout(() => observer.disconnect(), 1000);
          }
          
          // Timeout fallbacks
          [0, 10, 50, 100, 200, 500, 1000].forEach(delay => {
            setTimeout(() => forceScrollTop(), delay);
          });
        }).catch((err) => {
          console.error('Navigation error:', err);
          this.router.navigate([route]).then(() => {
            forceScrollTop();
            setTimeout(() => forceScrollTop(), 100);
          });
        });
      });
    }
  }
}

