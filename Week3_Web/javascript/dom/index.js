console.log(document.querySelector('meta'));
console.log(document.querySelectorAll('meta'));

console.log(document.querySelectorAll('p'));
console.log(document.querySelectorAll('main>section>div>p'));

const p = document.querySelector('main>section>div>p');

// setTimeout(() => p.remove(), 2500);

const div = document.querySelector('main>section>div');

const h1Elem = document.createElement('h1');
h1Elem.innerText = 'JavaScript DOM Manipulation';
// div.appendChild(h1Elem);
div.prepend(h1Elem);

const contactList = document.querySelector('#contacts-list');
let contacts = [];

const button = document.querySelector('button');
console.log(button);
// button.onclick = () => console.log(this); // don't use it
// button.addEventListener('click', () => console.log(this));
// button.addEventListener('click', function () {
//   console.log(this);
// });
// button.addEventListener('mouseenter', (event) => console.log(event));
// button.addEventListener('mouseleave', () => console.log('exit'), true);
button.addEventListener('click', () => {
  fetch('http://localhost:8080/java-web/api/hello')
    .then((resp) => resp.json())
    .then((json) => {
      contacts = json;
      contacts.forEach((elem) => {
        const li = document.createElement('li');
        li.innerHTML = `<span class="list-item-content">${elem}</span>`;
        li.classList.add('list-item', 'emphasize');
        contactList.appendChild(li);
      });
    });
});
