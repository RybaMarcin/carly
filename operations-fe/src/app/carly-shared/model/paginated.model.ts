export interface Sort {
  sorted: boolean;
  unsorted: boolean;
}

export interface Pagination {
  offset: number;
  pageNumber: number;
  pageSize: number;
  paged: boolean;
  sort: Sort;
  unpaged: boolean;
}

export interface Page<Model> {
  content: Model[];
  first: boolean;
  last: boolean;
  number: number;
  numberOfElements: number;
  pageable: Pagination;
  size: number;
  sort: Sort;
  totalElements: number;
  totalPages: number;
}
