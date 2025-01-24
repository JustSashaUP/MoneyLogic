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

loginLinkElement.addEventListener('click', () => {
  loginFormElement.showModal()
})

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

