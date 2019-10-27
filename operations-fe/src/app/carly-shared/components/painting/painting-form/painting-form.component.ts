import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {PartFormAction} from "../../../model/part-form-action.model";
import {FormGroup} from "@angular/forms";
import {ValueLabel} from "../../../model/value-label";
import {MessageService} from "../../../services/message.service";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {Router} from "@angular/router";
import {Painting} from "../../../model/painting.model";
import {paintingDetailsFormFields, paintingPreviews} from "./painting-form-fields";
import {PaintingManagementService} from "../../../resources/painting-management.service";

@Component({
  selector: 'painting-form',
  templateUrl: './painting-form.component.html',
  styleUrls: ['./painting-form.component.scss',
    '../../../../carly-shared/styles/side-nav.scss',
    '../../../../carly-shared/styles/buttons.scss']
})
export class PaintingFormComponent implements OnInit {

  @Input() isDisabled = false;
  @Input() formAction: PartFormAction;
  @Input() painting: Painting.Model;
  @Input() isRequest = false;
  @Input() edit = false;
  @Input() submitEvent: EventEmitter<Painting.Model> = new EventEmitter();
  @Input() details = false;

  paintingDetailsForm: FormGroup;
  paintingDetailsFormControls = this.fgService.addControlToModel(paintingDetailsFormFields)
    .map(controlModel => {
      if(controlModel.inputName === 'preview') {
        controlModel.selectOptions = paintingPreviews;
      }
      return controlModel;
    });

  paintingPreviews: Array<ValueLabel>;

  constructor(
    private messageService: MessageService,
    private fgService: FormGroupHelperService,
    private router: Router,
    private paintingService: PaintingManagementService
  ) {
  }

  ngOnInit() {

    this.paintingPreviews = paintingPreviews;

  }


  onSubmit($event) {

    this.paintingDetailsForm = $event;

    const painting: Painting.Model = {
      ...this.paintingDetailsForm.value
    };

    this.createOrUpdateWheels(painting);

  }


  createOrUpdateWheels(painting: Painting.Model) {
    let partAction;

    if(this.formAction !== PartFormAction.EDIT) {

      //todo: Implement createdDate here?

      partAction = this.paintingService.createPainting(painting);
    } else {
      partAction = this.paintingService.updatePainting(this.supplyWheelsInfo(painting));
    }

    partAction.subscribe(data => {
        this.messageService.showMessage('Painting created!');
        this.submitEvent.emit(painting);
        this.router.navigate(['/parts/painting', 'details', data.id, 'edit']);
      },
      error => console.log(error));
  }


  supplyWheelsInfo(painting: Painting.Model): Painting.Model {
    return {
      ...painting
    }
  }

}
