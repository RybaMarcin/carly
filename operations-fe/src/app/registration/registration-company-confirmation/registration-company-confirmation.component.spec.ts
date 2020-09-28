import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationCompanyConfirmationComponent } from './registration-company-confirmation.component';

describe('RegistrationCompanyConfirmationComponent', () => {
  let component: RegistrationCompanyConfirmationComponent;
  let fixture: ComponentFixture<RegistrationCompanyConfirmationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistrationCompanyConfirmationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationCompanyConfirmationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
