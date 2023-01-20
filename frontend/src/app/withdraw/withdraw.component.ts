import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { catchError, throwError } from 'rxjs';
import { Account } from '../account';
import { AccountService } from '../account.service';
import { jwtDecoder } from '../jwtDecoder';

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css'],
})
export class WithdrawComponent {
  @Input('status')
  status: number;
  message!: string;

  constructor(
    private httpClient: HttpClient,
    private accountService: AccountService
  ) {
    this.status = 0;
  }

  inputOnSubmit(form: NgForm) {
    if (form.invalid) {
      this.status = 2;
      let amount = form.control.get('amount');

      if (amount?.invalid) {
        if (amount.errors?.['required'])
          this.message = 'Amount field is required';
        else this.message = 'Min value for the Amount field is more than 0';
      }
      return;
    }
    console.log('In withdraw submit form!!!');
    this.httpClient
      .post<Account>('http://localhost:8081/personal/withdraw', {
        account: {
          username: jwtDecoder().sub,
        },
        amount: form.value.amount,
      })
      .pipe(
        catchError((error) => {
          this.message = error?.error;
          this.status = 2;
          return throwError(() => new Error('error'));
        })
      )
      .subscribe((response) => {
        this.accountService.account = response;
        this.status = 1;
      });
  }
}
