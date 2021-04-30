// console.log('JavaScript is awesome');
// var x = 10;
// console.log(x);

// x = 'Ten';
// console.log(x);

// var z = 100;
// const a = 100; //es6
// let b = 100; //es6

// const p = [1, 2, 3, 4];

// for (let i in p) {
//   console.log(i);
// }

// for (let i of p) {
//   console.log(i);
// }

// const august = { name: 'August', age: 38 };

// for (let i in august) {
//   console.log(i);
// }

// // for (let i of august) {
// //   console.log(i);
// // }

// // function Person() {}
// // function Student() {}
// // Student.prototype = Object.create(Person.prototype);
// // Student.prototype.turnInHomework = function() {}

// // var h = new Person();

// class Person {}
// class Student extends Person {
//   turnInHomework() {}
// }

// // function sum(x, y) {
// //   console.log(x);
// //   let z = 8;
// //   console.log(z);
// //   return x + y;
// // }

// function example() {
//   // var a declaration hoisted here
//   a = 4;
//   var a;
//   a++;
//   console.log(a); // prints 5
// }

// const bob = {};
// const ten = 10;
// bob.name = 'Bob';
// // bob = {};
// // ten = 11;

// function sum(x, y) {
//   var name = 'August';
//   if (y !== undefined) {
//     return x + y;
//   }
//   return function (z) {
//     console.log(name);
//     return x + z;
//   };
// }

// const message = (function () {
//   return 'Hi';
// })();

// console.log(message);

const TownModule = (function () {
  class Mayor {}
  class Citizen {}
  class PoliceOfficer {}
  class Doctor {}
  return {
    Mayor,
    Citizen,
    PoliceOfficer,
    Doctor,
  };
})();

// const august = {name: 'August', work: () => {}}
// const august = new Object();
// august.name = 'August';
// august.work = () => {};
// august.work();
// sum(2,2);

class Person {
  #name;
  #age;
  constructor(name, age) {
    this.#name = name;
    this.#age = age;
  }

  eat() {
    console.log('Yummy!');
  }

  watchCollegeFootball() {
    console.log('ZZZZZZZ');
  }

  get name() {
    return this.#name;
  }
  set name(name) {
    this.#name = name;
  }

  static sayHello() {
    console.log('Hello');
  }
}

class Student extends Person {
  #senority;

  constructor(name, age, senority) {
    super(name, age);
    this.#senority = senority;
  }
}

const me = new Person('August', 38);
const jesse = new Person();
const davidH = new Person(true);

console.log(me);
console.log(jesse);
console.log(davidH);
