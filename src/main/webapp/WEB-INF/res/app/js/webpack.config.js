var webpack = require('webpack');

module.exports = {
	context: __dirname + '/es6',
	entry: {
		app: './app.module.js',
		vendor: [
			'jquery',
			'angular',
			'angular-route',
			//'angular-ui-grid',
			'angular-resource',
			//'angular-animate',
			//'angular-ui-bootstrap',
			'angular-password',
			'bootstrap',
			//'local-date'
		]
	},
	output: {
		path: __dirname + '/bundles',
		filename: 'app.bundle.js'
	},
	module: {
		loaders: [
			{
				test: /\.css$/,
				loader: 'style-loader!css-loader'
			},
			{
				test: /\.js$/,
				loader: 'babel-loader',
				exclude: /node_modules/
			},
			{
				test: /\.html$/,
				loader: 'raw-loader'
			}
		]
	},
	plugins: [
		new webpack.optimize.CommonsChunkPlugin({name:'vendor', filename:'vendor.bundle.js'}),
		new webpack.ProvidePlugin({
			$: "jquery",
			jQuery: "jquery"
		})
	],
	resolve: {
		alias: {
			jquery: "jquery/src/jquery"
		}
	}
};