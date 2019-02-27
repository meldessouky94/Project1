import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginService } from './services/login.service';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RouterModule } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ReimbursementsService } from './services/reimbursements.service';
import { ManagerDashboardComponent } from './components/manager-dashboard/manager-dashboard.component';
import { PendingReimbControllerComponent } from './components/pending-reimb-controller/pending-reimb-controller.component';
import { PipeResolver } from '@angular/compiler';
import { ReimbStatusPipe } from './pipes/status-pipes.component';
import { ReimbTypePipe } from './pipes/type-pipes.components';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    ManagerDashboardComponent,
    PendingReimbControllerComponent,
    ReimbStatusPipe,
    ReimbTypePipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [LoginService, CookieService, ReimbursementsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
