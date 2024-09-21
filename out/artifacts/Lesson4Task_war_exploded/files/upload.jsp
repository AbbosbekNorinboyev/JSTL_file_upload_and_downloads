<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload JSP File</title>
    <link rel="stylesheet" href="/resources/css/bootstrap-utilities.rtl.min.css.map"/>
    <link rel="stylesheet" href="/resources/css.css"/>
</head>
<body class="body">
<form method="post" enctype="multipart/form-data">
    <div>
        <label for="upload" class="label">Upload file</label>
        <br>
        <input type="file" name="file" id="upload" class="input"/>
    </div>
    <button class="btn-green button">Upload</button>
</form>
</body>
</html>
