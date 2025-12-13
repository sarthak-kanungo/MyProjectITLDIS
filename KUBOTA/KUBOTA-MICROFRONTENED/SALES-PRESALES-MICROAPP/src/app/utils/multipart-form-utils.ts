export abstract class MultiPartFormUtil {

    static obj2fd(obj, form?, namespace?) {
        console.log("obj ", obj);
        let fd = form || new FormData();
        let formKey;

        for (let property in obj) {
            //if (obj.hasOwnProperty(property) && obj[property]) {
            if (obj.hasOwnProperty(property)) {
                if (namespace) {
                    formKey = namespace + '[' + property + ']';
                } else {
                    formKey = property;
                }

                if (obj[property] instanceof Date) {
                    fd.append(formKey, obj[property].toISOString());
                } else if (typeof obj[property] === 'object'
                    && !(obj[property] instanceof File)
                    && !(obj[property] instanceof Blob)) {
                    this.obj2fd(obj[property], fd, formKey);
                } else {
                    // if it's a string or a File object
                    fd.append(formKey, obj[property]);
                }
            }
        }

        console.log("fd ", fd);
        return fd;
    }
}