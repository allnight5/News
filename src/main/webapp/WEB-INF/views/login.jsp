<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<a href="/index.html">메인</a>
<p>로그인</p>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<form action="/member/login" method="post">
    email: <input type="text" name="email" />
    password:<input type="text" name="password" />
</form>
    <button type="submit">전송</button>
</body>
</html>
