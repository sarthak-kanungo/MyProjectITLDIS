export abstract class ObjectUtil {
    static removeNulls(obj) {
        Object.keys(obj).forEach(key => {
            obj[key] === null && delete obj[key];
            obj[key] === '' && delete obj[key];
        });
    }
    static deleteProperty(obj: object, keys: Array<string>) {
        if (keys.length <= 0) {
          return obj;
        }
        if (keys.length === 1) {
          delete obj[keys[0]];
          return obj
        }
        keys.forEach(key => {
          delete obj[key];
        });
        return obj;
      }
      static convertDate(date: string) {
        console.log('date', date);
        const todayTime = new Date(date);
        if(typeof todayTime == 'object' && todayTime != null && todayTime != undefined) {
          return `${todayTime.getFullYear()}-${todayTime.getMonth()+1 < 10 ? '0'+(todayTime.getMonth()+1) : todayTime.getMonth()+1}-${todayTime.getDate() < 10 ? '0' + todayTime.getDate() : todayTime.getDate()}`;
        }
        return ;
    }
}