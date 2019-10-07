import {DataSource} from '@angular/cdk/table';
import {HttpParams} from "@angular/common/http";
import {MatPaginator, PageEvent} from '@angular/material';
import {merge, Observable, Subject} from "rxjs";
import {MessageService} from "../../services/message.service";
import {catchError, map, startWith, switchMap} from "rxjs/operators";
import {Page} from "../../model/paginated.model";
import {Moment} from 'moment';
import {HttpParamsUtils} from '../http/http-params-utils';



/**
 * Abstract datasource with generic type of displayed data
 * */
export abstract class AbstractCarlyDataSource<T> extends DataSource<T>{

  protected static PAGE_PARAM = 'page';
  protected static SIZE_PARAM = 'size';
  protected params = new HttpParams();
  max = 0;
  loading = true;
  paginator: MatPaginator;
  private trigger = new Subject();
  dataToDisplay = [];

  protected constructor(
    private messageService: MessageService
  ) {
    super();
  }

  /**
   * Refresh data in table by calling loadPage
   *
   * @returns data to display in table
   * */
  protected observePage() {
    return merge(this.trigger, this.paginator.page).pipe(
      startWith(null),
      switchMap((paginator: PageEvent) => {
        this.loading = true;
        let params = this.params;
        const pageIndex = String(paginator ? paginator.pageIndex : this.paginator ? this.paginator.pageIndex : 0);
        const pageSize = String(paginator ? paginator.pageSize : this.paginator ? this.paginator.pageSize : 3);
        params = params.set(AbstractCarlyDataSource.PAGE_PARAM, pageIndex);
        params = params.set(AbstractCarlyDataSource.SIZE_PARAM, pageSize);
        return this.loadPage(params);
      }),
      map(result => {
        this.loading = false;
        if (result == null || result.totalElements === 0) {
          return [];
        }
        this.dataToDisplay = result.content;
        this.max = result.totalElements;
        return result.content;
      }),
      catchError(err => {
        this.loading = false;
        this.max = 0;
        this.messageService.showMessage('Connection problem');
        throw err;
      })
    );
  }

  /**
   * Loading data to table
   *
   * @returns a page of data with generic type
   * */
  protected abstract loadPage(params: HttpParams): Observable<Page<T>>;

  reloadTable() {
    this.reload(this.params);
  }

  reload(params: HttpParams, resetPage = true) {
    if (resetPage) {
      this.paginator.pageIndex = 0;
    }
    this.params = params;
    this.trigger.next();
  }

  addHttpDateParam(key: string, value: Moment) {
    this.params = HttpParamsUtils.appendDateTimeParam(this.params, key, value);
  }

  addHttpParam(key: string, value: string) {
    this.params = this.params.append(key, value);
  }

  disconnect() {
  }

}

