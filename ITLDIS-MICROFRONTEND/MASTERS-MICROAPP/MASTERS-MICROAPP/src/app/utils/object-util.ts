export abstract class ObjectUtil {
   
    static removeNulls(obj) {
        Object.keys(obj).forEach(key => {
            obj[key] === null && delete obj[key];
            obj[key] === '' && delete obj[key];
            obj[key] === undefined && delete obj[key];
        });
        return obj;
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
      static getDateIntoDDMMYYYY(data): string {
        const todayTime = new Date(data);
        const month = (todayTime.getMonth() + 1) < 10 ? '0' + (todayTime.getMonth() + 1) : todayTime.getMonth() + 1;
        const day = todayTime.getDate() < 10 ? '0' + todayTime.getDate() : todayTime.getDate();
        const year = todayTime.getFullYear();
        return `${day}-${month}-${year}`;
      }
      
      
}