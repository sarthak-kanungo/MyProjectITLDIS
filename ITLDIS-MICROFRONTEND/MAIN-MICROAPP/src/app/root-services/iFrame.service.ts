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
  MAIN = 'main',
  CRM_TRAINING = 'crm-training',
  TRAINING ='training',
  DASHBOARD='dashboard'

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
  onTokenResponse(token: string)
}

export interface IFrameRouteListener {
  onRoute(urlSegment: UrlSegment);
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

  private _tokenRequestListener: TokenRequestListener;
  private _tokenResponseListener: TokenResponseListener;
  private _iFrameRouteListener: IFrameRouteListener;
  readonly appID = IFrameMessageSource.MAIN;

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
    this.publishToIFrame({
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
      if (window.top) {
        window.top.postMessage(data, '*');
      }
    }
  }
  private publishToIFrame<T>(data: IFrameMessage<T>, index = 0) {
    if (this.isBrowser) {
      if (window.frames && window.frames[index]) {
        window.frames[index].postMessage(data, '*');
      }
    }
  }
  private onMessage(fn: (data, context) => void) {
    const context = this
    if (this.isBrowser) {
      window.addEventListener('message', (msg: object) => {
        
        fn(msg['data'], this);
      });
    }
  }
  private onMessageHandeler(msg: IFrameMessage<any>, thisRef: IFrameService) {
    
    if (msg) {
      switch (msg.type) {
        case IFrameMessageType.TOKEN_REQUEST:
          thisRef._tokenRequestListener && thisRef._tokenRequestListener.onTokenRequest();
          return;
        case IFrameMessageType.TOKEN_RESPONSE:
          thisRef._tokenResponseListener && thisRef._tokenResponseListener.onTokenResponse(msg.data);
          return;
        case IFrameMessageType.ROUTE: {
          if (msg.source !== thisRef.appID) {
            thisRef._iFrameRouteListener && thisRef._iFrameRouteListener.onRoute(msg.data);
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
