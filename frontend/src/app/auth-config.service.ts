import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthConfigService implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    // Get the JWT from localStorage
    const jwt: string | null = localStorage.getItem('jwt');

    // Check if the JWT is valid
    if (!this.isJwtValid(jwt)) {
      localStorage.removeItem('jwt');
      this.router.navigate(['']);
      return false;
    }
    return true;
  }

  private isJwtValid(jwt: string | null): boolean {
    // Check if the JWT is null or expired
    if (!jwt) {
      return false;
    }
    // Decode the JWT to get the expiration date
    const decodedJwt = this.decodeJwt(jwt);
    if (!decodedJwt) {
      return false;
    }

    // Check if the expiration date is in the past
    const expirationDate = new Date(0);
    expirationDate.setUTCSeconds(decodedJwt.exp);
    if (expirationDate < new Date()) {
      return false;
    }

    return true;
  }

  private decodeJwt(jwt: string): any {
    try {
      // Split the JWT into 3 parts
      const jwtParts = jwt.split('.');

      // Base64 decode the first and second parts
      const decodedHeader = atob(jwtParts[0]);
      const decodedPayload = atob(jwtParts[1]);

      // Parse the decoded parts as JSON
      const header = JSON.parse(decodedHeader);
      const payload = JSON.parse(decodedPayload);

      return payload;
    } catch (e) {
      return null;
    }
  }
}
