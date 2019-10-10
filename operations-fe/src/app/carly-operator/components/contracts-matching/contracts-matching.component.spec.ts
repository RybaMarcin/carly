import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContractsMatchingComponent } from './contracts-matching.component';

describe('ContractsMatchingComponent', () => {
  let component: ContractsMatchingComponent;
  let fixture: ComponentFixture<ContractsMatchingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContractsMatchingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContractsMatchingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
