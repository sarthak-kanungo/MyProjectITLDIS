import { AbstractControl, ValidatorFn, ValidationErrors } from '@angular/forms';
import { interval } from 'rxjs';

export class ValidateList {
    private _list: any[];
    private _control: AbstractControl;
    private _msg: string;
    constructor(
        readonly compaireKey = null,
        list?: any[]) {
        this._list = list;
        this._control = null;
        this._msg = 'Please select from list';
        Object.seal(this);
    }
    public get value() {
        return this._list;
    }
    public set value(v: any[]) {
        this._list = v;
        if (this._control) {
            this._control.setErrors({
                selectFromList: true,
                msg: this._msg
            });

        }
    }

    public set control(v: AbstractControl) {
        this._control = v;
    }


}
export class CustomValidators {
    static numberOnly = ValidateInt;
    static max = ValidateMax;
    static maxDecimalPoint = ValidateMaxDecimalPoint;
    static floatNumber = ValidateFloat;
    static mobileNumber = ValidateMobileNumber;
    static maxLength = ValidateMaxLength;
    static minLength = (minLength: number) => {
        return (control: AbstractControl) => {
            try {
                if (control.value === null) {
                    return null;
                }
                let val = control.value as any;
                if (`${val}`.length < minLength) {
                    return { minLength: true };
                }
            } catch (error) {
                return { validNumber: true };
            }
        }
    }
    static selectFromLists(msg = 'Please select from list') {
        return (control: AbstractControl) => {
            if(typeof control.value != 'object')
            return {
                selectFromList: msg,
            };
        }
    }
    
    static selectFromList(list: ValidateList, msg = 'Please select from list') {
        return (control: AbstractControl) => {
            list.control = control;
            if (!control.value) {
                if (control.hasError('selectFromList')) {
                    const error = { ...control.errors };
                    delete error.selectFromList;
                    delete error.msg

                    control.setErrors(error);
                }
                return;
            }

            if (list.value && list.value.length) {
                const includes = list.value.some((val) => {
                    if (val[list.compaireKey] === control.value[list.compaireKey]) {
                        return true;
                    }
                });
                if (includes) {
                    return null;
                }
                return {
                    selectFromList: true,
                    msg
                };
            }
            return null;
        }
    }
    static selectFromAutocompleteList(): ValidatorFn {
        return (control: AbstractControl): ValidationErrors | null => {
            if (control && typeof control.value === 'object') {
                return null;
            }
            return { selectFromList: true };
        }
    }
    static validateNumberExceptStartingZero(control: AbstractControl) {
        if (control.value === null) {
            return null;
        }
        return new RegExp(/^[1-9][0-9]*$/).test(control.value) ? null : { exceptZero: true };
    }
    static min(min: AbstractControl, msg?: string) {
        return (control: AbstractControl) => {
            try {
                if (control.hasError('required')) {
                    return null;
                }
                let numVal = parseFloat(`${control.value}`);
                let minVal = parseFloat(`${min.value}`);
                if (typeof numVal === 'number' && typeof minVal === 'number' && !isNaN(numVal) && !isNaN(minVal) && numVal > minVal) {
                    // control.patchValue(numVal, { onlySelf: true, emitEvent: false });
                    return null
                }
                return { minValue: true, msg: msg ? msg : `Value should be greater than ${minVal}` };

            } catch (error) {
                return { validNumber: true };
            }
        }
    }
    
    static moq(moq: AbstractControl, msg?: string) {
        return (control: AbstractControl) => {
            try {
                if (control.hasError('required')) {
                    return null;
                }
                let numVal = parseInt(`${control.value}`);
                let moqVal = parseInt(`${moq.value}`);
                
                if (typeof numVal === 'number' && typeof moqVal === 'number' && !isNaN(numVal) && !isNaN(moqVal) && numVal >= moqVal  && (numVal % moqVal) == 0) {
                    // control.patchValue(numVal, { onlySelf: true, emitEvent: false });
                    return null
                }
                return { moqValue: true, msg: msg ? msg : `Value should be multiple of ${moqVal}` };

            } catch (error) {
                return { validNumber: true };
            }
        }
    }
}
export function ValidateInt(control: AbstractControl) {
    try {

        if (control.value === null) {
            return control.errors;
        }
        if (typeof control.value === 'number') {
            return null;
        }
        let numVal = parseInt(control.value);
        if (typeof numVal === 'number' && !isNaN(numVal)) {
            control.patchValue(numVal);
            return control.errors;
        }

        control.patchValue(null, { onlySelf: true, emitEvent: false });
        // return { validNumber: true };
        return control.errors;
    } catch (error) {
        return { validNumber: true };
    }
}
export function ValidateMax(max: AbstractControl, msg?: string) {
    return (control: AbstractControl) => {
        try {
            if (typeof control.value === 'number') {
                return null;
            }

            let numVal = parseFloat(`${control.value}`);
            let maxVal = parseFloat(`${max.value}`);

            if (typeof numVal === 'number' && typeof maxVal === 'number' && !isNaN(numVal) && !isNaN(maxVal) && numVal <= maxVal) {
                control.patchValue(numVal, { onlySelf: true, emitEvent: false });
                return null
            }
            // control.patchValue(null, { onlySelf: true, emitEvent: false });
            return { maxValue: true, msg: msg ? msg : 'value should not exceeds' };

        } catch (error) {
            return { validNumber: true };
        }
    }
}
export function ValidateMaxDecimalPoint(decimalPoint: number) {
    return (control: AbstractControl) => {
        console.log('control: ', control);
        if (control.value === null) {
            return control.errors;
        }
        try {
            let floatRegex = new RegExp(`^\\d+(\\.(\\d{1,${decimalPoint}})?)?$`);
            const isRegexMatchForControl = floatRegex.test(control.value);
            if (typeof control.value === 'number' || control.value === null || isRegexMatchForControl) {
                return null;
            }
            let numVal = parseFloat(control.value);
            const isRegexMatchForNumVal = floatRegex.test(`${numVal}`);
            if (typeof numVal === 'number' && !isNaN(numVal)) {
                if (isRegexMatchForNumVal) {
                    control.patchValue(numVal);
                    return null;
                }
                let val = `${numVal}`.slice(0, `${numVal}`.length - 1);
                control.patchValue(val);
                return null
            }
            control.patchValue(null, { onlySelf: true, emitEvent: false });
            // return { validNumber: true };
            return control.errors;
        } catch (error) {
            return { validNumber: true };
        }
    }
}
export function ValidateFloat(control: AbstractControl) {
    if (control.value === null) {
        return control.errors;
    }
    try {
        let floatRegex = new RegExp(`^\\d+\\.+(\\d{ 1, 2 })?$`);
        if (typeof control.value === 'number' || control.value === null || `${control.value}`.match(floatRegex)) {
            return null;
        }
        let numVal = parseFloat(control.value);
        if (typeof numVal === 'number' && !isNaN(numVal)) {
            control.patchValue(numVal);
            return null
        }
        control.patchValue(null, { onlySelf: true, emitEvent: false });
        return control.errors;

    } catch (error) {
        return { validNumber: true };
    }
}
export function ValidateMobileNumber(control: AbstractControl) {
    if (control.value === null) {
        return control.errors;
    }
    try {
        if (typeof control.value === 'number' || control.value === null) {
            return null;
        }
        let numVal = parseInt(control.value);
        if (typeof numVal === 'number' && !isNaN(numVal) && `${numVal}`.length < 11) {
            control.patchValue(numVal);
            return null
        }
        if (typeof numVal === 'number' && !isNaN(numVal) && `${numVal}`.length >= 11) {
            let sliceValue = `${numVal}`.slice(0, 10);
            numVal = parseInt(sliceValue);
            control.patchValue(numVal);
            return null;
        }
        control.patchValue(null, { onlySelf: true, emitEvent: false });
        return control.errors;

    } catch (error) {
        return { validNumber: true };
    }
}
export function ValidateMaxLength(maxLength: number) {
    return (control: AbstractControl) => {
        try {
            if (control.value === null) {
                return control.errors;
            }
            let val = (control.value) as any;
            if (`${val}`.length > maxLength) {
                let sliceValue = `${val}`.slice(0, maxLength);
                control.patchValue(sliceValue);
                return null;
            }
            return control.errors;
            // return { validNumber: true };
        } catch (error) {
            return { validNumber: true };
        }
    }
}
