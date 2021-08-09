import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl, Validators } from '@angular/forms';
import { PensionServiceService } from '../pension-service.service';
@Component({
  selector: 'app-pension-details',
  templateUrl: './pension-details.component.html',
  styleUrls: ['./pension-details.component.css']
})
export class PensionDetailsComponent implements OnInit {

  toggler:boolean=true;
  alert:boolean=false;
  loader:boolean=false;
  pensionerData:any={};
  aadhar:string="";
  constructor(private _service:PensionServiceService) { }

  pensionDetails=new FormGroup({
    name:new FormControl("",Validators.required),
    dateOfBirth:new FormControl("",Validators.required),
    pan: new FormControl("",[Validators.required, Validators.pattern("^[a-z,A-Z]{5}[0-9]{4}[a-z,A-Z]{1}$")]),
    aadharNumber:new FormControl("",[Validators.required,Validators.pattern("^[1-9]{1}[0-9]{11}$")]),
    pensionType: new FormControl("",Validators.required),
    

  })

  ngOnInit(): void {

  }

  submitDetails(){
    this.loader=true;
    this._service.getPensionDetails(this.pensionDetails.value).subscribe(value=>{
      if(value==null){
        this.loader=false;
        this.alert=true;
      }
      else{
      this.pensionerData=value;
      this.aadhar=this.pensionDetails.value.aadharNumber;
      this.toggler=false;
      this.loader=false;
      }
    });
  }

}
