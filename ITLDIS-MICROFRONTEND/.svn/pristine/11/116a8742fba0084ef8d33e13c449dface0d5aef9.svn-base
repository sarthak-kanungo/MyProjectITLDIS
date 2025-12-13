export abstract class ObjectUtil {
    static removeNulls(obj) {
        Object.keys(obj).forEach(key => {
            obj[key] === null && delete obj[key] || obj[key] === '' && delete obj[key];

        })
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
