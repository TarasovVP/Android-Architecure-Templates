const { merge } = require('webpack-merge');
const path = require('path');

const fsConfig = require('./webpack.config.d/fs.js');
const wasmConfig = require('./webpack.config.d/wasm.js');

const finalConfig = merge(fsConfig, wasmConfig, {
    devServer: {
        historyApiFallback: true,
        static: {
            directory: path.join(__dirname, 'build/distributions'),
        },
        port: 8081,
        open: true,
    }
});

console.log('Final Config:', finalConfig);

module.exports = finalConfig;
