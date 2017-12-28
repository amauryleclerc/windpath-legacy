import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(private userService: UserService) { }

  user: any = null;

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(user => this.user = user, e => console.error(e));
  }

}
