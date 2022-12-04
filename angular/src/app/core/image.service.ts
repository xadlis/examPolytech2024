import { Injectable } from '@angular/core';
import { DomSanitizer, SafeStyle } from '@angular/platform-browser';
import { environment } from './../../environments/environment';

@Injectable()
export class ImageService {

  constructor( private _sanitizer: DomSanitizer) {}

  getImageUrl(source: string): SafeStyle {
    const sourceWithHost = `${environment.backendUrl}${source}`;
    return this._sanitizer.bypassSecurityTrustStyle(`url(${sourceWithHost})`);
  }

}
