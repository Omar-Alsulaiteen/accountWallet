import { HttpClient } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AccountService } from '../account.service';

@Component({
  selector: 'operation',
  templateUrl: './personal-transaction.component.html',
  styleUrls: ['./personal-transaction.component.css'],
})
export class PersonalTransactionComponent {
  @Input('status')
  status!: number;

  @Input('title')
  title!: string;

  @Input('message')
  message!: string;

  constructor(
    private httpClient: HttpClient,
    private accountService: AccountService
  ) {}

  @Input('inputOnSubmit')
  onSubmit!: (args: NgForm) => void;
}
