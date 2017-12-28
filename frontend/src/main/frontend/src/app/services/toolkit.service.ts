import { Injectable } from '@angular/core';

@Injectable()
export class ToolkitService {

  constructor() { }


  public getWsBaseUri(): string{
    let protocol = 'ws:';
    if (window.location.protocol === 'https:') {
      protocol = 'wss:';
    }
    return protocol + '//' + window.location.host;
  }

  public getBaseUri(): string{
    return window.location.protocol + '//' + window.location.host;
  }



}
