import { AbstractControl } from '@angular/forms';
import { MatHorizontalStepper } from '@angular/material';

export class CustomValidators {
    static numberOnly = ValidateInt;
    static max = ValidateMax;
    static maxDecimalPoint = ValidateMaxDecimalPoint;
    static floatNumber = ValidateFloat;
    static mobileNumber = ValidateMobileNumber;
    static maxLength = ValidateMaxLength;
    static serialNumber = ValidateSerialNo;
    static zeroOrEqual = ValidateZeroOrEqual;
    static minLength = (minLength: number) => {
        return (control: AbstractControl) => {
            try {
                if (control.value === null) {
                    return null;
                }
                let val = control.value as any;
                if (`${ val }`.length < minLength) {
                    return { minLength: true };
                }
            } catch (error) {
                return { validNumber: true };
            }
        }
    }
    static selectFromList(msg = 'Please select from list') {
        return (control: AbstractControl) => {
            if(typeof control.value != 'object')
            return {
                selectFromList: msg,
            };
        }
    }
    static autoCompleteValidation(msg = 'Please select from list') {
        return (control: AbstractControl) => {
            console.log('control', control);
            return {
                selectFromList: msg,
            };
        }
    }
    
    static ValidatePositiveNumber(maxNum: number) {
        return (control: AbstractControl) => {
           if(control.value != null) {
               console.log('Math.sign(maxNum)', Math.sign(maxNum));
               if(Math.sign(maxNum) == +1) {
                return null; 
               }
               else {
                return {negativeNumber: true}
               }
               
           }
        }
    }
}
export function ValidateSerialNo(control: AbstractControl) {
    if (control.value != null) {
        if (control.value.match(/^[a-z0-9]+$/i)) {
            return null;
        } 
        return {validSerialNo: true};
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
        // return { validNumber: true };

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
            let numVal = parseFloat(`${ control.value }`);
            let maxVal = parseFloat(`${ max.value }`);

            if (typeof numVal === 'number' && typeof maxVal === 'number' && !isNaN(numVal) && !isNaN(maxVal) && numVal <= maxVal) {
                control.patchValue(numVal, { onlySelf: true, emitEvent: false });
                return null
            }
            // control.patchValue(null, { onlySelf: true, emitEvent: false });
            return { maxValue: true, msg: msg ? msg : 'value should not exceeds'};

        } catch (error) {
            return { validNumber: true };
        }
    }
}
export function ValidateZeroOrEqual(max: AbstractControl, msg?: string) {
    return (control: AbstractControl) => {
        try {
            // if (typeof control.value === 'number') {
            //     return null;
            // }
            let numVal = parseFloat(`${ control.value }`);
            let maxVal = parseFloat(`${ max.value }`);

            if (typeof numVal === 'number' && typeof maxVal === 'number' && !isNaN(numVal) && !isNaN(maxVal) && (numVal == maxVal || numVal == 0)) {
                control.patchValue(numVal, { onlySelf: true, emitEvent: false });
                return null
            }
            // control.patchValue(null, { onlySelf: true, emitEvent: false });
            return { maxValue: true, msg: msg ? msg : 'value should not exceeds'};

        } catch (error) {
            return { validNumber: true };
        }
    }
}
export function ValidateMaxDecimalPoint(decimalPoint: number) {
    return (control: AbstractControl) => {
        try {
            let floatRegex = new RegExp(`^\\d+(\\.(\\d{1,${ decimalPoint }})?)?$`);
            const isRegexMatchForControl = floatRegex.test(control.value);
            if (typeof control.value === 'number' || control.value === null || isRegexMatchForControl) {
                return null;
            }
            let numVal = parseFloat(control.value);
            const isRegexMatchForNumVal = floatRegex.test(`${ numVal }`);
            if (typeof numVal === 'number' && !isNaN(numVal)) {
                if (isRegexMatchForNumVal) {
                    control.patchValue(numVal);
                    return null;
                }
                let val = `${ numVal }`.slice(0, `${ numVal }`.length - 1);
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
        if (typeof control.value === 'number' || control.value === null || `${ control.value }`.match(floatRegex)) {
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
        if (typeof numVal === 'number' && !isNaN(numVal) && `${ numVal }`.length < 11) {
            control.patchValue(numVal);
            return null
        }
        if (typeof numVal === 'number' && !isNaN(numVal) && `${ numVal }`.length >= 11) {
            let sliceValue = `${ numVal }`.slice(0, 10);
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
            if (`${ val }`.length > maxLength) {
                let sliceValue = `${ val }`.slice(0, maxLength);
                control.patchValue(sliceValue);
                return null;
            }
            // return { validNumber: true };
        } catch (error) {
            return { validNumber: true };
        }
    }
    
}

