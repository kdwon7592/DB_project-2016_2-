    <%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.*"%>
    <%@taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
						<table class="table">
                            <thead class="thead-inverse">
                                <tr>
                                    <td><b>강좌코드</b></td>
                					<td><b>강좌이름</b></td>
                					<td><b>강의실</b></td>
                					<td><b>강의시작시간</b></td>
                					<td><b>강의끝시간</b></td>
                					<td><b>학점</b></td>
                					<td><b>요일</b></td>
                					<td><b>교수이름</b></td>
                					<td><b>삭제</b></td>
                					<td><b>변경</b></td>
                				</tr>
                            </thead>
                            <tbody>
								<c:if test= "${cnt > 0}">
            					<c:forEach var= "i" begin= "0" end= "${cnt-1}">
	                    		<TR>
		                    		<TD>${course[i].getCid()}</TD>
		                            <TD>${course[i].getName()}</TD>
		                            <TD>${course[i].getRoom()}</TD>
		                            <TD>${course[i].getStime()}</TD>
		                            <TD>${course[i].getEtime()}</TD>
		                            <TD>${course[i].getDay()}</TD>
		                            <TD>${course[i].getCredit()}</TD>
		                            <TD>${pname[i]}</TD>
		                            <form action="delenroll" method="POST">
		                            <td><button type="submit" name = "button${i}" value = "${i}">삭제</button></td>
		                   			</form>
		                   			<form action="altenroll" method="GET">
		                            <td><button type="submit" name = "button${i}" value = "${i}">변경</button></td>
		                   			</form>
				                </TR>
			                </c:forEach>
			            </c:if>
			            <c:if test= "${cnt == 0}">
			            <tr>
			                <td><b>신청한 강의가 없습니다.</b></td>
			            </tr>
			            </c:if>
                            </tbody>
                        </table>
</body>
</html>