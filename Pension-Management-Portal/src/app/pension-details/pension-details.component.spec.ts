import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PensionServiceService} from '../pension-service.service';
import {HttpClient,HttpHandler} from '@angular/common/http';
import { Observable } from 'rxjs';

import { PensionDetailsComponent } from './pension-details.component';

describe('PensionDetailsComponent', () => {
  let component: PensionDetailsComponent;
  let fixture: ComponentFixture<PensionDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PensionDetailsComponent ],
      providers: [HttpClient, HttpHandler],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PensionDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  //Testing what if Valid data is provided for verification

  it("test Pesnsion detail component with valid detail",()=>{
  let mockService: jasmine.SpyObj<PensionServiceService>;
   mockService = jasmine.createSpyObj('PensionServiceService', ['getPensionDetails']);
    mockService.getPensionDetails.and.returnValue(new Observable((data)=>{
      data.next({ "name" : "padmin", "dateOfBirth" : "2000-08-30", "pan" : "PCASD1234Q", "aadharNumber" : 102233445566, "pensionType" : "Family" });
      data.complete();
    }));
    let comp=new PensionDetailsComponent(mockService);
    expect(comp.loader).toBeFalse();
    expect(comp.alert).toBeFalse();
    expect(comp.authFailed).toBeFalse();
    expect(comp.toggler).toBeTrue();
    comp.submitDetails();
    expect(comp.toggler).toBeFalse();
  })

  //Testing what if invalid data is provided for verification

  it("test Pesnsion detail component with invalid detail",()=>{
    let mockService: jasmine.SpyObj<PensionServiceService>;
     mockService = jasmine.createSpyObj('PensionServiceService', ['getPensionDetails']);
      mockService.getPensionDetails.and.returnValue(new Observable((data)=>{
        data.next({ "status":"NOT_FOUND" });
        data.complete();
      }));
      let comp=new PensionDetailsComponent(mockService);
      expect(comp.loader).toBeFalse();
      expect(comp.alert).toBeFalse();
      expect(comp.authFailed).toBeFalse();
      expect(comp.toggler).toBeTrue();
      comp.submitDetails();
      expect(comp.toggler).toBeTrue();
      expect(comp.alert).toBeTrue();
    })
});
