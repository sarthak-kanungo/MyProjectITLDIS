export enum Source {
    APPROVE = 'approve',
    REJECT = 'reject',
    REQUIRED = 'required',
    //Suraj--23-01-2024
    HOLD = 'hold'
}

export interface RemarkConfiguration {
    source: Source
}

export interface DialogResult {
    button: string
    remarkText: string
}