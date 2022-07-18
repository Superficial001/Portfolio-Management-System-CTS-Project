import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AssetsComponent } from './components/assets/assets.component';
import { AuthGuard } from './guards/auth.guard';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NetWorthComponent } from './components/net-worth/net-worth.component';

const routes: Routes = [
  {
    path: "",
    component: LoginComponent,
    pathMatch: "full"
  },
  {
    path: "home",
    component: HomeComponent,
    pathMatch: "full",
    canActivate: [AuthGuard]
  },
  {
    path: "viewNetWorth",
    component: NetWorthComponent,
    pathMatch: "full",
    canActivate: [AuthGuard]
  },
  {
    path: "sellAssets",
    component: AssetsComponent,
    pathMatch: "full",
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
