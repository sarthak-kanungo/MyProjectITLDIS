import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'camelCaseToRegularString'
})
export class CamelCaseToRegularStringPipe implements PipeTransform {
  transform(value: string, tableName?: string, tableType?: string): any {
     // // console.log('value...........................', value, tableName);
    let regularString = '';
    // if (value === 'enteredBy') {
    //   value = this.transformEnterBy(value,tableName);
    //    // // console.log('value', value)
    // }
    value.split('').map(char => {
      if (/[A-Z]/.test(char)) {
         // // console.log('char', char);
        regularString += ' ';
      }
      regularString += char;
    });
    regularString = regularString.charAt(0).toUpperCase() + regularString.slice(1);

     // // console.log('regularString >>>>>>> ', regularString);
    return regularString;
  }
  // changeKey
  private transformEnterBy(key, tableName: string, tableType?: string): string {
     // // console.log('tableName', tableName)
    const serachIndex: number = tableName.toLowerCase().search('voucher');
     // // console.log('serachIndex========>>', serachIndex);
    function isTransformKey() {
      if (serachIndex >= 0) {
        return true;
      }
      return false;
    }
    if (isTransformKey()) {
      return 'Staff';
    }
    return 'AddedBy';
  }
}
