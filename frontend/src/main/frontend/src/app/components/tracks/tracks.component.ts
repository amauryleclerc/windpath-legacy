import { Component, OnInit } from '@angular/core';
import { TracksService } from '../../services/tracks.service';
import { Track } from '../../models/track';

@Component({
  selector: 'app-tracks',
  templateUrl: './tracks.component.html',
  styleUrls: ['./tracks.component.css']
})
export class TracksComponent implements OnInit {

  tracks: Array<Track> = new Array<Track>();
  selectedTrackId: string = null;
  constructor(private tracksService: TracksService) { }

  ngOnInit() {
    this.tracksService.getTracks().subscribe(tracks => this.tracks = tracks, e => console.error(e));
    this.tracksService.getSelectedTrack().subscribe(track => this.selectedTrackId = track.id, e => console.error(e));
  }

  selectTrack(track: Track) {
    this.tracksService.selectTrack(track.id);
  }
}
