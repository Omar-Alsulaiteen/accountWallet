import { JsonPipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { Account } from '../account';
import { AccountService } from '../account.service';
import { jwtDecoder } from '../jwtDecoder';

@Component({
  selector: 'account-card',
  templateUrl: './account-card.component.html',
  styleUrls: ['./account-card.component.css'],
})
export class AccountCardComponent {
  message!: string;
  status: boolean;
  constructor(
    private httpClient: HttpClient,
    private accountService: AccountService
  ) {
    this.status = false;
  }

  async ngOnInit() {
    const payload: any = jwtDecoder();

    this.httpClient
      .get<Account>(`http://localhost:8081/account/${payload.sub}`)
      .pipe(
        catchError((error) => {
          this.message = error?.error;
          this.status = true;
          return throwError(() => new Error('error'));
        })
      )
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
