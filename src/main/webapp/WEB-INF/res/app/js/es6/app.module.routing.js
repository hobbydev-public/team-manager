export default function routing($routeProvider, $locationProvider) {
	'ngInject';

	$locationProvider.hashPrefix('!');
	$routeProvider
		.when('/', {
			redirectTo: '/dashboard'
		})
		.otherwise('/404');
}