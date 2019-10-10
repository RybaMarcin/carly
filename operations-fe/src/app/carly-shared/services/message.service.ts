import { Injectable } from '@angular/core';
import {MatSnackBar} from '@angular/material';

/**
 * Messaging service to display user a message
 * */
@Injectable()
export class MessageService {

  constructor(private snackBar: MatSnackBar) {
  }

  showMessage(message: string, duration: number = 8000) {
    this.snackBar.open(message, 'close', {
      duration: duration,
      horizontalPosition: 'left',
      verticalPosition: 'top'
    })
  }

}
