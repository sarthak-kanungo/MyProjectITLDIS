
import { UploadableFile } from 'src/app/ui/file-upload/file-upload';
// import { ViewPCRDomain, WarrantyPcrPhotos } from '../../product-concern-report/domain/product-concern-report.domain';

export interface AttendancReporteHeader {
    programNumber:string
    region: string,
    state: string,
    tsmName: string,
    delearName: string,
    empStatus: string,
    trainingTypeId: string,
    trainingModuleId: string,
    delearEmpDesignation: string,
    startDate: string,
    endDate: string
}

export interface AttendancReporteHeader1 {
    fromDate: string;
    toDate: string;
    page: number;
    size: number;
}


export interface FirstTimeBuyer {
    value: string;
    viewValue: string;
}

export interface FieldLevel {
    LEVEL_ID: number
    LEVEL_CODE?: string,
    LEVEL_DESC: string,
    SEQ_NO: string
}

export interface LevelHierarchy {
    org_hierarchy_id: number
    level_id?: number,
    hierarchy_code: string,
    hierarchy_desc: string,
    parent_org_hierarchy_id?: number
}