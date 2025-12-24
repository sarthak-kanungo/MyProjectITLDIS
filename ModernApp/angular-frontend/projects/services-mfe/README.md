# Services MFE

Micro Frontend for Services Module.

## Running

```bash
npm run serve:services
```

The MFE will be available at http://localhost:4203

## Exposed Components

- `ServicesModule` - Main services module component

## Module Federation

This MFE exposes:
- `./ServicesModule` - The main services module component

## Integration

To use this MFE in the shell application, load it using:

```typescript
loadRemoteModule({
  type: 'module',
  remoteEntry: 'http://localhost:4203/remoteEntry.js',
  exposedModule: './ServicesModule'
})
```

