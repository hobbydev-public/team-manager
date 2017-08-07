export default class CompanyService {
    constructor($resource) {
        'ngInject';

        this.res = $resource(
            'api/web/companies/:userId',
            {userId: '@owner.id'}
        );
    }

    addCompanyAccount(name, success, fail) {
        let _service = this;

        _service.res.save(
            {name:name},
            {},
            success,
            fail
        );
    }
}