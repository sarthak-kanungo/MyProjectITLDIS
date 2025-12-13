
    export interface DropDoenDepartments {
        departmentName: string,
        id: number
    }
    // export interface DropDoenKaibranch {
    //     branchName: string,
    //     id: number
    // }
    export interface AutoDesiganationLevel {
        designationLevel: string
    }
    export interface DropDesiganationLevel {
        designation: string
        id: number
    }
    export interface DropDesiganation {
        designation: string
    }
    export interface EmployeeNameByEmployeeCode {
        employeeName: string
    }
    export interface SaveEmployee {

        contactNo: string,
        departmentMasters: DepartmentMasters[],
        designationHierarchy: DesignationHierarchy
        emailId: string,
        employeeCode: string,
        employeeName: string,
       // branchDepotMasters: KaiBranch[],
        managementAccess: boolean,
        activeStatus: string,
        reportingUser: ReportingUser,
        hoDesignationId: number,
        hoDepartmentId: number,
        reportingUserId: number,
        hoDesignationLevelId: number,
    }
    export interface DesignationHierarchy {
        id: number
    }
    export interface DepartmentMasters {
        id: number
    }
    export interface KaiBranch {
        id: number
    }
    export interface ReportingUser {
        id: number
    }
    export interface EmployeeName {

    }
    export interface ReportingEmployeeDetail {
        id: number;
        employeeCode: string;
    }

    export interface EmployeeDetail {

        contactNo: string,
        departmentName: string,
        designation: string,
        emailId: string,
        employeeCode: string,
        employeeName: string,
        designationLevel: string,
        managementAccess: boolean,
        activeStatus: string,
        reportingEmployeeCode: string,
        reportingEmployeeName: string,
        directRepotee : string,
        departmentId : number,
        designationId : number, 
        designationLevelId : number,
        reportingId : number,

    }

    export interface ReportingEmployee {
        reportingEmployeeCode: string,
        reportingEmployeeName: string,
    }

    export interface DirectRepotees {
        id: number;
        directRepotee: string;
    }
    
