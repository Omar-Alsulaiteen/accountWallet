import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpHeaders,
  HttpErrorResponse,
} from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

@Injectable()
export class JwtInterceptorService implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    // Get the JWT from local storage
    const jwt = localStorage.getItem('jwt');

    // Check if the JWT is not null
    if (jwt) {
      // Clone the request and set the new header
      let value: string = 'Bearer ' + jwt;
      const authReq = req.clone({
        headers: new HttpHeaders().set('Authorization', value),
      });

      // Pass the cloned request with the new header to the next handler
      return next.handle(authReq);
    } else {
      // Pass the original request to the next handler
      return next.handle(req);
    }
  }
}
