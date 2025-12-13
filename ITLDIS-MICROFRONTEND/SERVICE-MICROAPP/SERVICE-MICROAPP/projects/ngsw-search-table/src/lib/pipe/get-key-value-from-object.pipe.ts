import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'getKeyValueFromObject'
})
export class GetKeyValueFromObjectPipe implements PipeTransform {
  transform(value: any, keyName: Array<string>): any {
    if (typeof value === 'object' && value !== null) {
      const val = this.hasProperty(value, keyName);
      return val;
    }
    return value;
  }
  private hasProperty(valueObj: Object, key: Array<string>) {
    let valueOfKey = null;
    key.forEach(keyName => {
      if (valueObj.hasOwnProperty(keyName)) {
        valueOfKey = valueObj[keyName];
        return;
      }
    });
    return valueOfKey;
  }
}
