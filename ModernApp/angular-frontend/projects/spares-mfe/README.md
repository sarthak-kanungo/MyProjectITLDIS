# Spares MFE

Micro Frontend for Spares Module.

## Running

```bash
npm run serve:spares
```

The MFE will be available at http://localhost:4204

## Exposed Components

- `SparesModule` - Main spares module component

## Module Federation

This MFE exposes:
- `./SparesModule` - The main spares module component

## Integration

To use this MFE in the shell application, load it using:

```typescript
loadRemoteModule({
  type: 'module',
  remoteEntry: 'http://localhost:4204/remoteEntry.js',
  exposedModule: './SparesModule'
})
```

