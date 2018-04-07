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
				<li><a href="see">Море</a></li>
				<li><a href="icetop">ТОР</a></li>
			</ul>
		</div>
	</div>
	<main>
	
<form action="${pageContext.request.contextPath}/editice" method="POST">
<table align="center" padding-top="20px">
       <tr class="outtable">
          <th class="outtable">Название</th>
          <th class="outtable">Размер</th>
          <th class="outtable">Количество рыбаков</th>
       </tr>
       <tr  class="outtable">
             <td class="outtable"><input type="text" name="name" value="${iceFloe.name}" /></td>
             <td class="outtable"><input type="text" name="size" value="${iceFloe.size}" /></td>
             <td class="outtable" align="center"><input type="text" name="numberOfFishermen" value="${iceFloe.numberOfFishermen}" /></td>
        </tr>
</table>
<div align="center">
		<p>
		<table>
			<tr>
				<td class="outtable">
             		<button type="submit" value="button">Добавить</button>
             	</td>
             	<td align="center" class="outtable">
             		<a href="icefloelist">Назад</a>
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