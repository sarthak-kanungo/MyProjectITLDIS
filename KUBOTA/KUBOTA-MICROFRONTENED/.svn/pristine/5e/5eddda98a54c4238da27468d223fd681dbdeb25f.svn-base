import { Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
 
import * as CryptoJS from 'crypto-js';
import { LocalStorageService } from '../root-service/local-storage.service';
 
@Injectable()
export class EncryptDecryptService {
  private CIPHER_KEY_LEN = 16;
  private key = 'thrsl@@@';
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private localStorage: LocalStorageService
  ) {}
 
  private generateSha512Hash(key: string): string {
    return CryptoJS.SHA512(key).toString();
  }
 
  private fixKey(key: string): string {
    if (key.length < this.CIPHER_KEY_LEN) {
      return key.padEnd(this.CIPHER_KEY_LEN, '0');
    }
    if (key.length > this.CIPHER_KEY_LEN) {
      return key.substring(0, this.CIPHER_KEY_LEN);
    }
    return key;
  }
 
  private getIV(): string {
    const ivCHARS = '1234567890';
    let iv = '';
    for (let i = 0; i < 16; i++) {
      iv += ivCHARS.charAt(Math.floor(Math.random() * ivCHARS.length));
    }
    return iv;
  }
 
  public encryptData(value: string): string {
    const sha = this.generateSha512Hash(this.key);
    const shaKey = sha.substring(0, 16);
    const fixedKey = this.fixKey(shaKey);
    const iv = this.getIV();
    const ivSpec = CryptoJS.enc.Utf8.parse(iv);
    const secretKey = CryptoJS.enc.Utf8.parse(fixedKey);
 
    const encrypted = CryptoJS.AES.encrypt(value, secretKey, {
      iv: ivSpec,
      mode: CryptoJS.mode.CBC,
      padding: CryptoJS.pad.Pkcs7,
    });
 
    const encryptedDataInBase64 = encrypted.ciphertext.toString(
      CryptoJS.enc.Base64
    );
    const ivInBase64 = CryptoJS.enc.Base64.stringify(ivSpec);
 
    return `${encryptedDataInBase64}:${ivInBase64}`;
  }
 
  public decrypt(data: string): string {
    const sha = this.generateSha512Hash(this.key);
    const shaKey = sha.substring(0, 16);
    const fixedKey = this.fixKey(shaKey);
 
    const [encryptedDataInBase64, ivInBase64] = data.split(':');
    const ivSpec = CryptoJS.enc.Base64.parse(ivInBase64);
    const secretKey = CryptoJS.enc.Utf8.parse(fixedKey);
 
    const decrypted = CryptoJS.AES.decrypt(encryptedDataInBase64, secretKey, {
      iv: ivSpec,
      mode: CryptoJS.mode.CBC,
      padding: CryptoJS.pad.Pkcs7,
    });
 
    return decrypted.toString(CryptoJS.enc.Utf8);
  }
}