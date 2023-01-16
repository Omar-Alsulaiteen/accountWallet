import { Component } from '@angular/core';
import { NgForm, NgModel } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  urlToHit!: string;
  responseData!: object;

  constructor(private httpClient: HttpClient) {}

  ngOnInit() {
    this.urlToHit = 'http://localhost:8081/account';
  }
  onSubmit(form: NgForm) {
    this.httpClient.post(this.urlToHit, form.value).subscribe((response) => {
      this.responseData = response;
      console.log(response);
    });
  }
}
