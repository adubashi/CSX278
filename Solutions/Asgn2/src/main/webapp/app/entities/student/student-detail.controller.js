(function() {
    'use strict';

    angular
        .module('sampleApp')
        .controller('StudentDetailController', StudentDetailController);

    StudentDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Student', 'School', 'Dormitory', 'PreferedContact'];

    function StudentDetailController($scope, $rootScope, $stateParams, previousState, entity, Student, School, Dormitory, PreferedContact) {
        var vm = this;

        vm.student = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sampleApp:studentUpdate', function(event, result) {
            vm.student = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
