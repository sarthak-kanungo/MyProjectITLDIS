export enum Source {
    APPROVE = 'approve',
    REJECT = 'rejecet',
    REQUIRED = 'required'
}

export interface RemarkConfiguration {
    source: Source
}

export interface DialogResult {
    button: string
    remarkText: string
}