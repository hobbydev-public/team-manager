export default class CompanyService {
    constructor($resource) {
        'ngInject';

        this.res = $resource(
            'api/web/companies/:id',
            {id: '@id'},
            {
                update: {
                    method: 'PUT',
                    params: {id: 'account'}
                },
                companyAccount: {
                    method: 'GET',
                    params: {id: 'account'},
                    isArray: false
                }
            }
        );
    }

    getCompanyAccount(success, fail) {
        let _service = this;
        return _service.res.companyAccount(
            {},
            success,
            fail
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

    editCompanyAccount(companyResource, success, fail) {
        companyResource.$update(
            {},
            success,
            fail
        );
    }
}