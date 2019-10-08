import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyContextComponent } from './company-context.component';

describe('CompanyContextComponent', () => {
  let component: CompanyContextComponent;
  let fixture: ComponentFixture<CompanyContextComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompanyContextComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompanyContextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
