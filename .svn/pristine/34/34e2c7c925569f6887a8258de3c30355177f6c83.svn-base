export class ETValidationError {
    readonly isRemove: boolean;
    constructor(
        readonly formControlName: string,
        readonly tableRowId: string,
        readonly msg?: string,
        readonly type?: { [key: string]: any }
    ) {
        if (!msg) {
            this.isRemove = true;
        }
        Object.seal(this);
    }
}