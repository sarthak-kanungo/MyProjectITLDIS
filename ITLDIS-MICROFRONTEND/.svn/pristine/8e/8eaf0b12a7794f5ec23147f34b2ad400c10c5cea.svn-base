export class ScrollToFormError {

    static scrollToError(): void {
        if (document) {
            const firstElementWithError = document.querySelector('.ng-invalid');
            this.scrollTo(firstElementWithError);
        }
    }

    static scrollTo(el: Element): void {
        if (el) {
            el.scrollIntoView({ behavior: 'smooth', block: 'center' });
        }
    }

}


