import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginService } from './login.service';
import { Assets } from '../model/assets';
import { CNW_URL } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class CalculateNetworthService {

  constructor(
    private http: HttpClient,
    private loginService: LoginService
  ) {}

  sellAsset(assets: any) {

    console.log(assets);
    

    let token = this.loginService.getToken();
    let userid = this.loginService.getUserId();
    let options = {
      headers: { "Authorization": "Bearer " + token}
    }

    return this.http.post(`${CNW_URL}/NetWorth/SellAssets/${userid}`, assets, options)

  }

  getAllAssets() {

    let token = this.loginService.getToken();
    let userid = this.loginService.getUserId();
    let options = {
      headers: { "Authorization": "Bearer " + token}
    }

    return this.http.get<Assets[]>(`${CNW_URL}/NetWorth/GetAllAssets/${userid}`, options)

  }

  getNetworth() {

    let token = this.loginService.getToken();
    let userid = this.loginService.getUserId();
    let options = {
      headers: { "Authorization": "Bearer " + token}
    }

    return this.http.get<number>(`${CNW_URL}/NetWorth/calculateNetworth/${userid}`, options)

  }

}
