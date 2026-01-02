import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

export interface PendingInstallation {
    vinNo: string;
    modelFamily: string;
    modelCode: string;
    customerName: string;
    mobilePh: string;
}

@Injectable({
    providedIn: 'root'
})
export class InstallationService {

    constructor(private http: HttpClient) { }

    getPendingInstallations(): Observable<PendingInstallation[]> {
        // Mock data to match legacy requirements
        const mockData: PendingInstallation[] = [
            { vinNo: 'CHASSIS123', modelFamily: 'TRACTOR', modelCode: 'RX750', customerName: 'JOHN DOE', mobilePh: '9876543210' },
            { vinNo: 'CHASSIS456', modelFamily: 'HARVESTER', modelCode: 'H-900', customerName: 'JANE SMITH', mobilePh: '9123456780' }
        ];
        return of(mockData);
    }
}
