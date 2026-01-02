import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { InstallationService, PendingInstallation } from '../../services/installation.service';

@Component({
    selector: 'app-pending-installation',
    standalone: true,
    imports: [CommonModule, FormsModule, RouterModule],
    templateUrl: './pending-installation.component.html',
    styleUrls: ['./pending-installation.component.scss']
})
export class PendingInstallationComponent implements OnInit {
    searchTerm: string = '';
    pendingInstallations: PendingInstallation[] = [];
    filteredInstallations: PendingInstallation[] = [];
    errorMessage: string = '';

    constructor(private installationService: InstallationService) { }

    ngOnInit(): void {
        this.installationService.getPendingInstallations().subscribe({
            next: (data) => {
                this.pendingInstallations = data;
                this.filterData();
            },
            error: (err) => {
                this.errorMessage = 'Failed to load data';
                console.error(err);
            }
        });
    }

    filterData(): void {
        if (!this.searchTerm) {
            this.filteredInstallations = this.pendingInstallations;
        } else {
            const term = this.searchTerm.toLowerCase();
            this.filteredInstallations = this.pendingInstallations.filter(item =>
                item.vinNo.toLowerCase().includes(term) ||
                item.customerName.toLowerCase().includes(term)
            );
        }
    }

    exportToCSV(): void {
        const header = ['S.No', 'Chassis No', 'Model Family', 'Model Code', 'Customer Name', 'Mobile Phone'];
        const csvData = this.filteredInstallations.map((item, index) => [
            index + 1,
            item.vinNo,
            item.modelFamily,
            item.modelCode,
            item.customerName,
            item.mobilePh
        ]);

        let csvContent = "data:text/csv;charset=utf-8,"
            + header.join(",") + "\n"
            + csvData.map(e => e.join(",")).join("\n");

        const encodedUri = encodeURI(csvContent);
        const link = document.createElement("a");
        link.setAttribute("href", encodedUri);
        link.setAttribute("download", "pending_installations.csv");
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    }
}
