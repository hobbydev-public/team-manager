import dashboardCtrl from './controllers/dashboardCtrl';

export default function routing($routeProvider) {
    'ngInject';

    $routeProvider
        .when('/dashboard', {
            template: require('./templates/dashboard.html'),
            controller: dashboardCtrl,
            controllerAs: '$ctrl'
        });
}