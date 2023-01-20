import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { catchError, throwError } from 'rxjs';
import { Account } from '../account';
import { AccountService } from '../account.service';
import { jwtDecoder } from '../jwtDecoder';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css'],
})
export class TransferComponent {
  accounts!: Account[];
  status!: number;
  message!: string;

  constructor(
    private httpClient: HttpClient,
    private accountService: AccountService
  ) {
    this.status = 0;
  }

  ngOnInit() {
    this.httpClient
      .get<Account[]>(
        `http://localhost:8081/account/all-except/${jwtDecoder().sub}`
      )
      .subscribe((response) => {
        this.accounts = response;
      });
  }

  onSubmit(form: NgForm) {
    if (form.invalid) {
      this.status = 2;
      let account = form.control.get('toAccount');
      let amount = form.control.get('amount');
      if (account?.invalid) {
        this.message = 'Account field is required';
      } else if (amount?.invalid) {
        if (amount.errors?.['required'])
          this.message = 'Amount field is required';
        else this.message = 'Min value for the Amount field is more than 0';
      }
      return;
    }

    this.httpClient
      .post<Account>('http://localhost:8081/global/transfer', {
        sender: {
          username: jwtDecoder().sub,
        },
        receiver: {
          id: form.value.toAccount,
        },
        amount: form.value.amount,
      })
      .pipe(
        catchError((error) => {
          console.log(error);
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
