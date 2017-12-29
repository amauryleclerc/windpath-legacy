import { Component, OnInit } from '@angular/core';
import { TracksService } from '../../services/tracks.service';

@Component({
  selector: 'app-add-track',
  templateUrl: './add-track.component.html',
  styleUrls: ['./add-track.component.css']
})
export class AddTrackComponent implements OnInit {

  constructor(private tracksService: TracksService) { }

  ngOnInit() {
  }

  createTrackFromFile(event) {
    const file: File = event.target.files[0];
    this.tracksService.createTrackFromFile(file)//
      .subscribe(v => console.log(v), e => console.error(e));
  }

}
