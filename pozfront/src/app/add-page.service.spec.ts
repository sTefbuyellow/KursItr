import { TestBed } from '@angular/core/testing';

import { AddPageService } from './add-page.service';

describe('AddPageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddPageService = TestBed.get(AddPageService);
    expect(service).toBeTruthy();
  });
});
