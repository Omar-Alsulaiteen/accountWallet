import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { AuthConfigService } from '../auth-config.service';
@Component({
  selector: 'login',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent {
  urlToHit!: string;
  responseData!: object;

  status: number;
  message!: string;

  constructor(private httpClient: HttpClient, private router: Router) {
    this.status = 0;
    this.urlToHit = 'http://localhost:8081/login';
  }

  onSubmit(form: NgForm) {
    if (form.invalid) {
      this.status = 2;
      if (form.control.get('username')?.invalid) {
        this.message = 'Username field is requried';
      } else {
        this.message = 'Password  field is requried';
      }
      return;
    }
    console.log(this.urlToHit);
    this.httpClient
      .post(this.urlToHit, form.value)
      .pipe(
        catchError((error) => {
          this.status = 2;
          if (error?.status == 406) {
            this.message = 'Invalid username or password';
          } else {
            this.message = error?.error;
          }
          return throwError(() => new Error('error'));
        })
      )
      .subscribe((response: any) => {
        this.responseData = response;
        localStorage.setItem('jwt', response.jwt);
        this.router.navigateByUrl('/app');
      });
  }
}
