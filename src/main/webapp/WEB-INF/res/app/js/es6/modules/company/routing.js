import companyDetailsCtrl from './controllers/companyDetailsCtrl';
import manageCompanyCtrl from './controllers/manageCompanyCtrl';

export default function routing($routeProvider) {
    'ngInject';

    $routeProvider
        .when('/company', {
            template: require('./templates/companyDetails.html'),
            controller: companyDetailsCtrl,
            controllerAs: '$ctrl',
        })
        .when('/company/manage', {
            template: require('./templates/manageCompany.html'),
            controller: manageCompanyCtrl,
            controllerAs: '$ctrl',
        });
}