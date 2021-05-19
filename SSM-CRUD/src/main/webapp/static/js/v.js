$('#form').bootstrapValidator({
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
                }
            }
        },
        staffEmail:{
            validators: {
                notEmpty: {
                    message: '邮箱不能为空'
                },
                emailAddress: {
                    message: '邮箱格式错误'
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
})