import { Injectable } from '@angular/core';

@Injectable()
export class itldisPatterns {

    allowCharactersOnly(event: KeyboardEvent) {
        const pattern = /[a-zA-Z]/;
        let inputChar = String.fromCharCode(event.charCode);
        if (!pattern.test(inputChar)) {
            event.preventDefault();
        }
    }

    allowCharactersWithSpace(event: KeyboardEvent) {
        const pattern = /[a-zA-Z ]/;
        let inputChar = String.fromCharCode(event.charCode);
        if (!pattern.test(inputChar)) {
            event.preventDefault();
        }
    }

    allowNumbersOnly(event: KeyboardEvent) {
        const pattern = /[0-9]/;
        let inputChar = String.fromCharCode(event.charCode);
        if (!pattern.test(inputChar)) {
            event.preventDefault();
        }
    }

    allowAlphaNumericsOnly(event: KeyboardEvent) {
        const pattern = /[a-zA-Z0-9]/;
        let inputChar = String.fromCharCode(event.charCode);
        if (!pattern.test(inputChar)) {
            event.preventDefault();
        }
    }

    blockCopyPaste(e: KeyboardEvent) {
        e.preventDefault()
    }
}