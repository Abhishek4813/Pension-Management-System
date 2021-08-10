import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorComponent } from './error/error.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { PensionDetailsComponent } from './pension-details/pension-details.component';

const routes: Routes = [
  {
    path: "", 
    component: HomeComponent
  },
  {
    path:"login",
    component: LoginComponent
  },
  {
    path:"pensiondetails",
    component: PensionDetailsComponent
  },
  {
    path:"error",
    component: ErrorComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
