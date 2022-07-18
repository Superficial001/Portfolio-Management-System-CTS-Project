import { TestBed } from '@angular/core/testing';

import { CalculateNetworthService } from './calculate-networth.service';

describe('CalculateNetworthService', () => {
  let service: CalculateNetworthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CalculateNetworthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
