import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router/src/interfaces';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { UserService } from './user.service';
import 'rxjs/add/operator/onErrorResumeNext';
import 'rxjs/add/observable/of';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private userService: UserService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.userService.getCurrentUser()//
      .map(user => true)//
      .do(null, e => this.router.navigate(["/login"]))//
      .onErrorResumeNext(e => false);
  }


}
