import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthConfigService } from '../auth-config.service';
@Component({
  selector: 'login',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent {
  urlToHit!: string;
  responseData!: object;

  constructor(private httpClient: HttpClient, private router: Router) {}

  ngOnInit() {
    this.urlToHit = 'http://localhost:8081/login';
  }
  onSubmit(form: NgForm) {
    console.log(this.urlToHit);
    this.httpClient
      .post(this.urlToHit, form.value)
      .subscribe((response: any) => {
        this.responseData = response;
        localStorage.setItem('jwt', response.jwt);
        this.router.navigateByUrl('/app');
      });
  }
}
