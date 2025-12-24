const { shareAll, withModuleFederationPlugin } = require('@angular-architects/module-federation/webpack');

module.exports = withModuleFederationPlugin({
  name: 'sparesMfe',
  filename: 'remoteEntry.js',
  exposes: {
    './SparesModule': 'projects/spares-mfe/src/app/spares-module.component.ts',
  },
  shared: {
    ...shareAll({ singleton: true, strictVersion: true, requiredVersion: 'auto' }),
  },
});

