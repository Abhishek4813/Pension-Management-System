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

  loader:boolean=false;

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
    this.loader=true;
  this._pensionService.getLogedIn(this.credential.value).subscribe((value)=>{
      this.loader=false;
      if(value.token){
        //if login successfull storing jwt token to session storage.
        sessionStorage.token=value.token;
        
        window.location.href="/pensiondetails";
      }
      //if login failed making alert message visible.
      else
      this.alert=true;
    },(error:any)=>{
      if(error.ok==false){
        this.loader=false;
        window.location.href="/error";
      };
    });
  }

}
