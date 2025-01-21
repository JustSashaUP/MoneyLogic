const registrationLinkElements = document.querySelectorAll('[data-js-registration-link]')
const registrationFormElement = document.querySelector('[data-js-registration-form]')

console.log(registrationLinkElements)

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