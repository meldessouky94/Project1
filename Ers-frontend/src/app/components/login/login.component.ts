import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';
import { analyzeAndValidateNgModules } from '@angular/compiler';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  userId: any;

  constructor(private loginService: LoginService,
              private cookie: CookieService,
              private router: Router) { }

  ngOnInit() {
  }

  login(): any {
    const credentials = {
      username: this.username,
      password: this.password,
    };

    this.loginService.login(credentials).subscribe( (payload) => {
      console.log(payload)
      const role = payload.role;
      this.cookie.set('userId', payload.userId);
      this.cookie.set('role', role);

      if (role == '1') {
      this.userId = payload;
      this.router.navigateByUrl('/dashboard');

    } else if (role == '2') {
      this.userId = payload;
      this.router.navigateByUrl('/manager-dashboard');

    } else {
       alert('Invalid credentials!');
    }
  }, (err) => {
    console.log(err);
  });
}
}
