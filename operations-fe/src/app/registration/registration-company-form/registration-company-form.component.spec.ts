import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationCompanyFormComponent } from './registration-company-form.component';

describe('RegistrationCompanyFormComponent', () => {
  let component: RegistrationCompanyFormComponent;
  let fixture: ComponentFixture<RegistrationCompanyFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistrationCompanyFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationCompanyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
