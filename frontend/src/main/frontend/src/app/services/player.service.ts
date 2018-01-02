import { Injectable } from '@angular/core';
import { Pt } from '../models/pt';
import { TracksService } from './tracks.service';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Subject } from 'rxjs/Subject';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/combineLatest';
import 'rxjs/add/operator/share';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/finally';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/operator/repeat';
import 'rxjs/add/operator/take';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/bufferCount';
import 'rxjs/add/operator/skipWhile';
import 'rxjs/add/operator/toArray';
import 'rxjs/add/observable/never';
import 'rxjs/add/observable/zip';
import 'rxjs/add/observable/from';
import 'rxjs/add/observable/concat';
import 'rxjs/add/observable/interval';
@Injectable()
export class PlayerService {

  private position: Observable<Pt>;
  private pauseSubject = new BehaviorSubject<boolean>(true);
  private speedSubject = new BehaviorSubject<number>(1000);
  private pointSubject = new Subject<Pt>();
  constructor(private tracksService: TracksService) {
    const interval: Observable<Number> = this.pauseSubject.combineLatest(this.speedSubject, (a, b) => [a, b])//
      .switchMap(tuple => tuple[0] ? Observable.concat<Number>(Observable.of(0),Observable.never()) : Observable.interval(Number(tuple[1])));

    this.position = this.tracksService.getSelectedTrack()//
      .do(() => this.pause())//
      .switchMap(track => this.pointSubject//
        .mergeMap(selectedPt => selectedPt == null ? Observable.of(track.pts) : Observable.from(track.pts)//
          .skipWhile(pt => selectedPt !== pt)//
          .toArray())//
        .startWith(track.pts))//
      .switchMap(pts => Observable.zip(Observable.from(pts), interval, (a, b) => a).finally(() => this.pause()).repeat())
      .share();
  }
  play() {
    this.pauseSubject.next(false);
  }
  stop() {
    this.pointSubject.next(null);
  }
  setToPoint(pt: Pt) {
    this.pointSubject.next(pt);
    this.play();
  }
  pause() {
    this.pauseSubject.next(true);
  }
  setSpeed(speed: number) {
    this.speedSubject.next(speed);
  }
  faster() {
    this.speedSubject.take(1).filter(s => s >= 0.1).subscribe(s => this.speedSubject.next(s / 10), e => console.error(e));
  }
  slower() {
    this.speedSubject.take(1).filter(s => s <= 10000).subscribe(s => this.speedSubject.next(s * 10), e => console.error(e));
  }
  getPosition(): Observable<Pt> {
    return this.position;
  }
  getSpeed(): Observable<number> {
    return this.speedSubject.asObservable();
  }
  isPlaying(): Observable<boolean> {
    return this.pauseSubject.asObservable().map(v => !v);
  }
  getDirection(): Observable<String> {
    return this.position.bufferCount(2, 1)//
      .map(l => this.getDirectionFromPts(l[0], l[1]))//
      .startWith('right')//
      .distinctUntilChanged();
  }

  private getDirectionFromPts(ptA: Pt, ptB: Pt): String {
    if (ptA.lon < ptB.lon) {
      return 'right';
    } else {
      return 'left';
    }
  }

}
