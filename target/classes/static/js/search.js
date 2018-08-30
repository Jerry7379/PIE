var vm =new Vue({
    el : '#main',
    template:'',
    data: {
        status: false,
        msg: "",
        result: {}
    }
})

$("#btnS").click(function(){
    var searchId =  $("#searchId").val();
    $.getJSON("http://localhost:8080/SID?id="+searchId, function (result, status) {
        vm.status = result.status;
        vm.msg = result.msg;
        vm.result = result.data;
    })
});