import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingReimbControllerComponent } from './pending-reimb-controller.component';

describe('PendingReimbControllerComponent', () => {
  let component: PendingReimbControllerComponent;
  let fixture: ComponentFixture<PendingReimbControllerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PendingReimbControllerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PendingReimbControllerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
