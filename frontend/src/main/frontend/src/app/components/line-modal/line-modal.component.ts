import { Component, OnInit, Input } from '@angular/core';
import { Line } from '../../models/line';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { PlayerService } from '../../services/player.service';

@Component({
  selector: 'app-line-modal',
  templateUrl: './line-modal.component.html',
  styleUrls: ['./line-modal.component.css']
})
export class LineModalComponent implements OnInit {

  @Input() line: Line;

  constructor(private activeModal: NgbActiveModal, private playerService: PlayerService) { }

  ngOnInit() {
  }

  close(){
    this.activeModal.close('Close click');
  }

  play(){
    this.playerService.setToPoint(this.line.ptA);
    this.activeModal.close('Set to point');
  }
}
