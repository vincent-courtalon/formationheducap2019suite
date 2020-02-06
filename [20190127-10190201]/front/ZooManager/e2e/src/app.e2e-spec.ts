import { AppPage } from './app.po';
import { browser, logging, element, by } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display company name in navbar', () => {
    page.navigateTo();
    //browser.pause(5000);
    expect(page.getNavBarCompanyName()).toEqual('Zoo manager');
  });

  it('should by default go to zoo list component', () => {
    page.navigateTo();
    //browser.sleep(10000);
    expect(page.getListComponentTitle()).toEqual('liste des zoos');
  });

  it('should go to animaux list component when clicking on router link', () => {
    page.navigateTo();
    //browser.sleep(10000);
    page.getLinkToClick("Liste Animaux").click();
    //browser.sleep(100000);
    expect(page.getListComponentTitle()).toEqual('liste des animaux');
    page.getLinkToClick("Liste Zoos").click();
    browser.sleep(2000);
    expect(page.getListComponentTitle()).toEqual('liste des zoos');
  });


  it('shoul display 2 page available on pagination when displaying animals, and be on page 1', () => {
    page.navigateTo();
    page.getLinkToClick("Liste Animaux").click();
    // je suis sur la page animaux
    browser.sleep(1000);
    var pageitems = element.all(by.css('ul.pagination li.pagination-page.page-item'));
    // je devrais avoir deux pages dans mon composant pagination
    expect(pageitems.count()).toEqual(2);
    var activeItemLink = element(by.css('ul.pagination li.pagination-page.page-item.active a.page-link'));
    expect(activeItemLink.getText()).toEqual("1");
  });

  it ('should display second page of animals when clicked on link to second page of pagination', () => {
    page.navigateTo();
    page.getLinkToClick("Liste Animaux").click();
    // je suis sur la page animaux
    browser.sleep(1000);
    var pageitemslink = element.all(by.css('ul.pagination li.pagination-page.page-item a'));
    // je click sur la deuxieme page
    pageitemslink.get(1).click();
    browser.sleep(1000);
    // je verifie si nous somme bien sur la page 2 
    var activeItemLink = element(by.css('ul.pagination li.pagination-page.page-item.active a.page-link'));
    expect(activeItemLink.getText()).toEqual("2");
  });

  it('first page should contain 10 animals, and second page 2', () => {
    page.navigateTo();
    page.getLinkToClick("Liste Animaux").click();
    // je suis sur la page animaux
    browser.sleep(1000);
    var animalLines = element.all(by.css('table tbody tr')) ;
    expect(animalLines.count()).toEqual(10);
    var pageitemslink = element.all(by.css('ul.pagination li.pagination-page.page-item a'));

    pageitemslink.get(1).click();
    browser.sleep(1000);
    var animalLines = element.all(by.css('table tbody tr')) ;
    expect(animalLines.count()).toEqual(3);
  });

  it('animal without zoo shoul be displayed with "non affecté à un zoo"', () => {
    page.navigateTo();
    page.getLinkToClick("Liste Animaux").click();
    // je suis sur la page animaux
    browser.sleep(1000);
    var animalZoos = element.all(by.css('table tbody tr td:nth-child(3)'));
    expect(animalZoos.count()).toEqual(10);
    expect(animalZoos.get(5).getText()).toEqual("non affecté à un zoo");
  });


  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
