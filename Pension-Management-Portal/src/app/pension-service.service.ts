import { Injectable } from '@angular/core';
import { HttpClient, JsonpClientBackend } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PensionServiceService {

  //base url
  private _url="http://localhost:8090/";
  constructor(private _http:HttpClient) { }

  //serice method for login
  getLogedIn(credential:any){
    return this._http.post<any>(this._url+"auth/authenticate",credential);
  }

  //service method for pension Detail Validation
  getPensionDetails(data:any){
    
    //getting token from session 
    const token=sessionStorage.getItem("token");

    //passing token to header
    const header = (token) ? { Authorization: `Bearer ${token}` } : undefined;
    

    //http request for login
    return this._http.post<any>(this._url+"process/PensionDetail",{name:data.name,
            dateOfBirth:data.dateOfBirth,
            pan: data.pan,
            aadharNumber:Number(data.aadharNumber),
            pensionType:data.pensionType},{
              headers:header 
            })
  }

  //service Method for Pension Disbursement.
  disburse(data:any){
    //getting token from session 
    const token= sessionStorage.getItem("token");

    //passing token to header
    const header = (token) ? { Authorization: `Bearer ${token}` } : undefined;
    
    //http reuest for pension Deisbursement
    return this._http.post<any>(this._url+"process/ProcessPension",data,{
      headers:header
    })
  }
}
