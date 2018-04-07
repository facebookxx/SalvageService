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
				<li><a href="fishermenlist">Рыбаки</a></li>
				<li><a href="see">Море</a></li>
				<li><a href="icetop">ТОР</a></li>
			</ul>
		</div>
	</div>
	<main>
	

<table align="center" padding-top="20px">
       <tr class="outtable">
          <th class="outtable">ID</th>
          <th class="outtable">Название</th>
          <th class="outtable">Размер</th>
          <th class="outtable">Количество рыбаков</th>
          <th class="outtable">Изменить</th>
       </tr>
       
       <c:forEach items="${listfloe}" var="iceFloe">
          <tr  class="outtable">
             <td class="outtable">${iceFloe.id}</td>
             <td class="outtable">${iceFloe.name}</td>
             <td class="outtable" align="center">${iceFloe.size} M2</td>
             <td class="outtable" align="center">${iceFloe.numberOfFishermen}</td>
             <form method="get" action="${pageContext.request.contextPath}/editice">
             	<td class="outtable">
             		<input type="hidden" name="id" value="${iceFloe.id}"/>
    				<button type="submit" name="button" value="shipgo">Изменить</button>
    			</td>
    		</form>
             
          </tr>
       </c:forEach>
    </table>
    
    <div align="center">
    <p>
    <p>Новая льдина
    </div>
    
    <form action="${pageContext.request.contextPath}/newice" method="POST">
    <table align="center" padding-top="20px">
       <tr class="outtable">
          <th class="outtable">Название</th>
          <th class="outtable">Размер, M2</th>
          <th class="outtable">Количество рыбаков</th>
       </tr>
          	<tr  class="outtable">
             <td class="outtable"><input type="text" name="name" value="${iceFloe.name}" /></td>
             <td class="outtable"><input type="text" name="size" value="${iceFloe.size}" /></td>
             <td class="outtable" align="center">
             <input type="text" name="numberOfFishermen" value="${iceFloe.numberOfFishermen}" />
             </td>
          </tr>
    </table>
    <div align="center">
    <button type="submit" value="button">Добавить</button>
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