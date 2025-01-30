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