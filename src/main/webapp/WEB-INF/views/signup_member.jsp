<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
</head>
<br>
<h2>회원가입</h2>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<p>*표시가 있을경우 필수 입력입니다.</p>
<form action="/member/signup" method="post">
    *이메일: <input type="text" name="email" />
    *비밀번호: <input type="text" name="password" />
    *닉네임: <input type="text" name="nickName" />
    <br>
    나이: <input type="text" name="age" />
    거주지: <input type="text" name="liveRegion" />
    <br>
    <button type="submit">전송</button>

</form>
<br>
<a href="/index.html">메인으로 이동</a>

</body>
</html>