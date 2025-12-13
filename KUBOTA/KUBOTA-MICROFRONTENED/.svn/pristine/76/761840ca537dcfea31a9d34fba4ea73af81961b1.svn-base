import { Injectable } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";

import { LocalStorageService } from "../root-services/local-storage.service";
import * as CryptoJS from "crypto-js";

@Injectable()
export class EncryptDecryptService {
  private CIPHER_KEY_LEN = 16;
  private key = "thrsl@@@";
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
      return key.padEnd(this.CIPHER_KEY_LEN, "0");
    }
    if (key.length > this.CIPHER_KEY_LEN) {
      return key.substring(0, this.CIPHER_KEY_LEN);
    }
    return key;
  }

  private getIV(): string {
    const ivCHARS = "1234567890";
    let iv = "";
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

  // public decrypt(data: string): string {
  //   console.log(data);
  //   const sha = this.generateSha512Hash(this.key);
  //   const shaKey = sha.substring(0, 16);
  //   const fixedKey = this.fixKey(shaKey);

  //   const [encryptedDataInBase64, ivInBase64] = data.split(":");
  //   const ivSpec = CryptoJS.enc.Base64.parse(ivInBase64);
  //   const secretKey = CryptoJS.enc.Utf8.parse(fixedKey);

  //   const decrypted = CryptoJS.AES.decrypt(encryptedDataInBase64, secretKey, {
  //     iv: ivSpec,
  //     mode: CryptoJS.mode.CBC,
  //     padding: CryptoJS.pad.Pkcs7,
  //   });

  //   return decrypted.toString(CryptoJS.enc.Utf8);
  // }

  public decrypt(data: string): string {
    console.log("Encrypted data:", data);

    // Generate SHA-512 hash of the key
    const sha = this.generateSha512Hash(this.key);
    const shaKey = sha.substring(0, 16); // Take the first 16 characters (128-bit key)
    const fixedKey = this.fixKey(shaKey); // Assuming fixKey is a method that processes the key

    let encryptedDataInBase64 = data;
    let ivSpec: any = null;

    // Check if the string contains a colon (i.e., split into encrypted data and IV)
    if (data.includes(":")) {
      console.log("igggg");
      const [encryptedData, ivInBase64] = data.split(":");
      encryptedDataInBase64 = encryptedData; // Encrypted data without IV
      ivSpec = CryptoJS.enc.Base64.parse(ivInBase64); // IV parsed from Base64
    } else {
      console.log("else");
      // If no colon, use a default IV (e.g., 16-byte zero IV)
      ivSpec = CryptoJS.enc.Hex.parse("00000000000000000000000000000000"); // 16-byte IV of zeros
    }

    // Parse the key into a format suitable for AES decryption
    const secretKey = CryptoJS.enc.Utf8.parse(fixedKey);

    // Decrypt with or without IV depending on the presence of IV in the input
    const decrypted = CryptoJS.AES.decrypt(encryptedDataInBase64, secretKey, {
      iv: ivSpec,
      mode: CryptoJS.mode.CBC, // CBC mode
      padding: CryptoJS.pad.Pkcs7, // Pkcs7 padding
    });

    // Convert the decrypted data to a UTF-8 string
    console.log(decrypted, "decrypted");
    return decrypted.toString(CryptoJS.enc.Utf8);
  }
}
