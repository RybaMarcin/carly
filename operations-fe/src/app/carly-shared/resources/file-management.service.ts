import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {UserManagementService} from "./user-management.service";
import {EMPTY, from, Observable} from "rxjs";
import {AccessLevel, CarlyFile, UserType} from "../model/carly-file.model";
import {mergeMap} from "rxjs/operators";
import {Roles} from "../model/roles.model";

declare const BASE_API_URL;

@Injectable()
export class FileManagementService {

  fileManagementApi = `${BASE_API_URL}/file-management`;

  constructor(
    private http: HttpClient,
    private userService: UserManagementService
  ) {
  }

  uploadFile(file: File, fileOwnerToAssignType: UserType, fileOwnerToAssignId: string, fileAccess: AccessLevel = AccessLevel.PUBLIC,
             customUserType: UserType = null, customUserId: string = null): Observable<CarlyFile> {

      return from(this.userService.getUserContext()).pipe(mergeMap(user => {
        const fd = new FormData();
        fd.append('file', file);
        fd.append('accessLevel', fileAccess);

        if(user.role === Roles.CARLY_OPERATOR && fileOwnerToAssignType) {
          fd.append('userType', fileOwnerToAssignType);
          if (fileOwnerToAssignId) {
            fd.append('userId', fileOwnerToAssignId);
          }
        }

        if (fileAccess == AccessLevel.CUSTOM) {
          fd.append('customUserType', customUserType);
          fd.append('customUserId', customUserId);
        }
        return this.http.post<CarlyFile>(`${this.fileManagementApi}/file`, fd);
      }));
  }

  downloadSecuredFile(fileId: string): Observable<Blob> {
    if (!fileId) {
      return EMPTY;
    }
    let params = new HttpParams();
    params = params.append('fileId', fileId);
    return this.http.get(`${this.fileManagementApi}/file`, {responseType: 'blob', params});
  }

  getFileWithPopup(file: CarlyFile) {
    this.getFileWithPopupByIdAndName(file.id, file.name);
  }

  getFileWithPopupByIdAndName(fileId: string, fileName: string) {
    this.downloadSecuredFile(fileId).subscribe(blob => this.downloadFile(blob, fileName));
  }

  private downloadFile(blob: Blob, name: string) {
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = name;
    document.body.appendChild(a);
    a.click();
    a.remove();
  }

}
