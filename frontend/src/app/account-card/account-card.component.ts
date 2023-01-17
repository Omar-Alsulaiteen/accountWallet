import { HttpClient } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { Account } from '../account';
import { AccountService } from '../account.service';

@Component({
  selector: 'account-card',
  templateUrl: './account-card.component.html',
  styleUrls: ['./account-card.component.css'],
})
export class AccountCardComponent {
  constructor(
    private httpClient: HttpClient,
    private accountService: AccountService
  ) {}

  ngOnInit() {
    this.httpClient
      .get<Account>('http://localhost:8081/account/1')
      .subscribe((response) => {
        this.accountService.account = response;
      });
  }
  getUsername() {
    return this.accountService.account?.username;
  }
  getId() {
    return this.accountService.account?.id;
  }
  getName() {
    return `${this.accountService.account?.firstName} ${this.accountService.account?.lastName}`;
  }
  getBalance() {
    return this.accountService.account?.balance;
  }
}
