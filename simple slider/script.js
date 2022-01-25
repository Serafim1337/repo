const leftButton = document.querySelector('#left');
const rightButton = document.querySelector('#right');
const imageList = document.querySelector('#list');
const width = 130;
const counter = 3;
let position = 0;

rightButton.addEventListener('click', rightButtonHandler);
leftButton.addEventListener('click', leftButtonHandler);
imageList.style.transition = 'ease 1s';

function rightButtonHandler(event) {
  position -= width * counter;
  position = Math.max(position, -width * (list.children.length - counter));
  imageList.style.transform = `translateX(${position}px)`
}

function leftButtonHandler(event) {
  position += width * counter;
  position = Math.min(position, 0);
  imageList.style.transform = `translateX(${position}px)`
}
