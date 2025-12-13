import { Component, OnInit, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { urlService } from '../../../../../webservice-config/baseurl';
import { environment } from '../../../../../../environments/environment.prod';
import { NgxImageCompressService } from 'ngx-image-compress';
import { PurchaseOrderCreateService } from '../../pages/purchase-order-create/purchase-order-create.service';
import { FileUploadService } from 'src/app/ui/file-upload/file-upload.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-document-upload',
  templateUrl: './document-upload.component.html',
  styleUrls: ['./document-upload.component.scss']
})
export class DocumentUploadComponent implements OnInit {
  public imageBaseUrl = `${environment.baseUrl}${urlService.api}/files/`;
  @Input('previewUrl') public previewUrl: string | ArrayBuffer;           // Url for preview image 
  @Input('previewTitle') public previewTitle: string;                     // Title of previewed image in box
  @Input('disabled') public disabled: boolean = false;                    // Disable 'choose file' upload button
  @Input('documentFormControl') public documentFormControl: FormControl;  // Formcontrol for validation
  @Input('isEdit') public isEdit: boolean = false;                        // Show download button for edit if image available
  @Input('documentName') public documentName: string = '';                // Name of 'choose file' button
  @Input('extension') public extension: string = '';
  public selectedFile: File;


  constructor(private imageCompress: NgxImageCompressService, 
    private purchaseOrderCreateService:PurchaseOrderCreateService,
    private fileUploadService: FileUploadService,
    private toasterService: ToastrService) { }
  localUrl: any;
  localCompressedURl: any;
  sizeOfOriginalImage: number;
  sizeOFCompressedImage: number;
  previewType: any;
  tpyeFlag: boolean;
  fileSelectUPload: boolean = false;
  ngOnInit() {
    this.fileSelectUPload = false;
  }

  public fileSelectionChanges(event, documentName) {
    const msg = this.fileUploadService.validateFileSize(event.target['files'][0],"PO")
		if(msg != 'OK'){
			this.toasterService.warning(msg);
			return false;
		}
    this.fileSelectUPload = true;
    this.tpyeFlag = false;
    if (event['target']['files'] && event['target']['files'][0]) {
      var reader = new FileReader();
      this.previewTitle = event['target']['files'][0]['name'];
      this.previewType = event['target']['files'][0]['type'];
      this.selectedFile = event['target']['files'][0];
      console.warn('Size is now:', this.selectedFile.size);
      
      this.purchaseOrderCreateService.fileSelectionSubject.next({documentName:documentName,title:this.previewTitle});
      
      if (this.previewType == "application/pdf") {
        this.tpyeFlag = true;
        reader.onload = async (ev) => {
          this.previewUrl = ev.target['result'];
          this.documentFormControl.patchValue(this.selectedFile)
          this.localUrl = this.previewUrl && !this.selectedFile ? this.imageBaseUrl + this.previewUrl : this.previewUrl;
        }
        reader.readAsDataURL(event['target']['files'][0]);
      }
      else {
        this.tpyeFlag = false;
        reader.onload = async (ev) => {
          this.previewUrl = ev.target['result'];
          // this.documentFormControl.patchValue(this.selectedFile)
          this.localUrl = this.previewUrl && !this.selectedFile ? this.imageBaseUrl + this.previewUrl : this.previewUrl;
          await this.compressFile(this.localUrl, this.previewTitle, this.previewType)
          this.documentFormControl.patchValue(this.selectedFile)
        }
        // reader.readAsDataURL(event['target']['files'][0]);
        reader.readAsDataURL(this.selectedFile);
      }
    }
  }
  async compressFile(image, fileName, type) {
    var orientation = -1;
    this.sizeOfOriginalImage = this.imageCompress.byteCount(image) / (1024 * 1024);
    console.warn('Size in bytes is now:', this.sizeOfOriginalImage);
    await this.imageCompress.compressFile(image, orientation, 50, 50).then(
      result => {
        this.localCompressedURl = result;
        this.sizeOFCompressedImage = this.imageCompress.byteCount(result) / (1024 * 1024)
        console.warn('Size in bytes after compression:', this.sizeOFCompressedImage);
        // call method that creates a blob from dataUri
        const imageBlob = this.dataURItoBlob(this.localCompressedURl.split(',')[1]);
        const imageName = fileName;
        this.selectedFile = new File([imageBlob], imageName, { type: type });
        this.previewTitle = this.selectedFile.name;
        this.previewType = this.selectedFile.type;
        console.warn('Size is after compression:', this.selectedFile.size);
      });
  }
  dataURItoBlob(dataURI) {
    const byteString = window.atob(dataURI);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);
    for (let i = 0; i < byteString.length; i++) {
      int8Array[i] = byteString.charCodeAt(i);
    }
    const blob = new Blob([int8Array], { type: 'image/jpeg' });
    return blob;
  }
  // public fileSelectionChanges(event) {
  //   if (event['target']['files'] && event['target']['files'][0]) {
  //     var reader = new FileReader();
  //     this.previewTitle = event['target']['files'][0]['name'];
  //     this.selectedFile = event['target']['files'][0];
  //     reader.onload = (ev) => {
  //       this.previewUrl = ev.target['result'];
  //       this.documentFormControl.patchValue(this.selectedFile)
  //     }
  //     reader.readAsDataURL(event['target']['files'][0]);
  //   }
  // }
  public deleteImage(documentName) {
    this.documentFormControl.reset();
    this.selectedFile = null;
    this.previewTitle = "";
    this.previewUrl = "";
    this.purchaseOrderCreateService.fileDeleteSubject.next(documentName)
  }
}