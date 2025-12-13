// export abstract class ObjectUtil {
//     static removeNulls(obj) {
//         Object.keys(obj).forEach(key => (obj[key] == null) && delete obj[key]);
//     }
// }


export abstract class ObjectUtil {
    static removeNulls(obj) {
        Object.keys(obj).forEach(key => {
            obj[key] === null && delete obj[key];
            obj[key] === '' && delete obj[key];
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
}