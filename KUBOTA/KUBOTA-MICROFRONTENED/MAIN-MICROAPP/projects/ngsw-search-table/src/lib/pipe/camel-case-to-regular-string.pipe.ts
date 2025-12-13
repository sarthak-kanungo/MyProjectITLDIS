import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'camelCaseToRegularString'
})
export class CamelCaseToRegularStringPipe implements PipeTransform {
  transform(value: string, tableName?: string, tableType?: string): any {
    let regularString = '';
    value.split('').map(char => {
      if (/[A-Z]/.test(char)) {
        regularString += ' ';
      }
      regularString += char;
    });
    regularString = regularString.charAt(0).toUpperCase() + regularString.slice(1);

    return regularString;
  }
  // changeKey
  private transformEnterBy(key, tableName: string, tableType?: string): string {
    const serachIndex: number = tableName.toLowerCase().search('voucher');
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
