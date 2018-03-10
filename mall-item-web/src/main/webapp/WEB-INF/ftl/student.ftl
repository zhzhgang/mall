<html>

<head>
    <title>测试</title>
</head>

<body>
    学生信息：<br>
    学号：${student.id}<br>
    姓名：${student.name}<br>
    年龄：${student.age}<br>
    性别：${student.sex}<br>

    学生信息列表：<br>
    <table border="1">
        <tr>
            <th>序号</th>
            <th>学号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>性别</th>
        </tr>
        <#list studentList as studentList>
            <#if studentList_index % 2 == 0>
            <tr bgcolor="red">
            <#else>
            <tr bgcolor="green">
            </#if>
                <td>${studentList_index}</td>
                <td>${studentList.id}</td>
                <td>${studentList.name}</td>
                <td>${studentList.age}</td>
                <td>${studentList.sex}</td>
            </tr>
        </#list>
    </table>
    <br>
    日期类型的处理：${date?datetime}
    <br>
    null 值的处理：${val!"这是默认值"}

    <br>
    include 标签测试：
    <#include "hello.ftl">
</body>

</html>