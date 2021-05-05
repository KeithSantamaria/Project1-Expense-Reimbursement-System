"use strict";
console.log('Hello, Typescript');
class Person {
}
class Student extends Person {
}
const sum = (x, y) => {
    if (y !== undefined) {
        return x + y;
    }
    return function (z) {
        return z + x;
    };
};
sum(10)(10);
