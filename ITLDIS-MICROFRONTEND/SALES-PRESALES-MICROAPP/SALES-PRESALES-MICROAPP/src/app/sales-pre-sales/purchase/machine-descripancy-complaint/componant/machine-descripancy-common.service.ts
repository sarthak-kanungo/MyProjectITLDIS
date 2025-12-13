
import { Injectable, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ViewEditMachineDescComplaintDomain } from 'MachineDescripancyComplaintModule';
import { UploadableFile } from 'itldis-file-upload';

export class MachineDescripancyCommonService {
  onChaisisNumber: EventEmitter<string> = new EventEmitter<string>()
  complaintDetailsForm : FormGroup

  emComplaintDetails = new EventEmitter<ViewEditMachineDescComplaintDomain>()
  files: Array<UploadableFile> = new Array<UploadableFile>()
  constructor() { }

}
