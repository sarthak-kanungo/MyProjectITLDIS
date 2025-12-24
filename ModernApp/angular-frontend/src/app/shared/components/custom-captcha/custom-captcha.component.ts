import { Component, OnInit, signal, computed, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

export type CaptchaType = 'math' | 'string';

export interface CaptchaChallenge {
  type: CaptchaType;
  question: string;
  answer: string;
  hint?: string;
}

@Component({
  selector: 'app-custom-captcha',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule
  ],
  templateUrl: './custom-captcha.component.html',
  styleUrls: ['./custom-captcha.component.scss']
})
export class CustomCaptchaComponent implements OnInit {
  @Output() verifiedEvent = new EventEmitter<CustomCaptchaComponent>();
  
  answerControl = new FormControl('', [Validators.required]);
  currentChallenge = signal<CaptchaChallenge | null>(null);
  isVerified = signal<boolean>(false);
  isExpired = signal<boolean>(false);
  attempts = signal<number>(0);
  maxAttempts = 3;
  errorMessage = signal<string>('');
  
  // String challenges
  private readonly stringChallenges = [
    { word: 'SECURITY', hint: 'First letter: S' },
    { word: 'VERIFY', hint: 'Starts with V' },
    { word: 'ACCESS', hint: 'Starts with A' },
    { word: 'LOGIN', hint: 'Starts with L' },
    { word: 'SAFE', hint: 'Starts with S' },
    { word: 'CODE', hint: 'Starts with C' },
    { word: 'GUARD', hint: 'Starts with G' },
    { word: 'SHIELD', hint: 'Starts with S' }
  ];

  // Math operators
  private readonly operators = ['+', '-', '*'];
  
  verified = computed(() => this.isVerified());
  challengeText = computed(() => this.currentChallenge()?.question || '');

  ngOnInit(): void {
    this.generateChallenge();
    this.answerControl.valueChanges.subscribe(() => {
      this.errorMessage.set('');
    });
  }

  generateChallenge(): void {
    const type: CaptchaType = Math.random() > 0.5 ? 'math' : 'string';
    
    if (type === 'math') {
      this.generateMathChallenge();
    } else {
      this.generateStringChallenge();
    }
    
    this.isVerified.set(false);
    this.isExpired.set(false);
    this.answerControl.reset();
    this.answerControl.setValidators([Validators.required]);
    this.errorMessage.set('');
  }

  private generateMathChallenge(): void {
    const num1 = Math.floor(Math.random() * 20) + 1;
    const num2 = Math.floor(Math.random() * 20) + 1;
    const operator = this.operators[Math.floor(Math.random() * this.operators.length)];
    
    let answer: number;
    let question: string;
    
    switch (operator) {
      case '+':
        answer = num1 + num2;
        question = `What is ${num1} + ${num2}?`;
        break;
      case '-':
        // Ensure positive result
        const larger = Math.max(num1, num2);
        const smaller = Math.min(num1, num2);
        answer = larger - smaller;
        question = `What is ${larger} - ${smaller}?`;
        break;
      case '*':
        // Use smaller numbers for multiplication
        const n1 = Math.floor(Math.random() * 10) + 1;
        const n2 = Math.floor(Math.random() * 10) + 1;
        answer = n1 * n2;
        question = `What is ${n1} × ${n2}?`;
        break;
      default:
        answer = num1 + num2;
        question = `What is ${num1} + ${num2}?`;
    }
    
    this.currentChallenge.set({
      type: 'math',
      question,
      answer: answer.toString()
    });
  }

  private generateStringChallenge(): void {
    const challenge = this.stringChallenges[Math.floor(Math.random() * this.stringChallenges.length)];
    const shuffled = this.shuffleString(challenge.word);
    
    this.currentChallenge.set({
      type: 'string',
      question: `Type the word: ${shuffled}`,
      answer: challenge.word.toLowerCase(),
      hint: challenge.hint
    });
  }

  private shuffleString(str: string): string {
    const arr = str.split('');
    for (let i = arr.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [arr[i], arr[j]] = [arr[j], arr[i]];
    }
    return arr.join('').toUpperCase();
  }

  verifyAnswer(): void {
    if (!this.currentChallenge() || !this.answerControl.value) {
      return;
    }

    const userAnswer = this.answerControl.value.trim().toLowerCase();
    const correctAnswer = this.currentChallenge()!.answer.toLowerCase();
    
    if (userAnswer === correctAnswer) {
      this.isVerified.set(true);
      this.isExpired.set(false);
      this.errorMessage.set('');
      this.attempts.set(0);
      this.answerControl.disable();
      setTimeout(() => {
        this.verifiedEvent.emit(this);
      }, 100);
    } else {
      this.attempts.update(a => a + 1);
      this.errorMessage.set(`Incorrect answer. ${this.maxAttempts - this.attempts()} attempt(s) remaining.`);
      this.answerControl.setErrors({ incorrect: true });
      
      if (this.attempts() >= this.maxAttempts) {
        this.isExpired.set(true);
        this.errorMessage.set('Maximum attempts reached. Please try a new challenge.');
        setTimeout(() => {
          this.generateChallenge();
        }, 2000);
      }
    }
  }

  refreshChallenge(): void {
    this.generateChallenge();
    this.attempts.set(0);
    this.errorMessage.set('');
  }

  getCaptchaValue(): string {
    return this.isVerified() ? 'verified' : '';
  }

  reset(): void {
    this.generateChallenge();
    this.attempts.set(0);
    this.isVerified.set(false);
    this.isExpired.set(false);
    this.errorMessage.set('');
    this.answerControl.reset();
  }
}

