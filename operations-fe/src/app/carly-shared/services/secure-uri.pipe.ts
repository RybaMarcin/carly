import { Pipe, PipeTransform } from '@angular/core';
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";
import {FileManagementService} from "../resources/file-management.service";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

@Pipe({
  name: 'SecureUri'
})
export class SecureUriPipe implements PipeTransform {

  constructor(
    private domSanitizer: DomSanitizer,
    private fileService: FileManagementService
  ) {
  }

  transform(id: string = ''): Observable<SafeUrl> {
    return this.fileService.downloadSecuredFile(id).pipe(map(data => this.createImageFromBlob(data)));
  }

  createImageFromBlob(image: Blob): SafeUrl {
    return this.domSanitizer.bypassSecurityTrustUrl(URL.createObjectURL(image));
  }

}
