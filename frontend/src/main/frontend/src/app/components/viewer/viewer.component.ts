import { Component, OnInit } from '@angular/core';
import { GoogleMapsAPIWrapper } from '@agm/core';
import { TracksService } from '../../services/tracks.service';
import { Track } from '../../models/track';
import { PlayerService } from '../../services/player.service';
import { Line } from '../../models/line';
import { Pt } from '../../models/pt';

@Component({
  selector: 'app-viewer',
  templateUrl: './viewer.component.html',
  styleUrls: ['./viewer.component.css']
})
export class ViewerComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
