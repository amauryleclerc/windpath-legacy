import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../../services/player.service';
import { TracksService } from '../../services/tracks.service';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent implements OnInit {

  isPlaying: Boolean = false;
  hasTrack: Boolean = false;
  playerSpeed: Number = 1;
  speed: String;
  knSpeed: number = 0;
  rotate: String = "rotate(0deg)";
  constructor(private playerService: PlayerService, private tracksService: TracksService) { }

  ngOnInit() {
    this.tracksService.getSelectedTrack().subscribe(v => {
      this.hasTrack = v != null;
    }, e => console.error(e))
    this.playerService.isPlaying().subscribe(v => {
      this.isPlaying = v;
    }, e => console.error(e));
    this.playerService.getSpeed().subscribe(speed => {
      this.playerSpeed = 1000 / speed;
    });
    this.playerService.getPosition()//
      .subscribe(p => {
        this.speed = p.getSpeedLabel();
        this.knSpeed = p.getKnotsSpeed();
        this.rotate = "rotate("+this.knSpeed*6+"deg)"
      });
  }

  play() {
    this.playerService.play();
  }
  pause() {
    this.playerService.pause();
  }
  faster() {
    this.playerService.faster();
  }
  slower() {
    this.playerService.slower();
  }
}
