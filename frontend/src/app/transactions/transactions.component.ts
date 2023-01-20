import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AccountService } from '../account.service';
import { jwtDecoder } from '../jwtDecoder';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css'],
})
export class TransactionsComponent {
  personalTransactions!: any;
  globalTransactions!: any;

  constructor(
    private httpClient: HttpClient,
    private accountService: AccountService
  ) {}

  async ngOnInit() {
    this.httpClient
      .get(`http://localhost:8081/personal/transactions/${jwtDecoder().sub}`)
      .subscribe((response) => {
        this.personalTransactions = response;
        console.log(response);
      });

    this.httpClient
      .get(`http://localhost:8081/global/transactions/${jwtDecoder().sub}`)
      .subscribe((response) => {
        this.globalTransactions = response;
        console.log(response);
      });
  }
}
