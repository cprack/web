Vue.http.options.emulateJSON = true;
let vm1 = new Vue({
    el:"#myModal",
    data:{
        modelDep:[]
    },
    methods: {
    },
    created(){
        this.$http.get("/SSM_CRUD/selectGid").then(response=>{
            this.modelDep = response.body.departments;
        })
    }
})

let vm2 = new Vue({
    el:"#table",
    data:{
        tableData:[],
        size:5,
        currentPage:1,
    },
    methods:{
        flip(index) {
            this.$http.get("/SSM_CRUD/gets",{params:{pageNum:index,pageSize:this.size}}).then(response=>{
                this.tableData = response.body.info;
                this.currentPage = index;
            })
        },
        pageSize(size){
            this.size = size;
            this.$http.get("/SSM_CRUD/gets",{params:{pageSize:size}}).then(response=>{
                this.tableData = response.body.info;
            })
        },
        selectStaff(id){
            this.$http.post("/SSM_CRUD/selectStaff",{staffId:id}).then(response=>{
                let staffName = response.body.info.staffName;
                $("#updateStaffName").html(staffName);
            })
        },
        deleteStaff(staffName,staffId){
            if(confirm("是否删除"+staffName+"的信息?")){
                this.$http.delete("/SSM_CRUD/deleteStaff",{params:{staffId:staffId}}).then(()=>{
                    alert("删除成功!");
                    this.flip(this.currentPage);
                },()=>{
                    alert("删除失败!");
                })
            }
        },
        checkAll(){
            $(".checkItem").prop("checked",$("#checkAll").prop("checked"));
        },
        checkSingle(){
            let flag = $(".checkItem:checked").length == this.size;
            $("#checkAll").prop("checked",flag);
        },
        deleteCheckedStaff(){
            let staffName=""
            $.each($(".checkItem:checked"),function () {
                staffName +=","+ $(this).parents("tr").find("th:eq(2)").text();
            })
            staffName = staffName.substring(1);
            if(confirm("是否删除员工:"+staffName+"的信息?")){
                this.$http.delete("/SSM_CRUD/deleteStaffs",{params:{staffName:staffName}}).then(()=>{
                    alert("删除成功!");
                    this.flip(this.currentPage);
                    $(".checkItem").prop("checked",false);
                },()=>{
                    alert("删除失败!");
                })
            }
        }
    },
    created() {
        this.$http.get("/SSM_CRUD/gets").then(response=>{
            this.tableData = response.body.info;
        })
    }
})

let vm3 = new Vue({
    el:"#updateModal",
    data:{
        dep:[],
        updateStaffName:"",
        updateStaffEmail:"",
        updateStaffGender:"",
        updateStaffGid:""
    },
    methods:{
        update(){
            this.$http.post("/SSM_CRUD/updateStaff",{
                staffName:this.$refs.updateStaffName.innerText,
                staffEmail:this.updateStaffEmail,
                staffGender:this.updateStaffGender,
                gid:this.updateStaffGid
            }).then(()=>{
                alert("更新成功!");
                $('#updateModal').modal('hide');
                vm2.$forceUpdate();
            },()=>{
                alert("更新失败!");
            })
        }
    },
    created(){
        this.$http.get("/SSM_CRUD/selectGid").then(response=>{
            this.dep = response.body.departments;
        })
    },
})
function validator(id){
    $(id).bootstrapValidator({
        message:'This value is not valid',
        container:'tooltip',
        feedbackIcons:{
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            staffName:{
                validators:{
                    notEmpty: {
                        message: '姓名不能为空'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z]{6,16}|[\u4e00-\u9fa5]{2,4}$/i,
                        message: '必须为2-4位汉字或6-16位英文'
                    },
                    remote:{
                        message:"该用户已经存在",
                        url:'/SSM_CRUD/selectEmp',
                        delay:2000,
                        type:'post'
                    }
                }
            },
            staffEmail:{
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    regexp:{
                        regexp:/^([a-zA-Z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/,
                        message:'邮箱格式不正确'
                    }
                }
            },
            staffGender:{
                validators: {
                    notEmpty:{
                        message:'性别不能为空'
                    }
                }
            },
            gid:{
                validators:{
                    notEmpty:{
                        message:'部门不能为空'
                    }
                }
            }
        }
    }).on('success.form.bv',function (e) {
        e.preventDefault();

        let $form = $(e.target);

        $.post($form.attr('action'), $form.serialize(), function() {
            alert("添加成功!");
            $('#myModal').modal('hide')
            vm2.$forceUpdate();
        }, 'json');
    })
}
$('#myModal').on('hidden.bs.modal',function () {
    $('#form').trigger('reset');
    $("#form").data('bootstrapValidator').destroy();
    $('#form').data('bootstrapValidator', null);
    validator("#form");
})
$('#updateModal').on('hidden.bs.modal',function () {
    $('#updateForm').trigger('reset');
    $("#updateForm").data('bootstrapValidator').destroy();
    $('#updateForm').data('bootstrapValidator', null);
    validator("#updateForm");
})
validator("#form");
validator("#updateForm");