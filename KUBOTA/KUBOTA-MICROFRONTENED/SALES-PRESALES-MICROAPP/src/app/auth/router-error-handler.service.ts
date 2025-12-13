import { Injectable, ErrorHandler } from '@angular/core';
import { Platform } from '@angular/cdk/platform';

/* This error handler is created for the error 'Loading chunk 25(or any number) failed'.
To use this handler, we have to add this service in the "Providers:[RouterErrorHandlerService]" 
of the module where the error occures */
@Injectable()
export class RouterErrorHandlerService implements ErrorHandler {
    constructor(
        private platform: Platform
    ) { }
    
    handleError(error: any): void {
        const chunkFailedMessage = /Loading chunk [\d]+ failed/;
        if (this.platform.isBrowser) {
            if (chunkFailedMessage.test(error.message)) {
                window.location.reload();
            }
        }
    }
}