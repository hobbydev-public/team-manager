export default class CompanyService {
    constructor($resource) {
        'ngInject';

        this.res = $resource(
            'api/web/companies/:userId',
            {userId: '@owner.id'},
            {
                update: {
                    method: 'PUT',
                    params: {userId: ''}
                },
                companyAccount: {
                    method: 'GET',
                    params: {userId: 'account'},
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