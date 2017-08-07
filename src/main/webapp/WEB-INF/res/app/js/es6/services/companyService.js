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

        let newCompany = new _service.res();
        newCompany.name = name;
        newCompany.$save(
            {},
            success,
            fail
        );
    }
}