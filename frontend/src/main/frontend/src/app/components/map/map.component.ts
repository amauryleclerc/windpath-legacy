import { Component, OnInit } from '@angular/core';
import { Line } from '../../models/line';
import { Pt } from '../../models/pt';
import { TracksService } from '../../services/tracks.service';
import { PlayerService } from '../../services/player.service';
import { PolyMouseEvent } from '@agm/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LineModalComponent } from '../line-modal/line-modal.component';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  lines: Array<Line> = [];
  center: Pt = new Pt(0, 0, 0, 0);
  zoom: Number = 15;
  maxSpeed: Pt;
  windsurfer: Pt;
  icon: String;
  constructor(private tracksService: TracksService, private playerService: PlayerService, private modalService: NgbModal ) { }

  ngOnInit() {
    this.tracksService.getSelectedTrack().subscribe(track => {
      this.lines.splice(0, this.lines.length);
      this.center = track.center;
      this.maxSpeed = track.maxSpeed;
      track.lines.forEach(line => {
        this.lines.push(line);
      });
    });
    this.playerService.getPosition().subscribe(pt => {
      this.windsurfer = pt;
    }, e => console.error(e));
    this.playerService.getDirection().subscribe(d => {
      this.icon = 'assets/' + d + '.gif';
    }, e => console.error(e));
  }

  onLineClick(line: Line){
    console.log(line);
    const modalRef = this.modalService.open(LineModalComponent);
    modalRef.componentInstance.line = line;
  }
}
