<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Report</title>
</head>
<body>
<h1>Raport</h1>
<table>
    <#list items as item>
    <tr>
        <td>${item.name}</td>

    </tr>
    </#list>
</table>
</body>
</html>