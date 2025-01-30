'use strict'

//===============MODALS=====================//
//===============MODALS=====================//

const registrationLinkElements = document.querySelectorAll('[data-js-registration-link]')
const registrationFormElement = document.querySelector('[data-js-registration-form]')

registrationLinkElements.forEach((link) => {
  link.addEventListener('click', () => {
    registrationFormElement.showModal()
  })
})

const loginLinkElement = document.querySelector('[data-js-login-link]')
const loginFormElement = document.querySelector('[data-js-login-form]')

// loginLinkElement.addEventListener('click', () => {
//   loginFormElement.showModal()
// })

//===============HIDING PASSWORD=====================//
//===============HIDING PASSWORD=====================//

const passwordHideButtonElements = document.querySelectorAll('[data-js-password-hide-button]');
const passwordInputElements = document.querySelectorAll('[data-js-password-input]');
const fieldPasswordWrapperElements = document.querySelectorAll('[data-js-field-password-wrapper]');

for (let i = 0; i < passwordHideButtonElements.length; i++) {
  passwordHideButtonElements[i].addEventListener('click', () => {
    const isPassword = passwordInputElements[i].type === 'password';

    if (isPassword) {
      passwordInputElements[i].type = 'text';
      fieldPasswordWrapperElements[i].style.setProperty('--line-opacity', '0');
    } else {
      passwordInputElements[i].type = 'password';
      fieldPasswordWrapperElements[i].style.setProperty('--line-opacity', '1');
    }
  });
}


//===============TABS=====================//
//===============TABS=====================//

const refs = {
  navigationsElement: document.querySelector('#tabs-home-page [data-js-navigation]'),
  totalElement: document.querySelector('#tabs-home-page [data-js-total]'),
  contentElement: document.querySelector('#tabs-home-page [data-js-content]'),
}

refs.navigationsElement.addEventListener('click', onChangeNavigation)

function onChangeNavigation(event) {

  const {target} = event 

  if (target.nodeName !== 'BUTTON') return

  const currentButtonElement = target

  clearPrevClasses()
  addCurrentClass(currentButtonElement)
}

function clearPrevClasses() {
  const prevActiveButtonElement = refs.navigationsElement.querySelector('.button--active')
  const prevActiveTotalElement = refs.totalElement.querySelector('.tabs__total-item--active')
  const prevActiveContentElement = refs.contentElement.querySelector('.tabs__item--active')

  if (prevActiveButtonElement) {
    prevActiveButtonElement.classList.remove('button--active')
    prevActiveTotalElement.classList.remove('tabs__total-item--active')
    prevActiveContentElement.classList.remove('tabs__item--active')
  }
}

function addCurrentClass(currentButtonElement) {
  const currentTabElement = currentButtonElement.dataset.tab

  const currentTotalElement = refs.totalElement.querySelector(
    `[data-js-total=${currentTabElement}]`
  )

  const currentContentElement = refs.contentElement.querySelector(
    `[data-js-content=${currentTabElement}]`
  )

  currentButtonElement.classList.add('button--active')
  currentTotalElement.classList.add('tabs__total-item--active')
  currentContentElement.classList.add('tabs__item--active')
}
