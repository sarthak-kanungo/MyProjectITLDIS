export abstract class ObjectUtil {

    static removeNulls(obj) {
        Object.keys(obj).forEach(key => {
        obj[key] === null && delete obj[key];
        obj[key] === '' && delete obj[key];
        obj[key] === undefined && delete obj[key];
        });
        return obj;
    }

    static convertDate(date: string) {
        console.log('date', date);
        const todayTime = new Date(date);
        if(typeof todayTime == 'object' && todayTime != null && todayTime != undefined) {
          return `${todayTime.getFullYear()}-${todayTime.getMonth()+1 < 10 ? '0'+(todayTime.getMonth()+1) : todayTime.getMonth()+1}-${todayTime.getDate()}`;
        }
        return ;
    }
    static sum(items, prop: string) {
      return items.reduce((prevVal, currVal) => {
         console.log('currVal', currVal);
         return prevVal + currVal.value[prop]
      }, 0);
    }

    static getDateIntoDDMMYYYY(data): string {
      const todayTime = new Date(data);
      const month = (todayTime.getMonth() + 1) < 10 ? '0' + (todayTime.getMonth() + 1) : todayTime.getMonth() + 1;
      const day = todayTime.getDate() < 10 ? '0' + todayTime.getDate() : todayTime.getDate();
      const year = todayTime.getFullYear();
      return `${day}-${month}-${year}`;
    }
}