import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'login',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent {
  urlToHit!: string;
  responseData!: object;

  constructor(private httpClient: HttpClient) {}

  ngOnInit() {
    this.urlToHit = 'http://localhost:8081/account/login';
  }
  onSubmit(form: NgForm) {
    console.log(this.urlToHit);
    this.httpClient.post(this.urlToHit, form.value).subscribe((response) => {
      this.responseData = response;
      console.log(response);
    });
  }
}
