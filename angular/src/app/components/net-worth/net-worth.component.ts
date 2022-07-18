import { Component, OnInit } from '@angular/core';
import { CalculateNetworthService } from 'src/app/services/calculate-networth.service';

@Component({
  selector: 'app-net-worth',
  templateUrl: './net-worth.component.html',
  styleUrls: ['./net-worth.component.css'],
  providers: [CalculateNetworthService]
})
export class NetWorthComponent implements OnInit {

  assets: any;
  networth: any;

  constructor(
    private calculateNetworthService: CalculateNetworthService
  ) {}

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

  getNetworth() {
    this.calculateNetworthService.getNetworth().subscribe(
      (response) => {
        this.networth = response
      },
      (error: any) => {
        console.log(error);
        
      }
    )
  }

  ngOnInit(): void {
    this.getAssets()
    this.getNetworth()
  }

}
