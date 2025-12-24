# Modern Angular App - Frontend

## Overview
A modern Angular 18+ application with the latest features including:
- Standalone components
- Signals for reactive state management
- Reactive forms
- Material Design UI
- Micro Frontends (MFE) architecture support
- TypeScript strict mode
- SCSS styling

## Features

### Authentication
- Login screen with username, password, and Google reCAPTCHA
- JWT token-based authentication
- Route guards for protected routes
- HTTP interceptors for automatic token injection

### Dashboard
- Modern sidebar navigation with dynamic menus
- Responsive design
- User profile menu
- Statistics cards

### Dynamic Reactive Forms
- Form builder with multiple field types:
  - Text, Email, Number
  - Select dropdowns
  - Radio buttons
  - Checkboxes
  - Date pickers
  - Textareas
  - Dynamic arrays
- Real-time validation
- Form preview

### Reactive Search
- Real-time search with debouncing
- Category filtering
- Tag-based filtering
- Search result highlighting

### Reactive Data Table
- Advanced table with:
  - Client-side pagination
  - Sorting
  - Filtering by multiple criteria
  - Search functionality
  - Action menus

## Getting Started

### Prerequisites
- Node.js 18+ and npm
- Angular CLI 18+

### Installation

1. Install dependencies:
```bash
npm install
```

2. Start the development server:
```bash
npm start
```

The application will be available at `http://localhost:4200`

### Build

Build for production:
```bash
npm run build
```

## Project Structure

```
src/
├── app/
│   ├── core/              # Core services, guards, interceptors
│   │   ├── guards/
│   │   ├── interceptors/
│   │   └── services/
│   ├── features/          # Feature modules
│   │   ├── auth/          # Authentication
│   │   │   └── login/
│   │   └── dashboard/     # Dashboard
│   │       ├── pages/
│   │       │   ├── dashboard-home/
│   │       │   ├── dynamic-forms/
│   │       │   ├── reactive-search/
│   │       │   └── reactive-table/
│   │       ├── dashboard.component.ts
│   │       └── dashboard.component.html
│   └── shared/            # Shared components (if any)
├── assets/                # Static assets
└── styles.scss            # Global styles
```

## Key Technologies

- **Angular 18+**: Latest Angular framework
- **TypeScript**: Type-safe JavaScript
- **RxJS**: Reactive programming
- **Angular Material**: UI component library
- **SCSS**: Advanced CSS with variables and nesting
- **Signals**: Modern reactive state management

## API Integration

The app connects to Spring Boot backend at `http://localhost:8080/api/auth`

### Login Endpoint
- **POST** `/api/auth/login`
- Body: `{ username, password, captcha }`
- Returns: `{ token, user, expiresIn }`

## Development

### Code Style
- Uses Angular standalone components
- Signals for state management
- Reactive forms throughout
- TypeScript strict mode enabled

### Styling
- SCSS with CSS variables
- Material Design theme
- Responsive design
- Modern UI with gradients and shadows

## Micro Frontends

The project is structured to support Micro Frontends (MFE) architecture. Each feature can be extracted into a separate micro frontend application.

## Testing

Run unit tests:
```bash
npm test
```

## Browser Support

- Chrome (latest)
- Firefox (latest)
- Safari (latest)
- Edge (latest)

