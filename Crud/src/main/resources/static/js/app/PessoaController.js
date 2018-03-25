'use strict';

angular.module('crudApp').controller('PessoaController',
    ['PessoaService', '$scope',  function( PessoaService, $scope) {

        var self = this;
        self.pessoa = {};
        self.pessoas=[];

        self.submit = submit;
        self.getAllPessoa = getAllPessoa;
        self.createPessoa = createPessoa;
        self.updatePessoa = updatePessoa;
        self.removePessoa = removePessoa;
        self.editPessoa = editPessoa;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.pessoa.id === undefined || self.pessoa.id === null) {
                console.log('Saving New Pessoa', self.pessoa);
                createPessoa(self.pessoa);
            } else {
                updatePessoa(self.pessoa, self.pessoa.id);
                console.log('Pessoa updated with id ', self.pessoa.id);
            }
        }

        function createPessoa(pessoa) {
            console.log('About to create pessoa');
            PessoaService.createPessoa(pessoa)
                .then(
                    function (response) {
                        console.log('Pessoa created successfully');
                        self.successMessage = 'Pessoa created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.user={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Pessoa');
                        self.errorMessage = 'Error while creating Pessoa: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updatePessoa(pessoa, id){
            console.log('About to update pessoa');
            PessoaService.updatePessoa(pessoa, id)
                .then(
                    function (response){
                        console.log('Pessoa updated successfully');
                        self.successMessage='Pessoa updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Pessoa');
                        self.errorMessage='Error while updating Pessoa '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removePessoa(id){
            console.log('About to remove Pessoa with id '+id);
            PessoaService.removePessoa(id)
                .then(
                    function(){
                        console.log('Pessoa '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing pessoa '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllPessoas(){
            return PessoaService.getAllPessoas();
        }

        function editPessoa(id) {
            self.successMessage='';
            self.errorMessage='';
            PessoaService.getPessoa(id).then(
                function (pessoa) {
                    self.pessoa = pessoa;
                },
                function (errResponse) {
                    console.error('Error while removing pessoa ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.user={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);