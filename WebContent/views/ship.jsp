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
				<li><a href="fishermenlist">Рыбаки</a></li>
				<li><a href="icefloelist">Льдины</a></li>
				<li><a href="see">Море</a></li>
				<li><a href="icetop">ТОР</a></li>
			</ul>
		</div>
	</div>
	<main>
	

<table align="center" padding-top="20px">
       <tr class="outtable">
          <th class="outtable">№</th>
          <th class="outtable">Название</th>
          <th class="outtable">Количество пассажиров</th>
          <th class="outtable">Место нахождения</th>
          <th class="outtable">Отправить</th>
          <th class="outtable">Вернулся</th>
          <th class="outtable">Списать</th>
       </tr>
       
       <c:forEach items="${listship}" var="ship">
          <tr  class="outtable">
             <td class="outtable">${ship.id}</td>
             <td class="outtable" align="center">${ship.name}</td>
             <td align="center" class="outtable" >${ship.numPas}</td>
             <td class="outtable" align="center">${ship.location}</td>
             <form method="get" action="${pageContext.request.contextPath}/editship">
             	<td class="outtable">
             		<input type="hidden" name="id" value="${ship.id}"/>
             		<input type="hidden" name=location value="${ship.location}"/>
    				<button type="submit" name="button" value="shipgo">Отправить</button>
    			</td>
    		</form>
    		<form method="POST" action="${pageContext.request.contextPath}/editship">
    			<td class="outtable">
    				<input type="hidden" name="id" value="${ship.id}"/>
    				<button type="submit" name="button" value="shipcencel">Вернулся</button>
             	</td>
             	<td class="outtable">
             		<input type="hidden" name="id" value="${ship.id}"/>
                	<button type="submit" name="button" value="killtask">Списать</button>
             </form>   	
             </td>
          </tr>
       </c:forEach>
    </table>
    
        <div align="center">
    <p><p><p><p>Новый корабль
    </div>
    
        <form action="${pageContext.request.contextPath}/newship" method="POST">
    <table align="center" padding-top="20px">
       <tr class="outtable">
       <th class="outtable">id</th>
          <th class="outtable">Название</th>
          <th class="outtable">Количество пассажиров</th>
       </tr>
          	<tr  class="outtable">
          	<td class="outtable"><input type="text" name="id" value="${ship.id}" /></td>
             <td class="outtable"><input type="text" name="name" value="${ship.name}" /></td>
             <td class="outtable"><input type="text" name="numPas" value="${ship.numPas}" /></td>      
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