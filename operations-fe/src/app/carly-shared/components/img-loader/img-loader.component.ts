import {Component, HostBinding, Input, OnInit} from '@angular/core';
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";
import {FileManagementService} from "../../resources/file-management.service";
import {map} from "rxjs/operators";

@Component({
  selector: 'img-loader',
  templateUrl: './img-loader.component.html',
  styleUrls: ['./img-loader.component.scss']
})
export class ImgLoaderComponent implements OnInit {

  @Input() imgId: string;
  @HostBinding('class.icon-shape')
  @Input() iconShape: boolean = true;
  imgSrc: SafeUrl;

  constructor(
    private domSanitizer: DomSanitizer,
    private fileService: FileManagementService
  ) {
  }

  ngOnInit() {
    this.fileService.downloadSecuredFile(this.imgId)
      .pipe(map(data => this.createImageFromBlob(data)))
      .subscribe(imgSrc => this.imgSrc = imgSrc);
  }

  createImageFromBlob(image: Blob): SafeUrl {
    return this.domSanitizer.bypassSecurityTrustUrl(URL.createObjectURL(image));
  }

}
