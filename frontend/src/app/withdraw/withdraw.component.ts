import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Account } from '../account';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css'],
})
export class WithdrawComponent {
  constructor(
    private httpClient: HttpClient,
    private accountService: AccountService
  ) {}

  inputOnSubmit(form: NgForm) {
    console.log('In withdraw submit form!!!');
    this.httpClient
      .post<Account>('http://localhost:8081/personal/withdraw', {
        account: {
          id: 1,
        },
        amount: form.value.amount,
      })
      .subscribe((response) => {
        console.log(response);
        this.accountService.account = response;
      });
  }
}
