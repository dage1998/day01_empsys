<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>所有员工信息</title>
		<style type="text/css">
			table td {
				text-align: center;
			}
			/*合并表格的边框*/
			
			table {
				width: 700px;
				border-collapse: collapse;
			}
			
			h3 {
				text-align: center;
			}
			
			div {
				margin: 0 auto;
				width: 700px;
			}
		</style>
		<script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
	</head>

	<body>
		<div>
			<h3>所有员工信息</h3>
			<a href="add.jsp">添加员工信息</a><br/>
			<table border="1">

				<tr>
					<th>序号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>手机</th>
					<th>操作</th>
				</tr>
				
				<%--<tr>--%>
					<%--<td>1</td>--%>
					<%--<td>张三</td>--%>
					<%--<td>男</td>--%>
					<%--<td>20</td>--%>
					<%--<td>1231313123</td>--%>
					<%--<td>--%>
						<%--<a href="update.jsp">修改</a>&nbsp;--%>
						<%--<a href="#">删除</a>--%>
					<%--</td>--%>
				<%--</tr>--%>

				<tbody id="tid"></tbody>


			</table>
		</div>


		<script type="text/javascript">
			$(function(){
				loadData();
			})

			function loadData(){
                $.ajax({
                    type:"get",
                    url:"list.do",
                    dataType:"json",
                    success:function(data){
                        if(data.code == 1){
                            // 清空子元素
                            $("#tid").empty();
                            var infos = data.info;
                            // 循环遍历
                            $(infos).each(function(){
                                var html = '<tr>\n' +
                                    '\t\t\t\t\t<td>' + this.id + '</td>\n' +
                                    '\t\t\t\t\t<td>' + this.name + '</td>\n' +
                                    '\t\t\t\t\t<td>' + this.sex + '</td>\n' +
                                    '\t\t\t\t\t<td>' + this.age + '</td>\n' +
                                    '\t\t\t\t\t<td>' + this.phone + '</td>\n' +
                                    '\t\t\t\t\t<td>\n' +
                                    '\t\t\t\t\t\t<a href="update.jsp?id=' + this.id + '">修改</a>&nbsp;\n' +
                                    // href中使用javascript前缀，表示点击超链接，调用js的函数
                                    //'\t\t\t\t\t\t<a href="javascript:delInfo(' + this.id + ')">删除</a>\n' +
										// 设置click事件，为了阻止超链接的默认操作，后面一定要写上return false
                                    '\t\t\t\t\t\t<a href="#" onclick="delInfo(' + this.id + ');return false;">删除</a>\n' +
                                    '\t\t\t\t\t</td>\n' +
                                    '\t\t\t\t</tr>';
                                // append 追加数据
                                // $(html) 创建dom对象
                                $("#tid").append($(html));
                            })
                        }

                    }
                })
			}

		</script>

		<script type="text/javascript">
			function delInfo(id){
			    $.ajax({
					type:"get",
					url:"delete.do?id=" + id,
					dataType:"json",
					success:function(data){
						if(data.code == 1){
							loadData();
						}else{
						    alert(data.info);
						}
					}
				})
			}

		</script>

	</body>

</html>