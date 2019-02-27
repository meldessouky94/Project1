import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ManagerDashboardComponent } from './components/manager-dashboard/manager-dashboard.component';
import { PendingReimbControllerComponent } from './components/pending-reimb-controller/pending-reimb-controller.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
path: 'login',
component: LoginComponent
  },
  {
path: 'dashboard',
component: DashboardComponent
  },
  {
path: 'manager-dashboard',
component: ManagerDashboardComponent
  },
  {
path: 'pending-view',
component: PendingReimbControllerComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
