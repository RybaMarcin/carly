import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from "@angular/common/http";
import {LocalStorageService} from "angular-2-local-storage";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {CARLY_JWT_TOKEN} from "../model/carly-jwt-token.model";
import {environment} from "../../../environments/environment";
import {catchError, map} from "rxjs/operators";

declare const SUBSCRIPTION_KEY;

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private storageService: LocalStorageService,
    private router: Router,
  ) {
  }


  intercept(req: HttpRequest<HttpHandler>, next: HttpHandler): Observable<HttpEvent<HttpHandler>> {
    let duplicateReq = req.clone({
      headers: req.headers
    });

    const carlyJwtToken = this.storageService.get(CARLY_JWT_TOKEN);

    if (carlyJwtToken) {
      duplicateReq = duplicateReq.clone({
        headers: duplicateReq.headers
          .set('Authorization', `Carly-Bearer ${carlyJwtToken}`)
      });
    }

    duplicateReq = duplicateReq.clone({
      headers: duplicateReq.headers
        .set('Ocp-Apim-Trace', 'true')
        .set('Ocp-Apim-Subscription-Key', SUBSCRIPTION_KEY || environment.subscriptionKey)
    });

    return next.handle(duplicateReq).pipe(
      map(next => {
        if (next instanceof HttpResponse) {
          const carlyJwtToken = next.headers.get(CARLY_JWT_TOKEN);
          if (carlyJwtToken) {
            this.storageService.set(CARLY_JWT_TOKEN, carlyJwtToken);
          }
        }
        return next;
      }),
      catchError(err => {
        if (err.status === 401) {
          this.router.navigate(['/logout']);
        }
        throw err;
      })
    );
  }



}
