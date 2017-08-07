// custom resources
//import './grid.css';

// vendor modules
import angular from 'angular';
import route from 'angular-route';
import resource from 'angular-resource';
/*import uigrid from 'angular-ui-grid';
import uibootstrap from 'angular-ui-bootstrap';
import animate from 'angular-animate';*/

// app utility modules
import components from './components';
import modals from './modals';
import services from './services';

// app modules
import dashboard from './modules/dashboard';
import profile from './modules/profile';

// routing
import routesConfig from './app.module.routing';

let rootModule = angular.module('app',
	[
		route, 'angular-password', resource, //uibootstrap, animate, uigrid, 'ui.calendar', 'ui-iconpicker', 'ngSanitize', 'ngCsv',
		components, modals, services,
		dashboard, profile
	])
	.controller('rootCtrl', function rootCtrl($scope, principalService) {

		principalService.getCurrentUser(function (currentUser) {
			$scope.$root.appContext = {
				currentUser: currentUser,
				currentPageContext:{}
			};
		});
	})
	.config(routesConfig);