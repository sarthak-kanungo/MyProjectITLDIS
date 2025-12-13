import { CamelCaseToRegularStringPipe } from './camel-case-to-regular-string.pipe';

describe('CamelCaseToRegularStringPipe', () => {
  it('create an instance', () => {
    const pipe = new CamelCaseToRegularStringPipe();
    expect(pipe).toBeTruthy();
  });
});
