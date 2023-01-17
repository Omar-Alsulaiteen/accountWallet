import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Account } from '../account';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css'],
})
export class TransferComponent {
  accounts!: Account[];

  constructor(
    private httpClient: HttpClient,
    private accountService: AccountService
  ) {}

  ngOnInit() {
    this.httpClient
      .get<Account[]>('http://localhost:8081/account/all-except/1')
      .subscribe((response) => {
        this.accounts = response;
      });
  }

  onSubmit(form: NgForm) {
    this.httpClient
      .post<Account>('http://localhost:8081/global/transfer', {
        sender: {
          id: 1,
        },
        receiver: {
          id: form.value.toAccount,
        },
        amount: form.value.amount,
      })
      .subscribe((response) => {
        console.log(response);
        this.accountService.account = response;
      });
  }
}
