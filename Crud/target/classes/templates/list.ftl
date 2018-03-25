<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead"> Cadastre a Pessoa </span></div>
		
		<div class="panel-body">
	        
	        <div class="formcontainer">
	            
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                
	                <input type="hidden" ng-model="ctrl.pessoa.id" />
	                
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Nome</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.pessoa.nome" id="nome" class="username form-control input-sm" placeholder="Insira seu name" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="email">Email</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.pessoa.email" id="email" class="form-control input-sm" placeholder="Insira seu e-mail." required ng-pattern="ctrl.onlyIntegers"/>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="telefone">Telefone</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.pessoa.telefone" id="telefone" class="form-control input-sm" placeholder="Insira seu telefone" required ng-pattern="ctrl.onlyNumbers"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.pessoa.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Lista de Pessoas </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>NOME</th>
		                <th>EMAIL</th>
		                <th>TELEFONE</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllPessoa()">
		                <td>{{u.id}}</td>
		                <td>{{u.nome}}</td>
		                <td>{{u.email}}</td>
		                <td>{{u.telefone}}</td>
		                <td><button type="button" ng-click="ctrl.editPessoa(u.id)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.removePessoa(u.id)" class="btn btn-danger custom-width">Remove</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>