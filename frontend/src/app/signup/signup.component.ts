import { Component } from '@angular/core';
import { NgForm, NgModel } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, last, throwError } from 'rxjs';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  urlToHit!: string;
  responseData!: object;
  message!: string;
  status: number;

  constructor(private httpClient: HttpClient, private router: Router) {
    this.urlToHit = 'http://localhost:8081/signup';
    this.status = 0;
  }

  ngOnInit() {}
  onSubmit(form: NgForm) {
    const username = form.control.get('username');
    const password = form.control.get('password');
    const cpassword = form.control.get('cpassword');
    const firstName = form.control.get('firstName');
    const lastName = form.control.get('lastName');

    if (form.invalid) {
      this.status = 2;

      if (username?.invalid) {
        this.message = 'Username field is required';
      } else if (firstName?.invalid) {
        this.message = 'First name field is required';
      } else if (lastName?.invalid) {
        this.message = 'Last name field is required';
      } else if (password?.invalid) {
        console.log(cpassword?.value);
        if (password.errors?.['minlength'])
          this.message = 'Password length must be 5 or more';
      }
      return;
    }

    if (password?.value != cpassword?.value) {
      this.status = 2;
      this.message = "Passwored Doesn't Match!!!";
      return;
    }

    this.httpClient
      .post(this.urlToHit, {
        account: form.value,
        password: form.value.password,
      })
      .pipe(
        catchError((error) => {
          this.status = 2;
          if (error?.status == 406) {
            this.message = 'Account with the same username already exists';
          } else {
            this.message = error?.error;
          }
          return throwError(() => new Error('error'));
        })
      )
      .subscribe((response) => {
        this.responseData = response;
        this.router.navigateByUrl('');
      });
  }
}
