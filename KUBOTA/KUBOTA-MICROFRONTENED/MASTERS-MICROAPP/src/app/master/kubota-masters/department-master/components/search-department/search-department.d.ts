declare module 'SearchDepartment' {
    export interface DepartmentCodeAuto {
        departmentCode:string,
        id:number,
    }
    export interface DropDownDepartmentNames {
        departmentName:string
        id:number,
    }
    export interface DropDownDealers {
        linkedToDealer:string
        id:number,
    }
    export interface DropDownCode {
        dealerCode:string
        id:number,
    }
    export interface SearchMaster {

        departmentCode: string,
        departmentName: string,
        // linkedToDealer: string,
        // dealerCode: string,

        page: number,
        size: number,
    }
export interface SubmitDepartment{
    //dealerCode: string,
    departmentCode: string,
    departmentName: string,
    id?: number,
    //linkedToDealer:string,
    // remarks: string
    
  }
}