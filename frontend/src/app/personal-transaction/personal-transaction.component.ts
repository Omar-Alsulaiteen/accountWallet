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
  constructor(
    private httpClient: HttpClient,
    private accountService: AccountService
  ) {}

  @Input('title')
  title!: string;

  @Input('inputOnSubmit')
  onSubmit!: (args: NgForm) => void;
}
