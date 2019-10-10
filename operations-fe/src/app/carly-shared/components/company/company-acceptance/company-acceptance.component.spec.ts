import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyAcceptanceComponent } from './company-acceptance.component';

describe('CompanyAcceptanceComponent', () => {
  let component: CompanyAcceptanceComponent;
  let fixture: ComponentFixture<CompanyAcceptanceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompanyAcceptanceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompanyAcceptanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
