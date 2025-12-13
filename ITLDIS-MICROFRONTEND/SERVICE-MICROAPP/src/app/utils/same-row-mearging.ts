export abstract class SameRowMearge {

    static spans = [];
    /*
         * Evaluated and store an evaluation of the rowspan for each row.
         * The key determines the column it affects, and the accessor determines the
         * value that should be checked for spanning.
         */
    static rowSpan(key: string, accessor: Function, listData: Array<object>) {
        // console.log("key ", key);
        // console.log("accessor ", accessor);
        // console.log("listData ", listData);
        for (let i = 0; i < listData.length;) {
            let currentValue = accessor(listData[i]);
            // console.log("currentValue ", currentValue);
            let count = 1;
            // Iterate through the remaining rows to see how many match
            // the current value as retrieved through the accessor.
            for (let j = i + 1; j < listData.length; j++) {
                if (currentValue != accessor(listData[j])) {
                    break;
                }
                count++;
            }
            if (!this.spans[i]) {
                this.spans[i] = {};
                // console.log("this.spans[i] ", this.spans[i]);
            }
            // Store the number of similar values that were found (the span)
            // and skip i to the next unique row.
            this.spans[i][key] = count;
            // console.log(" this.spans ", this.spans);
            i += count;
        }
    }
    static returnSpansArray() {
        return this.spans
    }
    // static getRowSpan(col, index) {
    //     return this.spans[index] && this.spans[index][col];
    // }

}