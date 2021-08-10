import { Component, Input, OnInit } from '@angular/core';
import { PensionServiceService } from '../pension-service.service';

@Component({
  selector: 'app-pension-disbursement',
  templateUrl: './pension-disbursement.component.html',
  styleUrls: ['./pension-disbursement.component.css']
})
export class PensionDisbursementComponent implements OnInit {

  toggler:boolean=false;
  loader:boolean=true;
  disburse:boolean= false;
  authFailed:boolean=false;
  @Input() data:any={};
  @Input() aadhar:string="";
  constructor(private _service:PensionServiceService) { }

  ngOnInit(): void {
  }

  doDisburse(){
   this.toggler= true;
   this._service.disburse({
     aadharNumber: this.aadhar,
     pensionAmount: this.data.pensionAmount,
   }).subscribe(value=>{
     this.loader=false;
     console.log(value.pensionStatusCode);
     if(value.message=="User not authorized"){
      sessionStorage.removeItem("token");
      this.authFailed=true;
      setTimeout(()=>{
        window.location.href="/login";
      },3000);
      
    }
    else if(value.pensionStatusCode==10){
       this.disburse=true;
     }
    else if(value.pensionStatusCode==21){
       this.disburse=false;
     }
   },(error:any)=>{
    if(error.ok==false){
      this.loader=false;
      window.location.href="/error";
    };
  });
  }
  }
