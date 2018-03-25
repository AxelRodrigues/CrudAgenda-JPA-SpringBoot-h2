'use strict';

angular.module('crudApp').factory('PessoaService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllPessoa: loadAllPessoa,
                getAllPessoa: getAllPessoa,
                getPessoa: getPessoa,
                createPessoa: createPessoa,
                updatePessoa: updatePessoa,
                removePessoa: removePessoa
            };

            return factory;

            function loadAllPessoa() {
                console.log('Fetching all pessoa');
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all pessoa');
                            $localStorage.users = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading pessoa');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllPessoa(){
                return $localStorage.pessoa;
            }

            function getPessoa(id) {
                console.log('Fetching Pessoa with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.PESSOA_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully pessoa with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading pessoa with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createPessoa(pessoa) {
                console.log('Creating Pessoa');
                var deferred = $q.defer();
                $http.post(urls.PESSOA_SERVICE_API, pessoa)
                    .then(
                        function (response) {
                            loadAllPessoa();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Pessoa : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updatePessoa(Pessoa, id) {
                console.log('Updating Pessoa with id '+id);
                var deferred = $q.defer();
                $http.put(urls.PESSOA_SERVICE_API + id, pessoa)
                    .then(
                        function (response) {
                            loadAllPessoa();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Pessoa with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removePessoa(id) {
                console.log('Removing Pessoa with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.PESSOA_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllPessoa();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Pessoa with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);