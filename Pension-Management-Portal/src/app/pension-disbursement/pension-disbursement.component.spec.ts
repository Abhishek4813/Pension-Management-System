import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PensionDisbursementComponent } from './pension-disbursement.component';

describe('PensionDisbursementComponent', () => {
  let component: PensionDisbursementComponent;
  let fixture: ComponentFixture<PensionDisbursementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PensionDisbursementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PensionDisbursementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
