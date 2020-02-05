import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.get(browser.baseUrl) as Promise<any>;
  }

  getNavBarCompanyName() {
    return element(by.css('div.d-flex h5')).getText() as Promise<string>;
  }

  getListComponentTitle() {
    return element(by.css('div.container-fluid h2')).getText() as Promise<string>;
  }

  getLinkToClick(texte : string) 
  {
    return element(by.linkText(texte));
  }
}
