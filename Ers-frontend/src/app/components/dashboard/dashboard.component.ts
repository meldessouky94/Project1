import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { ReimbursementsService } from 'src/app/services/reimbursements.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  gotReimb = false;
  userId: any;
  reimbursements: any;

  amount: any;
  description: any;
  type: any;
  author: any;

  constructor(private reimbursementService: ReimbursementsService,
              private cookie: CookieService,
              private router: Router) { }

  ngOnInit() {
    this.userId = this.cookie.get('userId');
  }

  logout() {
    this.cookie.deleteAll();
    this.router.navigateByUrl('');
  }

  getReimb() {
    this.reimbursementService.getReimb(this.userId).subscribe( (payload) => {
      console.log(payload);
      for (const key in payload) {
             if (payload.hasOwnProperty(key)) {
              this.reimbursements = payload;
                }
              }
            },
     (error) => console.log(error));
    }

    createReimb() {
      this.gotReimb = true;
    }

    addReimb(): any {
      const reimbursement = {
        amount: this.amount,
        description: this.description,
        type: this.type,
        author: this.userId
      };
      console.log(reimbursement);
      this.reimbursementService.addReimb(reimbursement).subscribe( (payload) => {
         console.log(payload);
  
        }, (err) => {
          console.log(err);
        });
     // } else {
       //   alert("Invalid username or password.")
  
      this.ngOnInit();
      }
}
