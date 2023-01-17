import { Injectable } from '@angular/core';
import { Account } from './account';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  account!: Account;
  constructor() {}

  updateAccount(account: Account) {
    this.account = account;
  }
}
