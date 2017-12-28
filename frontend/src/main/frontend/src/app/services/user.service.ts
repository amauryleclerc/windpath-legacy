import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ToolkitService } from './toolkit.service';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UserService {

  constructor(private httpClient: HttpClient, private toolkitService: ToolkitService) { }


  getCurrentUser(): Observable<any> {
    return this.httpClient.get<any>(this.toolkitService.getBaseUri() + "/user");
  }

}
