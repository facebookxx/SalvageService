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
				<li><a href="icefloelist">Льдины</a></li>
				<li><a href="icetop">ТОР</a></li>
			</ul>
		</div>
	</div>
	<main>
<div class="navcont" align="center">	
Количество в море:
</div>
<form action="${pageContext.request.contextPath}/see" method="POST">
<table align="center" padding-top="20px">
       <tr class="outtable">
          <th class="outtable">льдин</th>
          <th class="outtable">кораблей</th>
          <th class="outtable">рыбаков<p>на льдинах</th>
          <th class="outtable">рыбаков<p>всего</th>
       </tr>
       
       <c:forEach items="${listsee}" var="see">
          <tr  class="outtable">
             <td class="outtable" align="center">${see.numIceFloe}</td>
             <td class="outtable" align="center">${see.numShip}</td>
             <td class="outtable" align="center">${see.numFishIce}</td>
             <td class="outtable" align="center">${see.numFishermen}</td>
    	<input type="hidden" name="numIceFloe" value="${see.numIceFloe}"/>
    	<input type="hidden" name="numShip" value="${see.numShip}"/>
    	<input type="hidden" name="numFishIce" value="${see.numFishIce}"/>
    	<input type="hidden" name="numFishermen" value="${see.numFishermen}"/>
          </tr>
       </c:forEach>
    </table>
    
    <div align="center">

    	<button type="submit" value="button">Сохранить статистику</button>
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