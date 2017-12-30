//Modules
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule, HttpClient } from '@angular/common/http';
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
import { PlayerService } from './services/player.service';
import { PlayerComponent } from './components/player/player.component';
import { AddTrackComponent } from './components/add-track/add-track.component';
import { TracksComponent } from './components/tracks/tracks.component';
import { MapComponent } from './components/map/map.component';
import { LoginComponent } from './components/login/login.component';
import { AuthGuardService } from './services/auth-guard.service';

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'viewer', component: ViewerComponent, canActivate: [AuthGuardService] },
  { path: '', redirectTo: '/viewer', pathMatch: 'full' },
  { path: '*', redirectTo: '/viewer', pathMatch: 'full' },
];

@NgModule({
  declarations: [
    MainComponent,
    ViewerComponent,
    PlayerComponent,
    AddTrackComponent,
    TracksComponent,
    MapComponent,
    LoginComponent
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
  providers: [
    ToolkitService,
    UserService,
    TracksService,
    WebsocketService,
    PlayerService,
    AuthGuardService],
  bootstrap: [MainComponent]
})
export class AppModule { }
