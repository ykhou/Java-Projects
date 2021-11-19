<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!--代码编辑ace.js   本地-->
    <script src="/resources/ace/ace.js" type="text/javascript"></script>
    <script src="/resources/ace/ext-language_tools.js" type="text/javascript"></script>
    <!--代码编辑ace.js   远程-->
    <!--高亮和自动提示-->
<#--    <script src="https://cdn.bootcdn.net/ajax/libs/ace/1.4.9/ace.js"></script>-->
<#--    <script src="https://cdn.bootcdn.net/ajax/libs/ace/1.4.9/ext-language_tools.js"></script>-->

    <!--vue-->
    <script src="resources/vue/vue.js"></script>
    <script src="resources/vue/vue-resource.js"></script>
<#--    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>-->
<#--    <script src="https://cdn.bootcdn.net/ajax/libs/vue-resource/1.5.3/vue-resource.js"></script>-->
<#--    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>-->
    <title>java小助手</title>
    <style>
        #app {
            display: flex;
            justify-content: space-between;
        }
        .ace-editor {
            width: 45vw;
            height: 80vh;
            font-size: 20px;
            margin: 8px 0;
        }
        .btn {
            border-radius: 5px;
            width: 130px;
            height: 40px;
            background: #123456;
            color: white;
            border: none;
            outline: none;
            cursor: pointer;
            font-size: 14px;

        }
    </style>
</head>
<body>
    <div id="app">
        <div>
            <span>请输入mysql建表语句</span>
            <div ref="aceSql" class="ace-editor"></div>
            <div style="text-align: right">
                <input type="button" class="btn" value="点击生成EntityDo" @click="generateDO" />
            </div>

        </div>

        <div>
            <span>结果</span>
            <div ref="aceResult" class="ace-editor"></div>
        </div>
    </div>

<script>
    new Vue({
        el:"#app",
        data:{
            code:"",
            sqlEditor:null,
            resultEditor:null,
            themePath:"ace/theme/monokai",
            sqlModePath:"ace/mode/sql",
            javaModePath:"ace/mode/java",
            jsonPathPath:"ace/mode/json"
        },
        methods:{
            generateDO(){
                let body = {
                    "cont":this.code
                };
                // Content-Type: application/json;charset=UTF-8
                // "emulateJSON":true: application/x-www-form-urlencoded
                this.$http.post("/assist",body,{
                    // "emulateJSON":true
                }).then(res =>{
                    // console.log(res);
                    if (res.data.success) {
                        console.log("success");
                        this.resultEditor.getSession().setMode(this.javaModePath);
                        this.resultEditor.setValue(res.data.data);
                    } else {
                        console.log("fail");
                        this.resultEditor.getSession().setMode(this.javaModePath);
                        this.resultEditor.setValue(JSON.stringify(res.data, null, "\t"));
                    }
                },err => {
                    console.log(err);
                })
            }
        },
        mounted:function () {
            this.sqlEditor = ace.edit(this.$refs.aceSql,{
                theme:this.themePath,
                mode:this.sqlModePath,
                enableBasicAutocompletion:true,
                enableSnippets:true, // ife块提示
                enableLiveAutocompletion:true
            });
            // ace监听里面内容的变化
            this.sqlEditor.getSession().on("change",e => {
                this.code = this.sqlEditor.getValue();
            });
            this.resultEditor = ace.edit(this.$refs.aceResult,{
                theme:this.themePath,
                mode:this.javaModePath,
                enableBasicAutocompletion:true,
                enableSnippets:true, // ife块提示
                enableLiveAutocompletion:true
            });
        }
    });

</script>
</body>
</html>