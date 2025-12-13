export interface DealerDetailsForNewUser {
    id: number,
    code?: string,
    displayString:string
}

export interface DealerEmployeeCodeForNewUser {
    id: number
    employeeName?: string,
    usercode:string,
    value:string
}

export interface DealerFunction {
    roleid: number
    role_code?: string,
    role_name:string
}


export interface SubmitNewDealerDto{
    id:number
    userName:number
    dealerEmployeeId:number
    employeeCode:number
    employeeName:string
    userId:number
    password:string
    confirmPassword:string
    assignRole:string
    function:string
    message:string
    status:string
    isactive:string
}
export interface DealerRoleManu {
    roleId: number
    isactive: string,
}

export interface EmployeeName {
    employeeName: string;
    id: number;
}