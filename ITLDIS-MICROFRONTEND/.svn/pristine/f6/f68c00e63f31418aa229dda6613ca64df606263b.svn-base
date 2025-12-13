import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';


export enum IFrameMessageType {
  TOKEN_REQUEST = 'token_request',
  TOKEN_RESPONSE = 'token_response',
  ROUTE = 'route'
}
export enum IFrameMessageSource {
  SPARE = 'spare',
  SERVICE = 'service',
  WARRANTY = 'warranty',
  SALE_PRESALE = 'sale-presale',
  MAIN = 'main'
}
export interface IFrameMessage<T> {
  type: IFrameMessageType;
  source: string;
  data?: T;
}

export interface TokenRequestListener {
  onTokenRequest()
}

export interface TokenResponseListener {
  onTokenResponse(token: any)
}

export interface IFrameRouteListener {
  onRoute(url: UrlSegment)
}

export interface IFrameMessageListener {
  onTokenResponse()
}
export interface UrlSegment {
  url: string,
  queryParam?: { [key: string]: any }
}

@Injectable({
  providedIn: 'root'
})
export class IFrameService {

  private isBrowser: boolean;
  private _tokenRequestListener: TokenRequestListener
  private _tokenResponseListener: TokenResponseListener
  private _iFrameRouteListener: IFrameRouteListener
  appID = IFrameMessageSource.WARRANTY

  constructor(@Inject(PLATFORM_ID) platformId: Object) {
    this.isBrowser = isPlatformBrowser(platformId);
    if (this.isBrowser) {
      this.onMessage(this.onMessageHandeler)
    }
  }

  sendTokenRequest(source: IFrameMessageSource) {
    this.publishFromIFrame({
      type: IFrameMessageType.TOKEN_REQUEST,
      source: source
    })
  }

  sendRouteChangeRequest(source: IFrameMessageSource, urlSegment: UrlSegment) {
    console.log('warranty urlSegment', urlSegment);
    this.publishFromIFrame({
      type: IFrameMessageType.ROUTE,
      source: source,
      data: urlSegment
    })
  }

  publishTokenToIFrame(token: any) {
    this.publishToIFrame({
      source: IFrameMessageSource.MAIN,
      type: IFrameMessageType.TOKEN_RESPONSE,
      data: token
    })
  }

  private publishFromIFrame<T>(data: IFrameMessage<T>) {
    if (this.isBrowser) {
      window.top.postMessage(data, '*');
    }
  }
  private publishToIFrame<T>(data: IFrameMessage<T>, index = 0) {
    if (this.isBrowser) {
      window.frames[index].postMessage(data, '*');
    }
  }
  private onMessage(fn: (data, thisRef) => void) {
    if (this.isBrowser) {
      const thisRef = this;
      window.addEventListener('message', (msg: object) => {
        fn(msg['data'], thisRef);
      });
    }
  }
  private onMessageHandeler(msg: IFrameMessage<any>, _this: IFrameService) {
    console.log('warranty onMessageHandeler msg', msg);
    if (msg && _this.appID !== msg.source) {
      switch (msg.type) {
        case IFrameMessageType.TOKEN_REQUEST:
          _this._tokenRequestListener && _this._tokenRequestListener.onTokenRequest();
          break;
        case IFrameMessageType.TOKEN_RESPONSE:
          _this._tokenResponseListener && _this._tokenResponseListener.onTokenResponse(msg.data);
          break;
        case IFrameMessageType.ROUTE: {
          if (msg.source !== _this.appID) {
            _this._iFrameRouteListener && _this._iFrameRouteListener.onRoute(msg.data);
            return;
          }
        }
        default:
          break;
      }
    }
  }

  giveMeToken(source: IFrameMessageSource) {
    this.publishFromIFrame<any>({
      type: IFrameMessageType.TOKEN_REQUEST,
      source
    })
  }

  set iFrameRouteListener(iFrameRouteListener: IFrameRouteListener) {
    this._iFrameRouteListener = iFrameRouteListener
  }

  set tokenRequestListener(tokenRequestListener: TokenRequestListener) {
    this._tokenRequestListener = tokenRequestListener
  }

  set tokenResponseListener(tokenResponseListener: TokenResponseListener) {
    this._tokenResponseListener = tokenResponseListener
  }
}
