import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthConfigService } from './auth-config.service';
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
    canActivate: [AuthConfigService],
    children: [
      {
        path: 'withdraw',
        component: WithdrawComponent,
        canActivate: [AuthConfigService],
      },
      {
        path: 'deposit',
        component: DepositComponent,
        canActivate: [AuthConfigService],
      },
      {
        path: 'transfer',
        component: TransferComponent,
        canActivate: [AuthConfigService],
      },
      {
        path: 'transactions',
        component: TransactionsComponent,
        canActivate: [AuthConfigService],
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthConfigService],
})
export class AppRoutingModule {}
