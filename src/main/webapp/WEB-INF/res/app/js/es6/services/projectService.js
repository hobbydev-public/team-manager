export default class ProjectService {
    constructor($resource) {
        'ngInject';

        this.res = $resource(
            'api/web/projects/:projectId',
            {projectId: '@id'},
            {
                update: {
                    method: 'PUT'
                }
            }
        );
    }

    getProjects(success, fail) {
        let _service = this;
        return _service.res.query(
            {},
            success,
            fail
        );
    }

    getProjectById(projectId, success, fail) {
        let _service = this;

        return _service.res.get(
            {
                projectId: projectId
            },
            success,
            fail
        );
    }

    addProject(name, success, fail) {
        let _service = this;

        let newProject = new _service.res();
        newProject.name = name;

        newProject.$save(
            {},
            success,
            fail
        );
    }

    editProject(projectResource, success, fail) {
        projectResource.$update(
            {},
            success,
            fail
        );
    }
}