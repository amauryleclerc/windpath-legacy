import { Component, OnInit } from '@angular/core';
import { GoogleMapsAPIWrapper } from '@agm/core';
import { TracksService } from '../../services/tracks.service';
import { Track } from '../../models/track';

@Component({
  selector: 'app-viewer',
  templateUrl: './viewer.component.html',
  styleUrls: ['./viewer.component.css'],
  providers :[GoogleMapsAPIWrapper]
})
export class ViewerComponent implements OnInit {

  constructor(private googleMapsAPIWrapper: GoogleMapsAPIWrapper, private tracksService: TracksService) { }

  tracks: Array<Track> = new Array<Track>();

  ngOnInit() {
    this.tracksService.getTracks().subscribe(tracks => this.tracks = tracks, e => console.error(e));
  }

  createTrackFromFile(event){
    const file: File = event.target.files[0];
    this.tracksService.createTrackFromFile(file)//
      .subscribe(v => console.log(v), e => console.error(e));

  }
  
}
