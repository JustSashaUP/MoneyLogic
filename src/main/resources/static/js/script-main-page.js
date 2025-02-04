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

async function loadAccounts() {
    $.ajax(
        {
          url: "/profile/accounts/list",
          type: "GET",
          success: function (data) {
            let content = `<ul class="tabs__list" data-js-content>`
            data.forEach(account => {
              content += `<li class="tabs__item tabs__item--active" data-account-id="${account.id}">
                            <div class="">
                                <h4 class="">
                                    ${account.name}
                                </h4>
                                <span>Balance: ${account.balance}💰</span>
                            </div>
                        </li>`
            });
            content += `</ul>`;
            $("#main-header").html("Accounts");
            $("#content").html(content);

            $(".tabs__item").on("click", function () {
                let accountId = $(this).data("account-id");
                switchAccount(accountId);
            });
          },
          error: function(xhr, status, error) {
            console.error("Error loading accounts: " + error);
          }
        }
      );
}

async function switchAccount(accountId) {
    $.ajax(
        {
            url: `/profile/accounts/switch-account/${accountId}`,
            type: "POST",
            success: function (response) {
                console.log(response);
            },
            error: function(xhr, status, error) {
                console.error("Error switching accounts: " + error);
            }
        }
    )
}

async function loadIncomeCategories() {
    $.ajax(
        {
            url: "/profile/categories/max-income",
            type: "GET",
            success: function (data) {
                let content = `<ul class="tabs__list" data-js-content>`
                data.forEach(categoryMaxAmount => {
                    content += `<li class="tabs__item tabs__item--active">
                            <div class="">
                                <h4 class="">
                                ${categoryMaxAmount.category}
                                </h4>
                                <span></span>
                                <span>${categoryMaxAmount.maxAmount}</span>
                            </div>
                        </li>`
                });
                content += `</ul>`;
                header = ``;
                $("#main-header").html(header);
                $("#content").html(content);
            },
            error: function(xhr, status, error) {
                console.error("Error loading accounts: " + error);
            }
        }
    );
}