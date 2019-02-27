import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReimbursementsService {

  constructor(private httpClient: HttpClient) { }

  getReimb(userId: any) {
    const url = `${environment.apiUrl}/getreimb`;
    return this.httpClient.post(url, userId);
  }

  addReimb(reimbursement: any) {
    const url = `${environment.apiUrl}/addreimb`;
    return this.httpClient.post(url, reimbursement);
  }

  updateReimb(reimbursement: any) {
    const url = `${environment.apiUrl}/updatereimb`;
    return this.httpClient.post(url, reimbursement);
  }

  pendingReimb() {
    const url = `${environment.apiUrl}/pendingreimb`;
    return this.httpClient.get(url);
  }
}
