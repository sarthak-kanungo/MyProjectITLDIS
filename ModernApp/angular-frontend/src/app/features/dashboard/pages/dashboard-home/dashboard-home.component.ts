import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../../core/services/auth.service';

interface Activity {
  id: string;
  title: string;
  time: string;
  icon: string;
  type: 'success' | 'info' | 'warning';
}

@Component({
  selector: 'app-dashboard-home',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatDividerModule,
    RouterModule
  ],
  templateUrl: './dashboard-home.component.html',
  styleUrls: ['./dashboard-home.component.scss']
})
export class DashboardHomeComponent implements OnInit {
  currentUser = signal<any>(null);
  currentTime = signal<string>('');
  
  stats = signal([
    {
      title: 'Total Services',
      value: '1,234',
      icon: 'build',
      color: '#667eea',
      change: '+12%',
      route: '/dashboard/services'
    },
    {
      title: 'Total Spares',
      value: '5,678',
      icon: 'inventory',
      color: '#764ba2',
      change: '+8%',
      route: '/dashboard/spares'
    },
    {
      title: 'Pending Tasks',
      value: '42',
      icon: 'pending_actions',
      color: '#f59e0b',
      change: '-5%',
      route: '/dashboard/services/pending-pdi'
    },
    {
      title: 'Completed',
      value: '892',
      icon: 'check_circle',
      color: '#10b981',
      change: '+15%',
      route: '/dashboard/services/view-job-cards'
    }
  ]);

  quickActions = signal([
    {
      label: 'Create Job Card',
      icon: 'add_task',
      route: '/dashboard/services/create-job-card',
      color: '#667eea'
    },
    {
      label: 'View Inventory',
      icon: 'inventory',
      route: '/dashboard/spares/view-inventory',
      color: '#764ba2'
    },
    {
      label: 'Generate Invoice',
      icon: 'receipt_long',
      route: '/dashboard/services/generate-invoice',
      color: '#10b981'
    },
    {
      label: 'Counter Sale',
      icon: 'point_of_sale',
      route: '/dashboard/spares/counter-sale',
      color: '#f59e0b'
    }
  ]);

  recentActivities = signal<Activity[]>([
    {
      id: '1',
      title: 'Job Card #1234 completed',
      time: '2 hours ago',
      icon: 'check_circle',
      type: 'success'
    },
    {
      id: '2',
      title: 'New inventory added',
      time: '5 hours ago',
      icon: 'inventory',
      type: 'info'
    },
    {
      id: '3',
      title: 'Invoice #5678 generated',
      time: '1 day ago',
      icon: 'receipt',
      type: 'warning'
    }
  ]);

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.currentUser.set(this.authService.currentUser());
    this.updateTime();
    // Update time every minute
    setInterval(() => this.updateTime(), 60000);
  }

  updateTime(): void {
    const now = new Date();
    const options: Intl.DateTimeFormatOptions = { 
      weekday: 'long', 
      year: 'numeric', 
      month: 'long', 
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    };
    this.currentTime.set(now.toLocaleDateString('en-US', options));
  }

  getCurrentTime(): string {
    return this.currentTime();
  }

  getIconBackground(color: string): string {
    // Convert hex to rgba with 10% opacity
    const hex = color.replace('#', '');
    const r = parseInt(hex.substring(0, 2), 16);
    const g = parseInt(hex.substring(2, 4), 16);
    const b = parseInt(hex.substring(4, 6), 16);
    return `rgba(${r}, ${g}, ${b}, 0.1)`;
  }
}
