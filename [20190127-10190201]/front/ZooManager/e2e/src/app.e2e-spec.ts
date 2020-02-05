import { AppPage } from './app.po';
import { browser, logging } from 'protractor';

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
    browser.sleep(2000);
    expect(page.getListComponentTitle()).toEqual('liste des animaux');
    page.getLinkToClick("Liste Zoos").click();
    browser.sleep(2000);
    expect(page.getListComponentTitle()).toEqual('liste des zoos');
  });



  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
