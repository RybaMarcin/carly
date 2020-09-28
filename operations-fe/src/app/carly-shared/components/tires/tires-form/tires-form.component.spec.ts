import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TiresFormComponent } from './tires-form.component';

describe('TiresFormComponent', () => {
  let component: TiresFormComponent;
  let fixture: ComponentFixture<TiresFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TiresFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TiresFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
