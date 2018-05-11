<p align="center">
  <a href="https://getbootstrap.com/">
    <img src="https://files.cnblogs.com/files/selton/show.ico" alt="" width=72 height=72>
  </a>

  <h3 align="center">Bootstrap-table</h3>

  <p align="center">
    快速入门bootstrap-table----我的表单不可能这么帅.
    <br>
   
  </p>
</p>

<br>

## Table of contents

- [Quick start](#quick-start)
- [Why use it](#why-use)
- [What's included](#whats-included)
- [details](#details)
- [进阶之行内编辑](#editable)


##quick-start

快速使用:

- [Download bootstrap-table hello demo](https://github.com/seltonGitHub/helloBootTable)
- Clone the repo: `git clone https://github.com/seltonGitHub/helloBootTable.git`


没有配置java环境,移步[JDK安装与环境变量配置](https://jingyan.baidu.com/article/6dad5075d1dc40a123e36ea3.html);  
没有配置tomcat环境,移步[tomcat的下载和安装配置](https://jingyan.baidu.com/article/2c8c281daa77aa0008252aff.html);  
没有下载配置idea环境,移步[IntelliJ IDEA安装以及配置](https://jingyan.baidu.com/article/afd8f4debd60f434e286e91f.html);  


##why-use  
- 学习成本低,配置简单,文档齐全
- 与Bootstrap无缝衔接,整体风格一致,也便于二次开发
- 开发者活跃,Github定期维护
  
##whats-included

file list:

```
bootstrapDemo/
├── web/
│   ├── js
│   ├── WEB-INF
│   └── bootindex.html
└── src/
│   └── DataSendServlet.java


```

表单展示页面 (`bootindex.html`)  
javascript文件 (`showOrder.js`)从服务器取得数据,然后渲染表格  

##details  
 
	$("#table").bootstrapTable({
	  method: "post",
	  url: "获取后台数据的url",
	  ... ...
	
	});   

这里的js语句的所有渲染操作是针对html页面中的id为table的一个table,所以不要忘了在导入了该js的html中构建出id为table的table  

bootstrap-table中的重要键值的简单解释:  

- [url(必须修改)](#url)
- [pageSize(必须修改)](#pageSize)  
- [jsonstyle(必须修改)](#jsonstyle)
- [columns(必须修改)](#columns)
- [contentType(必须填写)](#contentType)
- [queryParams](#queryParams)
- [pageNumber](#pageNumber)
- [表格绑定事件](#表格绑定事件)

`showorder.js`会向服务器发起ajax访问  

`bootstrapTable`构建元素解析:  
####url  
	$("#table").bootstrapTable({
	  method: "post",
	  url: "获取后台数据的url",
	  ... ...
	
	}); 
ajax访问到的后台路径(必须),该后台需要按照指定的[json](#jsonstyle) 
 
####queryParams  
不需要任何修改,相当于ajax中的data键,发送给后台的数据,给出实现表单分页的两个参数,[offset](#offset)和[limit](#limit),在`oTableInit.queryParams`中给出,后台用`request.getParameter()`的方式拿到queryParams中传递过来的值,然后制定dao  

####pageSize  
当前table一次最多显示多少行,也就是你的table的一页应该展现多少行,必须  
  
####pageNumber  
起始页,一般是1不用改,这个和pageSize决定了queryParams中的offset的值,`offset=(pageSize - 1)   * pageSize,limit=pageSize`
  

####contentType  
`contentType: "application/x-www-form-urlencoded"`
####columns  
	$("#table").bootstrapTable({
	  method: "post",
	  url: "获取后台数据的url",
        [
        {field: 'testId', title: 'ID'},
        {field: 'testName', title: '姓名'},
        {field: 'testPassword', title: '密码'}
        ]  
	      ... ...
	  ]
	});  

你的table的表结构,以上例子表示表有三列,列的实际显示名字分别是ID,姓名,密码,但是field代表实际数据的名字,表中的数据是由于ajax向服务器发起访问,服务器返回给的数据中的rows的每一个json对象的键都会对应到field的列中-----[服务器返还的值](#jsonstyle)
  
###jsonstyle 

    {  
    "total":25,  
	    "rows":[
            {
            "testID":1,
            "testName":"xiaoming1",
            "testPassword":"xiaomingpwd1"
            },
            {
            "testID":2,
            "testName":"xiaoming2",
            "testPassword":"xiaomingpwd2"
            }
        ]
    }    
数据库返还给发起访问的ajax的数据,必须满足,包含两个json形式的键值对,
一个是total键,值为表单拥有者在数据库中的全部数据的数量(行数),这个数据和pageSize决定table展示的页面有多少页,另一个是rows键,值为多个json对象,rows的每一个json对象就是当前table页的一行实体展示,这里的rows相当于会给前端table两行数据,testID,testName,testPassword分别会被填入到table中的field对应的列中-----[前端接收到值表现](#columns)
  


####offset  

    oTableInit.queryParams = function (params) {

        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,    //params.limit,   页面大小
            offset: params.offset,
            testNum: 445,
            testNum1: 343
        };  

offset=([pageNumber](#pageNumber) - 1) * [pageSize](#pageSize),是会被发送到后台使用的数据,[后台数据提取sql语句示例](#sql示例)  
  
####limit  

    oTableInit.queryParams = function (params) {

        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,    //params.limit,   页面大小
            offset: params.offset,
            testNum: 445,
            testNum1: 343
        };
limit=[pageSize](#pageSize),是会被发送到后台使用的数据,[后台数据提取sql语句示例](#sql示例)

####sql示例  
  
`SELECT * FROM test WHERE id = ? LIMIT offset,limit`  

####表格绑定事件  
用于测试ajax返回的数据是最好的  

	$("#table").bootstrapTable({
	      method: "post",
	      url: "获取后台数据的url",
	      onLoadSuccess: function(){  //加载成功时执行
	            console.info("加载成功");
	      },
	      onLoadError: function(){  //加载失败时执行
	            console.info("加载数据失败");
	      }
	});    

关于事件,更为详细的介绍请访问[boottableDoc](http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/#%E4%BA%8B%E4%BB%B6)  

##editable  
*这是在操作的是table吗,感觉就像是数据库展现在了页面上*

如果你已经阅读完或者已经在自己代码中实现了上述功能,但是table存在的目的本来就不应该只是展现,应该还有寻常的CRUD,精力有限,只是实践了update,笔者使用的是行内编辑的方式实现的update,需要用到另一个工具X-editable,不过还好boottable有这样的插件,将x-editable封装整合到了当中,只需要引入`https://cdn.bootcss.com/bootstrap-table/1.12.1/extensions/editable/bootstrap-table-editable.min.js`  

- [快速使用](#start-editable)
- [方法详解](#details-editable)
- [关于editable的语法的细节](#more-editable)
- [x-editableDemo在线展示](http://vitalets.github.io/x-editable/demo-bs3.html)

####start editable  

	$("#table").bootstrapTable({
	  method: "post",
	  url: "获取后台数据的url",
        [
        {field: 'testId',
        title: 'ID',
        editable: {mode: 'inline'}
        },
        {field: 'testName', 
        title: '姓名'},
        {field: 'testPassword', 
        title: '密码'}
        ]  
	      ... ...
	  ]
	});  

    onEditableSave: function (field, row, oldValue, $el) {

        $.ajax({
            type: "post",
            url: "/ordercenter/updateOrder.json",
            data: {
                orderid: row.orderid,
                updateCol: field,
                updateVal: eval('row.'+field)
            },
            dataType: 'JSON',
            success: function (data, status) {
                console.log(data);
                if (status == "success") {
                    alert('旧数据: 订单号: ' + row.orderid + ' ' + field + ': ' + oldValue + '\r\n'
                    + '更新后的数据: 订单号: ' + data.updateId + ' ' + data.updateCol + ': ' + data.updateVal)
                }
            },
            error: function () {
                alert('编辑失败');
            },
            complete: function () {

            }

        });
    },

####details editable  

编辑后的提交方法统一放到`onEditableSave`事件里面统一处理

例子: 页面table中的列姓名,field为testName,实际的值为xiaoming1,通过修改将其改为xiaoming2,这时候field为testName,row为一个json,键值对分别为该行的所有键值组合,oldValue为xiaoming1
更为详细的描述请到[x-editable](http://vitalets.github.io/x-editable/)  

建议读者直接使用我的`onEditableSave`,它向后台发送了三个数据精确完成update,行特定标识和列特定标识定位到修改了哪一个具体的数据,再给出updateVal指出原本的数据被修改成了updateVal