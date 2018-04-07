<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>SalvageService</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<div class="navcont" align="center">
		<div class="nav">
			<ul>
				<li><a href="index.html">Главная</a></li>
				<li><a href="shiplist">Корабли</a></li>
				<li><a href="fihermenlist">Рыбаки</a></li>
				<li><a href="icefloelist">Льдины</a></li>
				<li><a href="see">Море</a></li>
			</ul>
		</div>
	</div>
	<main>
	

<table align="center" padding-top="20px">
       <tr class="outtable">
       <th class="outtable">Место в ТОР</th>
          <th class="outtable">Название</th>
          <th class="outtable">Размер</th>
          <th class="outtable">Количество рыбаков</th>
       </tr>
       
       <c:forEach items="${listfloe}" var="iceFloe">
          <tr  class="outtable">
          <td class="outtable">${iceFloe.id}</td>
             <td class="outtable">${iceFloe.name}</td>
             <td class="outtable" align="center">${iceFloe.size} M2</td>
             <td class="outtable" align="center">${iceFloe.numberOfFishermen}</td>
          </tr>
       </c:forEach>
    </table>
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