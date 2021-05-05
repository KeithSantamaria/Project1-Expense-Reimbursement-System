console.log('Hello, Typescript');

class Person {}
class Student extends Person {}

const sum = (x: number, y?: number): number | ((z: number) => number) => {
  if (y !== undefined) {
    return x + y;
  }
  return function (z: number) {
    return z + x;
  };
};

//@ts-ignore
sum(10)(10);
