import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { LoginService } from 'src/app/services/login.service';
import { AuthResponse } from '../../model/authResponse';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  providers: [LoginService]
})
export class NavbarComponent implements OnInit {
  credentials = {
    userid: '',
    upassword: ''
  }

  formData: any;

  getToken() {

    this.credentials.userid = this.formData.value.userid
    this.credentials.upassword = this.formData.value.upassword

    if (
      this.credentials.userid != '' && this.credentials.upassword != '' &&
      this.credentials.userid != null && this.credentials.upassword != null
    ) {
      this.loginService.generateToken(this.credentials).subscribe(
        (response) => {
          console.log(response);
          this.loginService.loginUser(response)
          window.location.href = "/home";

        },
        (error: any) => {
          console.log(error);
        }
      )
    }

    // console.log("clicked")/
    // console.log(this.formData.value)
  }

  constructor(
    private loginService: LoginService
  ) { }

  ngOnInit(): void {

    this.formData = new FormGroup({
      userid: new FormControl(),
      upassword: new FormControl()
    })

  }

}
