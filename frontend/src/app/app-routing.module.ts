import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DepositComponent } from './deposit/deposit.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { SignupComponent } from './signup/signup.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { TransferComponent } from './transfer/transfer.component';
import { WithdrawComponent } from './withdraw/withdraw.component';

const routes: Routes = [
  { path: '', component: LoginPageComponent },
  {
    path: 'signup',
    component: SignupComponent,
  },
  {
    path: 'app',
    component: HomepageComponent,
    children: [
      { path: 'withdraw', component: WithdrawComponent },
      { path: 'deposit', component: DepositComponent },
      { path: 'transfer', component: TransferComponent },
      { path: 'transactions', component: TransactionsComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
