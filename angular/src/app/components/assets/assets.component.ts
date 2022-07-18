import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CalculateNetworthService } from 'src/app/services/calculate-networth.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-assets',
  templateUrl: './assets.component.html',
  styleUrls: ['./assets.component.css']
})
export class AssetsComponent implements OnInit {

  assets: any;
  formData: any;

  sellAssetList = {
    assetid: "",
    units: "",
    type: ""
  }


  constructor(
    private http: HttpClient,
    private calculateNetworthService: CalculateNetworthService,
    private loginService: LoginService
  ) { }

  getAssets() {
    this.calculateNetworthService.getAllAssets().subscribe(
      (response) => {
        this.assets = response
      },
      (error: any) => {
        console.log(error);
      }
    )
  }


  sellAssets() {
    console.log(this.formData.value);
    for(let i = 0;i < this.assets.length;i++) {
      if(this.assets[i].assetid === this.formData.value.asset) {
        this.sellAssetList.assetid = this.formData.value.asset
        this.sellAssetList.units = this.formData.value.quantity
        this.sellAssetList.type = this.assets[i].type
      }
    }
    console.log(this.sellAssetList);
    this.calculateNetworthService.sellAsset(this.sellAssetList).subscribe(
      (response) => {
        console.log(response);
        window.location.href = "/viewNetWorth"
      },
      (error: any) => {
        console.log(error);
        
      }
    )
    
  }

  ngOnInit(): void {
    this.getAssets()
    this.formData = new FormGroup({
      asset: new FormControl(),
      quantity: new FormControl()
    })
  }

}
