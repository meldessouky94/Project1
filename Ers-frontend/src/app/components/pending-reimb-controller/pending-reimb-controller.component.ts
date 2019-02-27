import { Component, OnInit } from '@angular/core';
import { ReimbursementsService } from 'src/app/services/reimbursements.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pending-reimb-controller',
  templateUrl: './pending-reimb-controller.component.html',
  styleUrls: ['./pending-reimb-controller.component.css']
})
export class PendingReimbControllerComponent implements OnInit {

constructor(private reimbursementService: ReimbursementsService,
            private cookie: CookieService,
            private router: Router) { }
  
    reimbursements: any;
    status = "";
    user_id: any;
  
    submit(){
      this.router.navigateByUrl('manager-dashboard');
    }
  
    ngOnInit() {
      this.viewReimbursements();
      this.user_id = this.cookie.get('userId');
    }
  
    viewReimbursements() {
      this.reimbursementService.pendingReimb()
      .subscribe( (payload) => {
        console.log(payload);
        for (const key in payload) {
               if (payload.hasOwnProperty(key)) {
                 this.reimbursements = payload;
                  }
                }
              },
       (error) => console.log(error));
      }
      updateReimb(reimb: any) {
       // console.log(reimb.authorId);
       // console.log(this.cookie.get('id'));
        if(reimb.author != this.cookie.get('userId')) {
        if(this.status == '2') {
          reimb.resolver = this.user_id;
          reimb.status = 2;
          console.log(reimb);
  
          this.reimbursementService.updateReimb(reimb).subscribe( (payload) => {
          console.log(payload);
  
          }, (err) => {
            console.log(err);
          });
         this.status = "";
        }
        else if(this.status == '1'){
  
          reimb.resolver = this.user_id;
          reimb.status = 3;
          console.log(reimb);
          this.reimbursementService.updateReimb(reimb).subscribe( (payload) => {
            console.log(payload);
  
           }, (err) => {
             console.log(err);
           });
          this.status = "";
        }
     } else {
       alert("Finance Managers cannot process own tickets!")
     this.status = "";
     }
    }
  }
