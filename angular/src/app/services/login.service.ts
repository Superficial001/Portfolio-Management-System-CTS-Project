import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthResponse } from '../model/authResponse';
import { AUTH_URL } from '../app.constants';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }
  
  generateToken(formData: any) {
    return this.http.post<AuthResponse>(`${AUTH_URL}/login`, formData)
  }

  loginUser(formData: AuthResponse)
  {
    localStorage.setItem("token", formData.authToken)
    localStorage.setItem("userid", formData.userid)
    return true;
  }
  // To check the Login
  isLoggedIn()
  {
    let token=localStorage.getItem("token");
    if(token==undefined|| token===''||token==null)
    {
      return false;
    }
    else
    {
      return true;
    }
  }
  //To logout
  logout()
  {
    localStorage.removeItem('token');
    localStorage.removeItem('userid')
    window.location.href = "/"
    return true;
  }

  getToken()
  {
    return localStorage.getItem("token")
  }

  getUserId() {
    return localStorage.getItem("userid")
  }

}
