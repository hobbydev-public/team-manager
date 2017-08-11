import projectsListCtrl from './controllers/projectsListCtrl';

export default function routing($routeProvider) {
    'ngInject';

    $routeProvider
        .when('/projects', {
            template: require('./templates/projectsList.html'),
            controller: projectsListCtrl,
            controllerAs: '$ctrl',
        });
}