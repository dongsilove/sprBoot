<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Table Layout</title>
<script src="/api/js/jquery-3.4.1.min.js"></script>
<script>

</script>
</head>
<body>
	<div>
		<ul><form name="smsForm" action="/api/sendSms">
			<li><a href="#" onclick="smsForm.submit();">send sms</a></li>
			<li>전화번호 : <input type="text" class="" id="ftrId" name="phoneNumber" value="" title="phoneNumber"></li>
			<li>내용 : <input type="text" class="" id="ftrId" name="msg" value="" title="msg"></li>
			</form>
		</ul>
	</div>
</body>
</html>