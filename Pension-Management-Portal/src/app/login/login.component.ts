import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl, Validators } from '@angular/forms';
import { PensionServiceService } from '../pension-service.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private _pensionService:PensionServiceService) { }
  
  //toggle login error alert Message.
  alert:boolean=false;

  //login Response Holder
  public userData={};

  //login credentials
  credential=new FormGroup({
    username:new FormControl("",Validators.required),
    password:new FormControl("",Validators.required),
  })

  ngOnInit(): void {

    //if already login send user to pension-details.
    if(sessionStorage.getItem("token")){
      window.location.href="/pensiondetails";
    }
  }

  //login Method
  loginSubmit(){
    this._pensionService.getLogedIn(this.credential.value).subscribe(value=>{
      if(value.token){
        //if login successfull storing jwt token to session storage.
        sessionStorage.token=value.token;
        window.location.href="/pensiondetails";
      }
      //if login failed making alert message visible.
      else
      this.alert=true;
    });
  }

}
