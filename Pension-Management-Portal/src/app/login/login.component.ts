import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl, Validators } from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() { }

  alert:boolean=false;

  credential=new FormGroup({
    username:new FormControl("",Validators.required),
    password:new FormControl("",Validators.required),
  })

  ngOnInit(): void {
  }
  loginSubmit(){
    console.log(this.credential.value);
    this.alert=true;
    window.location.href="/pensiondetails";
  }

}
