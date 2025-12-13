export interface AutoDealer { 
    code:string
    displayString:string
    id:number
    name:string
}

export interface UserId {
    id: number
    employeeName?: string,
    usercode:string,
    value:string
}

// export interface UserBranch{
//     id: number
//     branch_code?: string,
//     branch_name:string,
//     isactive:string
//     IsmainBranch:string
//     Active_status:string
// }
export interface UserBranch{
    id: number
    branch_code?: string,
    branch_name:string,
    IsmainBranch:any
    Active_status:any
}

export interface UserToBranch{
    dealerEmployeId: number
    branchId?: string,
    isActive:string,
    createdBy:string
    message:string
    status:string

}
export interface SaveAbtuObject {
    id:any
    userToBranch: UserToBranch[];
}