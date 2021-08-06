import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-pension-details',
  templateUrl: './pension-details.component.html',
  styleUrls: ['./pension-details.component.css']
})
export class PensionDetailsComponent implements OnInit {


  constructor() { }

  pensionDetails=new FormGroup({
    name:new FormControl("",Validators.required),
    dob:new FormControl("",Validators.required),
    pan: new FormControl("",Validators.required),
    pensionType: new FormControl("",Validators.required),
    

  })

  ngOnInit(): void {
  }

}
