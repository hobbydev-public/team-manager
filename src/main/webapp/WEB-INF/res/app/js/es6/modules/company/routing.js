import companyDetailsCtrl from './controllers/companyDetailsCtrl';

export default function routing($routeProvider) {
    'ngInject';

    $routeProvider
        .when('/company', {
            template: require('./templates/companyDetails.html'),
            controller: companyDetailsCtrl,
            controllerAs: '$ctrl',
        });
}