<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>SalvageService</title>
	<script type="text/javascript" src="linkedselect.js"></script>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<div class="navcont" align="center">
		<div class="nav">
			<ul>
				<li><a href="index.html">Главная</a></li>
				<li><a href="shiplist">Корабли</a></li>
				<li><a href="fishermenlist">Рыбаки</a></li>
				<li><a href="icefloelist">Льдины</a></li>
				<li><a href="see">Море</a></li>
				<li><a href="icetop">ТОР</a></li>
			</ul>
		</div>
	</div>
	<main>
	<form method="POST" action="${pageContext.request.contextPath}/editship">
	<div>
		<P>Вы хотите отправить корабль ${ship.name} за рыбаками, что на льдине 
			<select id ="nameIce" name ="nameIce">			
				<c:forEach items="${listfloe}" var="iceFloe">
				<option value ="${iceFloe.name}">${iceFloe.name}</option>
				</c:forEach>
				
			</select> ?
			
		<p>Корабль может спасти максимум ${ship.numPas} рыбаков.
		<p>
		<table>
			<tr>
				<td class="outtable">
             		<input type="hidden" name="nameShip" value="${ship.name}"/>
             		<button type="submit" name="button" value="togo">Отравить</button>
             	</td>
             	<td align="center" class="outtable">
             		<a href="shiplist">Назад</a>
             	</td>
			</tr>
		</table>
	</div>

    </form>
	</main>

	<footer>
	<hr>
	<p align="center">
		<small>
		<time>01-2017</time> © Kravchenko Pavel
       </small>
	</p>
	</footer>

</body>
</html>