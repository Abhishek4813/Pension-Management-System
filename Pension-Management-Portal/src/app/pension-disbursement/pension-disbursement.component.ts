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
  @Input() data:any={};
  @Input() aadhar:string="";
  constructor(private _service:PensionServiceService) { }

  ngOnInit(): void {
  }

  doDisburse(){
   this.toggler= true;
   let i=0;
   this._service.disburse({
     aadharNumber: this.aadhar,
     pensionAmount: this.data.pensionAmount,
     serviceCharge: 500
   }).subscribe(value=>{
     console.log(value.pensionStatusCode);
     if(value.pensionStatusCode==10){
       this.loader=false;
       this.disburse=true;
     }
     if(value.pensionStatusCode==21){
       this.loader=false;
       this.disburse=false;
     }
   });
  }
  }
