import { AbstractControl } from '@angular/forms';

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
    static selectFromList(msg = 'Please select from list') {
        return (control: AbstractControl) => {
            return {
                selectFromList: msg,
            };
        }
    }
    static validateNumberExceptStartingZero(control: AbstractControl) {
        if (control.value === null) {
            return null;
        }
        return new RegExp(/^[1-9][0-9]*$/).test(control.value) ? null : { exceptZero: true };
    }
}
export function ValidateInt(control: AbstractControl) {
    try {
        if (typeof control.value === 'number' || control.value === null) {
            return null;
        }
        let numVal = parseInt(control.value);
        if (typeof numVal === 'number' && !isNaN(numVal)) {
            control.patchValue(numVal);
            return null
        }
        control.patchValue(null, { onlySelf: true, emitEvent: false });
        // control.validator returns the current 'required' validator for that control
        return control.validator(control);
    } catch (error) {
        return { validNumber: true };
    }
}
export function ValidateMax(max: AbstractControl, msg?: string) {
    return (control: AbstractControl) => {
        try {
            // if (typeof control.value === 'number') {
            //     return null;
            // }
            let numVal = parseFloat(`${control.value}`);
            let maxVal = parseFloat(`${max.value}`);

            if (typeof numVal === 'number' && typeof maxVal === 'number' && !isNaN(numVal) && !isNaN(maxVal) && numVal <= maxVal) {
                control.patchValue(numVal, { onlySelf: true, emitEvent: false });
                return null
            }else if(isNaN(numVal) || isNaN(maxVal)){  return null  }
            // control.patchValue(null, { onlySelf: true, emitEvent: false });
            return { maxValue: true, msg: msg ? msg : 'value should not exceeds' };

        } catch (error) {
            return { validNumber: true };
        }
    }
}
export function ValidateMaxDecimalPoint(decimalPoint: number) {
    return (control: AbstractControl) => {
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

        } catch (error) {
            return { validNumber: true };
        }
    }
}
export function ValidateFloat(control: AbstractControl) {
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
        // return { validNumber: true };

    } catch (error) {
        return { validNumber: true };
    }
}
export function ValidateMobileNumber(control: AbstractControl) {
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
        // return { validNumber: true };

    } catch (error) {
        return { validNumber: true };
    }
}
export function ValidateMaxLength(maxLength: number) {
    return (control: AbstractControl) => {
        try {
            if (control.value === null) {
                return null;
            }
            let val = (control.value) as any;
            if (`${val}`.length > maxLength) {
                let sliceValue = `${val}`.slice(0, maxLength);
                control.patchValue(sliceValue);
                return null;
            }
            // return { validNumber: true };
        } catch (error) {
            return { validNumber: true };
        }
    }
}
