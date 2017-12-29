import { Injectable } from '@angular/core';
import { Track } from '../models/track';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/observable/fromEvent';
import { WebsocketService } from './websocket.service';
import { ToolkitService } from './toolkit.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Pt } from '../models/pt';
@Injectable()
export class TracksService {

  private tracks: Map<string, Track> = new Map<string, Track>();
  private tracksSubject: BehaviorSubject<Map<string, Track>> = new BehaviorSubject<Map<string, Track>>(this.tracks);
  private selectTrackIdSubject: BehaviorSubject<string> = new BehaviorSubject<string>(null);

  constructor(private websocketService: WebsocketService, private toolkitService: ToolkitService, private httpClient: HttpClient) {
    websocketService.create(toolkitService.getWsBaseUri() + "/track/projection")//
      .subscribe(v => this.processEvent(v), e => console.error(e));
  }

  getSelectedTrack(): Observable<Track> {
    return this.selectTrackIdSubject//
      .filter(id => id != null)//
      .distinctUntilChanged()//
      .map(id => this.tracks.get(id));
  }

  getTracks(): Observable<Array<Track>> {
    return this.tracksSubject//
      .map(tracks => Array.from(tracks.values()));
  }

  selectTrack(trackId: string): void {
    if (this.tracks.has(trackId)) {
      this.selectTrackIdSubject.next(trackId);
    } else {
      console.error("track id " + trackId + " not found");
    }
  }

  createTrackFromFile(file: File): Observable<boolean> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/xml; charset=utf-8' });
    const myReader: FileReader = new FileReader();
    myReader.readAsText(file);
    return Observable.fromEvent<any>(myReader, 'loadend')//
      .map(evt => myReader.result)//
      .mergeMap(content => this.httpClient.post(this.toolkitService.getBaseUri() + "/service/gpx", content, { headers: headers }))//
      .do(v =>  console.log(v))//
      .map(obs => true)//
  }



  private processEvent(event: any) {
    if (event.type === "CreatedTrackEvent") {
      this.tracks.set(event.id, new Track(event.id, event.name, this.createPoints(event.points)));
      this.tracksSubject.next(this.tracks);
    } else if (event.type === "CurrentTrackEvent") {
      this.tracks.set(event.id, new Track(event.track.id, event.track.name, this.createPoints(event.track.points)));
      this.tracksSubject.next(this.tracks);
    }
  }

  private createPoints(points: Array<any>): Array<Pt>{
    return points.map(p => new Pt(p.lat,p.lon, p.speed,p.ele));
  }



}
