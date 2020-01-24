import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({
  name: 'myunsafe'
})
export class MyunsafePipe implements PipeTransform {

  constructor(private sanitizer: DomSanitizer) {}

  transform(value: any, type:  string): any {
    switch(type) {
      case 'html' : return  this.sanitizer.bypassSecurityTrustHtml(value);
      case 'style' : return this.sanitizer.bypassSecurityTrustStyle(value);
      case 'script' : return this.sanitizer.bypassSecurityTrustScript(value);
    }
    return value;
 
    //return this.sanitizer.bypassSecurityTrustScript(value);
  }

}
