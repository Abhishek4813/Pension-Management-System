import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PensionServiceService} from '../pension-service.service';
import {HttpClient,HttpHandler} from '@angular/common/http';
import { Observable } from 'rxjs';

import { PensionDisbursementComponent } from './pension-disbursement.component';

describe('PensionDisbursementComponent', () => {
  let component: PensionDisbursementComponent;
  let fixture: ComponentFixture<PensionDisbursementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PensionDisbursementComponent ],
      providers: [HttpClient, HttpHandler],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PensionDisbursementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });


  //testing if disbursement is successfull.

  it("Test pension disbursement component for a success",()=>{
    let mockService: jasmine.SpyObj<PensionServiceService>;
    mockService = jasmine.createSpyObj('PensionServiceService', ['disburse']);
    mockService.disburse.and.returnValue(new Observable((data)=>{
      data.next({pensionStatusCode:10});
      data.complete();
    }));
    let comp=new PensionDisbursementComponent(mockService);
    expect(comp.toggler).toBeFalse();
    expect(comp.loader).toBeTrue();
    expect(comp.disburse).toBeFalse();
    expect(comp.authFailed).toBeFalse();
    comp.doDisburse();
    expect(comp.loader).toBeFalse();
    expect(comp.disburse).toBeTrue();


  })

  //Testing if disburse is failed.

  it("Test pension disbursement component for a failure",()=>{
    let mockService: jasmine.SpyObj<PensionServiceService>;
    mockService = jasmine.createSpyObj('PensionServiceService', ['disburse']);
    mockService.disburse.and.returnValue(new Observable((data)=>{
      data.next({pensionStatusCode:21});
      data.complete();
    }));
    let comp=new PensionDisbursementComponent(mockService);
    expect(comp.toggler).toBeFalse();
    expect(comp.loader).toBeTrue();
    expect(comp.disburse).toBeFalse();
    expect(comp.authFailed).toBeFalse();
    comp.doDisburse();
    expect(comp.loader).toBeFalse();
    expect(comp.disburse).toBeFalse();
  })
});
