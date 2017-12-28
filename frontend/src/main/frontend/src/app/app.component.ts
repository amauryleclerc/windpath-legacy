import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { WebsocketService } from './websocket.service';
import 'rxjs/add/observable/bindCallback';
import 'rxjs/add/observable/from';
import 'rxjs/add/observable/fromEvent';
import 'rxjs/add/observable/empty';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers:[WebsocketService]
})
export class AppComponent {

  name: String;
  img: String;
  tracks : Array<String> = new Array<String>();
  
  constructor(private httpClient: HttpClient, private websocketService: WebsocketService) {
    let configs = new Map<string, string>();
    configs.values()
    this.httpClient.get<any>(this.getBaseUri()+"/user").subscribe(v => {
      this.name = v.name;
      this.img = v.picture;
    }, e => console.error(e));

    websocketService.connect(this.getWsBaseUri()+"/track/projection")//
    .map(msg => JSON.parse(msg.data))//
    .do(event => console.log(event))//
    .subscribe(v => this.processEvent(v), e => console.error(e));
  }


  private processEvent(event: any){
    if(event.type === "CreatedTrackEvent"){
      this.tracks.push(event.name);
    }else if(event.type === "CurrentTrackEvent"){
      this.tracks.push(event.track.name);
    }
  }



  test() {
    this.httpClient.get(this.getBaseUri()+"/test").subscribe(v => console.log(v), e => console.error(e));


  }
  user() {
    this.httpClient.get(this.getBaseUri()+"/user").subscribe(v => console.log(v), e => console.error(e));
  }



  gpx(event) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/xml; charset=utf-8' });
    const file: File = event.target.files[0];
    const myReader: FileReader = new FileReader();
    myReader.readAsText(file);
    Observable.fromEvent<any>(myReader, 'loadend')//
      .map(evt => myReader.result)//
      .mergeMap(content => this.httpClient.post(this.getBaseUri()+"/service/gpx", content, { headers: headers }))//
      .subscribe(v => console.log(v), e => console.error(e));


  }

  private getWsBaseUri(): string{
    let protocol = 'ws:';
    if (window.location.protocol === 'https:') {
      protocol = 'wss:';
    }
    return protocol + '//' + window.location.host;
  }

  private getBaseUri(): string{
    return window.location.protocol + '//' + window.location.host;
  }


  title = 'app';
}
