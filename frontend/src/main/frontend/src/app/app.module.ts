//Modules
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {HttpClientModule, HttpClient} from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { AgmCoreModule } from '@agm/core';
//Components
import { MainComponent } from './components/main/main.component';
import { ViewerComponent } from './components/viewer/viewer.component';
//Services
import { ToolkitService } from './services/toolkit.service';
import { UserService } from './services/user.service';
import { TracksService } from './services/tracks.service';
import { WebsocketService } from './services/websocket.service';

const appRoutes: Routes = [
  { path: 'viewer', component: ViewerComponent },
  { path: '', redirectTo: '/viewer', pathMatch: 'full' },
  { path: '*', redirectTo: '/viewer', pathMatch: 'full' },
];

@NgModule({
  declarations: [
    MainComponent,
    ViewerComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgbModule.forRoot(),
    RouterModule.forRoot(appRoutes, { useHash: true }),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBfSkyDgkMtKi7tWDvcQ33kwas-lbLAE08'
 })
  ],
  providers: [ToolkitService,UserService, TracksService, WebsocketService],
  bootstrap: [MainComponent]
})
export class AppModule { }
