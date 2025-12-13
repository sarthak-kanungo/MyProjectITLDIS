
export interface TableConfig {
    title: string;
    formControlName: string;
    key?: string;
    inputField: string;
    isNeedValueChanges?: boolean;
    displayKey?: string;
    patchKey?: string;
}

export interface PatchValue {
    rowIndex: number;
    patchValue: object;
    tableRowId?: string;
}
export interface assignListToSelect {
    formControlName: string;
    list: Array<object>
}

export interface ControlsConfig {
    [key: string]: any;
}
export interface ColumnRecord {
    recordList: Array<string>;
    formControlName: string;
}

export interface EtTableValueChangeEvent<T> {
    key: any;
    config: TableConfig;
    tableRow: T;
}
export interface EtAutocompleteSelectedEvent<T> {
    option: any;
    rowIndex: number;
    config: TableConfig;
    tableRow: T;
}