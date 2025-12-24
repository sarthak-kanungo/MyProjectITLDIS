const { shareAll, withModuleFederationPlugin } = require('@angular-architects/module-federation/webpack');

module.exports = withModuleFederationPlugin({
  name: 'servicesMfe',
  filename: 'remoteEntry.js',
  exposes: {
    './ServicesModule': 'projects/services-mfe/src/app/services-module.component.ts',
  },
  shared: {
    ...shareAll({ singleton: true, strictVersion: true, requiredVersion: 'auto' }),
  },
});

